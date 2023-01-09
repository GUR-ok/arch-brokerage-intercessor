package ru.gur.archintercessor.web.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {

    private final RequestScopedDataProvider requestScopedDataProvider;
    private final SessionScopedDataProvider sessionScopedDataProvider;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
                                    final HttpServletResponse httpServletResponse,
                                    final FilterChain filterChain) throws ServletException, IOException {
        System.out.println("!!! HEADERS");
        Enumeration headerNames = httpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = httpServletRequest.getHeader(key);
            logger.info(key + " " + value);
        }

        requestScopedDataProvider.setProfileId(
                Optional.ofNullable(httpServletRequest.getHeader("x-custom"))
                        .or(() -> Optional.ofNullable(httpServletRequest.getHeader("profileId")))
                        .map(UUID::fromString)
                        .orElseThrow(() -> new RuntimeException("ProfilId is null")));
        sessionScopedDataProvider.setProfileId(
                Optional.ofNullable(httpServletRequest.getHeader("x-custom"))
                        .or(() -> Optional.ofNullable(httpServletRequest.getHeader("profileId")))
                        .map(UUID::fromString)
                        .orElseThrow(() -> new RuntimeException("ProfilId is null")));

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
