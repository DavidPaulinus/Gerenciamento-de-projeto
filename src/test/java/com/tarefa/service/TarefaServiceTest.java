package com.tarefa.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import com.tarefa.model.Projeto;
import com.tarefa.model.Tarefa;
import com.tarefa.model.dto.TarefaDTO;
import com.tarefa.service.util.repository.TarefaRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class TarefaServiceTest {
	@MockBean
	private TarefaRepository repo;
	@Autowired
	private TarefaService serv;
	
	private TarefaDTO dto = new TarefaDTO("Nome 1", "Descrição 1", "Notificação 1",LocalDateTime.now().plusMonths(1), "Email@Email.com");
	private TarefaDTO dto2 = new TarefaDTO("Nome 1", "Descrição 1", "Notificação 1.1",LocalDateTime.now().plusMonths(1), "Email@Email.com");
	
	@Test
	@DisplayName("Retorna a instancia criada do tipo Tarefa")
	void cadastrarProduto() {
		var _prod = serv.criar(dto, 1l);
		
		assertInstanceOf(Tarefa.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Page<Tarefa>")
	void listarProduto() {
		var _prod = serv.listarTarefas();
		
		assertInstanceOf(Page.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Tarefa por ID para detalhar")
	void detalharProduto() {
		serv.criar(dto, 1l);
		var _prod = serv.detalharPorId(1l);
		
		assertInstanceOf(Projeto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Tarefa por ID ao editar")
	void editarProduto() {
		serv.criar(dto, 1l);
		var _prod = serv.alterarPorId(1l, dto2, 1l);
		
		assertInstanceOf(Projeto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma String informando que o projeto foi apagado")
	void apagarProduto() {
		var _prod = serv.apagarPorId(1l);
		
		assertInstanceOf(String.class, _prod);
	}
}
