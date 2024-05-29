package ma.zs.stocky.service.impl;

import ma.zs.stocky.bean.UserActivity;
import ma.zs.stocky.dao.facade.UserActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class UserActivityServiceImpl {
    private static final Logger logger = Logger.getLogger(UserActivityServiceImpl.class.getName());


    @Autowired
    private UserActivityDao dao;

    public void logLogin(String username) {
        logger.info("Logging login for user: " + username);
        UserActivity activity = new UserActivity();
        activity.setUsername(username);
        activity.setLoginTime(LocalDateTime.now());
        dao.save(activity);
        logger.info("Saved login activity for user: " + username);
    }

    public void logLogout(String username) {
        UserActivity activity = dao.findTopByUsernameAndLogoutTimeIsNullOrderByLoginTimeDesc(username);
        logger.info("Logging logout for user: " + username);
        if (activity != null) {
            activity.setLogoutTime(LocalDateTime.now());
            dao.save(activity);
            logger.info("Saved logout activity for user: " + username);
        } else {
            logger.warning("No login activity found for user: " + username);
        }
        }


    public Map<String, Long> getTimeSpentByRole() {
        List<UserActivity> activities = dao.findAll();
        logger.info("User activities: " + activities); // Log the activities
        Map<String, Long> timeSpentByRole = new HashMap<>();

        for (UserActivity activity : activities) {
            if (activity.getLogoutTime() != null) {
                long duration = Duration.between(activity.getLoginTime(), activity.getLogoutTime()).toMinutes();
                timeSpentByRole.merge(activity.getUsername(), duration, Long::sum);
            }
        }

        logger.info("Time spent by role calculated: " + timeSpentByRole); // Log the calculated time
        return timeSpentByRole;
    }
}
