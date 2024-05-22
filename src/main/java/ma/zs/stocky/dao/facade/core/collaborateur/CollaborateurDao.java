package ma.zs.stocky.dao.facade.core.collaborateur;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import java.util.List;


@Repository
public interface CollaborateurDao extends AbstractRepository<Collaborateur,Long>  {
    Collaborateur findByRef(String ref);
    int deleteByRef(String ref);


    @Query("SELECT NEW Collaborateur(item.id,item.ref) FROM Collaborateur item")
    List<Collaborateur> findAllOptimized();

}
