package api.parkingcontrol.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import api.parkingcontrol.services.UserService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService){
        this.userService = userService;
    }
    
    //Realiza a autenticação do usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username " + username));
    }
    
}
