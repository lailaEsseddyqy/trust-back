package ma.zs.stocky.service.impl.comptable.achat;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.achat.AchatDetail;
import ma.zs.stocky.dao.criteria.core.achat.AchatDetailCriteria;
import ma.zs.stocky.dao.facade.core.achat.AchatDetailDao;
import ma.zs.stocky.dao.specification.core.achat.AchatDetailSpecification;
import ma.zs.stocky.service.facade.comptable.achat.AchatDetailComptableService;
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
import ma.zs.stocky.service.facade.comptable.achat.AchatComptableService ;
import ma.zs.stocky.bean.core.achat.Achat ;

import java.util.List;
@Service
public class AchatDetailComptableServiceImpl implements AchatDetailComptableService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AchatDetail update(AchatDetail t) {
        AchatDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{AchatDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public AchatDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public AchatDetail findOrSave(AchatDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            AchatDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<AchatDetail> importData(List<AchatDetail> items) {
        List<AchatDetail> list = new ArrayList<>();
        for (AchatDetail t : items) {
            AchatDetail founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<AchatDetail> findAll() {
        return dao.findAll();
    }

    public List<AchatDetail> findByCriteria(AchatDetailCriteria criteria) {
        List<AchatDetail> content = null;
        if (criteria != null) {
            AchatDetailSpecification mySpecification = constructSpecification(criteria);
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


    private AchatDetailSpecification constructSpecification(AchatDetailCriteria criteria) {
        AchatDetailSpecification mySpecification =  (AchatDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(AchatDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<AchatDetail> findPaginatedByCriteria(AchatDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        AchatDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AchatDetailCriteria criteria) {
        AchatDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<AchatDetail> findByArticleId(Long id){
        return dao.findByArticleId(id);
    }
    public int deleteByArticleId(Long id){
        return dao.deleteByArticleId(id);
    }
    public long countByArticleId(Long id){
        return dao.countByArticleId(id);
    }
    public List<AchatDetail> findByAchatId(Long id){
        return dao.findByAchatId(id);
    }
    public int deleteByAchatId(Long id){
        return dao.deleteByAchatId(id);
    }
    public long countByAchatId(Long id){
        return dao.countByAchatId(id);
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
    public int delete(AchatDetail t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AchatDetail> delete(List<AchatDetail> list) {
		List<AchatDetail> result = new ArrayList();
        if (list != null) {
            for (AchatDetail t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public AchatDetail create(AchatDetail t) {
        AchatDetail loaded = findByReferenceEntity(t);
        AchatDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AchatDetail> create(List<AchatDetail> ts) {
        List<AchatDetail> result = new ArrayList<>();
        if (ts != null) {
            for (AchatDetail t : ts) {
				AchatDetail created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public AchatDetail findWithAssociatedLists(Long id){
        AchatDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<AchatDetail> update(List<AchatDetail> ts, boolean createIfNotExist) {
        List<AchatDetail> result = new ArrayList<>();
        if (ts != null) {
            for (AchatDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    AchatDetail loadedItem = dao.findById(t.getId()).orElse(null);
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





    public AchatDetail findByReferenceEntity(AchatDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(AchatDetail t){
        if( t != null) {
            t.setArticle(articleService.findOrSave(t.getArticle()));
            t.setAchat(achatService.findOrSave(t.getAchat()));
        }
    }



    public List<AchatDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<AchatDetail>> getToBeSavedAndToBeDeleted(List<AchatDetail> oldList, List<AchatDetail> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<AchatDetail> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ArticleComptableService articleService ;
    @Autowired
    private AchatComptableService achatService ;

    private @Autowired AchatDetailDao dao;


}
