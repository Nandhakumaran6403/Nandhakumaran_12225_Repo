package com.assessment.codequality.serviceimplementation;

import com.assessment.codequality.model.Request;
import com.assessment.codequality.repository.RequestRepository;
import com.assessment.codequality.service.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RequestServiceImplementationTest {

	@Autowired
	RequestRepository requestRepository;
	@Autowired
	RequestService requestService;

	@Test
	void testGetRequestById() {
		int id = 9;
		Request request = new Request();
		requestRepository.saveRequest(request);
		String result = null;
		if (requestService.getRequestById(id) != null) {
			result = "success";
		} else {
			result = "failure";
		}
		assertEquals("success", result);
	}

	@Test
	void testGetRequestByIdWithNullRequest() {
		int id = 999;

		String result = null;
		if (requestService.getRequestById(id) != null) {
			result = "success";
		} else {
			result = "failure";
		}

		assertEquals("failure", result);
	}

	

	@Test
	void testDeleteIdRequestWithNonExistingRequest() {
		int id = 1000;

		String result = requestService.deleteIdRequest(id);

		assertEquals("deletefailure", result);
	}
}