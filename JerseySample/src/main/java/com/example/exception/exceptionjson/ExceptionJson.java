package com.example.exception.exceptionjson;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;

import com.example.dto.ExceptionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class ExceptionJson {
	
	public String exceptionJson(ExceptionDto dto) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			throw new InternalServerErrorException();
		}
	}
	
}
