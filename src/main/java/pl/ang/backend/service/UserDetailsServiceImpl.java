package pl.ang.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pl.ang.backend.model.Role;
import pl.ang.backend.model.User;
import pl.ang.backend.repository.RoleRepository;
import pl.ang.backend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Użytkownik: " + username + " nie istnieje");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("USER"));
        user.setRoles(roles);
        user.setPassword(bCryptEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> saveAll(List<User> users) {
        for(User user : users) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("USER"));
            user.setRoles(roles);
            user.setUsername(bCryptEncoder.encode((user.getFirstName().charAt(0) + user.getLastName()).toLowerCase()));
            user.setPassword(bCryptEncoder.encode((user.getFirstName().charAt(0) + user.getLastName()).toLowerCase())); //dodamy jakis random number, na razie dla ulatwienia zostawiam taki jak login
        }

        return userRepository.saveAll(users);
    }
}
