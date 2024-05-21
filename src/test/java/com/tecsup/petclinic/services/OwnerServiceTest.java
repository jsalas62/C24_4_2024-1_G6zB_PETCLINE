package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
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
	public void testDeleteOwner() {
		// Crear un nuevo dueño
		Owner owner = new Owner();
		owner.setFirstName("Jane");
		owner.setLastName("Doe");
		owner.setAddress("456 Elm St");
		owner.setCity("Othertown");
		owner.setTelephone("9876543210");

		// Guardar el dueño
		Owner savedOwner = ownerService.saveOwner(owner);

		// Obtener el ID del dueño guardado
		Long ownerId = savedOwner.getId();

		// Eliminar el dueño por su ID
		ownerService.deleteOwner(ownerId);

		// Verificar que el dueño ya no exista
		assertThrows(OwnerNotFoundException.class, () -> ownerService.getOwnerById(ownerId));
	}

	@Test
	public void testUpdateOwner() {
		// Crear un nuevo dueño
		Owner owner = new Owner();
		owner.setFirstName("Alice");
		owner.setLastName("Smith");
		owner.setAddress("789 Oak St");
		owner.setCity("Sometown");
		owner.setTelephone("5551234567");

		// Guardar el dueño
		Owner savedOwner = ownerService.saveOwner(owner);

		// Modificar los datos del dueño
		String updatedFirstName = "UpdatedName";
		savedOwner.setFirstName(updatedFirstName);

		// Actualizar el dueño en la base de datos
		Owner updatedOwner = ownerService.saveOwner(savedOwner);

		// Obtener el dueño actualizado por su ID
		Long ownerId = updatedOwner.getId();
		Owner retrievedOwner = ownerService.getOwnerById(ownerId);

		// Verificar si los datos del dueño actualizado coinciden con los datos modificados
		assertEquals(updatedFirstName, retrievedOwner.getFirstName());
		// Agregar más aserciones según sea necesario para otros campos actualizados
	}
}
