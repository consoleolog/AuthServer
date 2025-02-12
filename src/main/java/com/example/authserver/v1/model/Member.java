package com.example.authserver.v1.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @FileName		: Member.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 사용자 Entity
 **/
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private AccountState accountState;

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAuth memberAuth;

    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberOAuth memberOAuth;

    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberInfo memberInfo;

    private Member(AccountState accountState, AuthProvider authProvider) {
        this.accountState = accountState;
        this.authProvider = authProvider;
    }
    public static Member of(AccountState accountState, AuthProvider authProvider) {
        return new Member(accountState, authProvider);
    }

}
