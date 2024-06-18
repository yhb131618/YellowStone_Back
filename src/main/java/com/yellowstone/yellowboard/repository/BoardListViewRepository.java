package com.yellowstone.yellowboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yellowstone.yellowboard.entity.BoardListViewEntity;

public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

}
