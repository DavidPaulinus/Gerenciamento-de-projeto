package com.tarefa.model;

import java.time.LocalDateTime;

import com.tarefa.model.dto.TarefaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tarefas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private String notificacao;
	private LocalDateTime prazo;
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	private Projeto projeto;
	private Boolean aFazer;

	public Tarefa(@Valid TarefaDTO dto, Projeto projeto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.notificacao = dto.notificacao();
		this.prazo = dto.prazo();
		this.email = dto.email();
		this.projeto = projeto;
		this.aFazer = true;
	}

	public void alterar(@Valid TarefaDTO dto, Projeto projeto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.notificacao = dto.notificacao();
		this.prazo = dto.prazo();
		this.email = dto.email();
		this.projeto = projeto;
	}
	
	public void completarTarefa() {
		this.aFazer = false;
	}
}
