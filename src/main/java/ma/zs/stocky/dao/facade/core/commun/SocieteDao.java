package ma.zs.stocky.dao.facade.core.commun;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.commun.Societe;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SocieteDao extends AbstractRepository<Societe,Long>  {



}
