package com.tarefa.model.dto;

import java.time.LocalDateTime;

import com.tarefa.model.Tarefa;

public record DetalharTarefaDTO(String nome,String descricao,String notificacao, LocalDateTime prazo,String email) {
	public DetalharTarefaDTO(Tarefa taref) {
		this(taref.getNome(), taref.getDescricao(), taref.getNotificacao(), taref.getPrazo(), taref.getEmail());
	}
}
