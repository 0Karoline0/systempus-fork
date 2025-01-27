package br.com.systempus.systempus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Systempus API", version = "1.0.0"),
servers = {@Server(url = "https://systempus.onrender.com/")},
tags = {
	@Tag(name = "Coordenador", description = "Descrição"),
	@Tag(name = "Professor", description = "TesteProfessor"),
	@Tag(name = "Disciplina", description = ""),
	@Tag(name = "Curso", description = ""),
	@Tag(name = "Modulo", description = "")
}

)
public class SystempusApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystempusApplication.class, args);
	}
}