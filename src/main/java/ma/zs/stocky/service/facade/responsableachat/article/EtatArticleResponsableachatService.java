package ma.zs.stocky.service.facade.responsableachat.article;

import java.util.List;
import ma.zs.stocky.bean.core.article.EtatArticle;
import ma.zs.stocky.dao.criteria.core.article.EtatArticleCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtatArticleResponsableachatService {



    List<EtatArticle> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);




	EtatArticle create(EtatArticle t);

    EtatArticle update(EtatArticle t);

    List<EtatArticle> update(List<EtatArticle> ts,boolean createIfNotExist);

    EtatArticle findById(Long id);

    EtatArticle findOrSave(EtatArticle t);

    EtatArticle findByReferenceEntity(EtatArticle t);

    EtatArticle findWithAssociatedLists(Long id);

    List<EtatArticle> findAllOptimized();

    List<EtatArticle> findAll();

    List<EtatArticle> findByCriteria(EtatArticleCriteria criteria);

    List<EtatArticle> findPaginatedByCriteria(EtatArticleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatArticleCriteria criteria);

    List<EtatArticle> delete(List<EtatArticle> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EtatArticle>> getToBeSavedAndToBeDeleted(List<EtatArticle> oldList, List<EtatArticle> newList);

    List<EtatArticle> importData(List<EtatArticle> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EtatArticle> importExcel(MultipartFile file);

}
