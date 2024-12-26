package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.Favorites;
import com.example.samuraitravel.entity.Review;


public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {

	//部分一致検索
	public List<Review> findByUserId(int userId);
		
	
}
