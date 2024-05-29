package ma.zs.stocky.service;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ma.zs.stocky.service.impl.UserActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Component
public class LoginFilter implements Filter {

    @Autowired
    private UserActivityServiceImpl userActivityServic;
    private static final Logger logger = Logger.getLogger(LoginFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null && session.getAttribute("loginTime") == null) {
                session.setAttribute("loginTime", LocalDateTime.now());
                userActivityServic.logLogin(username);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code
    }

    @Override
    public void destroy() {
        // Cleanup code
    }
}
