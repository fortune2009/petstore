package com.petstore.repository;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import jdk.jfr.Name;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-pet.sql"})
class PetRepositoryTest {

    @Autowired
    PetRepository mPetRepository;

    Logger log = Logger.getLogger(getClass().getName());

    Pet testPetData;

    @BeforeEach
    void setUp() {
        testPetData = mPetRepository.findById(200).orElse(null);
        assertThat(testPetData).isNotNull();
        log.info("Test pet data retrieved from database --> " + testPetData);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPetObject_thenSaveToDatebaseTest(){

        Pet pet = new Pet();
        pet.setAge(5);
        pet.setName("Lucky");
        pet.setSex(PetSex.MALE);
        pet.setTypes(PetTypes.DOG);
        pet.setBreed("bull dog");
        pet.setBirthDate(new Date());

        log.info("Created pet object --> " + pet);
        assertThat(pet.getId()).isNull();

        // save pet object to the database
        pet = mPetRepository.save(pet);
        log.info("After saving pet object --> " + pet);
        assertThat(pet.getId()).isNotNull();
    }

    @Test
    void whenFindAllPetIsCalled_thenRetrievePetsListTest(){

        List<Pet> allPets = mPetRepository.findAll();
        assertThat(allPets.size()).isEqualTo(8);
        log.info("All pets retrived from the database --> " + allPets);
//        assertThat(allPets).contains(testPetData);

    }

    @Test
    void whenPetDetailsIsUpdated_thenUpdateDatabaseDetails(){

        assertThat(testPetData.getName()).isEqualTo("Bobby");
        testPetData.setName("jiran");

        testPetData = mPetRepository.save(testPetData);
        assertThat(testPetData.getName()).isEqualTo("jiran");
    }

    @Test
    void whenDeleteIsCalled_thenDeletePetDataTest(){

        List<Pet> allPets = mPetRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(8);

        Pet savedPet = mPetRepository.findById(204).orElse(null);
        assertThat(savedPet).isNotNull();
        mPetRepository.deleteById(204);

        Pet deletePet = mPetRepository.findById(204).orElse(null);
        assertThat(deletePet).isNull();

        allPets = mPetRepository.findAll();
        assertThat(allPets).isNotNull();
        assertThat(allPets.size()).isEqualTo(7);
    }
}