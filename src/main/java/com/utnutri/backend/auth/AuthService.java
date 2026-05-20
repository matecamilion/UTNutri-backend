package com.utnutri.backend.auth;

import com.utnutri.backend.auth.dto.AuthResponse;
import com.utnutri.backend.auth.dto.LoginRequest;
import com.utnutri.backend.auth.dto.RegisterRequest;
import com.utnutri.backend.nutricionista.Nutricionista;
import com.utnutri.backend.nutricionista.NutricionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final NutricionistaRepository nutricionistaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (nutricionistaRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El username ya está en uso");
        }
        if (nutricionistaRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya está en uso");
        }

        Nutricionista nutri = Nutricionista.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .nombre(request.getNombre())
                .build();

        nutricionistaRepository.save(nutri);

        String token = jwtService.generateToken(nutri);
        return AuthResponse.builder()
                .token(token)
                .username(nutri.getUsername())
                .nombre(nutri.getNombre())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // Lanza excepción si las credenciales son inválidas
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        Nutricionista nutri = nutricionistaRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        String token = jwtService.generateToken(nutri);
        return AuthResponse.builder()
                .token(token)
                .username(nutri.getUsername())
                .nombre(nutri.getNombre())
                .build();
    }
}