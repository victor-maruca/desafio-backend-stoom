package com.example.desafio.Repositories;

import com.example.desafio.Models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {
    Address getAddressById(String id);
    Address deleteAddressById(String id);
}