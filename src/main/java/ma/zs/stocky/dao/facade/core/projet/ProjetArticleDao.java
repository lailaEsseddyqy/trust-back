package ma.zs.stocky.dao.facade.core.projet;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProjetArticleDao extends AbstractRepository<ProjetArticle,Long>  {

    List<ProjetArticle> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);
    List<ProjetArticle> findByProjetId(Long id);
    int deleteByProjetId(Long id);
    long countByProjetReference(String reference);


}
