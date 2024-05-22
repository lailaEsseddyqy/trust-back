package ma.zs.stocky.service.impl.comptable.projet;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.dao.criteria.core.projet.ProjetArticleCriteria;
import ma.zs.stocky.dao.facade.core.projet.ProjetArticleDao;
import ma.zs.stocky.dao.specification.core.projet.ProjetArticleSpecification;
import ma.zs.stocky.service.facade.comptable.projet.ProjetArticleComptableService;
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

import ma.zs.stocky.service.facade.comptable.article.ArticleComptableService ;
import ma.zs.stocky.bean.core.article.Article ;
import ma.zs.stocky.service.facade.comptable.projet.ProjetComptableService ;
import ma.zs.stocky.bean.core.projet.Projet ;

import java.util.List;
@Service
public class ProjetArticleComptableServiceImpl implements ProjetArticleComptableService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjetArticle update(ProjetArticle t) {
        ProjetArticle loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjetArticle.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjetArticle findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjetArticle findOrSave(ProjetArticle t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjetArticle result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<ProjetArticle> importData(List<ProjetArticle> items) {
        List<ProjetArticle> list = new ArrayList<>();
        for (ProjetArticle t : items) {
            ProjetArticle founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<ProjetArticle> findAll() {
        return dao.findAll();
    }

    public List<ProjetArticle> findByCriteria(ProjetArticleCriteria criteria) {
        List<ProjetArticle> content = null;
        if (criteria != null) {
            ProjetArticleSpecification mySpecification = constructSpecification(criteria);
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


    private ProjetArticleSpecification constructSpecification(ProjetArticleCriteria criteria) {
        ProjetArticleSpecification mySpecification =  (ProjetArticleSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjetArticleSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjetArticle> findPaginatedByCriteria(ProjetArticleCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjetArticleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjetArticleCriteria criteria) {
        ProjetArticleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjetArticle> findByArticleId(Long id){
        return dao.findByArticleId(id);
    }
    public int deleteByArticleId(Long id){
        return dao.deleteByArticleId(id);
    }
    public long countByArticleId(Long id){
        return dao.countByArticleId(id);
    }
    public List<ProjetArticle> findByProjetId(Long id){
        return dao.findByProjetId(id);
    }
    public int deleteByProjetId(Long id){
        return dao.deleteByProjetId(id);
    }
    public long countByProjetReference(String reference){
        return dao.countByProjetReference(reference);
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
    public int delete(ProjetArticle t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetArticle> delete(List<ProjetArticle> list) {
		List<ProjetArticle> result = new ArrayList();
        if (list != null) {
            for (ProjetArticle t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjetArticle create(ProjetArticle t) {
        ProjetArticle loaded = findByReferenceEntity(t);
        ProjetArticle saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetArticle> create(List<ProjetArticle> ts) {
        List<ProjetArticle> result = new ArrayList<>();
        if (ts != null) {
            for (ProjetArticle t : ts) {
				ProjetArticle created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public ProjetArticle findWithAssociatedLists(Long id){
        ProjetArticle result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetArticle> update(List<ProjetArticle> ts, boolean createIfNotExist) {
        List<ProjetArticle> result = new ArrayList<>();
        if (ts != null) {
            for (ProjetArticle t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjetArticle loadedItem = dao.findById(t.getId()).orElse(null);
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





    public ProjetArticle findByReferenceEntity(ProjetArticle t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjetArticle t){
        if( t != null) {
            t.setArticle(articleService.findOrSave(t.getArticle()));
            t.setProjet(projetService.findOrSave(t.getProjet()));
        }
    }



    public List<ProjetArticle> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProjetArticle>> getToBeSavedAndToBeDeleted(List<ProjetArticle> oldList, List<ProjetArticle> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<ProjetArticle> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ArticleComptableService articleService ;
    @Autowired
    private ProjetComptableService projetService ;

    private @Autowired ProjetArticleDao dao;


}
