package ma.zs.stocky.service.impl.chefprojet.commun;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.commun.Societe;
import ma.zs.stocky.dao.criteria.core.commun.SocieteCriteria;
import ma.zs.stocky.dao.facade.core.commun.SocieteDao;
import ma.zs.stocky.dao.specification.core.commun.SocieteSpecification;
import ma.zs.stocky.service.facade.chefprojet.commun.SocieteChefprojetService;
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
public class SocieteChefprojetServiceImpl implements SocieteChefprojetService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Societe update(Societe t) {
        Societe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Societe.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Societe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Societe findOrSave(Societe t) {
        if (t != null) {
            Societe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Societe> importData(List<Societe> items) {
        List<Societe> list = new ArrayList<>();
        for (Societe t : items) {
            Societe founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Societe> findAll() {
        return dao.findAll();
    }

    public List<Societe> findByCriteria(SocieteCriteria criteria) {
        List<Societe> content = null;
        if (criteria != null) {
            SocieteSpecification mySpecification = constructSpecification(criteria);
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


    private SocieteSpecification constructSpecification(SocieteCriteria criteria) {
        SocieteSpecification mySpecification =  (SocieteSpecification) RefelexivityUtil.constructObjectUsingOneParam(SocieteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Societe> findPaginatedByCriteria(SocieteCriteria criteria, int page, int pageSize, String order, String sortField) {
        SocieteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SocieteCriteria criteria) {
        SocieteSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Societe t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> delete(List<Societe> list) {
		List<Societe> result = new ArrayList();
        if (list != null) {
            for (Societe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Societe create(Societe t) {
        Societe loaded = findByReferenceEntity(t);
        Societe saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> create(List<Societe> ts) {
        List<Societe> result = new ArrayList<>();
        if (ts != null) {
            for (Societe t : ts) {
				Societe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Societe findWithAssociatedLists(Long id){
        Societe result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> update(List<Societe> ts, boolean createIfNotExist) {
        List<Societe> result = new ArrayList<>();
        if (ts != null) {
            for (Societe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Societe loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Societe findByReferenceEntity(Societe t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Societe> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Societe>> getToBeSavedAndToBeDeleted(List<Societe> oldList, List<Societe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Societe> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired SocieteDao dao;


}
