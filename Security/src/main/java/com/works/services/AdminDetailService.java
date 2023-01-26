package com.works.services;

import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminDetailService implements UserDetailsService {

    final PasswordEncoder passwordEncoder;
    final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(username);
        if (optionalAdmin.isPresent() ) {
            Admin admin = optionalAdmin.get();
            return new User(
                    admin.getEmail(),
                    admin.getPassword(),
                    admin.getEnable(),
                    true,
                    true,
                    true,
                    parseRoles( admin.getRoles() )
            );
        }
        throw new UsernameNotFoundException("Not Found");
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for( Role role : roles ) {
            ls.add( new SimpleGrantedAuthority( role.getName() ));
        }
        return ls;
    }


    public ResponseEntity register(Admin admin ) {
        Map hm = new LinkedHashMap();
        Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsIgnoreCase(admin.getEmail());
        if (optionalAdmin.isPresent() ) {
            hm.put("status", false);
            hm.put("result", "Email in use");
            return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
        admin.setPassword( passwordEncoder.encode(admin.getPassword()) );
        adminRepository.save(admin);
        hm.put("status", false);
        hm.put("result", admin);
        return new ResponseEntity(hm, HttpStatus.OK);
    }



}
