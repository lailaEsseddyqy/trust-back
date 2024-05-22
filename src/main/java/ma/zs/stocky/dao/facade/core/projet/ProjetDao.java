package ma.zs.stocky.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.projet.Projet;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.projet.Projet;
import java.util.List;


@Repository
public interface ProjetDao extends AbstractRepository<Projet,Long>  {
    Projet findByReference(String reference);
    int deleteByReference(String reference);

    List<Projet> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Projet> findByPieceJointId(Long id);
    int deleteByPieceJointId(Long id);
    long countByPieceJointCode(String code);
    List<Projet> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);

    @Query("SELECT NEW Projet(item.id,item.reference) FROM Projet item")
    List<Projet> findAllOptimized();

}
