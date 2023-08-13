package api.parkingcontrol.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import api.parkingcontrol.models.UserModel;
import api.parkingcontrol.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    public Optional<UserModel> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<UserModel> findById(UUID id){
        return userRepository.findById(id);
    }

    public Page<UserModel> findAll(Pageable pagaeable){
        return userRepository.findAll(pagaeable);
    }
}
