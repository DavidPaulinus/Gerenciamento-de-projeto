package com.tarefa.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tarefa.model.dto.TarefaDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class TarefaControllerTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private JacksonTester<TarefaDTO> jack;

	private TarefaDTO dto = new TarefaDTO("Nome 1", "Descrição 1", "Notificação 1",LocalDateTime.now().plusMonths(1), "Email@Email.com");
	private TarefaDTO dto2 = new TarefaDTO("Nome 1", "Descrição 1", "Notificação 1.1",LocalDateTime.now().plusMonths(1), "Email@Email.com");
	
	@Test
	void retorna201AoCriarUmaNovaTarefa() throws Exception {
		var _json = jack.write(dto).getJson();
		
		mock.perform(MockMvcRequestBuilders.post("/tarefa/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(201));
	}
	@Test
	void retorna200AoConsultarTarefaPorId() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/tarefa/detalhar/{id}", 4)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoListarTodasAsTarefas() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/tarefa/listar")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoAlterarUmaTarefaPorId() throws Exception {
		var _json = jack.write(dto2).getJson();
		
		mock.perform(MockMvcRequestBuilders.put("/tarefa/alterar/{id}", 4)
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoConcluirUmaTarefa() throws Exception {
		mock.perform(MockMvcRequestBuilders.put("/tarefa/concluir/{id}", 4)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoApagarUmaTarefaPorId() throws Exception {
		mock.perform(MockMvcRequestBuilders.delete("/tarefa/apagar/{id}", 4)).andExpect(MockMvcResultMatchers.status().is(200));
	}
}
