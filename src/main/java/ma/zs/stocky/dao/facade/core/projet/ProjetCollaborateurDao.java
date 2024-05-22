package ma.zs.stocky.dao.facade.core.projet;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjetCollaborateurDao extends AbstractRepository<ProjetCollaborateur,Long>  {

    List<ProjetCollaborateur> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurRef(String ref);
    List<ProjetCollaborateur> findByProjetId(Long id);
    int deleteByProjetId(Long id);
    long countByProjetReference(String reference);


}
