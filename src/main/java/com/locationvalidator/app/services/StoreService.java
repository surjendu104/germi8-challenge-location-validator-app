package com.locationvalidator.app.services;

import com.locationvalidator.app.payload.StoreDto;

import java.util.List;

public interface StoreService {
    public List<StoreDto> getStores(String city);
}
