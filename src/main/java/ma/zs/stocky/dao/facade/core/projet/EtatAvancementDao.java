package ma.zs.stocky.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.projet.EtatAvancement;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.projet.EtatAvancement;
import java.util.List;


@Repository
public interface EtatAvancementDao extends AbstractRepository<EtatAvancement,Long>  {
    EtatAvancement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatAvancement(item.id,item.libelle) FROM EtatAvancement item")
    List<EtatAvancement> findAllOptimized();

}
