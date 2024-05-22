package ma.zs.stocky.service.facade.comptable.achat;

import java.util.List;
import ma.zs.stocky.bean.core.achat.EtatAchat;
import ma.zs.stocky.dao.criteria.core.achat.EtatAchatCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtatAchatComptableService {







	EtatAchat create(EtatAchat t);

    EtatAchat update(EtatAchat t);

    List<EtatAchat> update(List<EtatAchat> ts,boolean createIfNotExist);

    EtatAchat findById(Long id);

    EtatAchat findOrSave(EtatAchat t);

    EtatAchat findByReferenceEntity(EtatAchat t);

    EtatAchat findWithAssociatedLists(Long id);

    List<EtatAchat> findAllOptimized();

    List<EtatAchat> findAll();

    List<EtatAchat> findByCriteria(EtatAchatCriteria criteria);

    List<EtatAchat> findPaginatedByCriteria(EtatAchatCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatAchatCriteria criteria);

    List<EtatAchat> delete(List<EtatAchat> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EtatAchat>> getToBeSavedAndToBeDeleted(List<EtatAchat> oldList, List<EtatAchat> newList);

    List<EtatAchat> importData(List<EtatAchat> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EtatAchat> importExcel(MultipartFile file);

}
