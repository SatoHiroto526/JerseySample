package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.dto.SampleDto;

@Mapper
public interface SampleMapper {
	
	List<SampleDto> getList();
	
	SampleDto getById(@Param("id") int id);

}
