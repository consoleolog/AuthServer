package com.example.authserver.v1.service;


import com.example.authserver.v1.common.model.CustomUserDetails;
import com.example.authserver.v1.global.error.exception.BadRequestException;
import com.example.authserver.v1.model.AccountState;
import com.example.authserver.v1.model.Member;
import com.example.authserver.v1.model.MemberAuth;
import com.example.authserver.v1.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberAuth memberAuth = MemberAuth.of(username);
        Member member = accountRepository.findByMemberAuth(memberAuth).orElseThrow();
        if (AccountState.block.equals(member.getAccountState())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "계정이 정지 상태입니다. 관리자에게 문의해 주세요.");
        }
        if (AccountState.stable.equals(member.getAccountState())) {
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "계정이 휴먼 상태입니다. 이메일 인증을 해주세요.");
        }
        if (member.getMemberAuth().getLoginFailCnt() > 9){
            throw new BadRequestException("비밀번호를 10회 이상 틀리셨습니다. 비밀번호를 재설정 해주세요.");
        }
        
        return CustomUserDetails.of(member.getMemberInfo());
    }

}
