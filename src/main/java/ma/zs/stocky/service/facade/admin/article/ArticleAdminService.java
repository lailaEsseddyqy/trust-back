package ma.zs.stocky.service.facade.admin.article;

import java.util.List;
import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ArticleAdminService {



    List<Article> findByCategorieArticleId(Long id);
    int deleteByCategorieArticleId(Long id);
    long countByCategorieArticleCode(String code);
    List<Article> findByEtatArticleId(Long id);
    int deleteByEtatArticleId(Long id);
    long countByEtatArticleId(Long id);
    List<Article> findByMarqueId(Long id);
    int deleteByMarqueId(Long id);
    long countByMarqueCode(String code);
    List<Article> findByFournisseurId(Long id);
    int deleteByFournisseurId(Long id);
    long countByFournisseurCode(String code);




	Article create(Article t);


    Article update(Article t);

    List<Article> update(List<Article> ts,boolean createIfNotExist);

    Article findById(Long id);

    Article findOrSave(Article t);

    Article findByReferenceEntity(Article t);

    Article findWithAssociatedLists(Long id);

    List<Article> findAllOptimized();

    List<Article> findAll();

    List<Article> findByCriteria(ArticleCriteria criteria);

    List<Article> findPaginatedByCriteria(ArticleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ArticleCriteria criteria);

    List<Article> delete(List<Article> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Article>> getToBeSavedAndToBeDeleted(List<Article> oldList, List<Article> newList);

    List<Article> importData(List<Article> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Article> importExcel(MultipartFile file);

}
