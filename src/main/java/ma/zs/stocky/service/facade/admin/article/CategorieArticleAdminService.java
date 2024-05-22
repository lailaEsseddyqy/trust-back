package ma.zs.stocky.service.facade.admin.article;

import java.util.List;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import ma.zs.stocky.dao.criteria.core.article.CategorieArticleCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface CategorieArticleAdminService {







	CategorieArticle create(CategorieArticle t);

    CategorieArticle update(CategorieArticle t);

    List<CategorieArticle> update(List<CategorieArticle> ts,boolean createIfNotExist);

    CategorieArticle findById(Long id);

    CategorieArticle findOrSave(CategorieArticle t);

    CategorieArticle findByReferenceEntity(CategorieArticle t);

    CategorieArticle findWithAssociatedLists(Long id);

    List<CategorieArticle> findAllOptimized();

    List<CategorieArticle> findAll();

    List<CategorieArticle> findByCriteria(CategorieArticleCriteria criteria);

    List<CategorieArticle> findPaginatedByCriteria(CategorieArticleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategorieArticleCriteria criteria);

    List<CategorieArticle> delete(List<CategorieArticle> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<CategorieArticle>> getToBeSavedAndToBeDeleted(List<CategorieArticle> oldList, List<CategorieArticle> newList);

    List<CategorieArticle> importData(List<CategorieArticle> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<CategorieArticle> importExcel(MultipartFile file);

}
