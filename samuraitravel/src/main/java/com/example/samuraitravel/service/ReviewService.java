package com.example.samuraitravel.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewListForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.repository.UserRepository;

@Service
//@Transactionalを書くところ
public class ReviewService {
	//reviewテーブルの値をを利用・登録する
	//HouseテーブルのIDを利用する
	//userテーブルのIDを利用する
	
	
	private final ReviewRepository reviewRepository;
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	


	public ReviewService(ReviewRepository reviewRepository,
			HouseRepository houseRepository,
			UserRepository userRepository) {
		this.reviewRepository = reviewRepository;
		this.houseRepository = houseRepository;
		this.userRepository = userRepository;
	}

	//新規レビューをDBに保存 //ReviewInputForm ←新規投稿のForm
	@Transactional
	//レビューの登録処理を行うcreate()メソッドを定義　※コントローラから呼び出して使う
	public void create(int score, String content,int houseId, int userId) {
	    Review review = new Review();
        House house = houseRepository.getReferenceById(houseId);
        User user = userRepository.getReferenceById(userId);
        
	    review.setHouse(house);
	    review.setUser(user);
	    //フォームの入力内容を受け取る
	    review.setScore(score);
	    review.setContent(content);

	    reviewRepository.save(review);
	}
	
	//レビュー一覧ページに表示するデータを取得する
    public List<ReviewListForm> findReviewsByHouseId(int houseId) {
        return convertReviewList(reviewRepository.findByHouseIdOrderByCreatedAtDesc(houseId));
    }
    
    private List<ReviewListForm> convertReviewList(List<Review> reviewList){
    	
    	List<ReviewListForm> convReviewList = new ArrayList<ReviewListForm>();
    	
    	for(Review review : reviewList) {
    		
    		ReviewListForm reviewListForm = new ReviewListForm();
    		reviewListForm.setId(review.getId());
    		reviewListForm.setHouse(review.getHouse());
    		reviewListForm.setUser(review.getUser());
    		reviewListForm.setContent(review.getContent());
    		reviewListForm.setScore(convertScore(review.getScore()));
    		reviewListForm.setUpdatedAt(review.getUpdatedAt());
    		
    		convReviewList.add(reviewListForm);
    	}
    	
    	return convReviewList;
    }

    private String convertScore(int score) {
    	
    	String retScoreStr= switch(score) {
    		case 1 -> retScoreStr="★";
    		case 2 -> retScoreStr="★★";
    		case 3 -> retScoreStr="★★★";
    		case 4 -> retScoreStr="★★★★";
    		case 5 -> retScoreStr="★★★★★";
    		default -> retScoreStr="★";
    	};
    	return retScoreStr;
    		
    }
    
    
	@Transactional
	//レビューの編集処理を行うupdate()メソッドを定義　※コントローラから呼び出して使う
	public void update(int score, String content,int houseId, int userId, int reviewId) {
	    Review review = new Review();
        House house = houseRepository.getReferenceById(houseId);
        User user = userRepository.getReferenceById(userId);
        
        review.setId(reviewId);//	編集するレビューのIDを取得し特定する
	    review.setHouse(house);
	    review.setUser(user);
	    //フォームの入力内容を受け取る
	    review.setScore(score);
	    review.setContent(content);

	    reviewRepository.save(review);
	}
}