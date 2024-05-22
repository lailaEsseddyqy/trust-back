package ma.zs.stocky.service.facade.comptable.achat;

import java.util.List;
import ma.zs.stocky.bean.core.achat.AchatDetail;
import ma.zs.stocky.dao.criteria.core.achat.AchatDetailCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AchatDetailComptableService {



    List<AchatDetail> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);
    List<AchatDetail> findByAchatId(Long id);
    int deleteByAchatId(Long id);
    long countByAchatId(Long id);




	AchatDetail create(AchatDetail t);

    AchatDetail update(AchatDetail t);

    List<AchatDetail> update(List<AchatDetail> ts,boolean createIfNotExist);

    AchatDetail findById(Long id);

    AchatDetail findOrSave(AchatDetail t);

    AchatDetail findByReferenceEntity(AchatDetail t);

    AchatDetail findWithAssociatedLists(Long id);

    List<AchatDetail> findAllOptimized();

    List<AchatDetail> findAll();

    List<AchatDetail> findByCriteria(AchatDetailCriteria criteria);

    List<AchatDetail> findPaginatedByCriteria(AchatDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AchatDetailCriteria criteria);

    List<AchatDetail> delete(List<AchatDetail> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<AchatDetail>> getToBeSavedAndToBeDeleted(List<AchatDetail> oldList, List<AchatDetail> newList);

    List<AchatDetail> importData(List<AchatDetail> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<AchatDetail> importExcel(MultipartFile file);

}
