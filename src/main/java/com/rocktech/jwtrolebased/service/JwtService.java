package com.rocktech.jwtrolebased.service;

import com.rocktech.jwtrolebased.dao.UserDao;
import com.rocktech.jwtrolebased.entity.JwtRequest;
import com.rocktech.jwtrolebased.entity.JwtResponse;
import com.rocktech.jwtrolebased.entity.User;
import com.rocktech.jwtrolebased.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse generateToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUserName();
        String password = jwtRequest.getPassword();
        authenticate(username, password);
        final UserDetails userDetails = loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails);
        User user = userDao.findById(username).get();
        return new JwtResponse(user, token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if (userDao.findById(username).isPresent()){
            user =userDao.findById(username).get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    getAuthorities(user)
            );
        }
        else {
            throw new UsernameNotFoundException("Username is not valid");
        }

    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" +role.getRoleName())));
        return authorities;
    }

    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException d){
            throw new Exception("user is disabled");
        } catch (BadCredentialsException b){
            throw new Exception("Bad credentials from user");
        }
    }
}
