package com.tarefa.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tarefa.model.Tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjetoDTO(
		@NotBlank
		String nome,
		@NotBlank
		String descricao,
		@NotNull
		@JsonFormat (pattern = "dd/MM/yyyy")
		LocalDate prazo, 
		List<Tarefa> tarefa) {
}
