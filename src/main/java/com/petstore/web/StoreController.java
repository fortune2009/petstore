package com.petstore.web;

import com.petstore.models.Pet;
import com.petstore.models.Store;
import com.petstore.service.exception.StoreObjectNotPresentException;
import com.petstore.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreService mStoreService;

    Logger log = Logger.getLogger(getClass().getName());

    @GetMapping("/")
    public String welcomePage(Model model){

        model.addAttribute("message", "Welcome to Pet Lovers Home page");
        return "index";
    }

    @GetMapping("/form")
    public String getStoreForm(Model model){
        model.addAttribute("store", new Store());

        return "create-store";
    }

    @PostMapping("/create")
    public String createStore(@ModelAttribute("store") Store store, Model model){ //ModelAttribute is used to postMap

        log.info("store information --> " + store);

        store = mStoreService.save(store);
        log.info("Store info after saving to the database --> " + store);
        model.addAttribute("storeObj", store);

        return "store-info";
    }

    @GetMapping("/list")
    public String getAllStores(Model model){

        List<Store> storeList = mStoreService.findAll();

        model.addAttribute("storeList", storeList);

        return "store-list";

    }

    @PostMapping("/pets/{id}")
    public String getStorePets(@PathVariable("id") Integer storeId, Model model) {
        List<Pet> storePets = null;
        try {
            storePets = mStoreService.findStorePets(storeId);
        } catch(StoreObjectNotPresentException e) {
            return "redirect:/";
        }

        model.addAttribute("storePets", storePets);
        return "pet-list";
    }

    @PostMapping("/pets/create/{id}")
    public String mapStoreIdToPet(@PathVariable("id") Integer storeId, Model model){

        //create pet object
        Pet pet = new Pet();

        //create store object and
        Store store = new Store();
        store.setId(storeId);

        pet.setPetStore(store);

        //returning model attribute
        model.addAttribute("pet", pet);

        return "create-pet";
    }

    @PostMapping("/pet/save")
    public  String savePetToStore(@ModelAttribute("pet") Pet pet, Model model){

        log.info("Pet info --> " + pet);

        return "redirect:/store/list";
    }
}

