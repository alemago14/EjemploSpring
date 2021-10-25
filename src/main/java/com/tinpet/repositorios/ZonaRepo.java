package com.tinpet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tinpet.entidades.Zona;

@Repository
public interface ZonaRepo extends JpaRepository<Zona, Integer>{

}
