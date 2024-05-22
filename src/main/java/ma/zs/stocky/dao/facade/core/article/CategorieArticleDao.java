package ma.zs.stocky.dao.facade.core.article;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import java.util.List;


@Repository
public interface CategorieArticleDao extends AbstractRepository<CategorieArticle,Long>  {
    CategorieArticle findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieArticle(item.id,item.libelle) FROM CategorieArticle item")
    List<CategorieArticle> findAllOptimized();

}
