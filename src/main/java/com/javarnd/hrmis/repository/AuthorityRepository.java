package com.javarnd.hrmis.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.javarnd.hrmis.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
