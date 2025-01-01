package com.example.exception.exceptionmapper;

import java.sql.SQLException;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import org.apache.ibatis.exceptions.PersistenceException;

import com.example.dto.ExceptionDto;
import com.example.exception.exceptionjson.ExceptionJson;
import com.example.util.Message;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

	@Inject
	private ExceptionJson json;

	@Override
	public Response toResponse(PersistenceException exception) {
		
		SQLException e = (SQLException)exception.getCause();
		ExceptionDto dto = new ExceptionDto(); 
		dto.setCode(Message.CODE_500);
		
		if("08001".equals(e.getSQLState())) {
			dto.setMsg(Message.EXCEPTION_4);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(json.exceptionJson(dto))
					.type(MediaType.APPLICATION_JSON)
					.build();
		}else {
			dto.setMsg(Message.EXCEPTION_5);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(json.exceptionJson(dto))
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		
	}

}
