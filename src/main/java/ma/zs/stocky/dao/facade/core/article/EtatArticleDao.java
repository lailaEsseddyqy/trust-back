package ma.zs.stocky.dao.facade.core.article;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.article.EtatArticle;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EtatArticleDao extends AbstractRepository<EtatArticle,Long>  {

    List<EtatArticle> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);

    @Query("SELECT NEW EtatArticle(item.id,item.libelle) FROM EtatArticle item")
    List<EtatArticle> findAllOptimized();

}
