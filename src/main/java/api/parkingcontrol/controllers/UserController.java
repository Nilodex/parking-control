package api.parkingcontrol.controllers;

import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import api.parkingcontrol.dtos.AuthenticationDto;
import api.parkingcontrol.dtos.LoginResponseDto;
import api.parkingcontrol.dtos.RegisterDto;
import api.parkingcontrol.models.UserModel;
import api.parkingcontrol.repository.RoleRepository;
import api.parkingcontrol.services.TokenService;
import api.parkingcontrol.services.UserService;
import jakarta.validation.Valid;

@Controller
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userServices;
    private final TokenService tokenService;

    public UserController(AuthenticationManager authenticationManager, UserService userServices, RoleRepository roleRepository, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.userServices = userServices;
        this.tokenService = tokenService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserModel>> getAllUser(@PageableDefault(page=0, size=10, sort="userId", direction = Sort.Direction.ASC) Pageable pagaeable){
        return ResponseEntity.status(HttpStatus.OK).body(userServices.findAll(pagaeable));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDto data){
        Optional<UserModel> userModelOptional = userServices.findByUsername(data.username());
        if(userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(data, userModel);
        userModel.setPassword(encryptedPassword);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(userModel));
    }
    
}
