package toky.gestiondestock.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toky.gestiondestock.dao.utilisateur.UserRepository;
import toky.gestiondestock.exception.ErrorCodes;
import toky.gestiondestock.exception.NotFoundException;

import java.util.Collections;

@Service
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        toky.gestiondestock.model.utilisateur.User user =  userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Aucun utilisateur avec l'Email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND));
        return new User(user.getEmail(),user.getPassword(), Collections.emptyList());
    }
}
