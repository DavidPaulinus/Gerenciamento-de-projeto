package com.tarefa.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.tarefa.model.Projeto;

public record DetalharProjetoDTO(String nome,String descricao,LocalDate dataCriacao,LocalDate prazo,List<String> tarefasNome) {
	
	public DetalharProjetoDTO(Projeto proj) {
		this(proj.getNome(), proj.getDescricao(), proj.getDataCriacao(), proj.getPrazo(),proj.getNomesTarefas());
	}

}
