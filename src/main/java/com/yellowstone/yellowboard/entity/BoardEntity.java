package com.yellowstone.yellowboard.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;

    private String title;
    private String content;
    private String writeDatetime; // 추천: LocalDateTime으로 변경
    private int favoriteCount;
    private int commentCount;
    private int viewCount;
    private String writerEmail;
}
