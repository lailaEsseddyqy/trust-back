package ma.zs.stocky.service.facade.admin.collaborateur;

import java.util.List;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DomaineInterventionAdminService {







	DomaineIntervention create(DomaineIntervention t);

    DomaineIntervention update(DomaineIntervention t);

    List<DomaineIntervention> update(List<DomaineIntervention> ts,boolean createIfNotExist);

    DomaineIntervention findById(Long id);

    DomaineIntervention findOrSave(DomaineIntervention t);

    DomaineIntervention findByReferenceEntity(DomaineIntervention t);

    DomaineIntervention findWithAssociatedLists(Long id);

    List<DomaineIntervention> findAllOptimized();

    List<DomaineIntervention> findAll();

    List<DomaineIntervention> findByCriteria(DomaineInterventionCriteria criteria);

    List<DomaineIntervention> findPaginatedByCriteria(DomaineInterventionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DomaineInterventionCriteria criteria);

    List<DomaineIntervention> delete(List<DomaineIntervention> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DomaineIntervention>> getToBeSavedAndToBeDeleted(List<DomaineIntervention> oldList, List<DomaineIntervention> newList);

    List<DomaineIntervention> importData(List<DomaineIntervention> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DomaineIntervention> importExcel(MultipartFile file);

}
