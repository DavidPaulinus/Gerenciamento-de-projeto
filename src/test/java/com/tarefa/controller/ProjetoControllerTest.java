package com.tarefa.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.tarefa.model.dto.ProjetoDTO;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ProjetoControllerTest {
	@Autowired
	private MockMvc mock;
	@Autowired
	private JacksonTester<ProjetoDTO> jack;

	private ProjetoDTO dto = new ProjetoDTO("Projeto 1", "Descrição 1", LocalDate.now().plusMonths(1), List.of(), null);
	private ProjetoDTO dto2 = new ProjetoDTO("Projeto 1.1", "Descrição 1", LocalDate.now().plusMonths(1), List.of(), null);
	
	@Test
	void retorna201AoCriarUmNovoProjeto() throws Exception {
		var _json = jack.write(dto).getJson();
		
		mock.perform(MockMvcRequestBuilders.post("/projeto/criar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(201));
	}
	@Test
	void retorna200AoConsultarProjetoPorId() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/projeto/detalhar/{id}", 1)).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoListarTodosOsProjeto() throws Exception {
		mock.perform(MockMvcRequestBuilders.get("/projeto/listar")).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoAlterarUmProjetoPorId() throws Exception {
		var _json = jack.write(dto2).getJson();
		
		mock.perform(MockMvcRequestBuilders.put("/projeto/alterar/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(_json)
				).andExpect(MockMvcResultMatchers.status().is(200));
	}
	@Test
	void retorna200AoApagarUmProjetoPorId() throws Exception {
		mock.perform(MockMvcRequestBuilders.delete("/projeto/apagar/{id}", 3)).andExpect(MockMvcResultMatchers.status().is(200));
	}
}
