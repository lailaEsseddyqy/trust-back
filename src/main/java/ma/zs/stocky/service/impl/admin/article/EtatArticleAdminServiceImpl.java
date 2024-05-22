package ma.zs.stocky.service.impl.admin.article;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.article.EtatArticle;
import ma.zs.stocky.dao.criteria.core.article.EtatArticleCriteria;
import ma.zs.stocky.dao.facade.core.article.EtatArticleDao;
import ma.zs.stocky.dao.specification.core.article.EtatArticleSpecification;
import ma.zs.stocky.service.facade.admin.article.EtatArticleAdminService;
import ma.zs.stocky.zynerator.service.AbstractServiceImpl;
import ma.zs.stocky.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
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

import ma.zs.stocky.service.facade.admin.article.ArticleAdminService ;
import ma.zs.stocky.bean.core.article.Article ;

import java.util.List;
@Service
public class EtatArticleAdminServiceImpl implements EtatArticleAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatArticle update(EtatArticle t) {
        EtatArticle loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatArticle.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatArticle findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatArticle findOrSave(EtatArticle t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EtatArticle result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EtatArticle> importData(List<EtatArticle> items) {
        List<EtatArticle> list = new ArrayList<>();
        for (EtatArticle t : items) {
            EtatArticle founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EtatArticle> findAll() {
        return dao.findAll();
    }

    public List<EtatArticle> findByCriteria(EtatArticleCriteria criteria) {
        List<EtatArticle> content = null;
        if (criteria != null) {
            EtatArticleSpecification mySpecification = constructSpecification(criteria);
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


    private EtatArticleSpecification constructSpecification(EtatArticleCriteria criteria) {
        EtatArticleSpecification mySpecification =  (EtatArticleSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatArticleSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatArticle> findPaginatedByCriteria(EtatArticleCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatArticleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatArticleCriteria criteria) {
        EtatArticleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EtatArticle> findByArticleId(Long id){
        return dao.findByArticleId(id);
    }
    public int deleteByArticleId(Long id){
        return dao.deleteByArticleId(id);
    }
    public long countByArticleId(Long id){
        return dao.countByArticleId(id);
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
    public int delete(EtatArticle t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatArticle> delete(List<EtatArticle> list) {
		List<EtatArticle> result = new ArrayList();
        if (list != null) {
            for (EtatArticle t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatArticle create(EtatArticle t) {
        EtatArticle loaded = findByReferenceEntity(t);
        EtatArticle saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatArticle> create(List<EtatArticle> ts) {
        List<EtatArticle> result = new ArrayList<>();
        if (ts != null) {
            for (EtatArticle t : ts) {
				EtatArticle created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EtatArticle findWithAssociatedLists(Long id){
        EtatArticle result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatArticle> update(List<EtatArticle> ts, boolean createIfNotExist) {
        List<EtatArticle> result = new ArrayList<>();
        if (ts != null) {
            for (EtatArticle t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatArticle loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EtatArticle findByReferenceEntity(EtatArticle t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(EtatArticle t){
        if( t != null) {
            t.setArticle(articleService.findOrSave(t.getArticle()));
        }
    }



    public List<EtatArticle> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatArticle>> getToBeSavedAndToBeDeleted(List<EtatArticle> oldList, List<EtatArticle> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EtatArticle> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ArticleAdminService articleService ;

    private @Autowired EtatArticleDao dao;


}
