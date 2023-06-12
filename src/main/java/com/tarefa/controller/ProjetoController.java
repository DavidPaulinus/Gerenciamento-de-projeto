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

import com.tarefa.model.dto.DetalharProjetoDTO;
import com.tarefa.model.dto.ProjetoDTO;
import com.tarefa.service.ProjetoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {
	@Autowired
	private ProjetoService serv;
	
	@PostMapping("/criar")
	@Transactional
	public ResponseEntity<DetalharProjetoDTO> criarProjeto(@RequestBody @Valid ProjetoDTO dto, UriComponentsBuilder uri){
		var _proj = serv.criarProjeto(dto);
		
		return ResponseEntity.created(
				uri.path("/projeto/criar").buildAndExpand(_proj.getId()).toUri()
				)
				.body(new DetalharProjetoDTO(_proj));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Page<DetalharProjetoDTO>> listarProjeto(){
		var _proj = serv.listarProjetos();
		
		return ResponseEntity.ok(_proj.map(DetalharProjetoDTO::new));
	}
	@GetMapping("/detalhar/{id}")
	public ResponseEntity<DetalharProjetoDTO> detalharProjetoPorId(@PathVariable Long id){
		var _proj = serv.detalharPorId(id);
		
		return ResponseEntity.ok(new DetalharProjetoDTO(_proj));
	}
	
	@PutMapping("/alterar/{id}")
	@Transactional
	public ResponseEntity<DetalharProjetoDTO> alterarProjeto(@RequestBody @Valid ProjetoDTO dto, @PathVariable Long id){
		var _proj = serv.alterarPorId(dto,id);
		
		return ResponseEntity.ok(new DetalharProjetoDTO(_proj));
	}
	
	@DeleteMapping("/apagar/{id}")
	@Transactional
	public ResponseEntity<String> apagarProjetoPorId(@PathVariable Long id){
		var _proj = serv.apagarPorId(id);
		
		return ResponseEntity.ok(_proj);
	}
}
