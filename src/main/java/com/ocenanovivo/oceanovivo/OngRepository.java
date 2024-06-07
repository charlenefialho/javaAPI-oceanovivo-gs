package com.ocenanovivo.oceanovivo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {
	  List<Ong> findByCategoriaAnimal(String categoriaAnimal);
}

