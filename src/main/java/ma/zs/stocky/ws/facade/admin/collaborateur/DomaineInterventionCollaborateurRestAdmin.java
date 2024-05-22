package  ma.zs.stocky.ws.facade.admin.collaborateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCollaborateurCriteria;
import ma.zs.stocky.service.facade.admin.collaborateur.DomaineInterventionCollaborateurAdminService;
import ma.zs.stocky.ws.converter.collaborateur.DomaineInterventionCollaborateurConverter;
import ma.zs.stocky.ws.dto.collaborateur.DomaineInterventionCollaborateurDto;
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
@RequestMapping("/api/admin/domaineInterventionCollaborateur/")
public class DomaineInterventionCollaborateurRestAdmin {




    @Operation(summary = "Finds a list of all domaineInterventionCollaborateurs")
    @GetMapping("")
    public ResponseEntity<List<DomaineInterventionCollaborateurDto>> findAll() throws Exception {
        ResponseEntity<List<DomaineInterventionCollaborateurDto>> res = null;
        List<DomaineInterventionCollaborateur> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DomaineInterventionCollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a domaineInterventionCollaborateur by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DomaineInterventionCollaborateurDto> findById(@PathVariable Long id) {
        DomaineInterventionCollaborateur t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DomaineInterventionCollaborateurDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  domaineInterventionCollaborateur")
    @PostMapping("")
    public ResponseEntity<DomaineInterventionCollaborateurDto> save(@RequestBody DomaineInterventionCollaborateurDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            DomaineInterventionCollaborateur myT = converter.toItem(dto);
            DomaineInterventionCollaborateur t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DomaineInterventionCollaborateurDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  domaineInterventionCollaborateur")
    @PutMapping("")
    public ResponseEntity<DomaineInterventionCollaborateurDto> update(@RequestBody DomaineInterventionCollaborateurDto dto) throws Exception {
        ResponseEntity<DomaineInterventionCollaborateurDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DomaineInterventionCollaborateur t = service.findById(dto.getId());
            converter.copy(dto,t);
            DomaineInterventionCollaborateur updated = service.update(t);
            DomaineInterventionCollaborateurDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of domaineInterventionCollaborateur")
    @PostMapping("multiple")
    public ResponseEntity<List<DomaineInterventionCollaborateurDto>> delete(@RequestBody List<DomaineInterventionCollaborateurDto> dtos) throws Exception {
        ResponseEntity<List<DomaineInterventionCollaborateurDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<DomaineInterventionCollaborateur> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified domaineInterventionCollaborateur")
    @DeleteMapping("")
    public ResponseEntity<DomaineInterventionCollaborateurDto> delete(@RequestBody DomaineInterventionCollaborateurDto dto) throws Exception {
		ResponseEntity<DomaineInterventionCollaborateurDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            DomaineInterventionCollaborateur t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified domaineInterventionCollaborateur")
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
    @Operation(summary = "Delete multiple domaineInterventionCollaborateurs by ids")
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



    @Operation(summary = "Finds a domaineInterventionCollaborateur and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DomaineInterventionCollaborateurDto> findWithAssociatedLists(@PathVariable Long id) {
        DomaineInterventionCollaborateur loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DomaineInterventionCollaborateurDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds domaineInterventionCollaborateurs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DomaineInterventionCollaborateurDto>> findByCriteria(@RequestBody DomaineInterventionCollaborateurCriteria criteria) throws Exception {
        ResponseEntity<List<DomaineInterventionCollaborateurDto>> res = null;
        List<DomaineInterventionCollaborateur> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DomaineInterventionCollaborateurDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated domaineInterventionCollaborateurs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DomaineInterventionCollaborateurCriteria criteria) throws Exception {
        List<DomaineInterventionCollaborateur> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DomaineInterventionCollaborateurDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets domaineInterventionCollaborateur data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DomaineInterventionCollaborateurCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DomaineInterventionCollaborateurDto> findDtos(List<DomaineInterventionCollaborateur> list){
        converter.initObject(true);
        List<DomaineInterventionCollaborateurDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DomaineInterventionCollaborateurDto> getDtoResponseEntity(DomaineInterventionCollaborateurDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DomaineInterventionCollaborateurAdminService service;
    @Autowired private DomaineInterventionCollaborateurConverter converter;





}
