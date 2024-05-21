package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String EXPECTED_NAME = "George";

		Owner owner = null;

		try {
			owner = ownerService.getOwnerById(ID);
		} catch (OwnerNotFoundException e) {
			fail(e.getMessage());
		}
		log.info("Owner: {}", owner);

		assertEquals(EXPECTED_NAME, owner.getFirstName());
	}

	@Test
	public void testGetAllOwners() {
		// Obtener la lista de todos los dueños
		List<Owner> owners = ownerService.getAllOwners();

		// Verificar que la lista no esté vacía
		assertFalse(owners.isEmpty());
	}


	@Test
	public void testSaveAndGetOwner() {
		// Crear un nuevo dueño
		Owner owner = new Owner();
		owner.setFirstName("John");
		owner.setLastName("Doe");
		owner.setAddress("123 Main St");
		owner.setCity("Anytown");
		owner.setTelephone("1234567890");

		// Guardar el dueño
		Owner savedOwner = ownerService.saveOwner(owner);

		// Obtener el ID del dueño guardado
		Long ownerId = savedOwner.getId();

		// Buscar el dueño por su ID
		Owner retrievedOwner = ownerService.getOwnerById(ownerId);

		// Verificar si el dueño recuperado es igual al dueño guardado
		assertEquals(savedOwner, retrievedOwner);
	}



}
