package ma.zs.stocky.dao.facade.core.article;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.article.Article;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ArticleDao extends AbstractRepository<Article,Long>  {

    List<Article> findByCategorieArticleId(Long id);
    int deleteByCategorieArticleId(Long id);
    long countByCategorieArticleCode(String code);
    List<Article> findByEtatArticleId(Long id);
    int deleteByEtatArticleId(Long id);
    long countByEtatArticleId(Long id);
    List<Article> findByMarqueId(Long id);
    int deleteByMarqueId(Long id);
    long countByMarqueCode(String code);
    List<Article> findByFournisseurId(Long id);
    int deleteByFournisseurId(Long id);
    long countByFournisseurCode(String code);


}
