package com.csv.csv.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.csv.csv.CSVHelper.csvHelper;
import com.csv.csv.Model.inventoryModel;
import com.csv.csv.Service.inventoryService;

@RestController
@RequestMapping("/csv")
public class inventoryController {
	@Autowired
	private inventoryService inventoryS;

	
	@PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public List<inventoryModel> guardarUsuario(@RequestParam("file") MultipartFile file) {
		
		try {
			inventoryS.guardarVarios(csvHelper.csvToSalesOrders(file.getInputStream(), ""));
			return csvHelper.csvToSalesOrders(file.getInputStream(), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@GetMapping("/todos")
	public List<inventoryModel> getAllSalesOrders() {
		
		return inventoryS.recuperarT();
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = "SalesOrders.csv";
		InputStreamResource file = new InputStreamResource(csvHelper.load(inventoryS.recuperarT()));

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);

	}
	
	@PostMapping("/guardar")
	public List<String> guardarUsuario(@RequestBody inventoryModel im) {
		List<String> errors = new ArrayList<String>();
		if (im.getId() != null) {
			errors.add("¡NO se requiere ID!");
		}
		if (im.getSubInventory() == null) {
			errors.add("getSubInventory");
		}
		if (im.getItem() == null) {
			errors.add("getItem");
		}
		if (im.getItem_Description() == null) {
			errors.add("getItem_Description");
		}
		if (im.getCustomer() == null) {
			errors.add("getCustomer");
		}
		if (im.getSanmina_Stock_Locators() == null) {
			errors.add("getSanmina_Stock_Locators");
		}
		if (im.getQuantity()<=0) {
			errors.add("getQuantity");
		}
		if (im.getTarget_Cost() <=0) {
			errors.add("getTarget_Cost");
		}
		if (im.getExtended_Target_Cost() <=0) {
			errors.add("getExtended_Target_Cost");
		}
		if (im.getsTD_Cost() <=0) {
			errors.add("getSTD_Cost");
		}
		if (im.getExtended_STD_Cost() <=0) {
			errors.add("getExtended_STD_Cost");
		}
		System.out.println("error");
		if (errors.isEmpty()) {
			inventoryS.guardar(im);
			return errors;
		} else {
			return errors;
		}
	}

}
