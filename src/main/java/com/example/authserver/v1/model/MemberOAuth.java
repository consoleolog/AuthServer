package com.example.authserver.v1.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @FileName		: MemberOAuth.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 사용자가 소셜 로그인할 때 사용하는 Entity
 **/
@ToString
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER_OAUTH")
@Entity
public class MemberOAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    private String externalId;
    private LocalDateTime lastLoginAt;
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;
}
