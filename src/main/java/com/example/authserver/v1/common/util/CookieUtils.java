package com.example.authserver.v1.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.Optional;

/**
 * @FileName		: CookieUtils.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 쿠키 관련 기능들
 **/
public class CookieUtils {

    /**
     * @MethodName		: getCookie
     * @Author			: ACR
     * @Description		: HttpServletRequest 에서 name 으로 쿠키 조회
     * @return : Optional<Cookie>
     **/
    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return Optional.of(cookie);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * @MethodName		: addCookie
     * @Author			: ACR
     * @Description		: HttpServletResponse 에 쿠키 저장 maxAge 는 분단위로 입력
     **/
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(maxAge * 60);
        response.addCookie(cookie);
    }

    /**
     * @MethodName		: removeCookie
     * @Author			: ACR
     * @Description		: 쿠키 삭제
     **/
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setPath("/");
                    cookie.setValue("");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * @MethodName		: serialize
     * @Author			: ACR
     * @Description		: 쿠키 직렬화
     * @return : String
     **/
    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    /**
     * @MethodName		: deserialize
     * @Author			: ACR
     * @Description		: T 클래스에 맞춰서 변환
     * @return : T
     **/
    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        byte[] data = Base64.getUrlDecoder().decode(cookie.getValue());
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return cls.cast(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
