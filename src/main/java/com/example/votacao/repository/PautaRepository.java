package com.example.votacao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.votacao.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer>{
	

}
