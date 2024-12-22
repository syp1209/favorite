package com.example.samuraitravel.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 public Page<Review> findByHouseOrderByCreatedAtDesc(House review, Pageable pageable);
	 public Page<Review> findByUserOrderByCreatedAtDesc(User review, Pageable pageable);
	 public List<Review> findByHouseIdOrderByCreatedAtDesc(int houseId);
	 public List<Review> findByHouseIdAndUserId(int houseId, int userId);
	 

	
}