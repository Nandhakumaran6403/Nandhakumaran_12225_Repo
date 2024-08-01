package com.assessment.codequality.serviceimplementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.codequality.model.User;
import com.assessment.codequality.repository.UserRepository;
import com.assessment.codequality.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

	private final UserRepository repo;

	public UserServiceImplementation(UserRepository repo) {
		this.repo = repo;
	}

	@Override
	public String saveAddUser(User user) {

		if (user != null) {
			repo.saveUser(user);
			return "addsuccess";
		} else {
			return "addfailure";
		}

	}

	public User getUserById(int id) {
			return repo.findUserById(id);
	}

	@Override
	public List<User> getAllUsers() {

		return repo.findAllUsers();
	}

	@Override
	public String updateIdUser(User user) {

		if (user != null) {
			repo.updateUser(user);
			return "updatesuccess";
		} else {
			return "updatefailure";
		}

	}

	@Override
	public String deleteIdUser(int id) {

		if (repo.findUserById(id) != null) {
			repo.deleteUser(id);
			return "deletesuccess";
		} else {
			return "deletefailure";
		}

	}
	
	
	@Override
	public User getUserFind() {

		return repo.find();
	}
}
