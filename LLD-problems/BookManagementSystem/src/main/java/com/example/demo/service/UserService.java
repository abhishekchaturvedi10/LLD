package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> createUser(List<User> users) {
		return userRepository.saveAll(users);
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long id) {
		try {
			return userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User updateUser(User user) {
		User oldUser = userRepository.findById(user.getId()).orElse(null);
		if (oldUser != null) {
			oldUser.setName(user.getName());
			oldUser.setEmail(user.getEmail());
			System.out.println("Updated user :: " + oldUser.getId());
			return userRepository.save(oldUser);
		} else {
			System.out.println("User not found, saving new user");
			return userRepository.save(user);
		}
	}
	
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User removed :: " + id;
	}
}
