package api.parkingcontrol.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import api.parkingcontrol.models.RoleModel;
import api.parkingcontrol.repository.RoleRepository;

@Controller
@RequestMapping("/roles")
public class RoleController  {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RoleModel>> getAllRoles(){
        return ResponseEntity.status(HttpStatus.OK).body(roleRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRole(@PathVariable(value="id") UUID id){
        Optional<RoleModel> roleModelOptional = roleRepository.findById(id);
        if(roleModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(roleModelOptional.get());
    }
}
