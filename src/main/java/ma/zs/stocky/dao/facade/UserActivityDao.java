package ma.zs.stocky.dao.facade;

import ma.zs.stocky.bean.UserActivity;
import ma.zs.stocky.zynerator.security.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserActivityDao extends JpaRepository<UserActivity ,Long> {
    UserActivity findTopByUsernameAndLogoutTimeIsNullOrderByLoginTimeDesc(String username);
}