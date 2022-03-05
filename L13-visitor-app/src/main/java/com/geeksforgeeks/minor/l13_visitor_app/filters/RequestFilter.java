package com.geeksforgeeks.minor.l13_visitor_app.filters;

import org.apache.catalina.connector.RequestFacade;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@Component
public class RequestFilter extends HttpFilter {
    private static final String REQUEST_ID = "requestId";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(REQUEST_ID,((RequestFacade) servletRequest).getHeader(REQUEST_ID));
        filterChain.doFilter(servletRequest,servletResponse);
        MDC.clear();
    }
}
