package com.sc.dubbo.svcc.mapper.master;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sc.dubbo.svcc.entity.master.DemoUserRoles;

public interface DemoUserRolesMapper {
    int deleteByPrimaryKey(String roleName);

    @Insert("insert into demo_user_roles (role_name, description)" + 
    		"    values (#{roleName,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR})")
    int insert(DemoUserRoles record);

    int insertSelective(DemoUserRoles record);

    @Select("select * from demo_user_roles" + 
    		"    where role_name = #{roleName,jdbcType=VARCHAR}")
    DemoUserRoles selectByPrimaryKey(String roleName);

    int updateByPrimaryKeySelective(DemoUserRoles record);

    int updateByPrimaryKey(DemoUserRoles record);
}