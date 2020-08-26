package com.petstore.service.store;

import com.petstore.models.Store;
import com.petstore.repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    @Mock
    StoreRepository mStoreRepository;

    @InjectMocks
    StoreService mStoreService;

    Store testStore;

    @BeforeEach
    void setUp() {
        mStoreService = new StoreServiceImpl();

        MockitoAnnotations.initMocks(this);
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
        when(mStoreService.findById(testStore.getId())).thenReturn(Optional.of(testStore));
        mStoreService.findById(testStore.getId());

        verify(mStoreRepository, times(1)).findById(testStore.getId());
    }

    @Test
    void update() {
        when((mStoreService.update(testStore))).thenReturn(testStore);
        mStoreService.update(testStore);

        verify(mStoreRepository, times(1)).save(testStore);
    }

    @Test
    void delete() {
        doNothing().when(mStoreRepository).deleteById(testStore.getId());
        mStoreRepository.deleteById(testStore.getId());

        verify(mStoreRepository, times(1)).deleteById(testStore.getId());
    }

    @Test
    void findAll() {
        List<Store> storeList = new ArrayList<>();
        when(mStoreService.findAll()).thenReturn(storeList);

        verify(mStoreRepository, times(1)).findAll();
    }
}