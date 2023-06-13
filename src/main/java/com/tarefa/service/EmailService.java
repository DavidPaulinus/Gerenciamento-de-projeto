package com.tarefa.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tarefa.model.Tarefa;

@Service
public class EmailService {
	@Autowired
	private TarefaService serv;
	
	@Autowired
	private JavaMailSender mailSend;
	
	public void sendEmail() {
		var _email = new SimpleMailMessage();
		
		for(Tarefa lista: serv.listarTarefas()) {
			if(lista.getPrazo().minusDays(7).isAfter(LocalDateTime.now()) && lista.getAFazer()){
				_email.setTo(lista.getEmail());
				_email.setSubject("Prazo está próximo");
				_email.setText("Email automático para avisar que você tem uma semana até o fim do prazo da tarefa.");
				
				mailSend.send(_email);
			}
		}
	}
}
