package com.example.resource;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.example.dto.SampleDto;
import com.example.exception.DataNotFoundException;
import com.example.model.Input;
import com.example.service.SampleService;

@Path("api")
@RequestScoped
public class SampleResource {
	
	@Inject
	private SampleService service;	
	
	
	@Path("getlist")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<SampleDto> getList(){
		return service.getList();
	}
	
	@Path("getbyid")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public SampleDto getById(Input input) {
		SampleDto dto = service.getById(input.getId());
		if(dto == null) {
			throw new DataNotFoundException();
		}else {
			return dto;
		}
	}

}
