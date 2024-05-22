package ma.zs.stocky.service.impl.responsableachat.achat;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.achat.EtatAchat;
import ma.zs.stocky.dao.criteria.core.achat.EtatAchatCriteria;
import ma.zs.stocky.dao.facade.core.achat.EtatAchatDao;
import ma.zs.stocky.dao.specification.core.achat.EtatAchatSpecification;
import ma.zs.stocky.service.facade.responsableachat.achat.EtatAchatResponsableachatService;
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
public class EtatAchatResponsableachatServiceImpl implements EtatAchatResponsableachatService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatAchat update(EtatAchat t) {
        EtatAchat loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatAchat.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatAchat findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatAchat findOrSave(EtatAchat t) {
        if (t != null) {
            EtatAchat result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EtatAchat> importData(List<EtatAchat> items) {
        List<EtatAchat> list = new ArrayList<>();
        for (EtatAchat t : items) {
            EtatAchat founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EtatAchat> findAll() {
        return dao.findAll();
    }

    public List<EtatAchat> findByCriteria(EtatAchatCriteria criteria) {
        List<EtatAchat> content = null;
        if (criteria != null) {
            EtatAchatSpecification mySpecification = constructSpecification(criteria);
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


    private EtatAchatSpecification constructSpecification(EtatAchatCriteria criteria) {
        EtatAchatSpecification mySpecification =  (EtatAchatSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatAchatSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatAchat> findPaginatedByCriteria(EtatAchatCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatAchatSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatAchatCriteria criteria) {
        EtatAchatSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(EtatAchat t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAchat> delete(List<EtatAchat> list) {
		List<EtatAchat> result = new ArrayList();
        if (list != null) {
            for (EtatAchat t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatAchat create(EtatAchat t) {
        EtatAchat loaded = findByReferenceEntity(t);
        EtatAchat saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAchat> create(List<EtatAchat> ts) {
        List<EtatAchat> result = new ArrayList<>();
        if (ts != null) {
            for (EtatAchat t : ts) {
				EtatAchat created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EtatAchat findWithAssociatedLists(Long id){
        EtatAchat result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatAchat> update(List<EtatAchat> ts, boolean createIfNotExist) {
        List<EtatAchat> result = new ArrayList<>();
        if (ts != null) {
            for (EtatAchat t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatAchat loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EtatAchat findByReferenceEntity(EtatAchat t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EtatAchat> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatAchat>> getToBeSavedAndToBeDeleted(List<EtatAchat> oldList, List<EtatAchat> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EtatAchat> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired EtatAchatDao dao;


}
