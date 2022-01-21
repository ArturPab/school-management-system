package pl.pabjan.schoolmanagementsystem.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.pabjan.schoolmanagementsystem.model.dto.LoginRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final String secret;
    private final long tokenExpirationTime;

    public AuthenticationFilter(String loginUrl, AuthenticationManager authenticationManager, String secret, long tokenExpirationTime) {
        this.authenticationManager = authenticationManager;
        this.secret = secret;
        this.tokenExpirationTime = tokenExpirationTime;
        setFilterProcessesUrl(loginUrl);
    }

    /**
     * @return authenticate with token
     * @throws AuthenticationException if login request is invalid
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            StringBuilder sb = getReqBody(request);
            ObjectMapper mapper = new ObjectMapper();
            LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getId() + "+" + loginRequest.getRole(), loginRequest.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InternalAuthenticationServiceException("Failed to parse authentication request body");
        }
    }

    /**
     * @param request with login request body as json
     * @return login request as string
     * @throws IOException when read of request will be wrong
     */
    private StringBuilder getReqBody(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb;
    }

    /**
     * when authentication is successful, then this method will be called
     *
     * @param authResult with user details
     * @throws IOException outputs response with id, token and user's role
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        String token = getJWTToken(user, request);
        response.getOutputStream().print("{\"id\": \"" + user.getUsername() + "\"," + "\"role\": \"" + user.getAuthorities() + "\"," + "\"token\": \"" + token + "\"}");
    }

    /**
     * when authentication is failed, then this method will be called
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }

    /**
     * @param user    details
     * @param request with url
     * @return jwt token where subject represents user's id and role
     */
    private String getJWTToken(User user, HttpServletRequest request) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        return JWT.create()
                .withSubject(user.getUsername() + "+" + user.getAuthorities().stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("Not found role")).getAuthority())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpirationTime))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

}
