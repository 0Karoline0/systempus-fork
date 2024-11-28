package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Disciplina;

public class DisciplinaDTO {
	private Integer id;
	private String nome;

	public DisciplinaDTO(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public static List<DisciplinaDTO> convertToDTO(List<Disciplina> disciplinas){
		return disciplinas.stream().map(d -> new DisciplinaDTO(d.getId(), d.getNome())).collect(Collectors.toList());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}
