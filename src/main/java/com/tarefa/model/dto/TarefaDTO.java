package com.tarefa.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TarefaDTO(
		@NotBlank
		String nome,
		@NotBlank
		String descricao,
		@NotBlank
		String notificacao, 
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime prazo,
		@Email
		String email
		) {

}
