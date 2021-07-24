package com.beautynail.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.beautynail.domain.Manicure;

public interface ManicureRepository extends JpaRepository<Manicure, Integer> {

}
