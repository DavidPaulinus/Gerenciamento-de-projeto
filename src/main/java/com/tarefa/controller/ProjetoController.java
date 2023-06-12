package com.tarefa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
