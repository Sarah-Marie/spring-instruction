package com.bmdb.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmdb.business.Movie;


public interface MovieRepository extends JpaRepository<Movie, Integer> {
	List<Movie> findAllByRating(String rating);

}
