package com.tarefa.model;

import java.time.LocalDate;
import java.util.List;

import com.tarefa.model.dto.ProjetoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_projetos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate dataCriacao;
	private LocalDate prazo;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Tarefa> tarefa;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Usuario> user;

	public Projeto(@Valid ProjetoDTO dto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.dataCriacao = LocalDate.now();
		this.prazo = dto.prazo();
		this.tarefa = dto.tarefa();
	}

	public List<String> getNomesTarefas() {
		if (tarefa != null) {
			return tarefa.stream().map(x -> x.getNome()).toList();
		}

		return null;
	}

	public List<String> getNomesUsuarios() {
		if (user != null) {
			return user.stream().map(x -> x.getUserName()).toList();
		}

		return null;
	}

	public void alterar(@Valid ProjetoDTO dto) {
		this.nome = dto.nome();
		this.descricao = dto.descricao();
		this.dataCriacao = LocalDate.now();
		this.prazo = dto.prazo();
		this.tarefa = dto.tarefa();
		this.user = dto.user();
	}

	public void adicionarTarefa(Tarefa tarefa) {
		this.tarefa.add(tarefa);
	}

	public void adicionarUsuario(Usuario user) {
		this.user.add(user);
	}
}
