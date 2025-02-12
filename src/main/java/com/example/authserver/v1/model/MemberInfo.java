package com.example.authserver.v1.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @FileName		: MemberInfo.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 사용자 정보 Entity
 **/
@ToString
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER_INFO")
@Entity
public class MemberInfo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;
    private String email;
    private String displayName;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Member member;
}
