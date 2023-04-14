package com.test.codingTest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.codingTest.model.Mobile;
import com.test.codingTest.service.MobileService;
import com.test.codingTest.service.UserService;

@RestController
@RequestMapping("/mobiles")
public class MobileController {

	@Autowired
	private MobileService mobileService;

	@Autowired
	private UserService userService;

	// get all mobiles
	@GetMapping("")
	public List<Mobile> getAllMobiles() {
		return mobileService.getAllMobiles();
	}

	// add one mobile
	@PostMapping("")
	public ResponseEntity<Mobile> createMobile(@RequestBody Mobile mobile,
			@RequestHeader("Authorization") String token) {
		if (!userService.validateToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Mobile createdMobile = mobileService.createMobile(mobile);
		return ResponseEntity.created(URI.create("/mobiles/" + createdMobile.getId())).body(createdMobile);
	}

	// delete one mobile
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMobile(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		if (userService.validateToken(token)) {
			mobileService.deleteMobile(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	// filter by brand, model, color
	@GetMapping("/filter")
	public List<Mobile> getMobilesByFilters(@RequestParam(required = false) String brand,
			@RequestParam(required = false) String model, @RequestParam(required = false) String color) {

		return mobileService.getMobilesByFilters(brand, model, color);
	}

	// filter by price range
	@GetMapping("/price")
	public List<Mobile> getMobilesByPriceRange(@RequestParam String minPrice, @RequestParam String maxPrice) {
		Double minPriceLong = Double.parseDouble(minPrice);
		Double maxPriceLong = Double.parseDouble(maxPrice);

		return mobileService.getMobilesByPriceRange(minPriceLong, maxPriceLong);
	}

	// sort by ASC or DESC by user input
	@GetMapping("/priceOrder")
	public ResponseEntity<List<Mobile>> getMobilesByPriceOrder(@RequestParam(value = "order") String order) {
		try {
			List<Mobile> mobiles = mobileService.getMobilesByPriceOrder(order);
			return ResponseEntity.ok().body(mobiles);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

}
