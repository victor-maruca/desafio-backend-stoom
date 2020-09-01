package com.example.desafio.Services;

import com.example.desafio.Helpers.BadRequestException;
import com.example.desafio.Helpers.CoordinatesHelper;
import com.example.desafio.Models.Address;
import com.example.desafio.Repositories.AddressRepository;

public class AddressService {

    public static Address getAddressById(String id, AddressRepository addressRepository) {
        return addressRepository.getAddressById(id);
    }

    public static Address deleteAddressById(String id, AddressRepository addressRepository) {
        return addressRepository.deleteAddressById(id);
    }

    public static Address patchAddressById(String id, Address address, AddressRepository addressRepository) {
        Address oldAddress = getAddressById(id, addressRepository);
        oldAddress.setStreetName(address.getStreetName() != null ? address.getStreetName() : oldAddress.getStreetName());
        oldAddress.setNumber(address.getNumber() != -1 ? address.getNumber() : oldAddress.getNumber());
        oldAddress.setComplement(address.getComplement() != null ? address.getComplement() : oldAddress.getComplement());
        oldAddress.setNeighbourhood(address.getNeighbourhood() != null ? address.getNeighbourhood() : oldAddress.getNeighbourhood());
        oldAddress.setCity(address.getCity() != null ? address.getCity() : oldAddress.getCity());
        oldAddress.setState(address.getState() != null ? address.getState() : oldAddress.getState());
        oldAddress.setCountry(address.getCountry() != null ? address.getCountry() : oldAddress.getCountry());
        oldAddress.setZipcode(address.getZipcode() != -1 ? address.getZipcode() : oldAddress.getZipcode());
        oldAddress.setLatitude(address.getLatitude() != null ? address.getLatitude() : oldAddress.getLatitude());
        oldAddress.setLongitude(address.getLongitude() != null ? address.getLongitude() : oldAddress.getLongitude());
        return addressRepository.save(oldAddress);
    }

    public static Address[] getAllAddresses(AddressRepository addressRepository) {
        return addressRepository.findAll().toArray(Address[]::new);
    }

    public static Address insertAddress(Address address, AddressRepository addressRepository) throws BadRequestException {
        if(
                address.getStreetName() == null ||
                        address.getNumber() == -1 ||
                        address.getNeighbourhood() == null ||
                        address.getCity() == null ||
                        address.getState() == null ||
                        address.getCountry() == null ||
                        address.getZipcode() == -1
        ) {
            throw new BadRequestException();
        }

        if(address.getLatitude() == null || address.getLongitude() == null) {
            try{
                Double[] coordinates = CoordinatesHelper.getCoordinates(address);
                address.setLatitude(coordinates[0]);
                address.setLongitude(coordinates[1]);
            } catch(Exception e) {
                System.out.println("Não foi possível obter coordenadas geográficas para esse endereço.");
            }
        }
        return addressRepository.insert(address);
    }
}
