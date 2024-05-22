package  ma.zs.stocky.ws.facade.responsableachat.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;
import ma.zs.stocky.service.facade.responsableachat.commun.FournisseurResponsableachatService;
import ma.zs.stocky.ws.converter.commun.FournisseurConverter;
import ma.zs.stocky.ws.dto.commun.FournisseurDto;
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
@RequestMapping("/api/responsableachat/fournisseur/")
public class FournisseurRestResponsableachat {




    @Operation(summary = "Finds a list of all fournisseurs")
    @GetMapping("")
    public ResponseEntity<List<FournisseurDto>> findAll() throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all fournisseurs")
    @GetMapping("optimized")
    public ResponseEntity<List<FournisseurDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a fournisseur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FournisseurDto> findById(@PathVariable Long id) {
        Fournisseur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            FournisseurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a fournisseur by code")
    @GetMapping("code/{code}")
    public ResponseEntity<FournisseurDto> findByCode(@PathVariable String code) {
	    Fournisseur t = service.findByReferenceEntity(new Fournisseur(code));
        if (t != null) {
            converter.init(true);
            FournisseurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  fournisseur")
    @PostMapping("")
    public ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Fournisseur myT = converter.toItem(dto);
            Fournisseur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FournisseurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  fournisseur")
    @PutMapping("")
    public ResponseEntity<FournisseurDto> update(@RequestBody FournisseurDto dto) throws Exception {
        ResponseEntity<FournisseurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Fournisseur t = service.findById(dto.getId());
            converter.copy(dto,t);
            Fournisseur updated = service.update(t);
            FournisseurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of fournisseur")
    @PostMapping("multiple")
    public ResponseEntity<List<FournisseurDto>> delete(@RequestBody List<FournisseurDto> dtos) throws Exception {
        ResponseEntity<List<FournisseurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Fournisseur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified fournisseur")
    @DeleteMapping("")
    public ResponseEntity<FournisseurDto> delete(@RequestBody FournisseurDto dto) throws Exception {
		ResponseEntity<FournisseurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Fournisseur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified fournisseur")
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
    @Operation(summary = "Delete multiple fournisseurs by ids")
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
    public List<FournisseurDto> findBySocieteId(@PathVariable Long id){
        return findDtos(service.findBySocieteId(id));
    }
    @Operation(summary = "delete by societe id")
    @DeleteMapping("societe/id/{id}")
    public int deleteBySocieteId(@PathVariable Long id){
        return service.deleteBySocieteId(id);
    }

    @Operation(summary = "Finds a fournisseur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FournisseurDto> findWithAssociatedLists(@PathVariable Long id) {
        Fournisseur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        FournisseurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds fournisseurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FournisseurDto>> findByCriteria(@RequestBody FournisseurCriteria criteria) throws Exception {
        ResponseEntity<List<FournisseurDto>> res = null;
        List<Fournisseur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FournisseurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated fournisseurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FournisseurCriteria criteria) throws Exception {
        List<Fournisseur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<FournisseurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets fournisseur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FournisseurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FournisseurDto> findDtos(List<Fournisseur> list){
        converter.initObject(true);
        List<FournisseurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FournisseurDto> getDtoResponseEntity(FournisseurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private FournisseurResponsableachatService service;
    @Autowired private FournisseurConverter converter;





}
