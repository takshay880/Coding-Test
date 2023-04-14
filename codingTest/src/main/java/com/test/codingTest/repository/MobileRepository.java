package com.test.codingTest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.codingTest.model.Mobile;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {

	// find mobiles by brand, model, and color
	List<Mobile> findByBrandAndModelAndColor(String brand, String model, String color);

	// find mobiles by brand and color
	List<Mobile> findByBrandAndColor(String brand, String color);

	// find mobiles by brand and model
	List<Mobile> findByBrandAndModel(String brand, String model);

	// find mobiles by model and color
	List<Mobile> findByModelAndColor(String model, String color);

	// find mobiles by brand
	List<Mobile> findByBrand(String brand);

	// find mobiles by color
	List<Mobile> findByColor(String color);

	// find mobiles by model
	List<Mobile> findByModel(String model);

	// find mobiles by price between minPrice and maxPrice
	List<Mobile> findByPriceBetween(Double minPrice, Double maxPrice);

	// find mobiles by price, order by ascending or descending
	List<Mobile> findByOrderByPriceAsc();

	List<Mobile> findByOrderByPriceDesc();
}
