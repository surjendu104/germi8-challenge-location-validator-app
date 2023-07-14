package com.locationvalidator.app.controller;

import com.locationvalidator.app.payload.StoreDto;
import com.locationvalidator.app.services.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
    @Autowired
    private StoreServiceImpl storeService;
    @GetMapping("/getStores/{city}")
    public ResponseEntity<List<StoreDto>> getStoresInCity(@PathVariable String city) {
        List<StoreDto> stores = this.storeService.getStores(city);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }
}
