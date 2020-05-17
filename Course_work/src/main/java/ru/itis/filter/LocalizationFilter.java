package ru.itis.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocalizationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getParameter("lang") != null) {
            String locale = servletRequest.getParameter("lang");
            if (locale.equals("en")) {
                ((HttpServletResponse) servletResponse).addCookie(new Cookie("localeInfo", "en"));
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (locale.equals("ru")) {
                ((HttpServletResponse) servletResponse).addCookie(new Cookie("localeInfo", "ru"));
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                servletResponse.getWriter().println("unrecognized lang");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
