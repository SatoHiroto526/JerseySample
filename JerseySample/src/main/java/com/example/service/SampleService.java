package com.example.service;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.example.dao.SampleMapper;
import com.example.dto.SampleDto;

@RequestScoped
public class SampleService {
	
	@Inject
	private SqlSession sqlSession;
	
	
	public List<SampleDto> getList(){
		 return sampleMapper().getList();
	}
	
	public SampleDto getById(int id) {
		return sampleMapper().getById(id);
	}
	
	private SampleMapper sampleMapper() {
		return sqlSession.getMapper(SampleMapper.class);
	}

}
