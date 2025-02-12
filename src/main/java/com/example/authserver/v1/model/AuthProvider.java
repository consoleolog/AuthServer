package com.example.authserver.v1.model;

/**
 * @FileName		: AuthProvider.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 로그인 수단 ( local 이 비밀번호 사용 )
 **/
public enum AuthProvider {
    local,
    kakao,
    naver,
    google,
}
