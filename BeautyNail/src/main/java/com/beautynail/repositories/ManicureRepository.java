package com.beautynail.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beautynail.domain.Manicure;
@Repository
public interface ManicureRepository extends JpaRepository<Manicure, Integer> {

}
