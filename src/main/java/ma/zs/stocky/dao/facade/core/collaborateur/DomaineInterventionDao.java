package ma.zs.stocky.dao.facade.core.collaborateur;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import java.util.List;


@Repository
public interface DomaineInterventionDao extends AbstractRepository<DomaineIntervention,Long>  {
    DomaineIntervention findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW DomaineIntervention(item.id,item.libelle) FROM DomaineIntervention item")
    List<DomaineIntervention> findAllOptimized();

}
