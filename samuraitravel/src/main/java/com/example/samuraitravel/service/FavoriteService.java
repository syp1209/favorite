package com.example.samuraitravel.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.Favorites;
import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.repository.FavoritesRepository;
import com.example.samuraitravel.repository.HouseRepository;
import com.example.samuraitravel.repository.UserRepository;

public class FavoriteService {	
	private final HouseRepository houseRepository;
	private final UserRepository userRepository;
	private final FavoritesRepository favoritesRepository;
	
	public FavoriteService(
		FavoritesRepository favoritesRepository,
		HouseRepository houseRepository,
		UserRepository userRepository
		) {

			this.favoritesRepository = favoritesRepository;
			this.houseRepository = houseRepository;
			this.userRepository = userRepository;
			}
	

	
	
	//お気に入り一覧ページに表示するデータを取得する
    //public List<FavoriteListForm> findFavoritesByUserId(int UserId) {
    //    return getFavoriteHouseList(FavoritesRepository.findByUserId(UserId));
    //}
    
    
  
    
    //private getFavoriteHouseList(List<Review> reviewList){
    	

    //	}
    	
    //	return getFavoriteHouseList;
    //}
    
	//Favoriteテーブルに追加する
	@Transactional
	public void input(int houseId, int userId) {
	    Favorites favorites = new Favorites();
        House house = houseRepository.getReferenceById(houseId);
        User user = userRepository.getReferenceById(userId);
        
        favorites.setHouse(house);
        favorites.setUser(user);

        favoritesRepository.save(favorites);
	}
}
