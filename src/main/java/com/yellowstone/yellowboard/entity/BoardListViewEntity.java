package com.yellowstone.yellowboard.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {
    @Id
    private int boardNumber;
    private String title;
    private String content;
    private String viewCount;
    private int favoriteCount;
    private int commentCount;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

}
