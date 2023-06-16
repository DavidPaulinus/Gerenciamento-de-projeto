package com.tarefa.model;

public record DetalharUsuario(String userName, String senha, TipoConta tipo) {
	public DetalharUsuario(Usuario user) {
		this(user.getUserName(), user.getSenha(), user.getTipo());
	}

}
