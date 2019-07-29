package com.sc.dubbo.svcb.mapper.master;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sc.dubbo.svcb.entity.master.DemoUser;

@Mapper
public interface DemoUserMapper {
    int deleteByPrimaryKey(Integer oidIndex);

    @Insert("insert into demo_user (oid_index, user_id, password, \r\n" + 
    		"      user_name, email, fk_role, \r\n" + 
    		"      user_status, login_failed_cnt, latest_active_time, \r\n" + 
    		"      paswd_reset, last_paswd_changed, version_id, \r\n" + 
    		"      last_upd_by)\r\n" + 
    		"    values (#{oidIndex,jdbcType=INTEGER}, #{userId,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, \r\n" + 
    		"      #{userName,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{fkRole,jdbcType=VARCHAR}, \r\n" + 
    		"      #{userStatus,jdbcType=CHAR}, #{loginFailedCnt,jdbcType=INTEGER}, #{latestActiveTime,jdbcType=TIMESTAMP}, \r\n" + 
    		"      #{paswdReset,jdbcType=BIT}, #{lastPaswdChanged,jdbcType=TIMESTAMP}, #{versionId,jdbcType=SMALLINT}, \r\n" + 
    		"      #{lastUpdBy,jdbcType=TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "oidIndex") //here is to configure RETURN_GENERATED_KEYS
    int insert(DemoUser record);

    @Select("select * from demo_user where oid_index = #{oidIndex}")
    DemoUser selectByPrimaryKey(Integer oidIndex);

    @Update("update demo_user set user_name=#{userName,jdbcType=VARCHAR}  where oid_index = #{oidIndex}")
    int updateByPrimaryKey(DemoUser record);
    
    @Select("select * from demo_user where user_id = #{userId}")
    DemoUser getByUserId( @Param("userId") String userId);

    @Select("select * from demo_user")
    List<DemoUser> selectAllUser();
    
    @Select("SELECT count(0) FROM demo_user")
    int getTotalCnt();
    
    @Delete("delete from demo_user where oid_index = #{oidIndex}")
    int deleteUserById(Integer oidIndex);
    
}