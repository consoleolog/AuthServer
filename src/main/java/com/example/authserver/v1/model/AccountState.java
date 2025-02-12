package com.example.authserver.v1.model;

/**
 * @FileName		: AccountState.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 계정 상태 ( normal : 정상, stable : 대기 상태 => 계정 휴면 (인증 요구), block => 계정 정지 )
 **/
public enum AccountState {
    normal,
    stable,
    block
}
