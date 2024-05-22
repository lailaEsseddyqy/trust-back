package ma.zs.stocky.service.facade.comptable.projet;

import java.util.List;
import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjetComptableService {



    List<Projet> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);
    List<Projet> findByPieceJointId(Long id);
    int deleteByPieceJointId(Long id);
    long countByPieceJointCode(String code);
    List<Projet> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);




	Projet create(Projet t);

    Projet update(Projet t);

    List<Projet> update(List<Projet> ts,boolean createIfNotExist);

    Projet findById(Long id);

    Projet findOrSave(Projet t);

    Projet findByReferenceEntity(Projet t);

    Projet findWithAssociatedLists(Long id);

    List<Projet> findAllOptimized();

    List<Projet> findAll();

    List<Projet> findByCriteria(ProjetCriteria criteria);

    List<Projet> findPaginatedByCriteria(ProjetCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjetCriteria criteria);

    List<Projet> delete(List<Projet> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Projet>> getToBeSavedAndToBeDeleted(List<Projet> oldList, List<Projet> newList);

    List<Projet> importData(List<Projet> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Projet> importExcel(MultipartFile file);

}
