package com.petstore.repository;

import com.petstore.models.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

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
}