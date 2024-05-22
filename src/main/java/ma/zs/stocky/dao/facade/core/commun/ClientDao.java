package ma.zs.stocky.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.commun.Client;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.commun.Client;
import java.util.List;


@Repository
public interface ClientDao extends AbstractRepository<Client,Long>  {
    Client findByCode(String code);
    int deleteByCode(String code);

    List<Client> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteId(Long id);

    @Query("SELECT NEW Client(item.id,item.code) FROM Client item")
    List<Client> findAllOptimized();

}
