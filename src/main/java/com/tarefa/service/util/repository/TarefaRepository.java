package com.tarefa.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tarefa.model.Tarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	
	@Query(value = "DELET FROM tb_tarefas WHERE projeto_id = ?1", nativeQuery = true)
	void removeAllByProjetoId(Long id);

}
