package ma.zs.stocky.service.facade.comptable.projet;

import java.util.List;
import ma.zs.stocky.bean.core.projet.EtatAvancement;
import ma.zs.stocky.dao.criteria.core.projet.EtatAvancementCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtatAvancementComptableService {







	EtatAvancement create(EtatAvancement t);

    EtatAvancement update(EtatAvancement t);

    List<EtatAvancement> update(List<EtatAvancement> ts,boolean createIfNotExist);

    EtatAvancement findById(Long id);

    EtatAvancement findOrSave(EtatAvancement t);

    EtatAvancement findByReferenceEntity(EtatAvancement t);

    EtatAvancement findWithAssociatedLists(Long id);

    List<EtatAvancement> findAllOptimized();

    List<EtatAvancement> findAll();

    List<EtatAvancement> findByCriteria(EtatAvancementCriteria criteria);

    List<EtatAvancement> findPaginatedByCriteria(EtatAvancementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatAvancementCriteria criteria);

    List<EtatAvancement> delete(List<EtatAvancement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EtatAvancement>> getToBeSavedAndToBeDeleted(List<EtatAvancement> oldList, List<EtatAvancement> newList);

    List<EtatAvancement> importData(List<EtatAvancement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EtatAvancement> importExcel(MultipartFile file);

}
