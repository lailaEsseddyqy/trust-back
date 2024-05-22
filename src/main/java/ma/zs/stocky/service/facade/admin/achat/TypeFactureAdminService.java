package ma.zs.stocky.service.facade.admin.achat;

import java.util.List;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import ma.zs.stocky.dao.criteria.core.achat.TypeFactureCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeFactureAdminService {







	TypeFacture create(TypeFacture t);

    TypeFacture update(TypeFacture t);

    List<TypeFacture> update(List<TypeFacture> ts,boolean createIfNotExist);

    TypeFacture findById(Long id);

    TypeFacture findOrSave(TypeFacture t);

    TypeFacture findByReferenceEntity(TypeFacture t);

    TypeFacture findWithAssociatedLists(Long id);

    List<TypeFacture> findAllOptimized();

    List<TypeFacture> findAll();

    List<TypeFacture> findByCriteria(TypeFactureCriteria criteria);

    List<TypeFacture> findPaginatedByCriteria(TypeFactureCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeFactureCriteria criteria);

    List<TypeFacture> delete(List<TypeFacture> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeFacture>> getToBeSavedAndToBeDeleted(List<TypeFacture> oldList, List<TypeFacture> newList);

    List<TypeFacture> importData(List<TypeFacture> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeFacture> importExcel(MultipartFile file);

}
