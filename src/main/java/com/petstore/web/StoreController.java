package com.petstore.web;

import com.petstore.models.Store;
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

    @PostMapping("/store/pets/{id}")
    public String getStorePets(@PathVariable("id") Integer id){

        return "";
    }
}

