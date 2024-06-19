package wepik.backend.global.config;

import static wepik.backend.global.exception.ErrorCode.UNAUTHORIZED_REQUEST;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();

        //조회는 로그인 필요X
        if (request.getMethod().equals("GET")) {
            return true;
        }

        if (session.getAttribute("admin") == null) {
            response.setStatus(401);
            return false;
        }

        return true;
    }
}