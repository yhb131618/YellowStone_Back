package com.yellowstone.yellowboard.service;

import org.springframework.http.ResponseEntity;

import com.yellowstone.yellowboard.dto.request.auth.SignInRequestDto;
import com.yellowstone.yellowboard.dto.request.auth.SignUpRequestDto;
import com.yellowstone.yellowboard.dto.response.auth.SignInResponseDto;
import com.yellowstone.yellowboard.dto.response.auth.SignUpResponseDto;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

}
