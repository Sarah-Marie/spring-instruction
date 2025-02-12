package com.bmdb.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmdb.business.Actor;

import com.bmdb.business.JsonResponse;

import com.bmdb.db.ActorRepository;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	private ActorRepository actorRepo;
	
	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<Actor> actors = actorRepo.findAll();
		if (actors.size()==0)  {
			jr = JsonResponse.getErrorInstance("No actors found.");
		}
		else  {
			jr = JsonResponse.getInstance(actors);
		}
		return jr;
	}
	
	
	
	@GetMapping("/list/{birthDate}")
	public JsonResponse findByBirthDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate birthDate) {
		//LocalDate id = LocalDate.parse(birthDate);
		JsonResponse jr = null; 
		List<Actor> actors = actorRepo.findAllByBirthDateBefore(birthDate);
		if (actors.size()==0)  {
			jr = JsonResponse.getErrorInstance("No actors found.");
		}
		else  {
			jr = JsonResponse.getInstance(actors);
		}
		return jr;
	}
	
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		
		JsonResponse jr = null;
		Optional<Actor> actor = actorRepo.findById(id);
		if (actor.isPresent())  {
			jr = JsonResponse.getInstance(actor.get());
		}
		else {
			jr = JsonResponse.getErrorInstance("No actor found for id: "+id);
		}
		
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse createActor(@RequestBody Actor a) {
		JsonResponse jr = null;
		
		try {
			a = actorRepo.save(a);
			jr = JsonResponse.getInstance(a);
		} 
		
		catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		}
		
		
		catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating actor: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}
	
	@PutMapping("/")
	public JsonResponse updateActor(@RequestBody Actor a) {
JsonResponse jr = null;
		
		try {
			a = actorRepo.save(a);
			jr = JsonResponse.getInstance(a);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating actor: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deleteActor(@PathVariable int id) {
JsonResponse jr = null;
		
		try {
			actorRepo.deleteById(id);
			jr = JsonResponse.getInstance(id);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting actor: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}


}
