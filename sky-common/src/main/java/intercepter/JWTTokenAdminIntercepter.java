package intercepter;

import constant.EmpConstant;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import property.JWTProperty;
import utils.BaseContext;
import utils.JWTUtils;

@Slf4j
@Component
public class JWTTokenAdminIntercepter implements HandlerInterceptor {
    @Autowired
    private JWTProperty jwtProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String token = request.getHeader("token");
        try {
            log.info("员工token是{}",token);
            Claims claims = JWTUtils.parseJWT(jwtProperty.getSecretKey(),token);
            long empId = Long.valueOf( claims.get(EmpConstant.EMP_ID_INTERCEPTER).toString());
            BaseContext.setId(empId);
            return true;
        }catch(Exception e)
        {
            response.setStatus(401);
            return false;
        }
    }
}
