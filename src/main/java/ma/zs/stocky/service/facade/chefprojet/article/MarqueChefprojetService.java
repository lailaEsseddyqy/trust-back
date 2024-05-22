package ma.zs.stocky.service.facade.chefprojet.article;

import java.util.List;
import ma.zs.stocky.bean.core.article.Marque;
import ma.zs.stocky.dao.criteria.core.article.MarqueCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface MarqueChefprojetService {







	Marque create(Marque t);

    Marque update(Marque t);

    List<Marque> update(List<Marque> ts,boolean createIfNotExist);

    Marque findById(Long id);

    Marque findOrSave(Marque t);

    Marque findByReferenceEntity(Marque t);

    Marque findWithAssociatedLists(Long id);

    List<Marque> findAllOptimized();

    List<Marque> findAll();

    List<Marque> findByCriteria(MarqueCriteria criteria);

    List<Marque> findPaginatedByCriteria(MarqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MarqueCriteria criteria);

    List<Marque> delete(List<Marque> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Marque>> getToBeSavedAndToBeDeleted(List<Marque> oldList, List<Marque> newList);

    List<Marque> importData(List<Marque> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Marque> importExcel(MultipartFile file);

}
