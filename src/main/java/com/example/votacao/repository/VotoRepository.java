package com.example.votacao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.votacao.entity.Voto;
import com.example.votacao.entity.VotoPK;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK>{
	
}
