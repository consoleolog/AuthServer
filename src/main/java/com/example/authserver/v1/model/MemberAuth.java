package com.example.authserver.v1.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @FileName		: MemberAuth.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 사용자가 비밀번호로 로그인시 쓰는 Entity
 **/
@ToString
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER_AUTH")
@Entity
public class MemberAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    private String loginId;
    private String loginPwd;
    private LocalDateTime lastLoginAt;
    private LocalDateTime loginFailAt;
    @Builder.Default
    private Integer loginFailCnt = 0;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;

    private MemberAuth(String loginId){
        this.loginId = loginId;
    }
    public static MemberAuth of(String loginId) {
        return new MemberAuth(loginId);
    }
}
