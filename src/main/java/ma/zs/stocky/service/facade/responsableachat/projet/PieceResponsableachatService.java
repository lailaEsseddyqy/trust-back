package ma.zs.stocky.service.facade.responsableachat.projet;

import java.util.List;
import ma.zs.stocky.bean.core.projet.Piece;
import ma.zs.stocky.dao.criteria.core.projet.PieceCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PieceResponsableachatService {







	Piece create(Piece t);

    Piece update(Piece t);

    List<Piece> update(List<Piece> ts,boolean createIfNotExist);

    Piece findById(Long id);

    Piece findOrSave(Piece t);

    Piece findByReferenceEntity(Piece t);

    Piece findWithAssociatedLists(Long id);

    List<Piece> findAllOptimized();

    List<Piece> findAll();

    List<Piece> findByCriteria(PieceCriteria criteria);

    List<Piece> findPaginatedByCriteria(PieceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PieceCriteria criteria);

    List<Piece> delete(List<Piece> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Piece>> getToBeSavedAndToBeDeleted(List<Piece> oldList, List<Piece> newList);

    List<Piece> importData(List<Piece> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Piece> importExcel(MultipartFile file);

}
