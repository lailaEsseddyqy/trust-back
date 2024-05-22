package ma.zs.stocky.dao.facade.core.achat;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import java.util.List;


@Repository
public interface TypeFactureDao extends AbstractRepository<TypeFacture,Long>  {
    TypeFacture findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeFacture(item.id,item.libelle) FROM TypeFacture item")
    List<TypeFacture> findAllOptimized();

}
