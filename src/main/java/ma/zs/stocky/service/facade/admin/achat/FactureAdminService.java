package ma.zs.stocky.service.facade.admin.achat;

import java.util.List;
import ma.zs.stocky.bean.core.achat.Facture;
import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.dao.criteria.core.achat.FactureCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface FactureAdminService {



    List<Facture> findByTypeFactureId(Long id);
    int deleteByTypeFactureId(Long id);
    long countByTypeFactureCode(String code);



    List<Facture> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);

	Facture create(Facture t);

    void genererPDFetEnvoyer(Long factureId);

    Facture update(Facture t);

    List<Facture> update(List<Facture> ts,boolean createIfNotExist);

    Facture findById(Long id);

    Facture findOrSave(Facture t);

    Facture findByReferenceEntity(Facture t);

    Facture findWithAssociatedLists(Long id);

    List<Facture> findAllOptimized();

    List<Facture> findAll();

    List<Facture> findByCriteria(FactureCriteria criteria);

    List<Facture> findPaginatedByCriteria(FactureCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FactureCriteria criteria);

    List<Facture> delete(List<Facture> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Facture>> getToBeSavedAndToBeDeleted(List<Facture> oldList, List<Facture> newList);

    List<Facture> importData(List<Facture> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Facture> importExcel(MultipartFile file);

}
