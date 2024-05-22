package ma.zs.stocky.service.facade.admin.commun;

import java.util.List;
import ma.zs.stocky.bean.core.commun.Client;
import ma.zs.stocky.dao.criteria.core.commun.ClientCriteria;
import ma.zs.stocky.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface ClientAdminService {



    List<Client> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteId(Long id);




	Client create(Client t);

    Client update(Client t);

    List<Client> update(List<Client> ts,boolean createIfNotExist);

    Client findById(Long id);

    Client findOrSave(Client t);

    Client findByReferenceEntity(Client t);

    Client findWithAssociatedLists(Long id);

    List<Client> findAllOptimized();

    List<Client> findAll();

    List<Client> findByCriteria(ClientCriteria criteria);

    List<Client> findPaginatedByCriteria(ClientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ClientCriteria criteria);

    List<Client> delete(List<Client> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Client>> getToBeSavedAndToBeDeleted(List<Client> oldList, List<Client> newList);

    List<Client> importData(List<Client> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Client> importExcel(MultipartFile file);

}
