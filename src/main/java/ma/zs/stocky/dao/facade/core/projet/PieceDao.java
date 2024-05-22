package ma.zs.stocky.dao.facade.core.projet;

import org.springframework.data.jpa.repository.Query;
import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.projet.Piece;
import org.springframework.stereotype.Repository;
import ma.zs.stocky.bean.core.projet.Piece;
import java.util.List;


@Repository
public interface PieceDao extends AbstractRepository<Piece,Long>  {
    Piece findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Piece(item.id,item.code) FROM Piece item")
    List<Piece> findAllOptimized();

}
