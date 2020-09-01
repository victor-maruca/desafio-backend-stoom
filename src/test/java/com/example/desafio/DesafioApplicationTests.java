package com.example.desafio;

import com.example.desafio.Models.Address;
import com.example.desafio.Repositories.AddressRepository;
import com.example.desafio.Services.AddressService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DesafioApplicationTests {

	final static Address address1 = new Address(
			"Rua Zuneide Aparecida Marin",
			43,
			"complement1",
			"Barão Geraldo",
			"Campinas",
			"SP",
			"Brasil",
			123456
	);
	final static Address address2 = new Address(
			"street2",
			2,
			"complement2",
			"neighbourhood2",
			"city2",
			"state2",
			"country2",
			234567
	);
	final static Address address3 = new Address(
			"street3",
			3,
			"complement3",
			"neighbourhood3",
			"city3",
			"state3",
			"country3",
			345678
	);
	final static Address address4 = new Address(
			"street4",
			4,
			"complement4",
			"neighbourhood4",
			"city4",
			"state4",
			"country4",
			456789
	);

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	AddressRepository addressRepository;

	@BeforeAll
	void setup() {
		mongoTemplate.insert(address2);
		mongoTemplate.insert(address3);
		mongoTemplate.insert(address4);
	}

	@AfterAll
	void tearDown() {
		mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(address1.getId())), Address.class);
		mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(address2.getId())), Address.class);
		mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(address3.getId())), Address.class);
		mongoTemplate.findAndRemove(Query.query(Criteria.where("id").is(address4.getId())), Address.class);
	}

	@Test
	void testInsert() {
		assert addressRepository.findById(address1.getId()).isEmpty();
		try{
			AddressService.insertAddress(address1, addressRepository);
		} catch (Exception e) {
			assert false;
		}
		Optional<Address> a = addressRepository.findById(address1.getId());
		assert a.isPresent();
		assert a.get().getLatitude() != null;
		assert a.get().getLongitude() != null;
	}

	@Test
	void testPatch() {
		assert addressRepository.findById(address2.getId()).isPresent();
		assert addressRepository.findById(address2.getId()).get().getStreetName().equals(address2.getStreetName());

		Address patchedAddress = new Address(
				"new_street_name",
				2,
				"complement2",
				"neighbourhood2",
				"city2",
				"state2",
				"country2",
				234567
		);
		patchedAddress.setId(address2.getId());

		AddressService.patchAddressById(
				address2.getId(),
				patchedAddress,
				addressRepository
		);

		assert addressRepository.findById(address2.getId()).isPresent();
		assert addressRepository.findById(address2.getId()).get().getStreetName().equals(patchedAddress.getStreetName());
	}

	@Test
	void testGetById() {
		assert AddressService.getAddressById(address4.getId(), addressRepository) != null;
	}

	@Test
	void testGetAll() {
		assert AddressService.getAllAddresses(addressRepository).length >= 3;
	}

	@Test
	void testDelete() {
		AddressService.deleteAddressById(address3.getId(), addressRepository);

		assert addressRepository.findById(address3.getId()).isEmpty();
	}

}
