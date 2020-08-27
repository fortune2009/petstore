package com.petstore.service.pet;

import com.petstore.models.Pet;
import com.petstore.repository.PetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceTest {

    @Mock
    PetRepository mPetRepository;

    @InjectMocks
    PetService mPetService;

    Pet testPet;

    @BeforeEach
    void setUp() {
        mPetService = new PetServiceImpl();

        MockitoAnnotations.initMocks(this);
        testPet = new Pet();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        when(mPetService.save(testPet)).thenReturn(testPet);
        mPetService.save(testPet);

        verify(mPetRepository, times(1)).save(testPet);
    }

    @Test
    void findById() {
        when(mPetService.findById(3)).thenReturn(Optional.of(testPet));
        mPetService.findById(3);

        verify(mPetRepository, times(1)).findById(3);
    }

    @Test
    void update() {
        when(mPetService.update(testPet)).thenReturn(testPet);
        mPetService.update(testPet);

        verify(mPetRepository, times(1)).save(testPet);

    }

    @Test
    void delete() {

        doNothing().when(mPetRepository).deleteById(8);
        mPetRepository.deleteById(8);

        verify(mPetRepository, times(1)).deleteById(8);
    }

    @Test
    void findAll() {
        List<Pet> petList = new ArrayList<>();
        when(mPetService.findAll()).thenReturn(petList);
        mPetService.findAll();

        verify(mPetRepository, times(1)).findAll();
    }
}