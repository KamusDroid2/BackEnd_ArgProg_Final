package com.kamus2.amarok.Security.Controller;

import com.kamus2.amarok.Security.Dto.JwtDto;
import com.kamus2.amarok.Security.Dto.LoginUser;
import com.kamus2.amarok.Security.Dto.NewUser;
import com.kamus2.amarok.Security.Entity.Rol;
import com.kamus2.amarok.Security.Entity.User;
import com.kamus2.amarok.Security.Enums.RolName;
import com.kamus2.amarok.Security.Service.RolService;
import com.kamus2.amarok.Security.Service.UserService;
import com.kamus2.amarok.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "https://frontamarok2.web.app/")


public class AuthController {

    @Autowired
    PasswordEncoder passwordencoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
    if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Email no valido"), HttpStatus.BAD_REQUEST);
        
        if (userService.existsByNameUser(newUser.getNameUser())) 
            return new ResponseEntity(new Mensaje("El usuario ya existe"), HttpStatus.BAD_REQUEST);
        
        if (userService.existsByEmail(newUser.getEmail())) 
            return new ResponseEntity(new Mensaje("El email ya existe"), HttpStatus.BAD_REQUEST);
        
        User user = new User(newUser.getName(), newUser.getNameUser(), newUser.getEmail(), passwordencoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin")) 
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
         
        user.setRoles(roles);
        userService.save(user);
        
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos Errados"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUser.getNameUser(), loginUser.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

}


