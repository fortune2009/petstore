package com.petstore.service.pet;

import com.petstore.models.Pet;
import com.petstore.repository.PetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceTest {

    @Mock
    PetRepository mPetRepository;

    PetService mPetService;

    Pet testPet;

    @BeforeEach
    void setUp() {
        mPetService = new PetServiceImpl();

        MockitoAnnotations.initMocks(this);
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
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }
}