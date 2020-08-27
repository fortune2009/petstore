package com.petstore.service.store;

import com.petstore.models.Pet;
import com.petstore.models.PetSex;
import com.petstore.models.PetTypes;
import com.petstore.models.Store;
import com.petstore.repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Sql(scripts = {"classpath:db\\insert-store.sql"})
class StoreServiceTest {

    @Mock
    StoreRepository mStoreRepository;

    @Autowired                      // For concrete test, here we dont mock to enable us test by adding pet to store
    StoreRepository mConcreteRepository;

    @InjectMocks
    StoreService mStoreService = new StoreServiceImpl();;

    Store testStore;

    Logger log = Logger.getLogger(getClass().getName());

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        testStore = new Store();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        when(mStoreService.save(testStore)).thenReturn(testStore);
        mStoreService.save(testStore);

        verify(mStoreRepository, times(1)).save(testStore);
    }

    @Test
    void findById() {
        when(mStoreService.findById(13)).thenReturn(Optional.of(testStore));
        mStoreService.findById(13);

        verify(mStoreRepository, times(1)).findById(13);
    }

    @Test
    void update() {
        when((mStoreService.update(testStore))).thenReturn(testStore);
        mStoreService.update(testStore);

        verify(mStoreRepository, times(1)).save(testStore);
    }

    @Test
    void delete() {
        doNothing().when(mStoreRepository).deleteById(9);
        mStoreRepository.deleteById(9);

        verify(mStoreRepository, times(1)).deleteById(9);
    }

    @Test
    void findAll() {
        List<Store> storeList = new ArrayList<>();
        when(mStoreService.findAll()).thenReturn(storeList);
        mStoreService.findAll();

        verify(mStoreRepository, times(1)).findAll();
    }

    @Test
    void addPets() {

        // retreive store details
        Store savedStore = mConcreteRepository.findById(500).orElse(null);
        log.info("Retrieved store from the database --> " + savedStore);
        assertThat(savedStore).isNotNull();
        assertThat(savedStore.getPets()).hasSize(0);

        // add pets to store
        Pet pet = new Pet();
        pet.setName("bingo");
        pet.setId(300);
        pet.setAge(3);
        pet.setSex(PetSex.FEMALE);
        pet.setTypes(PetTypes.FISH);
        pet.setBreed("fingerlings");
        pet.setBirthDate(new Date());
        pet.setPetStore(savedStore);   // set the saved store above

        savedStore.addPet(pet);
        // save store
        mConcreteRepository.save(savedStore);

        //retrieve store
        Store savedStore2 = mConcreteRepository.findById(500).orElse(null);
        assertNotNull(savedStore2);
        log.info("Retrieved store after adding pet --> " + savedStore2.toString());

        //verify that pet is added to store
        assertThat(savedStore2.getPets()).hasSize(1);

    }
}