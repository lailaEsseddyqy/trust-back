package ma.zs.stocky.service.facade.admin.commun;

import java.util.List;
import ma.zs.stocky.bean.core.commun.Societe;
import ma.zs.stocky.dao.criteria.core.commun.SocieteCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface SocieteAdminService {







	Societe create(Societe t);

    Societe update(Societe t);

    List<Societe> update(List<Societe> ts,boolean createIfNotExist);

    Societe findById(Long id);

    Societe findOrSave(Societe t);

    Societe findByReferenceEntity(Societe t);

    Societe findWithAssociatedLists(Long id);

    List<Societe> findAllOptimized();

    List<Societe> findAll();

    List<Societe> findByCriteria(SocieteCriteria criteria);

    List<Societe> findPaginatedByCriteria(SocieteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SocieteCriteria criteria);

    List<Societe> delete(List<Societe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Societe>> getToBeSavedAndToBeDeleted(List<Societe> oldList, List<Societe> newList);

    List<Societe> importData(List<Societe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Societe> importExcel(MultipartFile file);

}
