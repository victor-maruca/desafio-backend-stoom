package com.example.desafio.Controllers;

import com.example.desafio.Helpers.BadRequestException;
import com.example.desafio.Models.Address;
import com.example.desafio.Repositories.AddressRepository;
import com.example.desafio.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/get-all")
    public Address[] getAllAddresses() { return AddressService.getAllAddresses(this.addressRepository); }

    @GetMapping("/get-address-by-id/{id}")
    public Address getAddressById(@PathVariable String id) {
        return AddressService.getAddressById(id, this.addressRepository);
    }

    @DeleteMapping("/delete-address-by-id/{id}")
    public Address deleteAddressById(@PathVariable String id) {
        return AddressService.deleteAddressById(id, this.addressRepository);
    }

    @PatchMapping("/patch-address-by-id/{id}")
    public Address patchAddressById(@PathVariable String id, @RequestBody Address address) {
        return AddressService.patchAddressById(id, address, this.addressRepository);
    }

    @PostMapping("/insert-address")
    public Address insertAddress(@RequestBody Address address) throws BadRequestException {
        return new AddressService().insertAddress(address, this.addressRepository);
    }

}
