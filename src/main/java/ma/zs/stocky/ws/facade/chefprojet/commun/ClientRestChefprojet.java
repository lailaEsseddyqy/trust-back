package  ma.zs.stocky.ws.facade.chefprojet.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.commun.Client;
import ma.zs.stocky.dao.criteria.core.commun.ClientCriteria;
import ma.zs.stocky.service.facade.chefprojet.commun.ClientChefprojetService;
import ma.zs.stocky.ws.converter.commun.ClientConverter;
import ma.zs.stocky.ws.dto.commun.ClientDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/chefprojet/client/")
public class ClientRestChefprojet {




    @Operation(summary = "Finds a list of all clients")
    @GetMapping("")
    public ResponseEntity<List<ClientDto>> findAll() throws Exception {
        ResponseEntity<List<ClientDto>> res = null;
        List<Client> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all clients")
    @GetMapping("optimized")
    public ResponseEntity<List<ClientDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ClientDto>> res = null;
        List<Client> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a client by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        Client t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ClientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a client by code")
    @GetMapping("code/{code}")
    public ResponseEntity<ClientDto> findByCode(@PathVariable String code) {
	    Client t = service.findByReferenceEntity(new Client(code));
        if (t != null) {
            converter.init(true);
            ClientDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  client")
    @PostMapping("")
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Client myT = converter.toItem(dto);
            Client t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ClientDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  client")
    @PutMapping("")
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto dto) throws Exception {
        ResponseEntity<ClientDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Client t = service.findById(dto.getId());
            converter.copy(dto,t);
            Client updated = service.update(t);
            ClientDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of client")
    @PostMapping("multiple")
    public ResponseEntity<List<ClientDto>> delete(@RequestBody List<ClientDto> dtos) throws Exception {
        ResponseEntity<List<ClientDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Client> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified client")
    @DeleteMapping("")
    public ResponseEntity<ClientDto> delete(@RequestBody ClientDto dto) throws Exception {
		ResponseEntity<ClientDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Client t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified client")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }
    @Operation(summary = "Delete multiple clients by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
        ResponseEntity<List<Long>> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (ids != null) {
            service.deleteByIdIn(ids);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(ids, status);
        return res;
     }


    @Operation(summary = "find by societe id")
    @GetMapping("societe/id/{id}")
    public List<ClientDto> findBySocieteId(@PathVariable Long id){
        return findDtos(service.findBySocieteId(id));
    }
    @Operation(summary = "delete by societe id")
    @DeleteMapping("societe/id/{id}")
    public int deleteBySocieteId(@PathVariable Long id){
        return service.deleteBySocieteId(id);
    }

    @Operation(summary = "Finds a client and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ClientDto> findWithAssociatedLists(@PathVariable Long id) {
        Client loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ClientDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds clients by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ClientDto>> findByCriteria(@RequestBody ClientCriteria criteria) throws Exception {
        ResponseEntity<List<ClientDto>> res = null;
        List<Client> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ClientDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated clients by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ClientCriteria criteria) throws Exception {
        List<Client> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ClientDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets client data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ClientCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ClientDto> findDtos(List<Client> list){
        converter.initObject(true);
        List<ClientDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ClientDto> getDtoResponseEntity(ClientDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ClientChefprojetService service;
    @Autowired private ClientConverter converter;





}
