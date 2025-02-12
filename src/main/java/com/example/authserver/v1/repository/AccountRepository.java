package com.example.authserver.v1.repository;

import com.example.authserver.v1.model.Member;
import com.example.authserver.v1.model.MemberAuth;
import com.example.authserver.v1.model.MemberOAuth;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @FileName		: AccountRepository.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: 계정 관련 Repository
 **/
public interface AccountRepository extends JpaRepository<Member, Long> {

    @EntityGraph(attributePaths = {"memberInfo","memberAuth", "memberOAuth"})
    Optional<Member> findByMemberId(Long memberId);

    @EntityGraph(attributePaths = {"memberAuth"})
    Optional<Member> findByMemberAuth(MemberAuth memberAuth);
    @EntityGraph(attributePaths = {"memberOAuth"})
    Optional<Member> findByMemberOAuth(MemberOAuth memberOAuth);


}
