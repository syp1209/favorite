package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewEditForm {
	
	private int reviewId;
	
    @NotBlank(message = "評価を５段階で再選択してください")
    private int selectedScore;
    
    @NotBlank(message = "レビュー内容を再入力してください")
    private String content;

    
	public int getReviewId() {
		return this.reviewId;
	}
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
    
    
    
    
	public int getSelectedScore() {
		return this.selectedScore;
	}

	public void setSelectedScore(int selectedScore) {
	    this.selectedScore = selectedScore;
	}

	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
