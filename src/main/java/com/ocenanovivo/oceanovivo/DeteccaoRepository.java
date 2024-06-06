package com.ocenanovivo.oceanovivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeteccaoRepository extends JpaRepository<Deteccao, Long> {
}
