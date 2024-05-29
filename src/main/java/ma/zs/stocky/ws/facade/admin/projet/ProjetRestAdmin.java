package  ma.zs.stocky.ws.facade.admin.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ma.zs.stocky.bean.core.article.Article;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCriteria;
import ma.zs.stocky.service.facade.admin.projet.ProjetAdminService;
import ma.zs.stocky.ws.converter.projet.ProjetConverter;
import ma.zs.stocky.ws.dto.projet.ProjetDto;
import ma.zs.stocky.zynerator.controller.AbstractController;
import ma.zs.stocky.zynerator.dto.AuditEntityDto;
import ma.zs.stocky.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.stocky.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.stocky.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/projet/")
public class ProjetRestAdmin {


    @Operation(summary = "Finds a list of all projets")
    @GetMapping("")
    public ResponseEntity<List<ProjetDto>> findAll() throws Exception {
        ResponseEntity<List<ProjetDto>> res = null;
        List<Projet> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<ProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all projets")
    @GetMapping("optimized")
    public ResponseEntity<List<ProjetDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProjetDto>> res = null;
        List<Projet> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a projet by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjetDto> findById(@PathVariable Long id) {
        Projet t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjetDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a projet by reference")
    @GetMapping("reference/{reference}")
    public ResponseEntity<ProjetDto> findByReference(@PathVariable String reference) {
	    Projet t = service.findByReferenceEntity(new Projet(reference));
        if (t != null) {
            converter.init(true);
            ProjetDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  projet")
    @PostMapping("")
    public ResponseEntity<ProjetDto> save(@RequestBody ProjetDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Projet myT = converter.toItem(dto);
            Projet t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjetDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projet")
    @PutMapping("")
    public ResponseEntity<ProjetDto> update(@RequestBody ProjetDto dto) throws Exception {
        ResponseEntity<ProjetDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Projet t = service.findById(dto.getId());
            converter.copy(dto,t);
            Projet updated = service.update(t);
            ProjetDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projet")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjetDto>> delete(@RequestBody List<ProjetDto> dtos) throws Exception {
        ResponseEntity<List<ProjetDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Projet> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified projet")
    @DeleteMapping("")
    public ResponseEntity<ProjetDto> delete(@RequestBody ProjetDto dto) throws Exception {
		ResponseEntity<ProjetDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Projet t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified projet")
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
    @Operation(summary = "Delete multiple projets by ids")
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



    @Operation(summary = "Finds a projet and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjetDto> findWithAssociatedLists(@PathVariable Long id) {
        Projet loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjetDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projets by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjetDto>> findByCriteria(@RequestBody ProjetCriteria criteria) throws Exception {
        ResponseEntity<List<ProjetDto>> res = null;
        List<Projet> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<ProjetDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projets by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjetCriteria criteria) throws Exception {
        List<Projet> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<ProjetDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projet data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjetCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }



    public List<ProjetDto> findDtos(List<Projet> list){
        converter.initList(false);
        converter.initObject(true);
        List<ProjetDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjetDto> getDtoResponseEntity(ProjetDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjetAdminService service;
    @Autowired private ProjetConverter converter;





}
