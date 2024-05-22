package  ma.zs.stocky.ws.facade.responsableachat.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import ma.zs.stocky.dao.criteria.core.projet.ProjetCollaborateurCriteria;
import ma.zs.stocky.service.facade.responsableachat.projet.ProjetCollaborateurResponsableachatService;
import ma.zs.stocky.ws.converter.projet.ProjetCollaborateurConverter;
import ma.zs.stocky.ws.dto.projet.ProjetCollaborateurDto;
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
@RequestMapping("/api/responsableachat/projetCollaborateur/")
public class ProjetCollaborateurRestResponsableachat {




    @Operation(summary = "Finds a list of all projetCollaborateurs")
    @GetMapping("")
    public ResponseEntity<List<ProjetCollaborateurDto>> findAll() throws Exception {
        ResponseEntity<List<ProjetCollaborateurDto>> res = null;
        List<ProjetCollaborateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProjetCollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a projetCollaborateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjetCollaborateurDto> findById(@PathVariable Long id) {
        ProjetCollaborateur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjetCollaborateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  projetCollaborateur")
    @PostMapping("")
    public ResponseEntity<ProjetCollaborateurDto> save(@RequestBody ProjetCollaborateurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ProjetCollaborateur myT = converter.toItem(dto);
            ProjetCollaborateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjetCollaborateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projetCollaborateur")
    @PutMapping("")
    public ResponseEntity<ProjetCollaborateurDto> update(@RequestBody ProjetCollaborateurDto dto) throws Exception {
        ResponseEntity<ProjetCollaborateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjetCollaborateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjetCollaborateur updated = service.update(t);
            ProjetCollaborateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projetCollaborateur")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjetCollaborateurDto>> delete(@RequestBody List<ProjetCollaborateurDto> dtos) throws Exception {
        ResponseEntity<List<ProjetCollaborateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjetCollaborateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified projetCollaborateur")
    @DeleteMapping("")
    public ResponseEntity<ProjetCollaborateurDto> delete(@RequestBody ProjetCollaborateurDto dto) throws Exception {
		ResponseEntity<ProjetCollaborateurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            ProjetCollaborateur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified projetCollaborateur")
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
    @Operation(summary = "Delete multiple projetCollaborateurs by ids")
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



    @Operation(summary = "Finds a projetCollaborateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjetCollaborateurDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjetCollaborateur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjetCollaborateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projetCollaborateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjetCollaborateurDto>> findByCriteria(@RequestBody ProjetCollaborateurCriteria criteria) throws Exception {
        ResponseEntity<List<ProjetCollaborateurDto>> res = null;
        List<ProjetCollaborateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjetCollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projetCollaborateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjetCollaborateurCriteria criteria) throws Exception {
        List<ProjetCollaborateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProjetCollaborateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projetCollaborateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjetCollaborateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjetCollaborateurDto> findDtos(List<ProjetCollaborateur> list){
        converter.initObject(true);
        List<ProjetCollaborateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjetCollaborateurDto> getDtoResponseEntity(ProjetCollaborateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjetCollaborateurResponsableachatService service;
    @Autowired private ProjetCollaborateurConverter converter;





}
