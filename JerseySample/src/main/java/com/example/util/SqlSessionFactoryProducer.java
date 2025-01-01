package com.example.util;

import java.io.IOException;
import java.io.InputStream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

@Dependent
public class SqlSessionFactoryProducer {
	
	@Produces
	@ApplicationScoped
	@SessionFactoryProvider
	public SqlSessionFactory produceFactory() throws IOException{
		InputStream fileStream = Resources.getResourceAsStream("mybatis-config.xml");
		return new SqlSessionFactoryBuilder().build(fileStream);
	}

}
