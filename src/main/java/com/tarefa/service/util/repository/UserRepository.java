package com.tarefa.service.util.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefa.model.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

}
