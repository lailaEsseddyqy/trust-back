package ma.zs.stocky.service.impl.comptable.projet;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCollaborateurCriteria;
import ma.zs.stocky.dao.facade.core.projet.ProjetCollaborateurDao;
import ma.zs.stocky.dao.specification.core.projet.ProjetCollaborateurSpecification;
import ma.zs.stocky.service.facade.comptable.projet.ProjetCollaborateurComptableService;
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

import ma.zs.stocky.service.facade.comptable.projet.ProjetComptableService ;
import ma.zs.stocky.bean.core.projet.Projet ;
import ma.zs.stocky.service.facade.comptable.collaborateur.CollaborateurComptableService ;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur ;

import java.util.List;
@Service
public class ProjetCollaborateurComptableServiceImpl implements ProjetCollaborateurComptableService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjetCollaborateur update(ProjetCollaborateur t) {
        ProjetCollaborateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ProjetCollaborateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ProjetCollaborateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ProjetCollaborateur findOrSave(ProjetCollaborateur t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ProjetCollaborateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<ProjetCollaborateur> importData(List<ProjetCollaborateur> items) {
        List<ProjetCollaborateur> list = new ArrayList<>();
        for (ProjetCollaborateur t : items) {
            ProjetCollaborateur founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<ProjetCollaborateur> findAll() {
        return dao.findAll();
    }

    public List<ProjetCollaborateur> findByCriteria(ProjetCollaborateurCriteria criteria) {
        List<ProjetCollaborateur> content = null;
        if (criteria != null) {
            ProjetCollaborateurSpecification mySpecification = constructSpecification(criteria);
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


    private ProjetCollaborateurSpecification constructSpecification(ProjetCollaborateurCriteria criteria) {
        ProjetCollaborateurSpecification mySpecification =  (ProjetCollaborateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjetCollaborateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<ProjetCollaborateur> findPaginatedByCriteria(ProjetCollaborateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjetCollaborateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjetCollaborateurCriteria criteria) {
        ProjetCollaborateurSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<ProjetCollaborateur> findByCollaborateurId(Long id){
        return dao.findByCollaborateurId(id);
    }
    public int deleteByCollaborateurId(Long id){
        return dao.deleteByCollaborateurId(id);
    }
    public long countByCollaborateurRef(String ref){
        return dao.countByCollaborateurRef(ref);
    }
    public List<ProjetCollaborateur> findByProjetId(Long id){
        return dao.findByProjetId(id);
    }
    public int deleteByProjetId(Long id){
        return dao.deleteByProjetId(id);
    }
    public long countByProjetReference(String reference){
        return dao.countByProjetReference(reference);
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
    public int delete(ProjetCollaborateur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetCollaborateur> delete(List<ProjetCollaborateur> list) {
		List<ProjetCollaborateur> result = new ArrayList();
        if (list != null) {
            for (ProjetCollaborateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProjetCollaborateur create(ProjetCollaborateur t) {
        ProjetCollaborateur loaded = findByReferenceEntity(t);
        ProjetCollaborateur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetCollaborateur> create(List<ProjetCollaborateur> ts) {
        List<ProjetCollaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (ProjetCollaborateur t : ts) {
				ProjetCollaborateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public ProjetCollaborateur findWithAssociatedLists(Long id){
        ProjetCollaborateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ProjetCollaborateur> update(List<ProjetCollaborateur> ts, boolean createIfNotExist) {
        List<ProjetCollaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (ProjetCollaborateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ProjetCollaborateur loadedItem = dao.findById(t.getId()).orElse(null);
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





    public ProjetCollaborateur findByReferenceEntity(ProjetCollaborateur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(ProjetCollaborateur t){
        if( t != null) {
            t.setCollaborateur(collaborateurService.findOrSave(t.getCollaborateur()));
            t.setProjet(projetService.findOrSave(t.getProjet()));
        }
    }



    public List<ProjetCollaborateur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<ProjetCollaborateur>> getToBeSavedAndToBeDeleted(List<ProjetCollaborateur> oldList, List<ProjetCollaborateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<ProjetCollaborateur> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ProjetComptableService projetService ;
    @Autowired
    private CollaborateurComptableService collaborateurService ;

    private @Autowired ProjetCollaborateurDao dao;


}
