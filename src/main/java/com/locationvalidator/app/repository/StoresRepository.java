package com.locationvalidator.app.repository;

import com.locationvalidator.app.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoresRepository extends JpaRepository<Store, Integer> {
}
