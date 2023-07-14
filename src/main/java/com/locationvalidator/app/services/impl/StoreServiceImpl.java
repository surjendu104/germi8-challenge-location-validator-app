package com.locationvalidator.app.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.locationvalidator.app.model.Store;
import com.locationvalidator.app.payload.StoreDto;
import com.locationvalidator.app.repository.StoresRepository;
import com.locationvalidator.app.services.StoreService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoresRepository storesRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<StoreDto> getStores(String city) {
        JSONObject response = this.getResponse(city);
        String jsonArray = getKeys(response, "results");
        JSONArray newArray = new JSONArray(jsonArray);
        Iterator<?> iterator = newArray.iterator();
        List<StoreDto> list = new ArrayList<>();
        while(iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            System.out.println(object);
            Store store = new Store();
            store.setStoreName(getKeys(object, "name"));
            store.setStoreDisplayName(getKeys(object, "displayName"));
            store.setCity(city);

            store.setAddress(getKeys(object, "formattedAddress"));
            store.setLatitude(getKeys(new JSONObject(getKeys(object, "geoPoint")), "latitude"));
            store.setLongitude(getKeys(new JSONObject(getKeys(object, "geoPoint")), "longitude"));

            this.storesRepository.save(store);
            list.add(this.modelMapper.map(store, StoreDto.class));
        }
        return list;
    }
    private JSONObject getResponse(String city) {
        String url = "https://www.shoppersstop.com/store-finder";
        url+="?q="+city+"&page="+"0";

        //System.out.println(url);

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            JSONObject jsonObject = new JSONObject(response.body());
            if(response.statusCode() == 200) {
                return jsonObject;
            }else {
                System.out.println("Error");
            }

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONObject();
    }

    // Json parser to parse dynamic json
    public static String parseObject(JSONObject json, String key) {
        return json.get(key).toString();
    }
    public static String getKeys(JSONObject json, String key) {
        String value = "";
        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        if(!exists) {
            keys = json.keys();
            while(keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if(json.get(nextKeys) instanceof JSONObject) {
                        if(!exists) {
                            value = getKeys(json.getJSONObject(nextKeys),key);
                            if(!value.isEmpty()) {
                                break;
                            }
                        }
                    } else if(json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = json.getJSONArray(nextKeys);
                        for(int i = 0; i<jsonArray.length(); i++) {
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerJSON = new JSONObject(jsonArrayString);
                            if(!exists) {
                                value = getKeys(innerJSON, key);
                                if(!value.isEmpty()) {
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            value = parseObject(json, key);
        }
        return value;
    }

}
