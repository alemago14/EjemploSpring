package com.tinpet.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinpet.entidades.Voto;

@Repository
public interface VotoRepo extends JpaRepository<Voto, Integer>{

	@Query("SELECT v FROM Voto v WHERE v.m1.id = :id ORDER BY v.fecha")
	public List<Voto> VotosPropios(@Param("id")Integer id);
	
	@Query("SELECT v FROM Voto v WHERE v.m2.id = :id ORDER BY v.fecha")
	public List<Voto> VotosRecibidos(@Param("id")Integer id);
}
