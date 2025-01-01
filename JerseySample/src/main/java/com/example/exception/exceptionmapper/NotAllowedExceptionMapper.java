package com.example.exception.exceptionmapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import com.example.dto.ExceptionDto;
import com.example.exception.exceptionjson.ExceptionJson;
import com.example.util.Message;

@Provider
public class NotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {
	
	@Inject
	private ExceptionJson json;

	@Override
	public Response toResponse(NotAllowedException exception) {
		
		ExceptionDto dto = new ExceptionDto(); 
		dto.setCode(Message.CODE_405);
		dto.setMsg(Message.EXCEPTION_3);
		
		return Response.status(Response.Status.METHOD_NOT_ALLOWED)
				.entity(json.exceptionJson(dto))
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
