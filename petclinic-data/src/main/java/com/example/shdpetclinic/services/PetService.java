package com.example.shdpetclinic.services;

import com.example.shdpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findByID(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
