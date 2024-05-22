package ma.zs.stocky.service.impl.responsableachat.commun;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;
import ma.zs.stocky.dao.facade.core.commun.FournisseurDao;
import ma.zs.stocky.dao.specification.core.commun.FournisseurSpecification;
import ma.zs.stocky.service.facade.responsableachat.commun.FournisseurResponsableachatService;
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

import ma.zs.stocky.service.facade.responsableachat.commun.SocieteResponsableachatService ;
import ma.zs.stocky.bean.core.commun.Societe ;

import java.util.List;
@Service
public class FournisseurResponsableachatServiceImpl implements FournisseurResponsableachatService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Fournisseur update(Fournisseur t) {
        Fournisseur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Fournisseur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Fournisseur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Fournisseur findOrSave(Fournisseur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Fournisseur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Fournisseur> importData(List<Fournisseur> items) {
        List<Fournisseur> list = new ArrayList<>();
        for (Fournisseur t : items) {
            Fournisseur founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Fournisseur> findAll() {
        return dao.findAll();
    }

    public List<Fournisseur> findByCriteria(FournisseurCriteria criteria) {
        List<Fournisseur> content = null;
        if (criteria != null) {
            FournisseurSpecification mySpecification = constructSpecification(criteria);
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


    private FournisseurSpecification constructSpecification(FournisseurCriteria criteria) {
        FournisseurSpecification mySpecification =  (FournisseurSpecification) RefelexivityUtil.constructObjectUsingOneParam(FournisseurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Fournisseur> findPaginatedByCriteria(FournisseurCriteria criteria, int page, int pageSize, String order, String sortField) {
        FournisseurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FournisseurCriteria criteria) {
        FournisseurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Fournisseur> findBySocieteId(Long id){
        return dao.findBySocieteId(id);
    }
    public int deleteBySocieteId(Long id){
        return dao.deleteBySocieteId(id);
    }
    public long countBySocieteId(Long id){
        return dao.countBySocieteId(id);
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
    public int delete(Fournisseur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Fournisseur> delete(List<Fournisseur> list) {
		List<Fournisseur> result = new ArrayList();
        if (list != null) {
            for (Fournisseur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Fournisseur create(Fournisseur t) {
        Fournisseur loaded = findByReferenceEntity(t);
        Fournisseur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Fournisseur> create(List<Fournisseur> ts) {
        List<Fournisseur> result = new ArrayList<>();
        if (ts != null) {
            for (Fournisseur t : ts) {
				Fournisseur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Fournisseur findWithAssociatedLists(Long id){
        Fournisseur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Fournisseur> update(List<Fournisseur> ts, boolean createIfNotExist) {
        List<Fournisseur> result = new ArrayList<>();
        if (ts != null) {
            for (Fournisseur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Fournisseur loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Fournisseur findByReferenceEntity(Fournisseur t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Fournisseur t){
        if( t != null) {
            t.setSociete(societeService.findOrSave(t.getSociete()));
        }
    }



    public List<Fournisseur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Fournisseur>> getToBeSavedAndToBeDeleted(List<Fournisseur> oldList, List<Fournisseur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Fournisseur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private SocieteResponsableachatService societeService ;

    private @Autowired FournisseurDao dao;


}
