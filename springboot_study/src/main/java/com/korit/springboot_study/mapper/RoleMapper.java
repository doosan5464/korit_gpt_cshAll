package com.korit.springboot_study.mapper;

import com.korit.springboot_study.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int insertAll(List<Role> roles);


}
