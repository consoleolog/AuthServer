package com.example.authserver.v1.common.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @FileName		: ApiResponse.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: Response 에 담을 데이터
 **/
@AllArgsConstructor
@Setter
public class ApiResponse {
    private boolean success;
    private String message;
    private Map<String, Object> data;
}
