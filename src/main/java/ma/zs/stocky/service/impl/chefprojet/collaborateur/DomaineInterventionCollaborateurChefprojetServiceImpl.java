package ma.zs.stocky.service.impl.chefprojet.collaborateur;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCollaborateurCriteria;
import ma.zs.stocky.dao.facade.core.collaborateur.DomaineInterventionCollaborateurDao;
import ma.zs.stocky.dao.specification.core.collaborateur.DomaineInterventionCollaborateurSpecification;
import ma.zs.stocky.service.facade.chefprojet.collaborateur.DomaineInterventionCollaborateurChefprojetService;
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

import ma.zs.stocky.service.facade.chefprojet.collaborateur.DomaineInterventionChefprojetService ;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention ;
import ma.zs.stocky.service.facade.chefprojet.collaborateur.CollaborateurChefprojetService ;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur ;

import java.util.List;
@Service
public class DomaineInterventionCollaborateurChefprojetServiceImpl implements DomaineInterventionCollaborateurChefprojetService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DomaineInterventionCollaborateur update(DomaineInterventionCollaborateur t) {
        DomaineInterventionCollaborateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DomaineInterventionCollaborateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DomaineInterventionCollaborateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DomaineInterventionCollaborateur findOrSave(DomaineInterventionCollaborateur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DomaineInterventionCollaborateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DomaineInterventionCollaborateur> importData(List<DomaineInterventionCollaborateur> items) {
        List<DomaineInterventionCollaborateur> list = new ArrayList<>();
        for (DomaineInterventionCollaborateur t : items) {
            DomaineInterventionCollaborateur founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DomaineInterventionCollaborateur> findAll() {
        return dao.findAll();
    }

    public List<DomaineInterventionCollaborateur> findByCriteria(DomaineInterventionCollaborateurCriteria criteria) {
        List<DomaineInterventionCollaborateur> content = null;
        if (criteria != null) {
            DomaineInterventionCollaborateurSpecification mySpecification = constructSpecification(criteria);
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


    private DomaineInterventionCollaborateurSpecification constructSpecification(DomaineInterventionCollaborateurCriteria criteria) {
        DomaineInterventionCollaborateurSpecification mySpecification =  (DomaineInterventionCollaborateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(DomaineInterventionCollaborateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<DomaineInterventionCollaborateur> findPaginatedByCriteria(DomaineInterventionCollaborateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        DomaineInterventionCollaborateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DomaineInterventionCollaborateurCriteria criteria) {
        DomaineInterventionCollaborateurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DomaineInterventionCollaborateur> findByDomaineInterventionId(Long id){
        return dao.findByDomaineInterventionId(id);
    }
    public int deleteByDomaineInterventionId(Long id){
        return dao.deleteByDomaineInterventionId(id);
    }
    public long countByDomaineInterventionCode(String code){
        return dao.countByDomaineInterventionCode(code);
    }
    public List<DomaineInterventionCollaborateur> findByCollaborateurId(Long id){
        return dao.findByCollaborateurId(id);
    }
    public int deleteByCollaborateurId(Long id){
        return dao.deleteByCollaborateurId(id);
    }
    public long countByCollaborateurRef(String ref){
        return dao.countByCollaborateurRef(ref);
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
    public int delete(DomaineInterventionCollaborateur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomaineInterventionCollaborateur> delete(List<DomaineInterventionCollaborateur> list) {
		List<DomaineInterventionCollaborateur> result = new ArrayList();
        if (list != null) {
            for (DomaineInterventionCollaborateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DomaineInterventionCollaborateur create(DomaineInterventionCollaborateur t) {
        DomaineInterventionCollaborateur loaded = findByReferenceEntity(t);
        DomaineInterventionCollaborateur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomaineInterventionCollaborateur> create(List<DomaineInterventionCollaborateur> ts) {
        List<DomaineInterventionCollaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (DomaineInterventionCollaborateur t : ts) {
				DomaineInterventionCollaborateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DomaineInterventionCollaborateur findWithAssociatedLists(Long id){
        DomaineInterventionCollaborateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DomaineInterventionCollaborateur> update(List<DomaineInterventionCollaborateur> ts, boolean createIfNotExist) {
        List<DomaineInterventionCollaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (DomaineInterventionCollaborateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DomaineInterventionCollaborateur loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DomaineInterventionCollaborateur findByReferenceEntity(DomaineInterventionCollaborateur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DomaineInterventionCollaborateur t){
        if( t != null) {
            t.setDomaineIntervention(domaineInterventionService.findOrSave(t.getDomaineIntervention()));
            t.setCollaborateur(collaborateurService.findOrSave(t.getCollaborateur()));
        }
    }



    public List<DomaineInterventionCollaborateur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<DomaineInterventionCollaborateur>> getToBeSavedAndToBeDeleted(List<DomaineInterventionCollaborateur> oldList, List<DomaineInterventionCollaborateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DomaineInterventionCollaborateur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private DomaineInterventionChefprojetService domaineInterventionService ;
    @Autowired
    private CollaborateurChefprojetService collaborateurService ;

    private @Autowired DomaineInterventionCollaborateurDao dao;


}
