package ma.zs.stocky.service.impl.chefprojet.achat;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.achat.Facture;
import ma.zs.stocky.dao.criteria.core.achat.FactureCriteria;
import ma.zs.stocky.dao.facade.core.achat.FactureDao;
import ma.zs.stocky.dao.specification.core.achat.FactureSpecification;
import ma.zs.stocky.service.facade.chefprojet.achat.FactureChefprojetService;
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

import ma.zs.stocky.service.facade.chefprojet.achat.TypeFactureChefprojetService ;
import ma.zs.stocky.bean.core.achat.TypeFacture ;

import java.util.List;
@Service
public class FactureChefprojetServiceImpl implements FactureChefprojetService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Facture update(Facture t) {
        Facture loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Facture.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Facture findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Facture findOrSave(Facture t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Facture result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Facture> importData(List<Facture> items) {
        List<Facture> list = new ArrayList<>();
        for (Facture t : items) {
            Facture founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Facture> findAll() {
        return dao.findAll();
    }

    public List<Facture> findByCriteria(FactureCriteria criteria) {
        List<Facture> content = null;
        if (criteria != null) {
            FactureSpecification mySpecification = constructSpecification(criteria);
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


    private FactureSpecification constructSpecification(FactureCriteria criteria) {
        FactureSpecification mySpecification =  (FactureSpecification) RefelexivityUtil.constructObjectUsingOneParam(FactureSpecification.class, criteria);
        return mySpecification;
    }

    public List<Facture> findPaginatedByCriteria(FactureCriteria criteria, int page, int pageSize, String order, String sortField) {
        FactureSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FactureCriteria criteria) {
        FactureSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Facture> findByTypeFactureId(Long id){
        return dao.findByTypeFactureId(id);
    }
    public int deleteByTypeFactureId(Long id){
        return dao.deleteByTypeFactureId(id);
    }
    public long countByTypeFactureCode(String code){
        return dao.countByTypeFactureCode(code);
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
    public int delete(Facture t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Facture> delete(List<Facture> list) {
		List<Facture> result = new ArrayList();
        if (list != null) {
            for (Facture t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Facture create(Facture t) {
        Facture loaded = findByReferenceEntity(t);
        Facture saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Facture> create(List<Facture> ts) {
        List<Facture> result = new ArrayList<>();
        if (ts != null) {
            for (Facture t : ts) {
				Facture created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Facture findWithAssociatedLists(Long id){
        Facture result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Facture> update(List<Facture> ts, boolean createIfNotExist) {
        List<Facture> result = new ArrayList<>();
        if (ts != null) {
            for (Facture t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Facture loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Facture findByReferenceEntity(Facture t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Facture t){
        if( t != null) {
            t.setTypeFacture(typeFactureService.findOrSave(t.getTypeFacture()));
        }
    }



    public List<Facture> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Facture>> getToBeSavedAndToBeDeleted(List<Facture> oldList, List<Facture> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Facture> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TypeFactureChefprojetService typeFactureService ;

    private @Autowired FactureDao dao;


}
