package sn.esmt.guess_the_number.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestNumberInterceptor implements HandlerInterceptor {
     @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String number = request.getParameter("number");

        Integer n = Integer.parseInt(number);

        if (number == null) {
            return true;
        }

        return true;
    }
}
