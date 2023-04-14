package com.test.codingTest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.codingTest.model.Mobile;
import com.test.codingTest.repository.MobileRepository;

@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileRepository mobileRepository;

	// save one mobile
	@Override
	public Mobile createMobile(Mobile mobile) {
		return mobileRepository.save(mobile);
	}

	// delete one mobile
	@Override
	public void deleteMobile(Long id) {
		mobileRepository.deleteById(id);
	}

	// get all mobiles
	@Override
	public List<Mobile> getAllMobiles() {
		return mobileRepository.findAll();
	}

	// filtered mobiles
	@Override
	public List<Mobile> getMobilesByFilters(String brand, String model, String color) {
		Stream<Mobile> mobileStream = getAllMobiles().stream();

		if (brand != null && !brand.trim().isEmpty()) {
			mobileStream = mobileStream.filter(m -> m.getBrand().equalsIgnoreCase(brand));

		}
		if (model != null && !model.trim().isEmpty()) {
			mobileStream = mobileStream.filter(m -> m.getModel().equalsIgnoreCase(model));
		}
		if (color != null && !color.trim().isEmpty()) {
			mobileStream = mobileStream.filter(m -> m.getColor().equalsIgnoreCase(color));
		}

		return mobileStream.collect(Collectors.toList());
	}

	// filter by price range
	@Override
	public List<Mobile> getMobilesByPriceRange(Double minPrice, Double maxPrice) {
		return mobileRepository.findByPriceBetween(minPrice, maxPrice);
	}

	// order by
	@Override
	public List<Mobile> getMobilesByPriceOrder(String order) {
		if (order.equalsIgnoreCase("ASC")) {
			return mobileRepository.findByOrderByPriceAsc();
		} else if (order.equalsIgnoreCase("DESC")) {
			return mobileRepository.findByOrderByPriceDesc();
		} else {
			throw new IllegalArgumentException("Order should be either 'ASC' or 'DESC'");
		}
	}
}
