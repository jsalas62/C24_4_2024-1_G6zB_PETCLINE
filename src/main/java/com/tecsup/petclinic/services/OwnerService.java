package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;

import java.util.List;

public interface OwnerService {
    Owner saveOwner(Owner owner);
    List<Owner> getAllOwners();
    Owner getOwnerById(Long id);
    void deleteOwner(Long id);
}
