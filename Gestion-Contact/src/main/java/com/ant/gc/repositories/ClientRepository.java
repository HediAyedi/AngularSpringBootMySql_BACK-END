package com.ant.gc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.gc.entities.Client;


public interface ClientRepository extends JpaRepository<Client, Integer> {

}
