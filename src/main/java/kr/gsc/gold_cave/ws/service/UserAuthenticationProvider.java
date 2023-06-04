package kr.gsc.gold_cave.ws.service;

import kr.gsc.gold_cave.ws.model.SimpleUser;
import kr.gsc.gold_cave.ws.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication request) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, request, "Only UsernamePasswordAuthenticationToken is supported");

        UsernamePasswordAuthenticationToken result = null;
        final String userId = (String) request.getPrincipal();
        final String password = (String) request.getCredentials();

        final User user = userService.getUser(userId);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found : " + userId);
        }
//
//        if(!user.isEnabled()) {
//            throw new DisabledException("User is not enabled : " + userId);
//        }

        if ((password != null) && (password.length() > 0) && (passwordEncoder.matches(password, user.getPassword()))) {
            final List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getType().name()));

            result = new UsernamePasswordAuthenticationToken(userId, password, authorities);
            result.setDetails(getSimpleUser(user));
        } else {
            throw new BadCredentialsException("Bad credentials");
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private SimpleUser getSimpleUser(User user) {
        return SimpleUser.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .type(user.getType())
                .createdDatetime(user.getCreatedDatetime())
                .updatedDatetime(user.getUpdatedDatetime())
                .build();
    }
}
