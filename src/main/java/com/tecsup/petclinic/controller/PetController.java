package com.tecsup.petclinic.controller;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

   @Autowired
    private PetService petService;

   @GetMapping
    public List<Pet> getAllPets () {
       return petService.findAll();
   }
}
