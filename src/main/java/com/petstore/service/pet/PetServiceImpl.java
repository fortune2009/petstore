package com.petstore.service.pet;

import com.petstore.models.Pet;
import com.petstore.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    PetRepository mPetRepository;

    @Override
    public Pet save(Pet pet) {
        return mPetRepository.save(pet);
    }

    @Override
    public Optional<Pet> findById(Integer petId) {
        return mPetRepository.findById(petId);
    }

    @Override
    public Pet update(Pet pet) {
        return mPetRepository.save(pet);
    }

    @Override
    public void delete(Integer petId) {
        mPetRepository.deleteById(petId);
    }

    @Override
    public List<Pet> findAll() {
        return mPetRepository.findAll();
    }
}
