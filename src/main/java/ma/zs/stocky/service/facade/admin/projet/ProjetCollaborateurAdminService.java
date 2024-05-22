package ma.zs.stocky.service.facade.admin.projet;

import java.util.List;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCollaborateurCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjetCollaborateurAdminService {



    List<ProjetCollaborateur> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurRef(String ref);
    List<ProjetCollaborateur> findByProjetId(Long id);
    int deleteByProjetId(Long id);
    long countByProjetReference(String reference);




	ProjetCollaborateur create(ProjetCollaborateur t);

    ProjetCollaborateur update(ProjetCollaborateur t);

    List<ProjetCollaborateur> update(List<ProjetCollaborateur> ts,boolean createIfNotExist);

    ProjetCollaborateur findById(Long id);

    ProjetCollaborateur findOrSave(ProjetCollaborateur t);

    ProjetCollaborateur findByReferenceEntity(ProjetCollaborateur t);

    ProjetCollaborateur findWithAssociatedLists(Long id);

    List<ProjetCollaborateur> findAllOptimized();

    List<ProjetCollaborateur> findAll();

    List<ProjetCollaborateur> findByCriteria(ProjetCollaborateurCriteria criteria);

    List<ProjetCollaborateur> findPaginatedByCriteria(ProjetCollaborateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjetCollaborateurCriteria criteria);

    List<ProjetCollaborateur> delete(List<ProjetCollaborateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<ProjetCollaborateur>> getToBeSavedAndToBeDeleted(List<ProjetCollaborateur> oldList, List<ProjetCollaborateur> newList);

    List<ProjetCollaborateur> importData(List<ProjetCollaborateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<ProjetCollaborateur> importExcel(MultipartFile file);

}
