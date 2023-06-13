package com.tarefa.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TarefaDTO(
		@NotBlank
		String nome,
		@NotBlank
		String descricao,
		@NotBlank
		String notificacao,
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		@NotNull
		LocalDateTime prazo,
		@Email
		String email
		) {

}
