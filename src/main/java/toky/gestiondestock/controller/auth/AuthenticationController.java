package toky.gestiondestock.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toky.gestiondestock.auth.AuthenticationRequest;
import toky.gestiondestock.auth.AuthenticationResponse;
import toky.gestiondestock.services.auth.AuthenticationUserService;
import toky.gestiondestock.utils.JwtUtils;


import static toky.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationUserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accesToken(jwt).build());
    }
}
