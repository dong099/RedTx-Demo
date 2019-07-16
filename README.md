# Demo for RedTx - Distributed Transaction management and implementation

# Maven Projects
1. consul-common      :  commonly used configuration
2. consul-service-a   :  a module to launch a distributed transaction, calling b and c with database actions
3. consul-service-b   :  b moudle called by module a, to update its own db tables
4. consul-service-c   :  c moudle called by module a, to update its own db tables
5. consul-service-mgr :  distributed transaction backend management server.


# stacks points
* Spring boot 2.0.4
* Spring cloud Finchley
* Consul Server 1.4.4
* Hystrix & Dashboard
* Spring Cloud Sleuth/zipkin  
* Ribbon 
* Feign
* Netty
* mybatis/mysql
* Druid

# steps to use RedTX open source projects

## New a spring cloud app as the RedTx server(refer to consul-service-mgr)
1. pom.xml
```
<dependency>
  <groupId>com.sc</groupId>
  <artifactId>redtx-mgr</artifactId>
  <version>1.0</version>
</dependency>
```
2. add one annotation -@RedtxServer on springboot main class
```
@RedtxServer
@SpringBootApplication
public class RedTxServer {
...
}
```

3. check the sample configuration - application.yml (redis and host/port setup)

4. start this app, it should show:
```
=========================================================
RedTx Server runs on hotst/port: 172.10.1.63:9011 
=========================================================
``` 

## New a spring cloud app as the RedTx client app (refer to consul-service-a/b/c)
1. pom.xml
```
<dependency>
  <groupId>com.sc</groupId>
  <artifactId>redtx-core</artifactId>
  <version>1.0</version>
</dependency>
```

2. add annotations

a). springboot main class - @EnableRedTx
```
@EnableRedTx
@SpringBootApplication
public class ServiceAApplication {
...
}
```
b). transaction method on service class - @RedTxTransaction
```
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {
 
	@Autowired
	private FeignServiceBClient serviceBClient;
	
	@Autowired
	private FeignServiceCClient serviceCClient;

	@Override
	@RedTxTransaction
	public String execRedTx(String value, String rollbackFlag) {
		
		//codes on this section
		log.debug("now enter into A - service - {}, {}", value, rollbackFlag);
		
		String bResult = serviceBClient.runRedTx(value);		
		log.debug("now called B - service- Result - {}", bResult);
		
		String cResult = serviceCClient.runRedTx(value);
		log.debug("now called C - service- Result - {}", cResult);
	 
		return "success";
	}
}
```

3. start this client app, it will show:
```
=========================================================
Netty Client connected to server successfully! RedtxClientConfig [txTimeout=6000, mgrAddrs=172.10.1.63:9011, txSyncCallTimeout=3000]
=========================================================
```



# consul server list
```
[root@hadoopnode3 ~]# consul members -http-addr=10.0.0.8:8500
Node     Address            Status  Type    Build  Protocol  DC   Segment
node104  10.0.0.104:8301    alive   server  1.4.4  2         dc1  <all>
node19   10.0.0.19:8301     alive   server  1.4.4  2         dc1  <all>
node9    10.0.0.9:8301      alive   server  1.4.4  2         dc1  <all>
node8    10.0.0.8:8301      alive   client  1.4.4  2         dc1  <default>
```