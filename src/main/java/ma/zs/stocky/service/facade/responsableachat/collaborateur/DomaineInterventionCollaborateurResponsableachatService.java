package ma.zs.stocky.service.facade.responsableachat.collaborateur;

import java.util.List;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCollaborateurCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DomaineInterventionCollaborateurResponsableachatService {



    List<DomaineInterventionCollaborateur> findByDomaineInterventionId(Long id);
    int deleteByDomaineInterventionId(Long id);
    long countByDomaineInterventionCode(String code);
    List<DomaineInterventionCollaborateur> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurRef(String ref);




	DomaineInterventionCollaborateur create(DomaineInterventionCollaborateur t);

    DomaineInterventionCollaborateur update(DomaineInterventionCollaborateur t);

    List<DomaineInterventionCollaborateur> update(List<DomaineInterventionCollaborateur> ts,boolean createIfNotExist);

    DomaineInterventionCollaborateur findById(Long id);

    DomaineInterventionCollaborateur findOrSave(DomaineInterventionCollaborateur t);

    DomaineInterventionCollaborateur findByReferenceEntity(DomaineInterventionCollaborateur t);

    DomaineInterventionCollaborateur findWithAssociatedLists(Long id);

    List<DomaineInterventionCollaborateur> findAllOptimized();

    List<DomaineInterventionCollaborateur> findAll();

    List<DomaineInterventionCollaborateur> findByCriteria(DomaineInterventionCollaborateurCriteria criteria);

    List<DomaineInterventionCollaborateur> findPaginatedByCriteria(DomaineInterventionCollaborateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DomaineInterventionCollaborateurCriteria criteria);

    List<DomaineInterventionCollaborateur> delete(List<DomaineInterventionCollaborateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DomaineInterventionCollaborateur>> getToBeSavedAndToBeDeleted(List<DomaineInterventionCollaborateur> oldList, List<DomaineInterventionCollaborateur> newList);

    List<DomaineInterventionCollaborateur> importData(List<DomaineInterventionCollaborateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DomaineInterventionCollaborateur> importExcel(MultipartFile file);

}
