package com.locationvalidator.app.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    private int id;
    private String storeName;
    private String storeDisplayName;
    private String city;
//    private String openingTime;
//    private String closingTime;
    private String address;
    private String latitude;
    private String longitude;
}
