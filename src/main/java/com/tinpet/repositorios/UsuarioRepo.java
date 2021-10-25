package com.tinpet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinpet.entidades.Usuario;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>{

	@Query("SELECT u FROM Usuario u WHERE u.mail = :mail")
	public Usuario buscarPorMail(@Param("mail")String mail);
}
