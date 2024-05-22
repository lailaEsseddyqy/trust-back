package ma.zs.stocky.dao.facade.core.achat;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.achat.EtatAchat;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.achat.EtatAchat;
import java.util.List;


@Repository
public interface EtatAchatDao extends AbstractRepository<EtatAchat,Long>  {
    EtatAchat findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatAchat(item.id,item.libelle) FROM EtatAchat item")
    List<EtatAchat> findAllOptimized();

}
