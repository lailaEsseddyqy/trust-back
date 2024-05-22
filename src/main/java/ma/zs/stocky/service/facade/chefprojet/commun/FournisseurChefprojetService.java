package ma.zs.stocky.service.facade.chefprojet.commun;

import java.util.List;
import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface FournisseurChefprojetService {



    List<Fournisseur> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteId(Long id);




	Fournisseur create(Fournisseur t);

    Fournisseur update(Fournisseur t);

    List<Fournisseur> update(List<Fournisseur> ts,boolean createIfNotExist);

    Fournisseur findById(Long id);

    Fournisseur findOrSave(Fournisseur t);

    Fournisseur findByReferenceEntity(Fournisseur t);

    Fournisseur findWithAssociatedLists(Long id);

    List<Fournisseur> findAllOptimized();

    List<Fournisseur> findAll();

    List<Fournisseur> findByCriteria(FournisseurCriteria criteria);

    List<Fournisseur> findPaginatedByCriteria(FournisseurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FournisseurCriteria criteria);

    List<Fournisseur> delete(List<Fournisseur> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Fournisseur>> getToBeSavedAndToBeDeleted(List<Fournisseur> oldList, List<Fournisseur> newList);

    List<Fournisseur> importData(List<Fournisseur> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Fournisseur> importExcel(MultipartFile file);

}
