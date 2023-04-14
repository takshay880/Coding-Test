package com.test.codingTest.service;

import com.test.codingTest.model.Mobile;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface MobileService {

	Mobile createMobile(Mobile mobile);

	void deleteMobile(Long id);

	List<Mobile> getAllMobiles();

	List<Mobile> getMobilesByFilters(String brand, String model, String color);

	List<Mobile> getMobilesByPriceRange(Double minPrice, Double maxPrice);

	List<Mobile> getMobilesByPriceOrder(String order);

}
