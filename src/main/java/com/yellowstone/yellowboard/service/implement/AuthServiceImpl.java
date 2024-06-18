package com.yellowstone.yellowboard.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yellowstone.yellowboard.dto.request.auth.SignInRequestDto;
import com.yellowstone.yellowboard.dto.request.auth.SignUpRequestDto;
import com.yellowstone.yellowboard.dto.response.ResponseDto;
import com.yellowstone.yellowboard.dto.response.auth.SignInResponseDto;
import com.yellowstone.yellowboard.dto.response.auth.SignUpResponseDto;
import com.yellowstone.yellowboard.entity.UserEntity;
import com.yellowstone.yellowboard.provider.JwtProvider;
import com.yellowstone.yellowboard.repository.UserRepository;
import com.yellowstone.yellowboard.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail)
                return ResponseEntity.badRequest().body(SignUpResponseDto.duplicateEmail());

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname)
                return ResponseEntity.badRequest().body(SignUpResponseDto.duplicateNickname());

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber)
                return ResponseEntity.badRequest().body(SignUpResponseDto.duplicateTelNumber());

            // 비밀번호 인코딩
            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            // UserEntity 생성 및 저장
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            // 데이터베이스 오류 응답
            return SignUpResponseDto.success();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;

        try {

            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null)
                return SignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched)
                return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();

        }
        return SignInResponseDto.success(token);
    }

}
