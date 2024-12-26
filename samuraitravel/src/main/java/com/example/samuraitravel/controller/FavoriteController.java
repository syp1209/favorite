package com.example.samuraitravel.controller;

import org.springframework.stereotype.Controller;

import com.example.samuraitravel.repository.HouseRepository;

@Controller
public class FavoriteController {
	
	private HouseRepository houseRepository;
	

	public FavoriteController(HouseRepository houseRepository) {
		
		this.houseRepository = houseRepository;
		
	}
	
    //お気に入り一覧ページ
    //@GetMapping("/houses/favoriteList")
    //public String showFavoriteList(
    //		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    //		Model model) {
    	
    	//userDetailsImplを条件に
    	//紐づくFavoritesテーブルの一覧を取得する。
       // List<FavoriteListForm> favorites = favoriteService.findFavoritesByUserId(userDetailsImpl.getUser().getId());
        
        //House house = houseRepository.getReferenceById(houseId);
        //User user = userDetailsImpl.getUser();
        
      
    }
    
//}
