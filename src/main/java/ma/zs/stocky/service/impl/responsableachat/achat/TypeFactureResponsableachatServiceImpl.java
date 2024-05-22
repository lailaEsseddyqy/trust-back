package ma.zs.stocky.service.impl.responsableachat.achat;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import ma.zs.stocky.dao.criteria.core.achat.TypeFactureCriteria;
import ma.zs.stocky.dao.facade.core.achat.TypeFactureDao;
import ma.zs.stocky.dao.specification.core.achat.TypeFactureSpecification;
import ma.zs.stocky.service.facade.responsableachat.achat.TypeFactureResponsableachatService;
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
public class TypeFactureResponsableachatServiceImpl implements TypeFactureResponsableachatService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeFacture update(TypeFacture t) {
        TypeFacture loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeFacture.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeFacture findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeFacture findOrSave(TypeFacture t) {
        if (t != null) {
            TypeFacture result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeFacture> importData(List<TypeFacture> items) {
        List<TypeFacture> list = new ArrayList<>();
        for (TypeFacture t : items) {
            TypeFacture founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeFacture> findAll() {
        return dao.findAll();
    }

    public List<TypeFacture> findByCriteria(TypeFactureCriteria criteria) {
        List<TypeFacture> content = null;
        if (criteria != null) {
            TypeFactureSpecification mySpecification = constructSpecification(criteria);
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


    private TypeFactureSpecification constructSpecification(TypeFactureCriteria criteria) {
        TypeFactureSpecification mySpecification =  (TypeFactureSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeFactureSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeFacture> findPaginatedByCriteria(TypeFactureCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeFactureSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeFactureCriteria criteria) {
        TypeFactureSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeFacture t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeFacture> delete(List<TypeFacture> list) {
		List<TypeFacture> result = new ArrayList();
        if (list != null) {
            for (TypeFacture t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeFacture create(TypeFacture t) {
        TypeFacture loaded = findByReferenceEntity(t);
        TypeFacture saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeFacture> create(List<TypeFacture> ts) {
        List<TypeFacture> result = new ArrayList<>();
        if (ts != null) {
            for (TypeFacture t : ts) {
				TypeFacture created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeFacture findWithAssociatedLists(Long id){
        TypeFacture result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeFacture> update(List<TypeFacture> ts, boolean createIfNotExist) {
        List<TypeFacture> result = new ArrayList<>();
        if (ts != null) {
            for (TypeFacture t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeFacture loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeFacture findByReferenceEntity(TypeFacture t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeFacture> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeFacture>> getToBeSavedAndToBeDeleted(List<TypeFacture> oldList, List<TypeFacture> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeFacture> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeFactureDao dao;


}
