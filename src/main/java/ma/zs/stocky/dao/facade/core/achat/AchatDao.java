package ma.zs.stocky.dao.facade.core.achat;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.achat.Achat;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AchatDao extends AbstractRepository<Achat,Long>  {

    List<Achat> findByEtatAchatId(Long id);
    int deleteByEtatAchatId(Long id);
    long countByEtatAchatCode(String code);
    List<Achat> findByFournisseurId(Long id);
    int deleteByFournisseurId(Long id);
    long countByFournisseurCode(String code);
    List<Achat> findByFactureId(Long id);
    int deleteByFactureId(Long id);
    long countByFactureId(Long id);


}
