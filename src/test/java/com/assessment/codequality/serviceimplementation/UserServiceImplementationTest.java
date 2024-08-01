package com.assessment.codequality.serviceimplementation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assessment.codequality.model.User;

@SpringBootTest
class UserServiceImplementationTest {

	@Autowired
	UserServiceImplementation userService;

	@Test
	void testSaveUser() {
		User obj = new User(0, "Nagarjun", "naga@gmail.com", "Madurai", 123456789, "Money", "");
		assertEquals("addsuccess", userService.saveAddUser(obj));
	}

	@Test
	void testSaveUserNull() {
		User obj = null;
		assertEquals("addfailure", userService.saveAddUser(obj));
	}

	@Test
	void testUpdateUser() {
		User obj = new User(1, "nandha", "nnn@gmail.com", "madurai", 12345678, "food and Money", "Waiting List");
		userService.updateIdUser(obj);
		obj.setName("Gokul");
		assertEquals("updatesuccess", userService.updateIdUser(obj));

		User retrievedUser = userService.getUserById(1);
		assertEquals("Gokul", retrievedUser.getName());
	}

	@Test
	void testUpdateUserNull() {
		User obj = null;
		assertEquals("updatefailure", userService.updateIdUser(obj));
	}

	@Test
	void testGetUserById() {

		User retrievedUser = userService.getUserById(7);
		assertNotNull(retrievedUser);
		assertEquals("Ponraj Marikannan", retrievedUser.getName());
		assertEquals("100", retrievedUser.getDonation());
		assertEquals(987654321, retrievedUser.getPhoneNumber());
	}

	@Test
	void testGetUserById_NotFound() {
		User retrievedUser = userService.getUserById(1000);
		assertNull(retrievedUser);
	}

	

	@Test
	void testDeleteUserNotFound() {

		assertEquals("deletefailure", userService.deleteIdUser(1000));
	}
}
