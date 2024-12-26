package com.example.samuraitravel.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.security.UserDetailsImpl;

import ch.qos.logback.core.model.Model;

@Controller
public class FavoriteController {
	
	private HouseRepository houseRepository;
	

	public FavoriteController(HouseRepository houseRepository) {
		
		this.houseRepository = houseRepository;
		
	}
	
    //お気に入り一覧ページ
    @GetMapping("/favoriteList")
    public String showFavoriteList(
    		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		Model model) {
				
    	
    	//userDetailsImplを条件に
    	//紐づくFavoritesテーブルの一覧を取得する。
    	//List<FavoriteListForm> favorites = FavoriteService.findFavoritesByUserId(userDetailsImpl.getUser().getId());
        
        //House house = houseRepository.getReferenceById(houseId);
        //User user = userDetailsImpl.getUser();
    	
    	return null ;
    }
    
    
    //お気に入りリスト追加
    @GetMapping("/favorite/input")
    public String inputFavorites(
    		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		@RequestParam Integer houseId,
    		Model model) {
    	
		House house = houseRepository.getReferenceById(houseId);
		User user = userDetailsImpl.getUser();
		//model.addAttribute("house", house);
		//model.addAttribute("user", user);
		
		return "/favoriteList";
    }
    
    
    //お気に入りリスト削除
    @GetMapping("/favorite/delete")
    public String deleteFavorites(
    		@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
    		@RequestParam Integer houseId,
    		Model model) {
    	
    	
		House house = houseRepository.getReferenceById(houseId);
		User user = userDetailsImpl.getUser();
		
		//model.addAttribute("house", house);
		//model.addAttribute("user", user);
		
		//FavoritesRepository.deleteByHouseIdandUserId(houseId,userDetailsImpl.getUser().getId());
		return "/favoriteList";
		
    }
    
    }
    
//}
