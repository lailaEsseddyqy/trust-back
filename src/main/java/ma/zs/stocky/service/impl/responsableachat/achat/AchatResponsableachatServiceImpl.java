package ma.zs.stocky.service.impl.responsableachat.achat;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.achat.Achat;
import ma.zs.stocky.dao.criteria.core.achat.AchatCriteria;
import ma.zs.stocky.dao.facade.core.achat.AchatDao;
import ma.zs.stocky.dao.specification.core.achat.AchatSpecification;
import ma.zs.stocky.service.facade.responsableachat.achat.AchatResponsableachatService;
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

import ma.zs.stocky.service.facade.responsableachat.achat.AchatDetailResponsableachatService ;
import ma.zs.stocky.bean.core.achat.AchatDetail ;
import ma.zs.stocky.service.facade.responsableachat.commun.FournisseurResponsableachatService ;
import ma.zs.stocky.bean.core.commun.Fournisseur ;
import ma.zs.stocky.service.facade.responsableachat.achat.FactureResponsableachatService ;
import ma.zs.stocky.bean.core.achat.Facture ;
import ma.zs.stocky.service.facade.responsableachat.achat.EtatAchatResponsableachatService ;
import ma.zs.stocky.bean.core.achat.EtatAchat ;

import java.util.List;
@Service
public class AchatResponsableachatServiceImpl implements AchatResponsableachatService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Achat update(Achat t) {
        Achat loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Achat.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Achat findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Achat findOrSave(Achat t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Achat result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Achat> importData(List<Achat> items) {
        List<Achat> list = new ArrayList<>();
        for (Achat t : items) {
            Achat founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Achat> findAll() {
        return dao.findAll();
    }

    public List<Achat> findByCriteria(AchatCriteria criteria) {
        List<Achat> content = null;
        if (criteria != null) {
            AchatSpecification mySpecification = constructSpecification(criteria);
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


    private AchatSpecification constructSpecification(AchatCriteria criteria) {
        AchatSpecification mySpecification =  (AchatSpecification) RefelexivityUtil.constructObjectUsingOneParam(AchatSpecification.class, criteria);
        return mySpecification;
    }

    public List<Achat> findPaginatedByCriteria(AchatCriteria criteria, int page, int pageSize, String order, String sortField) {
        AchatSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AchatCriteria criteria) {
        AchatSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Achat> findByEtatAchatId(Long id){
        return dao.findByEtatAchatId(id);
    }
    public int deleteByEtatAchatId(Long id){
        return dao.deleteByEtatAchatId(id);
    }
    public long countByEtatAchatCode(String code){
        return dao.countByEtatAchatCode(code);
    }
    public List<Achat> findByFournisseurId(Long id){
        return dao.findByFournisseurId(id);
    }
    public int deleteByFournisseurId(Long id){
        return dao.deleteByFournisseurId(id);
    }
    public long countByFournisseurCode(String code){
        return dao.countByFournisseurCode(code);
    }
    public List<Achat> findByFactureId(Long id){
        return dao.findByFactureId(id);
    }
    public int deleteByFactureId(Long id){
        return dao.deleteByFactureId(id);
    }
    public long countByFactureId(Long id){
        return dao.countByFactureId(id);
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
    public int delete(Achat t) {
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
        achatDetailService.deleteByAchatId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Achat> delete(List<Achat> list) {
		List<Achat> result = new ArrayList();
        if (list != null) {
            for (Achat t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Achat create(Achat t) {
        Achat loaded = findByReferenceEntity(t);
        Achat saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getAchatDetails() != null) {
                t.getAchatDetails().forEach(element-> {
                    element.setAchat(saved);
                    achatDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Achat> create(List<Achat> ts) {
        List<Achat> result = new ArrayList<>();
        if (ts != null) {
            for (Achat t : ts) {
				Achat created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Achat findWithAssociatedLists(Long id){
        Achat result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setAchatDetails(achatDetailService.findByAchatId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Achat> update(List<Achat> ts, boolean createIfNotExist) {
        List<Achat> result = new ArrayList<>();
        if (ts != null) {
            for (Achat t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Achat loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Achat achat){
    if(achat !=null && achat.getId() != null){
        List<List<AchatDetail>> resultAchatDetails= achatDetailService.getToBeSavedAndToBeDeleted(achatDetailService.findByAchatId(achat.getId()),achat.getAchatDetails());
            achatDetailService.delete(resultAchatDetails.get(1));
        ListUtil.emptyIfNull(resultAchatDetails.get(0)).forEach(e -> e.setAchat(achat));
        achatDetailService.update(resultAchatDetails.get(0),true);
        }
    }




    public Achat findByReferenceEntity(Achat t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Achat t){
        if( t != null) {
            t.setEtatAchat(etatAchatService.findOrSave(t.getEtatAchat()));
            t.setFournisseur(fournisseurService.findOrSave(t.getFournisseur()));
            t.setFacture(factureService.findOrSave(t.getFacture()));
        }
    }



    public List<Achat> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Achat>> getToBeSavedAndToBeDeleted(List<Achat> oldList, List<Achat> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Achat> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private AchatDetailResponsableachatService achatDetailService ;
    @Autowired
    private FournisseurResponsableachatService fournisseurService ;
    @Autowired
    private FactureResponsableachatService factureService ;
    @Autowired
    private EtatAchatResponsableachatService etatAchatService ;

    private @Autowired AchatDao dao;


}
