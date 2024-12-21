package com.example.samuraitravel.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewInputForm {
    @NotBlank(message = "評価を５段階で選択してください")
    private int selectedScore;
    
    @NotBlank(message = "レビュー内容を入力してください")
    private String content;

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
