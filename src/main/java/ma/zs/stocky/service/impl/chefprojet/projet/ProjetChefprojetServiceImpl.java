package ma.zs.stocky.service.impl.chefprojet.projet;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCriteria;
import ma.zs.stocky.dao.facade.core.projet.ProjetDao;
import ma.zs.stocky.dao.specification.core.projet.ProjetSpecification;
import ma.zs.stocky.service.facade.chefprojet.projet.ProjetChefprojetService;
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

import ma.zs.stocky.service.facade.chefprojet.projet.ProjetArticleChefprojetService ;
import ma.zs.stocky.bean.core.projet.ProjetArticle ;
import ma.zs.stocky.service.facade.chefprojet.projet.PieceChefprojetService ;
import ma.zs.stocky.bean.core.projet.Piece ;
import ma.zs.stocky.service.facade.chefprojet.projet.ProjetCollaborateurChefprojetService ;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur ;
import ma.zs.stocky.service.facade.chefprojet.projet.EtatAvancementChefprojetService ;
import ma.zs.stocky.bean.core.projet.EtatAvancement ;
import ma.zs.stocky.service.facade.chefprojet.commun.ClientChefprojetService ;
import ma.zs.stocky.bean.core.commun.Client ;

import java.util.List;
@Service
public class ProjetChefprojetServiceImpl implements ProjetChefprojetService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Projet update(Projet t) {
        Projet loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Projet.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Projet findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Projet findOrSave(Projet t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Projet result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Projet> importData(List<Projet> items) {
        List<Projet> list = new ArrayList<>();
        for (Projet t : items) {
            Projet founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Projet> findAll() {
        return dao.findAll();
    }

    public List<Projet> findByCriteria(ProjetCriteria criteria) {
        List<Projet> content = null;
        if (criteria != null) {
            ProjetSpecification mySpecification = constructSpecification(criteria);
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


    private ProjetSpecification constructSpecification(ProjetCriteria criteria) {
        ProjetSpecification mySpecification =  (ProjetSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProjetSpecification.class, criteria);
        return mySpecification;
    }

    public List<Projet> findPaginatedByCriteria(ProjetCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProjetSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProjetCriteria criteria) {
        ProjetSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Projet> findByClientId(Long id){
        return dao.findByClientId(id);
    }
    public int deleteByClientId(Long id){
        return dao.deleteByClientId(id);
    }
    public long countByClientCode(String code){
        return dao.countByClientCode(code);
    }
    public List<Projet> findByPieceJointId(Long id){
        return dao.findByPieceJointId(id);
    }
    public int deleteByPieceJointId(Long id){
        return dao.deleteByPieceJointId(id);
    }
    public long countByPieceJointCode(String code){
        return dao.countByPieceJointCode(code);
    }
    public List<Projet> findByEtatAvancementId(Long id){
        return dao.findByEtatAvancementId(id);
    }
    public int deleteByEtatAvancementId(Long id){
        return dao.deleteByEtatAvancementId(id);
    }
    public long countByEtatAvancementCode(String code){
        return dao.countByEtatAvancementCode(code);
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
    public int delete(Projet t) {
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
        projetCollaborateurService.deleteByProjetId(id);
        projetArticleService.deleteByProjetId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Projet> delete(List<Projet> list) {
		List<Projet> result = new ArrayList();
        if (list != null) {
            for (Projet t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Projet create(Projet t) {
        Projet loaded = findByReferenceEntity(t);
        Projet saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getProjetCollaborateurs() != null) {
                t.getProjetCollaborateurs().forEach(element-> {
                    element.setProjet(saved);
                    projetCollaborateurService.create(element);
                });
            }
            if (t.getProjetArticles() != null) {
                t.getProjetArticles().forEach(element-> {
                    element.setProjet(saved);
                    projetArticleService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Projet> create(List<Projet> ts) {
        List<Projet> result = new ArrayList<>();
        if (ts != null) {
            for (Projet t : ts) {
				Projet created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Projet findWithAssociatedLists(Long id){
        Projet result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setProjetCollaborateurs(projetCollaborateurService.findByProjetId(id));
            result.setProjetArticles(projetArticleService.findByProjetId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Projet> update(List<Projet> ts, boolean createIfNotExist) {
        List<Projet> result = new ArrayList<>();
        if (ts != null) {
            for (Projet t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Projet loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Projet projet){
    if(projet !=null && projet.getId() != null){
        List<List<ProjetCollaborateur>> resultProjetCollaborateurs= projetCollaborateurService.getToBeSavedAndToBeDeleted(projetCollaborateurService.findByProjetId(projet.getId()),projet.getProjetCollaborateurs());
            projetCollaborateurService.delete(resultProjetCollaborateurs.get(1));
        ListUtil.emptyIfNull(resultProjetCollaborateurs.get(0)).forEach(e -> e.setProjet(projet));
        projetCollaborateurService.update(resultProjetCollaborateurs.get(0),true);
        List<List<ProjetArticle>> resultProjetArticles= projetArticleService.getToBeSavedAndToBeDeleted(projetArticleService.findByProjetId(projet.getId()),projet.getProjetArticles());
            projetArticleService.delete(resultProjetArticles.get(1));
        ListUtil.emptyIfNull(resultProjetArticles.get(0)).forEach(e -> e.setProjet(projet));
        projetArticleService.update(resultProjetArticles.get(0),true);
        }
    }




    public Projet findByReferenceEntity(Projet t){
        return t==null? null : dao.findByReference(t.getReference());
    }
    public void findOrSaveAssociatedObject(Projet t){
        if( t != null) {
            t.setClient(clientService.findOrSave(t.getClient()));
            t.setPieceJoint(pieceService.findOrSave(t.getPieceJoint()));
            t.setEtatAvancement(etatAvancementService.findOrSave(t.getEtatAvancement()));
        }
    }



    public List<Projet> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Projet>> getToBeSavedAndToBeDeleted(List<Projet> oldList, List<Projet> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Projet> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private ProjetArticleChefprojetService projetArticleService ;
    @Autowired
    private PieceChefprojetService pieceService ;
    @Autowired
    private ProjetCollaborateurChefprojetService projetCollaborateurService ;
    @Autowired
    private EtatAvancementChefprojetService etatAvancementService ;
    @Autowired
    private ClientChefprojetService clientService ;

    private @Autowired ProjetDao dao;


}
