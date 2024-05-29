package ma.zs.stocky.ws.facade.admin;

import ma.zs.stocky.service.impl.UserActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserActivityServiceImpl userActivityService;
    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    @GetMapping("/time-spent-by-role")
    public  ResponseEntity<Map<String, Long>> getTimeSpentByRole() {
        Map<String, Long> timeSpent = userActivityService.getTimeSpentByRole();
        logger.info("Time spent by role: " + timeSpent);
        return ResponseEntity.ok(timeSpent);
    }
}
