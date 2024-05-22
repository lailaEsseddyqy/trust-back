package  ma.zs.stocky.ws.facade.chefprojet.achat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.achat.Achat;
import ma.zs.stocky.dao.criteria.core.achat.AchatCriteria;
import ma.zs.stocky.service.facade.chefprojet.achat.AchatChefprojetService;
import ma.zs.stocky.ws.converter.achat.AchatConverter;
import ma.zs.stocky.ws.dto.achat.AchatDto;
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
@RequestMapping("/api/chefprojet/achat/")
public class AchatRestChefprojet {




    @Operation(summary = "Finds a list of all achats")
    @GetMapping("")
    public ResponseEntity<List<AchatDto>> findAll() throws Exception {
        ResponseEntity<List<AchatDto>> res = null;
        List<Achat> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<AchatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a achat by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AchatDto> findById(@PathVariable Long id) {
        Achat t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AchatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  achat")
    @PostMapping("")
    public ResponseEntity<AchatDto> save(@RequestBody AchatDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Achat myT = converter.toItem(dto);
            Achat t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AchatDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  achat")
    @PutMapping("")
    public ResponseEntity<AchatDto> update(@RequestBody AchatDto dto) throws Exception {
        ResponseEntity<AchatDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Achat t = service.findById(dto.getId());
            converter.copy(dto,t);
            Achat updated = service.update(t);
            AchatDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of achat")
    @PostMapping("multiple")
    public ResponseEntity<List<AchatDto>> delete(@RequestBody List<AchatDto> dtos) throws Exception {
        ResponseEntity<List<AchatDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Achat> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified achat")
    @DeleteMapping("")
    public ResponseEntity<AchatDto> delete(@RequestBody AchatDto dto) throws Exception {
		ResponseEntity<AchatDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Achat t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified achat")
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
    @Operation(summary = "Delete multiple achats by ids")
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


    @Operation(summary = "find by etatAchat id")
    @GetMapping("etatAchat/id/{id}")
    public List<AchatDto> findByEtatAchatId(@PathVariable Long id){
        return findDtos(service.findByEtatAchatId(id));
    }
    @Operation(summary = "delete by etatAchat id")
    @DeleteMapping("etatAchat/id/{id}")
    public int deleteByEtatAchatId(@PathVariable Long id){
        return service.deleteByEtatAchatId(id);
    }

    @Operation(summary = "Finds a achat and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AchatDto> findWithAssociatedLists(@PathVariable Long id) {
        Achat loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AchatDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds achats by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AchatDto>> findByCriteria(@RequestBody AchatCriteria criteria) throws Exception {
        ResponseEntity<List<AchatDto>> res = null;
        List<Achat> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<AchatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated achats by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AchatCriteria criteria) throws Exception {
        List<Achat> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<AchatDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets achat data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AchatCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AchatDto> findDtos(List<Achat> list){
        converter.initList(false);
        converter.initObject(true);
        List<AchatDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AchatDto> getDtoResponseEntity(AchatDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AchatChefprojetService service;
    @Autowired private AchatConverter converter;





}
