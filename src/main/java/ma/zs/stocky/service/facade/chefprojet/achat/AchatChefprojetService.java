package ma.zs.stocky.service.facade.chefprojet.achat;

import java.util.List;
import ma.zs.stocky.bean.core.achat.Achat;
import ma.zs.stocky.dao.criteria.core.achat.AchatCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AchatChefprojetService {



    List<Achat> findByEtatAchatId(Long id);
    int deleteByEtatAchatId(Long id);
    long countByEtatAchatCode(String code);
    List<Achat> findByFournisseurId(Long id);
    int deleteByFournisseurId(Long id);
    long countByFournisseurCode(String code);
    List<Achat> findByFactureId(Long id);
    int deleteByFactureId(Long id);
    long countByFactureId(Long id);




	Achat create(Achat t);

    Achat update(Achat t);

    List<Achat> update(List<Achat> ts,boolean createIfNotExist);

    Achat findById(Long id);

    Achat findOrSave(Achat t);

    Achat findByReferenceEntity(Achat t);

    Achat findWithAssociatedLists(Long id);

    List<Achat> findAllOptimized();

    List<Achat> findAll();

    List<Achat> findByCriteria(AchatCriteria criteria);

    List<Achat> findPaginatedByCriteria(AchatCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AchatCriteria criteria);

    List<Achat> delete(List<Achat> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Achat>> getToBeSavedAndToBeDeleted(List<Achat> oldList, List<Achat> newList);

    List<Achat> importData(List<Achat> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Achat> importExcel(MultipartFile file);

}
