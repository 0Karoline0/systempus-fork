package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Disciplina;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisciplinaDTO {
	private Integer id;
	private String nome;
	private Integer quantidadeCarga;

	public DisciplinaDTO(Integer id, String nome, Integer quantidadeCarga) {
		super();
		this.id = id;
		this.nome = nome;
		this.quantidadeCarga = quantidadeCarga;
	}
	
	public static DisciplinaDTO convertToDTO(Disciplina disciplina){
		return new DisciplinaDTO(disciplina.getId(), disciplina.getNome(), disciplina.getQuantidadeCargas());
	}
	
	public static List<DisciplinaDTO> convertToDTO(List<Disciplina> disciplinas){
		return disciplinas.stream().map(d -> new DisciplinaDTO(d.getId(), d.getNome(), d.getQuantidadeCargas())).collect(Collectors.toList());
	}
	
	
}
