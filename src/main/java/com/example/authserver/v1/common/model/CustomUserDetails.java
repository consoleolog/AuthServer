package com.example.authserver.v1.common.model;

import com.example.authserver.v1.model.AuthProvider;
import com.example.authserver.v1.model.MemberInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @FileName		: CustomUserDetails.java
 * @Author			: ACR
 * @Date			: 25. 2. 12.
 * @Description		: UserPrincipal 객체
 **/
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private Long memberId;
    private String displayName;
    private AuthProvider authProvider;
    private List<GrantedAuthority> authorities;

    protected CustomUserDetails(
            Long memberId,
            String displayName,
            AuthProvider authProvider,
            List<GrantedAuthority> authorities
    ) {
        this.memberId = memberId;
        this.displayName = displayName;
        this.authProvider = authProvider;
        this.authorities = authorities;
    }
    public static CustomUserDetails of(MemberInfo memberInfo){
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        return new CustomUserDetails(
                memberInfo.getMember().getMemberId(),
                memberInfo.getDisplayName(),
                memberInfo.getMember().getAuthProvider(),
                authorities);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return displayName;
    }

    @Override
    public String getName() {
        return memberId.toString();
    }
}
