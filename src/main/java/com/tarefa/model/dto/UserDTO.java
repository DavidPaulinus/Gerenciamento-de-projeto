package com.tarefa.model.dto;

import com.tarefa.model.TipoConta;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
		@NotBlank
		String userName,
		@NotBlank
		String senha,
		TipoConta tipo
		) {

}
