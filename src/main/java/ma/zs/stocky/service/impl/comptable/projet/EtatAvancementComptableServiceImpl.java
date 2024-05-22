package ma.zs.stocky.service.impl.comptable.projet;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.projet.EtatAvancement;
import ma.zs.stocky.dao.criteria.core.projet.EtatAvancementCriteria;
import ma.zs.stocky.dao.facade.core.projet.EtatAvancementDao;
import ma.zs.stocky.dao.specification.core.projet.EtatAvancementSpecification;
import ma.zs.stocky.service.facade.comptable.projet.EtatAvancementComptableService;
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
public class EtatAvancementComptableServiceImpl implements EtatAvancementComptableService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatAvancement update(EtatAvancement t) {
        EtatAvancement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatAvancement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatAvancement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatAvancement findOrSave(EtatAvancement t) {
        if (t != null) {
            EtatAvancement result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EtatAvancement> importData(List<EtatAvancement> items) {
        List<EtatAvancement> list = new ArrayList<>();
        for (EtatAvancement t : items) {
            EtatAvancement founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EtatAvancement> findAll() {
        return dao.findAll();
    }

    public List<EtatAvancement> findByCriteria(EtatAvancementCriteria criteria) {
        List<EtatAvancement> content = null;
        if (criteria != null) {
            EtatAvancementSpecification mySpecification = constructSpecification(criteria);
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


    private EtatAvancementSpecification constructSpecification(EtatAvancementCriteria criteria) {
        EtatAvancementSpecification mySpecification =  (EtatAvancementSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatAvancementSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatAvancement> findPaginatedByCriteria(EtatAvancementCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatAvancementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatAvancementCriteria criteria) {
        EtatAvancementSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(EtatAvancement t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAvancement> delete(List<EtatAvancement> list) {
		List<EtatAvancement> result = new ArrayList();
        if (list != null) {
            for (EtatAvancement t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatAvancement create(EtatAvancement t) {
        EtatAvancement loaded = findByReferenceEntity(t);
        EtatAvancement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAvancement> create(List<EtatAvancement> ts) {
        List<EtatAvancement> result = new ArrayList<>();
        if (ts != null) {
            for (EtatAvancement t : ts) {
				EtatAvancement created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EtatAvancement findWithAssociatedLists(Long id){
        EtatAvancement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAvancement> update(List<EtatAvancement> ts, boolean createIfNotExist) {
        List<EtatAvancement> result = new ArrayList<>();
        if (ts != null) {
            for (EtatAvancement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatAvancement loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EtatAvancement findByReferenceEntity(EtatAvancement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EtatAvancement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatAvancement>> getToBeSavedAndToBeDeleted(List<EtatAvancement> oldList, List<EtatAvancement> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EtatAvancement> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EtatAvancementDao dao;


}
