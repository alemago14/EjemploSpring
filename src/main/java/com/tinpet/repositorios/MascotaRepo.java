package com.tinpet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tinpet.entidades.Mascota;

@Repository
public interface MascotaRepo extends JpaRepository<Mascota, Integer> {

	@Query("SELECT m FROM Mascota m WHERE m.nombre = :nombre")
	public Mascota bucarPorNombre(@Param("nombre") String nombre);
}
