package com.tarefa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.tarefa.model.Tarefa;
import com.tarefa.model.dto.TarefaDTO;
import com.tarefa.service.util.repository.TarefaRepository;

import jakarta.validation.Valid;

@Service
public class TarefaService {
	@Autowired
	private TarefaRepository repo;
	@Autowired
	private ProjetoService proServ;

	public Tarefa criar(@Valid TarefaDTO dto, Long projetoId) {
		var _taref = new Tarefa(avaliaTarefa(dto), proServ.detalharPorId(projetoId));
		repo.save(_taref);
		
		proServ.assimilarTarefa(_taref, projetoId);
		
		return _taref;
	}

	public Page<Tarefa> listarTarefas() {
		return new PageImpl<>(repo.findAll());
	}

	public Tarefa detalharPorId(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Erro ao achar por ID"));
	}

	public Tarefa alterarPorId(Long id, @Valid TarefaDTO dto, Long projetoId) {
		var _taref = detalharPorId(id);
		var _proj = proServ.detalharPorId(projetoId);
		
		_taref.alterar(dto, _proj);

		return _taref;
	}

	public String apagarPorId(Long id) {
		repo.deleteById(id);

		return "Tarefa apagada com sucesso!";
	}

	private TarefaDTO avaliaTarefa(TarefaDTO dto) {
		for (Tarefa lista : listarTarefas()) {
			if (lista.getNome().equals(dto.nome()) && lista.getPrazo().equals(dto.prazo())) {
				throw new RuntimeException("Não é possível ter mais de uma tarefa com mesmo nome e prazo");
			}
		}

		return dto;
	}

	public String concluir(Long id) {
		var _taref = detalharPorId(id);
		
		if(!_taref.getAFazer()) {
			throw new RuntimeException("Tarefa já concluída. Não pode ser reconcluída.");
		}
		_taref.completarTarefa();
		
		return "Tarefa concluída com sucesso.";
	}

}
