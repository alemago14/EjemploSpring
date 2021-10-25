package com.tinpet.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinpet.entidades.Foto;

public interface FotoRepo extends JpaRepository<Foto, Integer> {

}
