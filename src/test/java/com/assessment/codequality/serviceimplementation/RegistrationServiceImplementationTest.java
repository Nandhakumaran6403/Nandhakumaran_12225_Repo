package com.assessment.codequality.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.assessment.codequality.model.Registration;

@SpringBootTest
class RegistrationServiceImplementationTest {

	@Autowired
	RegistrationServiceImplementation registrationService;

	@Test
	void testAddRegistration() {
		Registration registration = new Registration(0, "suriya", "donator", "sss@gmail.com", "suriya123");

		assertEquals("addsuccess", registrationService.addRegistration(registration));
	}

	@Test
	void testAddRegistrationFailure() {
		Registration registration = null;

		assertEquals("addfailure", registrationService.addRegistration(registration));
	}

	@Test
	void testUpdateRegistration() {
		Registration obj = new Registration(6, "Suriyakumaran", "employee", "nnn@gmail.com", "nandha123");
		registrationService.updateRegistration(obj);
		obj.setName("Suriyakumaran");
		assertEquals("updatesuccess", registrationService.updateRegistration(obj));

		Registration retrievedRegistration = registrationService.getRegistration(6);
		assertNotNull(retrievedRegistration);
		assertEquals("Suriyakumaran", retrievedRegistration.getName());
	}

	@Test
	void testUpdateRegistrationNull() {
		Registration obj = null;
		assertEquals("updatefailure", registrationService.updateRegistration(obj));
	}

	@Test
	void testGetRegistration() {

		Registration retrievedRegistration = registrationService.getRegistration(8);
		assertNotNull(retrievedRegistration);
		assertEquals("ponraj", retrievedRegistration.getName());
		assertEquals("nandha123", retrievedRegistration.getPassword());
	}

	@Test
	void testGetRegistration_NotFound() {
		Registration retrievedRegistration = registrationService.getRegistration(999);
		assertNull(retrievedRegistration);
	}

	

	@Test
	void testDeleteRegistrationNotFound() {
		assertEquals("deletefailure", registrationService.deleteRegistrationById(1000));
	}

}
