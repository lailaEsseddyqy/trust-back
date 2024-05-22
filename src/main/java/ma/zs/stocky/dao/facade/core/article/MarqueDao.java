package ma.zs.stocky.dao.facade.core.article;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.article.Marque;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.article.Marque;
import java.util.List;


@Repository
public interface MarqueDao extends AbstractRepository<Marque,Long>  {
    Marque findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Marque(item.id,item.libelle) FROM Marque item")
    List<Marque> findAllOptimized();

}
