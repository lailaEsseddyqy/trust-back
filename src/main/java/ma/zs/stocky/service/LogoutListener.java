
package ma.zs.stocky.service;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import ma.zs.stocky.service.impl.UserActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class LogoutListener implements HttpSessionListener {

    @Autowired
    private UserActivityServiceImpl userActivityService;
    private static final Logger logger = Logger.getLogger(LogoutListener.class.getName());


    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        String username = (String) event.getSession().getAttribute("username");
        if (username != null) {
            userActivityService.logLogout(username);
            logger.info("LogoutListener: User " + username + " logged out.");
        }
    }
}
