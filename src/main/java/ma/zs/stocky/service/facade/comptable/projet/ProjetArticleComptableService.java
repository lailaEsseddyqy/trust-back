package ma.zs.stocky.service.facade.comptable.projet;

import java.util.List;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.dao.criteria.core.projet.ProjetArticleCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ProjetArticleComptableService {



    List<ProjetArticle> findByArticleId(Long id);
    int deleteByArticleId(Long id);
    long countByArticleId(Long id);
    List<ProjetArticle> findByProjetId(Long id);
    int deleteByProjetId(Long id);
    long countByProjetReference(String reference);




	ProjetArticle create(ProjetArticle t);

    ProjetArticle update(ProjetArticle t);

    List<ProjetArticle> update(List<ProjetArticle> ts,boolean createIfNotExist);

    ProjetArticle findById(Long id);

    ProjetArticle findOrSave(ProjetArticle t);

    ProjetArticle findByReferenceEntity(ProjetArticle t);

    ProjetArticle findWithAssociatedLists(Long id);

    List<ProjetArticle> findAllOptimized();

    List<ProjetArticle> findAll();

    List<ProjetArticle> findByCriteria(ProjetArticleCriteria criteria);

    List<ProjetArticle> findPaginatedByCriteria(ProjetArticleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProjetArticleCriteria criteria);

    List<ProjetArticle> delete(List<ProjetArticle> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<ProjetArticle>> getToBeSavedAndToBeDeleted(List<ProjetArticle> oldList, List<ProjetArticle> newList);

    List<ProjetArticle> importData(List<ProjetArticle> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<ProjetArticle> importExcel(MultipartFile file);

}
