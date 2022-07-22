package com.lifeblog.blog.configuration;

import com.lifeblog.blog.entity.Authority;
import com.lifeblog.blog.entity.Role;
import com.lifeblog.blog.entity.User;
import com.lifeblog.blog.repository.AuthorityRepository;
import com.lifeblog.blog.repository.RoleRepository;
import com.lifeblog.blog.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class InitialSetup {


    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialSetup(AuthorityRepository authorityRepository, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent readyEvent) {

        Authority read_authority = createAuthority("READ_AUTHORITY");
        Authority write_authority = createAuthority("WRITE_AUTHORITY");
        Authority delete_authority = createAuthority("DELETE_AUTHORITY");

        Role role_user = createRole("ROLE_USER", new HashSet<>(List.of(read_authority)));
        Role role_admin = createRole("ROLE_ADMIN", new HashSet<>(List.of(read_authority, write_authority, delete_authority)));

        if (role_admin == null)
            return;

        User adminUser = new User();
        adminUser.setName("Admin User");
        adminUser.setUsername("adminuser");
        adminUser.setEmail("admin@localhost");
        adminUser.setPassword(passwordEncoder.encode("adminpassword"));
        adminUser.setRoles((new HashSet<>(List.of(role_admin))));
        userRepository.save(adminUser);


    }

    @Transactional
    Authority createAuthority(String authorityName) {
        Authority authority = authorityRepository.findByName(authorityName);
        if (authority == null) {
            authority = new Authority(authorityName);
            authorityRepository.save(authority);
        }
        return authority;
    }

    @Transactional
    Role createRole(String roleName, Set<Authority> authorities) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new Role(roleName, authorities);
            roleRepository.save(role);
        }
        return role;
    }
}
