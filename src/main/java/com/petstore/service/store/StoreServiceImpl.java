package com.petstore.service.store;

import com.petstore.models.Pet;
import com.petstore.models.Store;
import com.petstore.repository.StoreRepository;
import com.petstore.service.exception.StoreObjectNotPresentException;
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

    @Override
    public Store addPet(Pet pet, Integer storeId) throws NullPointerException, StoreObjectNotPresentException{

        //validate that pet is not null
        if (pet == null){
            throw new NullPointerException("Pet Object Should not be null");
        }
        //check that store exist
        Optional<Store> result = mStoreRepository.findById(storeId);

        if(result.isPresent()){

            Store savedStore = result.get();
            savedStore.addPet(pet);
           return mStoreRepository.save(savedStore);
        }
        else {
            throw new StoreObjectNotPresentException("store not present in the database");
        }

    }

    @Override
    public List<Pet> findStorePets(Integer storeId) throws StoreObjectNotPresentException {
        //find store
        Optional<Store> result = mStoreRepository.findById(storeId);

        if(result.isPresent()){
            Store savedStore = result.get();

            return savedStore.getPets();
        }
        else{
            throw new StoreObjectNotPresentException("Store does not exist");
        }
        //return pet list

    }
}
