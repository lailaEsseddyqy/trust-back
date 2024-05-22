package ma.zs.stocky.dao.facade.core.collaborateur;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DomaineInterventionCollaborateurDao extends AbstractRepository<DomaineInterventionCollaborateur,Long>  {

    List<DomaineInterventionCollaborateur> findByDomaineInterventionId(Long id);
    int deleteByDomaineInterventionId(Long id);
    long countByDomaineInterventionCode(String code);
    List<DomaineInterventionCollaborateur> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurRef(String ref);


}
