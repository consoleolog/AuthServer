package com.example.authserver.repository;

import com.example.authserver.v1.model.*;
import com.example.authserver.v1.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testInsert(){
        Member member = Member.of(AccountState.normal, AuthProvider.local);
        System.out.println(member);

        MemberInfo memberInfo = MemberInfo.builder()
                .email("test@test.com")
                .displayName("displayName")
                .member(member)
                .build();
        MemberAuth pwdAuth = MemberAuth.builder()
                .loginId("loginId")
                .loginPwd("loginPwd")
                .member(member)
                .build();
        member.setMemberAuth(pwdAuth);
        member.setMemberInfo(memberInfo);
        accountRepository.save(member);
    }

}
