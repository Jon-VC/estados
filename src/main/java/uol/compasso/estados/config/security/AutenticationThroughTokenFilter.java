package uol.compasso.estados.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uol.compasso.estados.modelo.User;
import uol.compasso.estados.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticationThroughTokenFilter extends OncePerRequestFilter {

    public AutenticationThroughTokenFilter(TokenService tokenService, UserRepository repository) {
        this.tokenService = tokenService;
    }

    private TokenService tokenService;
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = recuperateToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid){
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        Long idUser = tokenService.getIdUser(token);
        User user = repository.findById(idUser).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperateToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
