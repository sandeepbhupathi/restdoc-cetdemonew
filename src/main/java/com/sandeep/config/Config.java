package com.sandeep.config;


import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableJpaRepositories(basePackages={"com.sandeep"})

public class Config {

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	 ObjectMapper objectMapper =jsonConverter.getObjectMapper();
	 SimpleModule module = new SimpleModule("Stream");
	 module.addSerializer(Stream.class, new JsonSerializer<Stream>() {


	    @Override
	    public void serialize(Stream value, JsonGenerator gen, SerializerProvider serializers)
	            throws IOException, JsonProcessingException {
	        serializers.findValueSerializer(Iterator.class, null)
	        .serialize(value.iterator(), gen, serializers);

	    }
	});

	 objectMapper.registerModule(module);
	 jsonConverter.setObjectMapper(objectMapper);
	 return jsonConverter;
	}
	
	@Bean
	public DataSource getDataSource(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase eb = builder.setType(EmbeddedDatabaseType.HSQL)
				.build();
		return eb;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(getDataSource());
		em.setPackagesToScan(new String[] { "com.sandeep" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
		return em;
	}

	private Properties additionalProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return prop;
	}
}
