package ma.zs.stocky.dao.facade.core.achat;

import ma.zs.stocky.zynerator.repository.AbstractRepository;
import ma.zs.stocky.bean.core.achat.AchatDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AchatDetailDao extends AbstractRepository<AchatDetail,Long>  {

    List<AchatDetail> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);
    List<AchatDetail> findByAchatId(Long id);
    int deleteByAchatId(Long id);
    long countByAchatId(Long id);


}
