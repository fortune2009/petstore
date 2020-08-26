package com.petstore.service.pet;

import com.petstore.models.Pet;

import java.util.List;

public interface PetService {
    Pet save(Pet pet);

    Pet findById(Integer petId);

    Pet update(Pet pet);

    void delete(Integer petId);

    List<Pet> findAll();
}
