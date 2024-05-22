package ma.zs.stocky.service.facade.responsableachat.collaborateur;

import java.util.List;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import ma.zs.stocky.dao.criteria.core.collaborateur.CollaborateurCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CollaborateurResponsableachatService {







	Collaborateur create(Collaborateur t);

    Collaborateur update(Collaborateur t);

    List<Collaborateur> update(List<Collaborateur> ts,boolean createIfNotExist);

    Collaborateur findById(Long id);

    Collaborateur findOrSave(Collaborateur t);

    Collaborateur findByReferenceEntity(Collaborateur t);

    Collaborateur findWithAssociatedLists(Long id);

    List<Collaborateur> findAllOptimized();

    List<Collaborateur> findAll();

    List<Collaborateur> findByCriteria(CollaborateurCriteria criteria);

    List<Collaborateur> findPaginatedByCriteria(CollaborateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CollaborateurCriteria criteria);

    List<Collaborateur> delete(List<Collaborateur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Collaborateur>> getToBeSavedAndToBeDeleted(List<Collaborateur> oldList, List<Collaborateur> newList);

    List<Collaborateur> importData(List<Collaborateur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Collaborateur> importExcel(MultipartFile file);

}
