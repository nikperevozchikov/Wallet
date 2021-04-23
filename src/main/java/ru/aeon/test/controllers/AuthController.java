package ru.aeon.test.controllers;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.aeon.test.dto.UserDTO;
import ru.aeon.test.dto.response.JwtResponse;
import ru.aeon.test.dto.response.MessageResponse;
import ru.aeon.test.models.User;
import ru.aeon.test.repository.UserRepository;
import ru.aeon.test.security.jwt.JwtUtils;
import ru.aeon.test.service.UserDetailsImpl;
import ru.aeon.test.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;

//import ru.aeon.test.models.Role;
//import ru.aeon.test.repository.RoleRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
//    @Resource
//    ConsumerTokenServices tokenServices;
    @Autowired
AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
//    @Autowired
//    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    @ApiOperation(value = "login User", response = UserDTO.class)
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()));
                //roles));
    }

    @PostMapping("/signup")
    @ApiOperation(value = "create User Registr", response = UserDTO.class)
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO) {
        User saved;
        if (userRepository.existsByUsername(userDTO.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(userDTO.getId(),userDTO.getUserName(), userDTO.getEmail(),
                encoder.encode(userDTO.getPassword()),userDTO.getDateCreated(LocalDate.now())
              );
//        try {
//            saved = userService.save(UserMapper.dtoToDO(userDTO));
//        } catch (Exception ex) {
//            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE, null, ex);
//            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<UserDTO>(UserMapper.doToDTO(saved), HttpStatus.CREATED);

       // Set<String> strRoles = signUpRequest.getRole();
        //Set<Role> roles = new HashSet<>();

//        if (strRoles == null) {
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "mod":
//                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//
//                        break;
//                    default:
//                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }

       // user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


//    @RequestMapping(method = RequestMethod.POST, value = "/tokens/revoke/{tokenId:.*}")
//    @ResponseBody
//    public String revokeToken(@PathVariable String tokenId) {
//        tokenServices.revokeToken(tokenId);
//        return tokenId;
//    }
}
