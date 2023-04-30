package com.code.santhosh.santhoshreadsdataloader;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.code.santhosh.santhoshreadsdataloader.Connection.DataStaxAstraProperties;
import com.code.santhosh.santhoshreadsdataloader.author.Author;
import com.code.santhosh.santhoshreadsdataloader.author.AuthorRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class SanthoshReadsDataLoaderApplication {

	@Autowired AuthorRepository authorRepository;
	public static void main(String[] args) {
		
		SpringApplication.run(SanthoshReadsDataLoaderApplication.class, args);
		
	}

	@PostConstruct
	public void start(){
		Author author = new Author();
		author.setId("1");
		author.setName("Santhosh");
		author.setPersonalName("coder");
		authorRepository.save(author);

	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
		System.out.println("********************************");
		System.out.println(bundle);
        return builder -> builder.withCloudSecureConnectBundle(bundle);
 }
}
