package com.csv.csv.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csv.csv.Model.inventoryModel;
import com.csv.csv.Repository.inventoryRepository;

@Service
public class inventoryService {
	@Autowired
	private inventoryRepository inventoryR;
	
	public void guardar (inventoryModel inventory) {
		inventoryR.save(inventory);
	}
	public void guardarVarios (List<inventoryModel> inventories) {
		inventoryR.saveAll(inventories);
	}
	public List<inventoryModel> recuperarT() {
		List<inventoryModel> inventories=inventoryR.findAll();
		return inventories;
	}
}
