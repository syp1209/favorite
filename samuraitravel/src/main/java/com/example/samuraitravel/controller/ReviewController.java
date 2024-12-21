package com.example.samuraitravel.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewEditForm;
import com.example.samuraitravel.form.ReviewInputForm;
import com.example.samuraitravel.form.ReviewListForm;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.ReviewRepository;
import com.example.samuraitravel.security.UserDetailsImpl;
import com.example.samuraitravel.service.ReviewService;

@Controller

public class ReviewController {

	private ReviewService reviewService;
	private HouseRepository houseRepository;
	private ReviewRepository reviewRepository;

	public ReviewController(
			ReviewService reviewService,
			HouseRepository houseRepository,
			ReviewRepository reviewRepository) {
		this.reviewService = reviewService;
		this.houseRepository = houseRepository;
		this.reviewRepository = reviewRepository;
	}
    
    //レビュー一覧ページ
    @GetMapping("/houses/review/list")
    public String showReviewList(
    		@RequestParam Integer houseId,
    		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		Model model) {
    	
    	//リクエスト情報に設定されているHouseIDを条件に
    	//紐づくレビュー情報の一覧を取得する。
        List<ReviewListForm> reviews = reviewService.findReviewsByHouseId(houseId);
        House house = houseRepository.getReferenceById(houseId);
        //User user = userDetailsImpl.getUser();
        
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("house", house);
       // model.addAttribute("user", user);//ログイン中のユーザのみにラジオボタンを表示する際に必要
        
        return "review/list";
    }
    
    
    private Map<Integer, String> getReviewList() {
    	
    	Map<Integer, String> reviewMap = new HashMap<>();
    	reviewMap.put(1, "★");
    	reviewMap.put(2, "★★");
    	reviewMap.put(3, "★★★");
    	reviewMap.put(4, "★★★★");
    	reviewMap.put(5, "★★★★★");
    	
   	 	return reviewMap;
    }
	

	
    //レビュー投稿ページ
	@GetMapping("/houses/review/list/input")
    public String inputReview(
    		@RequestParam Integer houseId,
    		@ModelAttribute House house,
    		Model model) {
        
        //Scoreにドロップダウンを設定したReviewInputFormをInput.htmlに渡す
        model.addAttribute("scoreList", getReviewList());
        model.addAttribute("house", house);
        model.addAttribute("houseId", houseId);
        
        return "review/input";
    }

	
    //レビュー投稿ページの「投稿」ボタンクリック→DB登録→レビュー一覧へリダイレクト
	@PostMapping("/houses/review/list/input/create")
    public String createReview(
    		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, 
    		@RequestParam Integer houseId,
    		@ModelAttribute ReviewInputForm reviewInputform,
    		Model model) {
		
		House house = houseRepository.getReferenceById(houseId);
		User user = userDetailsImpl.getUser();
		
		reviewService.create(reviewInputform.getSelectedScore(),reviewInputform.getContent(),houseId,user.getId());
		
		//レビュー一覧に表示する最新の情報を取得
        List<ReviewListForm> reviews = reviewService.findReviewsByHouseId(houseId);
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("house", house);
        
        return "review/list";
	}
    
	//レビュー編集ページ表示
	@GetMapping(path="/houses/review/list/edit", params="edit")
	public String editReview(
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
	    	@RequestParam Integer houseId,
	    	@RequestParam Integer reviewId,
	    	Model model) {
			
		//エラー箇所↓
		//ReviewEditForm reviewEditForm = new ReviewEditForm(review.getReviewId(), review.getSelectedScore(), review.getContent());
		
		//Reviewテーブルから取得した各項目の値をReviewEditFormに設定
		Optional<Review> review = reviewRepository.findById(reviewId);
		ReviewEditForm reviewEditForm = new ReviewEditForm();
		reviewEditForm.setReviewId(reviewId);
		reviewEditForm.setSelectedScore(review.get().getScore());
		reviewEditForm.setContent(review.get().getContent());
	            
	    //edit.htmlに渡す各値を設定
	    model.addAttribute("scoreList", getReviewList());
	    model.addAttribute("houseId", houseId);
	    model.addAttribute("review", review);
	    model.addAttribute("reviewEditForm",reviewEditForm);
	        
	    return "review/edit";
    }
   
    //レビューの編集内容をDBへ登録
	@PostMapping("/houses/review/list/edit/update")
	public String updateReview(
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
	    	@RequestParam Integer houseId,
	    	@RequestParam Integer reviewId,
	    	@ModelAttribute ReviewEditForm reviewEditform,
	    	Model model) {
	        
			
		reviewService.update(reviewEditform.getSelectedScore(),reviewEditform.getContent(),houseId, userDetailsImpl.getUser().getId(), reviewEditform.getReviewId());



		//更新後の最新情報で一覧を再表示
		List<ReviewListForm> reviews = reviewService.findReviewsByHouseId(houseId);
	    House house = houseRepository.getReferenceById(houseId);
	    User user = userDetailsImpl.getUser();
	    
	    model.addAttribute("reviews", reviews);
	    model.addAttribute("house", house);
	    model.addAttribute("user", user);//ログイン中のユーザのみにラジオボタンを表示する際に必要
	    
	    return "/review/list";
	}
    
	@GetMapping(path="/houses/review/list/edit", params="delete")
	public String deleteReview(
			@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
	    	@RequestParam Integer houseId,
	    	@RequestParam Integer reviewId,
	    	RedirectAttributes redirectAttributes,
	    	Model model) {
		
		//レビュー情報の削除
		reviewRepository.deleteById(reviewId);
		
		//削除後の最新情報で一覧を再表示
		List<ReviewListForm> reviews = reviewService.findReviewsByHouseId(houseId);
		House house = houseRepository.getReferenceById(houseId);
		User user = userDetailsImpl.getUser();
		
		model.addAttribute("reviews", reviews);
		model.addAttribute("house", house);
		model.addAttribute("user", user);//ログイン中のユーザのみにラジオボタンを表示する際に必要
		        
		redirectAttributes.addFlashAttribute("successMessage", "レビュー情報を削除しました。");
	    
	    return "/review/list";
	}
}