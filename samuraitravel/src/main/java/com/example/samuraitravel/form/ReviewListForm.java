package com.example.samuraitravel.form;

import java.sql.Timestamp;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;

import lombok.Data;

@Data
public class ReviewListForm {
	
	private Integer id;
	private House house;
	private User user;
	private String score;
	private String content;
	private Timestamp updatedAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
