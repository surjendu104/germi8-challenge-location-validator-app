package com.locationvalidator.app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Store Name")
    private String storeName;
    @Column(name = "Store Display Name")
    private String storeDisplayName;
    @Column(name = "City")
    private String city;
//    @Column(name = "Opening Time")
//    private String openingTime;
//    @Column(name = "Closing Time")
//    private String closingTime;
    @Column(name = "Address")
    private String address;
    @Column(name = "Latitude")
    private String latitude;
    @Column(name = "Longitude")
    private String longitude;
}
