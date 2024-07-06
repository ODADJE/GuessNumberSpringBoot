package sn.esmt.guess_the_number.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoggingInterceptor implements  HandlerInterceptor  {
  
  @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        System.out.println("PATH: " + request.getServletPath());
        System.out.println("METHOD: " + request.getMethod());
        System.out.println("ADDR: " + request.getRemoteAddr());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
