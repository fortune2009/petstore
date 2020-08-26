package com.petstore.service.store;

import com.petstore.models.Store;
import com.petstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService{

    @Autowired
    StoreRepository mStoreRepository;

    @Override
    public Store save(Store store) {
        return mStoreRepository.save(store);
    }

    @Override
    public Optional<Store> findById(Integer storeId) {
        return mStoreRepository.findById(storeId);
    }

    @Override
    public Store update(Store store) {
        return mStoreRepository.save(store);
    }

    @Override
    public void delete(Integer storeId) {
        mStoreRepository.deleteById(storeId);
    }

    @Override
    public List<Store> findAll() {
        return mStoreRepository.findAll();
    }
}
