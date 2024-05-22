package ma.zs.stocky.service.impl.responsableachat.article;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import ma.zs.stocky.dao.criteria.core.article.CategorieArticleCriteria;
import ma.zs.stocky.dao.facade.core.article.CategorieArticleDao;
import ma.zs.stocky.dao.specification.core.article.CategorieArticleSpecification;
import ma.zs.stocky.service.facade.responsableachat.article.CategorieArticleResponsableachatService;
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


import java.util.List;
@Service
public class CategorieArticleResponsableachatServiceImpl implements CategorieArticleResponsableachatService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieArticle update(CategorieArticle t) {
        CategorieArticle loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategorieArticle.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategorieArticle findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategorieArticle findOrSave(CategorieArticle t) {
        if (t != null) {
            CategorieArticle result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<CategorieArticle> importData(List<CategorieArticle> items) {
        List<CategorieArticle> list = new ArrayList<>();
        for (CategorieArticle t : items) {
            CategorieArticle founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<CategorieArticle> findAll() {
        return dao.findAll();
    }

    public List<CategorieArticle> findByCriteria(CategorieArticleCriteria criteria) {
        List<CategorieArticle> content = null;
        if (criteria != null) {
            CategorieArticleSpecification mySpecification = constructSpecification(criteria);
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


    private CategorieArticleSpecification constructSpecification(CategorieArticleCriteria criteria) {
        CategorieArticleSpecification mySpecification =  (CategorieArticleSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategorieArticleSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategorieArticle> findPaginatedByCriteria(CategorieArticleCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategorieArticleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategorieArticleCriteria criteria) {
        CategorieArticleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(CategorieArticle t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieArticle> delete(List<CategorieArticle> list) {
		List<CategorieArticle> result = new ArrayList();
        if (list != null) {
            for (CategorieArticle t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieArticle create(CategorieArticle t) {
        CategorieArticle loaded = findByReferenceEntity(t);
        CategorieArticle saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieArticle> create(List<CategorieArticle> ts) {
        List<CategorieArticle> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieArticle t : ts) {
				CategorieArticle created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public CategorieArticle findWithAssociatedLists(Long id){
        CategorieArticle result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieArticle> update(List<CategorieArticle> ts, boolean createIfNotExist) {
        List<CategorieArticle> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieArticle t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategorieArticle loadedItem = dao.findById(t.getId()).orElse(null);
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





    public CategorieArticle findByReferenceEntity(CategorieArticle t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CategorieArticle> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategorieArticle>> getToBeSavedAndToBeDeleted(List<CategorieArticle> oldList, List<CategorieArticle> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<CategorieArticle> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired CategorieArticleDao dao;


}
