package com.tarefa.service;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;
import java.util.List;

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
import com.tarefa.model.dto.ProjetoDTO;
import com.tarefa.service.util.repository.ProjetoRepository;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
public class ProjetoServiceTest {
	@MockBean
	private ProjetoRepository repo;
	@Autowired
	private ProjetoService serv;
	
	private ProjetoDTO dto = new ProjetoDTO("Projeto 1", "Descrição 1", LocalDate.now().plusMonths(1), List.of());
	private ProjetoDTO dto2 = new ProjetoDTO("Projeto 1.1", "Descrição 1", LocalDate.now().plusMonths(1), List.of());
	
	@Test
	@DisplayName("Retorna a instancia criada do tipo Projeto")
	void cadastrarProduto() {
		var _prod = serv.criarProjeto(dto);
		
		assertInstanceOf(Projeto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Page<Projeto>")
	void listarProduto() {
		var _prod = serv.listarProjetos();
		
		assertInstanceOf(Page.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Projeto por ID para detalhar")
	void detalharProduto() {
		serv.criarProjeto(dto);
		var _prod = serv.detalharPorId(1l);
		
		assertInstanceOf(Projeto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma instancia do tipo Projeto por ID ao editar")
	void editarProduto() {
		serv.criarProjeto(dto);
		var _prod = serv.alterarPorId(dto2, 1l);
		
		assertInstanceOf(Projeto.class, _prod);
	}
	
	@Test
	@DisplayName("Retorna uma String informando que o projeto foi apagado")
	void apagarProduto() {
		var _prod = serv.apagarPorId(1l);
		
		assertInstanceOf(String.class, _prod);
	}
}
