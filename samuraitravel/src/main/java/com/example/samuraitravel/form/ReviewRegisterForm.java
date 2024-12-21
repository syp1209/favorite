package com.example.samuraitravel.form;
import lombok.Data;

@Data
public class ReviewRegisterForm {
	//reviewテーブルへの登録に利用するForm
    private Integer review_id;
    
    private Integer house_id;    
        
    private Integer user_id;    
        
    private String score;    
    
    private String content;
    
    
    
}