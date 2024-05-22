package ma.zs.stocky.service.impl.admin.collaborateur;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import ma.zs.stocky.dao.criteria.core.collaborateur.CollaborateurCriteria;
import ma.zs.stocky.dao.facade.core.collaborateur.CollaborateurDao;
import ma.zs.stocky.dao.specification.core.collaborateur.CollaborateurSpecification;
import ma.zs.stocky.service.facade.admin.collaborateur.CollaborateurAdminService;
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

import ma.zs.stocky.service.facade.admin.collaborateur.DomaineInterventionCollaborateurAdminService ;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur ;

import java.util.List;
@Service
public class CollaborateurAdminServiceImpl implements CollaborateurAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Collaborateur update(Collaborateur t) {
        Collaborateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Collaborateur.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Collaborateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Collaborateur findOrSave(Collaborateur t) {
        if (t != null) {
            Collaborateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Collaborateur> importData(List<Collaborateur> items) {
        List<Collaborateur> list = new ArrayList<>();
        for (Collaborateur t : items) {
            Collaborateur founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Collaborateur> findAll() {
        return dao.findAll();
    }

    public List<Collaborateur> findByCriteria(CollaborateurCriteria criteria) {
        List<Collaborateur> content = null;
        if (criteria != null) {
            CollaborateurSpecification mySpecification = constructSpecification(criteria);
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


    private CollaborateurSpecification constructSpecification(CollaborateurCriteria criteria) {
        CollaborateurSpecification mySpecification =  (CollaborateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(CollaborateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Collaborateur> findPaginatedByCriteria(CollaborateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        CollaborateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CollaborateurCriteria criteria) {
        CollaborateurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(Collaborateur t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        domaineInterventionCollaborateurService.deleteByCollaborateurId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Collaborateur> delete(List<Collaborateur> list) {
		List<Collaborateur> result = new ArrayList();
        if (list != null) {
            for (Collaborateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Collaborateur create(Collaborateur t) {
        Collaborateur loaded = findByReferenceEntity(t);
        Collaborateur saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getDomaineInterventionCollaborateurs() != null) {
                t.getDomaineInterventionCollaborateurs().forEach(element-> {
                    element.setCollaborateur(saved);
                    domaineInterventionCollaborateurService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Collaborateur> create(List<Collaborateur> ts) {
        List<Collaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (Collaborateur t : ts) {
				Collaborateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Collaborateur findWithAssociatedLists(Long id){
        Collaborateur result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setDomaineInterventionCollaborateurs(domaineInterventionCollaborateurService.findByCollaborateurId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Collaborateur> update(List<Collaborateur> ts, boolean createIfNotExist) {
        List<Collaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (Collaborateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Collaborateur loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Collaborateur collaborateur){
    if(collaborateur !=null && collaborateur.getId() != null){
        List<List<DomaineInterventionCollaborateur>> resultDomaineInterventionCollaborateurs= domaineInterventionCollaborateurService.getToBeSavedAndToBeDeleted(domaineInterventionCollaborateurService.findByCollaborateurId(collaborateur.getId()),collaborateur.getDomaineInterventionCollaborateurs());
            domaineInterventionCollaborateurService.delete(resultDomaineInterventionCollaborateurs.get(1));
        ListUtil.emptyIfNull(resultDomaineInterventionCollaborateurs.get(0)).forEach(e -> e.setCollaborateur(collaborateur));
        domaineInterventionCollaborateurService.update(resultDomaineInterventionCollaborateurs.get(0),true);
        }
    }




    public Collaborateur findByReferenceEntity(Collaborateur t){
        return t==null? null : dao.findByRef(t.getRef());
    }



    public List<Collaborateur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Collaborateur>> getToBeSavedAndToBeDeleted(List<Collaborateur> oldList, List<Collaborateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Collaborateur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private DomaineInterventionCollaborateurAdminService domaineInterventionCollaborateurService ;

    private @Autowired CollaborateurDao dao;


}
