package com.tarefa.model;

import com.tarefa.model.dto.UserDTO;
import com.tarefa.service.util.Avaliador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	private String senha;
	private TipoConta tipo;

	public Usuario(@Valid UserDTO dto) {
		this.userName = dto.userName();
		this.senha = dto.senha();
		this.tipo = Avaliador.validarTipoConta(dto);
	}

	public void alterar(@Valid UserDTO dto) {
		this.userName = dto.userName();
		this.senha = dto.senha();
	}
}
