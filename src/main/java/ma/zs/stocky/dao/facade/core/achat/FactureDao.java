package ma.zs.stocky.dao.facade.core.achat;

import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.achat.Facture;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FactureDao extends AbstractRepository<Facture,Long>  {

    List<Facture> findByTypeFactureId(Long id);
    int deleteByTypeFactureId(Long id);
    long countByTypeFactureCode(String code);
    List<Facture> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);


}
