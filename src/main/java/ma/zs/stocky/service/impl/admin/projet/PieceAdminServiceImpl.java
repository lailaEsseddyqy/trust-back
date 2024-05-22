package ma.zs.stocky.service.impl.admin.projet;


import ma.zs.stocky.zynerator.exception.EntityNotFoundException;
import ma.zs.stocky.bean.core.projet.Piece;
import ma.zs.stocky.dao.criteria.core.projet.PieceCriteria;
import ma.zs.stocky.dao.facade.core.projet.PieceDao;
import ma.zs.stocky.dao.specification.core.projet.PieceSpecification;
import ma.zs.stocky.service.facade.admin.projet.PieceAdminService;
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
public class PieceAdminServiceImpl implements PieceAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Piece update(Piece t) {
        Piece loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Piece.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Piece findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Piece findOrSave(Piece t) {
        if (t != null) {
            Piece result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Piece> importData(List<Piece> items) {
        List<Piece> list = new ArrayList<>();
        for (Piece t : items) {
            Piece founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Piece> findAll() {
        return dao.findAll();
    }

    public List<Piece> findByCriteria(PieceCriteria criteria) {
        List<Piece> content = null;
        if (criteria != null) {
            PieceSpecification mySpecification = constructSpecification(criteria);
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


    private PieceSpecification constructSpecification(PieceCriteria criteria) {
        PieceSpecification mySpecification =  (PieceSpecification) RefelexivityUtil.constructObjectUsingOneParam(PieceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Piece> findPaginatedByCriteria(PieceCriteria criteria, int page, int pageSize, String order, String sortField) {
        PieceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PieceCriteria criteria) {
        PieceSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Piece t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Piece> delete(List<Piece> list) {
		List<Piece> result = new ArrayList();
        if (list != null) {
            for (Piece t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Piece create(Piece t) {
        Piece loaded = findByReferenceEntity(t);
        Piece saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Piece> create(List<Piece> ts) {
        List<Piece> result = new ArrayList<>();
        if (ts != null) {
            for (Piece t : ts) {
				Piece created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Piece findWithAssociatedLists(Long id){
        Piece result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Piece> update(List<Piece> ts, boolean createIfNotExist) {
        List<Piece> result = new ArrayList<>();
        if (ts != null) {
            for (Piece t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Piece loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Piece findByReferenceEntity(Piece t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Piece> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Piece>> getToBeSavedAndToBeDeleted(List<Piece> oldList, List<Piece> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Piece> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PieceDao dao;


}
