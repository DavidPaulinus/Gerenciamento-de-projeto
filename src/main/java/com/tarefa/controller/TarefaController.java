package com.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tarefa.model.dto.DetalharTarefaDTO;
import com.tarefa.model.dto.TarefaDTO;
import com.tarefa.service.TarefaService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
	@Autowired
	private TarefaService serv;

	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<DetalharTarefaDTO> criarTarefa(@RequestBody @Valid TarefaDTO dto, @PathParam(value = "projetoId") Long projetoId, UriComponentsBuilder uri) {
		var _taref = serv.criar(dto, projetoId);

		return ResponseEntity.created(
				uri.path("/tarefa/criar").buildAndExpand(_taref.getId()).toUri()
				)
				.body(new DetalharTarefaDTO(_taref));
	}
	@GetMapping("/listar")
	public ResponseEntity<Page<DetalharTarefaDTO>> listarTarefas(){
		var _tarefs = serv.listarTarefas();
		
		return ResponseEntity.ok(_tarefs.map(DetalharTarefaDTO::new));
	}
	@GetMapping("/detalhar/{id}")
	public ResponseEntity<DetalharTarefaDTO> detalharTarefaPorId(@PathVariable Long id){
		var _taref = serv.detalharPorId(id);
		
		return ResponseEntity.ok(new DetalharTarefaDTO(_taref));
	}
	@PutMapping("/alterar/{id}")
	@Transactional
	public ResponseEntity<DetalharTarefaDTO> alterarTarefaPorId(@PathVariable Long id, @RequestBody @Valid TarefaDTO dto, @PathParam(value = "projetoId")Long projetoId){
		var _taref = serv.alterarPorId(id, dto, projetoId);
		
		return ResponseEntity.ok(new DetalharTarefaDTO(_taref));
	}
	@DeleteMapping("/apagar/{id}")
	@Transactional
	public ResponseEntity<String> apagarTarefaPorId(@PathVariable Long id){
		var _taref = serv.apagarPorId(id);
		
		return ResponseEntity.ok(_taref);
	}
	
	@PutMapping("/concluir/{id}")
	@Transactional
	public ResponseEntity<String> concluirTarefa(@PathVariable Long id){
		var _taref = serv.concluir(id);
		
		return ResponseEntity.ok(_taref);
	}

}
