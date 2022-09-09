package com.nirma.userservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nirma.userservice.entity.User;
import com.nirma.userservice.model.Bike;
import com.nirma.userservice.model.Car;
import com.nirma.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User> >getAll(){
		List<User> users=userService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();		
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User >getById(@PathVariable("id") int id){
		User user=userService.getUserById(id);
		if(user ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User >save(@RequestBody User user){
		User userNew=userService.save(user);
		if(userNew ==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userNew);
	}
	
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCars(@PathVariable("userId") Integer userId){
		User user=userService.getUserById(userId);
		if (userId ==null) {
			return ResponseEntity.notFound().build();
			
		}
		List<Car> cars=userService.getCars(userId);
		return ResponseEntity.ok(cars);
	}
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") Integer userId){
		User user=userService.getUserById(userId);
		if (userId ==null) {
			return ResponseEntity.notFound().build();
			
		}
		List<Bike> bikes=userService.getBykes(userId);
		return ResponseEntity.ok(bikes);
	}
	
	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
		Car newCar=userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/savebike")
	public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
		Bike newBike=userService.saveBike(bike);
		return ResponseEntity.ok(newBike);
	}
	
	@GetMapping("/getAll/{userId}")
	public ResponseEntity<Map<String,Object>> getAllUser(@PathVariable("userId") int userId){
		Map<String,Object> datos=userService.getUserandVehicles(userId);
		return ResponseEntity.ok(datos);
	}
	
	
}
