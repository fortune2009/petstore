package com.petstore.repository;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import com.petstore.models.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-store.sql"})
class StoreRepositoryTest {

    @Autowired
    StoreRepository mStoreRepository;

    Logger log = Logger.getLogger(getClass().getName());

    Store mTestStore;

    @BeforeEach
    void setUp() {
        mTestStore = mStoreRepository.findById(500).orElse(null);
        assertThat(mTestStore).isNotNull();
    }

    @AfterEach
    void tearDown() {
    }

    void createNewStoreTest(){

        Store londonStore = new Store();
        londonStore.setStoreName("LondonStore");
        londonStore.setState("Lagos");
        londonStore.setStoreNumber(1451);
        londonStore.setAddress("12, london street");
        londonStore.setCountry("Nigeria");

        assertThat(londonStore.getId()).isNull();

        londonStore = mStoreRepository.save(londonStore);

        log.info("Store object after saving --> " + londonStore);
    }

    @Test
    void whenAddPetsToStore_thenSaveToObjectTest(){
        Store londonStore = new Store();
        londonStore.setStoreName("LondonStore");
        londonStore.setState("Lagos");
        londonStore.setStoreNumber(1451);
        londonStore.setAddress("12, london street");
        londonStore.setCountry("Nigeria");

        assertThat(londonStore.getId()).isNull();

        // fetch a pet from database

    }

    @Test
    void whenAddPetsToStore (){

        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setBreed("Bull Dog");
        pet.setTypes(PetTypes.DOG);
        pet.setSex(PetSex.MALE);
        pet.setAge(5);
        pet.setPetStore(mTestStore);
        mTestStore.addPet(pet);

        mTestStore = mStoreRepository.save(mTestStore);

        log.info("Added pets to the store --> " + mTestStore);
    }

    @Test
    void whenStoreIsRetrieved_thenRetrievedStoredPets() {

        // create pets
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setBreed("Bull Dog");
        pet.setTypes(PetTypes.DOG);
        pet.setSex(PetSex.MALE);
        pet.setAge(5);
        pet.setPetStore(mTestStore);

        log.info("Added pets to the store --> " + mTestStore);

        // add pets to store
        Pet pet2 = new Pet();
        pet2.setName("Jack");
        pet2.setBreed("Bull Dog");
        pet2.setTypes(PetTypes.DOG);
        pet2.setSex(PetSex.MALE);
        pet2.setAge(5);
        pet2.setPetStore(mTestStore);

        //add pets to store
        mTestStore.addPet(pet);
        mTestStore.addPet(pet2);

        mTestStore = mStoreRepository.save(mTestStore);
        log.info("Added pets to the store --> " + mTestStore);

        Store savedStore = mStoreRepository.findById(mTestStore.getId()).orElse(null);
        assertThat(savedStore.getPets()).isNotNull();
        assertThat(savedStore.getPets()).hasSize(2);

//        List<Pet> petList = savedStore.getPets();
//
//        petList.forEach(System.out::println);
    }

    @Test
    void retrievesAllPetsInaStoreTest() {

        // create pets
        Pet pet = new Pet();
        pet.setName("Jack");
        pet.setBreed("Bull Dog");
        pet.setTypes(PetTypes.DOG);
        pet.setSex(PetSex.MALE);
        pet.setAge(5);
        pet.setPetStore(mTestStore);

        log.info("Added pets to the store --> " + mTestStore);

        // add pets to store
        Pet pet2 = new Pet();
        pet2.setName("Jack");
        pet2.setBreed("Bull Dog");
        pet2.setTypes(PetTypes.DOG);
        pet2.setSex(PetSex.MALE);
        pet2.setAge(5);
        pet2.setPetStore(mTestStore);

        //add pets to store
        mTestStore.addPet(pet);
        mTestStore.addPet(pet2);

        mTestStore = mStoreRepository.save(mTestStore);
        log.info("Added pets to the store --> " + mTestStore);

        Store savedStore = mStoreRepository.findById(mTestStore.getId()).orElse(null);
        assertThat(savedStore.getPets()).isNotNull();
        assertThat(savedStore.getPets()).hasSize(2);

        List<Pet> petList = savedStore.getPets();

        petList.forEach(System.out::println);
    }
}