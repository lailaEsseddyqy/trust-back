package ma.zs.stocky.service.impl.chefprojet.article;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.article.Marque;
import ma.zs.stocky.dao.criteria.core.article.MarqueCriteria;
import ma.zs.stocky.dao.facade.core.article.MarqueDao;
import ma.zs.stocky.dao.specification.core.article.MarqueSpecification;
import ma.zs.stocky.service.facade.chefprojet.article.MarqueChefprojetService;
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
public class MarqueChefprojetServiceImpl implements MarqueChefprojetService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Marque update(Marque t) {
        Marque loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Marque.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Marque findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Marque findOrSave(Marque t) {
        if (t != null) {
            Marque result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Marque> importData(List<Marque> items) {
        List<Marque> list = new ArrayList<>();
        for (Marque t : items) {
            Marque founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Marque> findAll() {
        return dao.findAll();
    }

    public List<Marque> findByCriteria(MarqueCriteria criteria) {
        List<Marque> content = null;
        if (criteria != null) {
            MarqueSpecification mySpecification = constructSpecification(criteria);
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


    private MarqueSpecification constructSpecification(MarqueCriteria criteria) {
        MarqueSpecification mySpecification =  (MarqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(MarqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<Marque> findPaginatedByCriteria(MarqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        MarqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MarqueCriteria criteria) {
        MarqueSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Marque t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Marque> delete(List<Marque> list) {
		List<Marque> result = new ArrayList();
        if (list != null) {
            for (Marque t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Marque create(Marque t) {
        Marque loaded = findByReferenceEntity(t);
        Marque saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Marque> create(List<Marque> ts) {
        List<Marque> result = new ArrayList<>();
        if (ts != null) {
            for (Marque t : ts) {
				Marque created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Marque findWithAssociatedLists(Long id){
        Marque result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Marque> update(List<Marque> ts, boolean createIfNotExist) {
        List<Marque> result = new ArrayList<>();
        if (ts != null) {
            for (Marque t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Marque loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Marque findByReferenceEntity(Marque t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Marque> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Marque>> getToBeSavedAndToBeDeleted(List<Marque> oldList, List<Marque> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Marque> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired MarqueDao dao;


}
