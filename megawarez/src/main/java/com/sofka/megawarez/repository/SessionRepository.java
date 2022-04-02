package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends JpaRepository<Session, Integer> {

}