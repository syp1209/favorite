package com.example.samuraitravel.form;
import java.util.List;


public class ReviewDropdownForm {

	  private String selectedValue;
	  private List<String> scores;

	  // ゲッターとセッター
	  public String getSelectedScore() {
	    return selectedValue;
	  }

	  public void setSelectedScore(String selectedValue) {
	    this.selectedValue = selectedValue;
	  }

	  public List<String> getScores() {
	    return scores;
	  }

	  public void setScores(List<String> scores) {
	    this.scores = scores;
	  }

}
