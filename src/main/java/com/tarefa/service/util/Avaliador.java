package com.tarefa.service.util;

import com.tarefa.model.TipoConta;
import com.tarefa.model.dto.UserDTO;

public class Avaliador {
	
	public static TipoConta validarTipoConta(UserDTO dto) {
		return dto.tipo() == null ? TipoConta.BASICA : dto.tipo();
	}
}
