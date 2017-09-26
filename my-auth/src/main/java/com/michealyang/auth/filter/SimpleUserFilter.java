package com.michealyang.auth.filter;

import com.michealyang.auth.domain.User;
import com.michealyang.auth.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by michealyang on 16/3/20.
 */
@Component
public class SimpleUserFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(SimpleUserFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        logger.info("[doFilterInternal] uri=#{}", uri);

        UserUtil.unbindUser();

        //1. 对于静态资源，要通过
        if(uri.startsWith("/static/")
                || uri.startsWith("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        if("/favicon.ico".equals(uri)){
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        if(session != null) {
            logger.info("sessionId=#{}", session.getId());
        }
        User user = (User)WebUtils.getSessionAttribute(request, "user");
        logger.info("[doFilterInternal] user=#{}", user);

        UserUtil.bind(user);

        filterChain.doFilter(request, response);
    }
}
