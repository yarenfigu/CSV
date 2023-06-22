package com.csv.csv.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csv.csv.Model.inventoryModel;

@Repository
public interface inventoryRepository extends JpaRepository<inventoryModel, Integer>{

}
