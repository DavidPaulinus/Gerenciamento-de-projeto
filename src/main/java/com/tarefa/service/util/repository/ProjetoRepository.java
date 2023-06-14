package com.tarefa.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tarefa.model.Projeto;

import jakarta.transaction.Transactional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	@Modifying
    @Transactional
	@Query(value = "DELETE FROM tb_projetos_tarefa WHERE projeto_id = ?1", nativeQuery = true)
	void removeIdFromTarefaProjetoCorrelation(Long id);

	@Modifying
    @Transactional
	@Query(value = "DELETE FROM tb_tarefas WHERE projeto_id = ?1", nativeQuery = true)
	void removeAllByProjetoId(Long id);
	
}
