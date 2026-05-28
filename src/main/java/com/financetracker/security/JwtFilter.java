package com.financetracker.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader("Authorization");
        String token = null, email = null;
        if(header!=null && header.startsWith("Bearer ")){
            token = header.substring(7);
            try{ email = jwtUtil.extractEmail(token); }catch(Exception ignored){}
        }
        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails ud = userDetailsService.loadUserByUsername(email);
            if(jwtUtil.validateToken(token)){
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req,res);
    }
}
