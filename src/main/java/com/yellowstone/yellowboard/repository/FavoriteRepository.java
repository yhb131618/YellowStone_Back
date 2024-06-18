package com.yellowstone.yellowboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yellowstone.yellowboard.entity.FavoriteEntity;
import com.yellowstone.yellowboard.entity.primaryKey.FavoritePk;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

}
