package com.ant.gc.repositories;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.gc.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
// List<Contact> findByNom(String nom);
// @Query("select c from Contact c where c.nom=:nom")
// List<Contact> hetByNom(String nom);

 public boolean existsByEmail (String email);
 public boolean existsByEmailAndId (String email,Integer id);

//public List<Contact> hetByNom(String string);
 
}
