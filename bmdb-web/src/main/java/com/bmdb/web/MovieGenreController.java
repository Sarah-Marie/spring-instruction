package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bmdb.business.JsonResponse;
import com.bmdb.business.MovieGenre;
import com.bmdb.db.MovieGenreRepository;

@RestController
@RequestMapping("/movie-genres")
public class MovieGenreController {
	@Autowired
	private MovieGenreRepository moviegenreRepo;
	
	@GetMapping("/")
	public JsonResponse list() {
		JsonResponse jr = null;
		List<MovieGenre> moviegenres = moviegenreRepo.findAll();
		if (moviegenres.size()==0)  {
			jr = JsonResponse.getErrorInstance("No movie genres found.");
		}
		else  {
			jr = JsonResponse.getInstance(moviegenres);
		}
		return jr;
	}
	
	
	// Disclaimer  This method may not follow strict API Style Guide rules
	@GetMapping("/by-movie-id")
	public JsonResponse listByMovieId(@RequestParam int movieId) {
		JsonResponse jr = null;
		List<MovieGenre> moviegenres = moviegenreRepo.findAllByMovieId(movieId);
		if (moviegenres.size()==0)  {
			jr = JsonResponse.getErrorInstance("No movie genres found for movie id:  "+movieId+".");
		}
		else  {
			jr = JsonResponse.getInstance(moviegenres);
		}
		return jr;
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable int id) {
		
		JsonResponse jr = null;
		Optional<MovieGenre> moviegenre = moviegenreRepo.findById(id);
		if (moviegenre.isPresent())  {
			jr = JsonResponse.getInstance(moviegenre.get());
		}
		else {
			jr = JsonResponse.getErrorInstance("No movie genre found for id: "+id);
		}
		
		return jr;
	}
	
	@PostMapping("/")
	public JsonResponse createMovieGenre(@RequestBody MovieGenre mg) {
		JsonResponse jr = null;
		
		try {
			mg = moviegenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} 
		
		catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getErrorInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();
		}
		
		
		catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error creating movie genre: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}
	
	@PutMapping("/")
	public JsonResponse updateMovieGenre(@RequestBody MovieGenre mg) {
JsonResponse jr = null;
		
		try {
			mg = moviegenreRepo.save(mg);
			jr = JsonResponse.getInstance(mg);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error updating movie genre: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse deleteMovieGenre(@PathVariable int id) {
JsonResponse jr = null;
		
		try {
			moviegenreRepo.deleteById(id);
			jr = JsonResponse.getInstance(id);
		} catch (Exception e) {
			jr = JsonResponse.getErrorInstance("Error deleting movie genre: "+e.getMessage());
			e.printStackTrace();
		}
		
		
		return jr;
		
	}


}
