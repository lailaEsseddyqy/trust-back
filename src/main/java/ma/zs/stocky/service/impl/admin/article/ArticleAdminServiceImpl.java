package ma.zs.stocky.service.impl.admin.article;


import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.dao.facade.core.projet.ProjetDao;
import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;
import ma.zs.stocky.dao.facade.core.article.ArticleDao;
import ma.zs.stocky.dao.specification.core.article.ArticleSpecification;
import ma.zs.stocky.service.facade.admin.article.ArticleAdminService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.stocky.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.stocky.service.facade.admin.article.EtatArticleAdminService ;
import ma.zs.stocky.service.facade.admin.commun.FournisseurAdminService ;
import ma.zs.stocky.service.facade.admin.article.MarqueAdminService ;
import ma.zs.stocky.service.facade.admin.article.CategorieArticleAdminService ;

@Service
public class ArticleAdminServiceImpl implements ArticleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Article update(Article t) {
        Article loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Article.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Article findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Article findOrSave(Article t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Article result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Article> importData(List<Article> items) {
        List<Article> list = new ArrayList<>();
        for (Article t : items) {
            Article founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Article> findAll() {
        return dao.findAll();
    }

    public List<Article> findByCriteria(ArticleCriteria criteria) {
        List<Article> content = null;
        if (criteria != null) {
            ArticleSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ArticleSpecification constructSpecification(ArticleCriteria criteria) {
        ArticleSpecification mySpecification =  (ArticleSpecification) RefelexivityUtil.constructObjectUsingOneParam(ArticleSpecification.class, criteria);
        return mySpecification;
    }

    public List<Article> findPaginatedByCriteria(ArticleCriteria criteria, int page, int pageSize, String order, String sortField) {
        ArticleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ArticleCriteria criteria) {
        ArticleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Article> findByCategorieArticleId(Long id){
        return dao.findByCategorieArticleId(id);
    }
    public int deleteByCategorieArticleId(Long id){
        return dao.deleteByCategorieArticleId(id);
    }
    public long countByCategorieArticleCode(String code){
        return dao.countByCategorieArticleCode(code);
    }
    public List<Article> findByEtatArticleId(Long id){
        return dao.findByEtatArticleId(id);
    }
    public int deleteByEtatArticleId(Long id){
        return dao.deleteByEtatArticleId(id);
    }
    public long countByEtatArticleId(Long id){
        return dao.countByEtatArticleId(id);
    }
    public List<Article> findByMarqueId(Long id){
        return dao.findByMarqueId(id);
    }
    public int deleteByMarqueId(Long id){
        return dao.deleteByMarqueId(id);
    }
    public long countByMarqueCode(String code){
        return dao.countByMarqueCode(code);
    }
    public List<Article> findByFournisseurId(Long id){
        return dao.findByFournisseurId(id);
    }
    public int deleteByFournisseurId(Long id){
        return dao.deleteByFournisseurId(id);
    }
    public long countByFournisseurCode(String code){
        return dao.countByFournisseurCode(code);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Article t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Article> delete(List<Article> list) {
		List<Article> result = new ArrayList();
        if (list != null) {
            for (Article t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Article create(Article t) {
        Article loaded = findByReferenceEntity(t);
        Article saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Article> create(List<Article> ts) {
        List<Article> result = new ArrayList<>();
        if (ts != null) {
            for (Article t : ts) {
				Article created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Article findWithAssociatedLists(Long id){
        Article result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Article> update(List<Article> ts, boolean createIfNotExist) {
        List<Article> result = new ArrayList<>();
        if (ts != null) {
            for (Article t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Article loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }





    public Article findByReferenceEntity(Article t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Article t){
        if( t != null) {
            t.setCategorieArticle(categorieArticleService.findOrSave(t.getCategorieArticle()));
            t.setEtatArticle(etatArticleService.findOrSave(t.getEtatArticle()));
            t.setMarque(marqueService.findOrSave(t.getMarque()));
            t.setFournisseur(fournisseurService.findOrSave(t.getFournisseur()));
        }
    }



    public List<Article> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Article>> getToBeSavedAndToBeDeleted(List<Article> oldList, List<Article> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Article> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EtatArticleAdminService etatArticleService ;
    @Autowired
    private FournisseurAdminService fournisseurService ;
    @Autowired
    private MarqueAdminService marqueService ;
    @Autowired
    private CategorieArticleAdminService categorieArticleService ;

    private @Autowired ArticleDao dao;
    private @Autowired ProjetDao projetDao;


}
