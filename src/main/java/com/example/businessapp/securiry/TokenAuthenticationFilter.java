//package com.example.businessapp.securiry;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
//
//@Slf4j
//@Component
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        try {
//            getJwtFromRequest(request)
//                    .flatMap(tokenProvider::validationTokenAndGetJWT)
//                    .ifPresent(jwt ->{
//                        String username = jwt.getBody().getSubject();
//                        UserDetails user = userDetailsService.loadUserByUsername(username);
//                        UsernamePasswordAuthenticationToken authentication =
//                                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    });
//        }catch (Exception e){
//            log.error("Cannot set user authentication", e);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private Optional<String> getJwtFromRequest(HttpServletRequest request){
//        // get string token from header
//        String tokenHeader = request.getHeader(TOKEN_HEADER);
//
//        // check token is text and start with bearer
//        if(StringUtils.hasText(tokenHeader) && tokenHeader.startsWith(TOKEN_PREFIX)){
//            return Optional.of(tokenHeader.replace(TOKEN_PREFIX, ""));
//        }
//        return Optional.empty();
//    }
//
//    public static final String TOKEN_HEADER = "Authorization";
//    public static final String TOKEN_PREFIX = "Bearer ";
//}
