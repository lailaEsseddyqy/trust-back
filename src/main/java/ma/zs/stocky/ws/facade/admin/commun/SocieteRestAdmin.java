package  ma.zs.stocky.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.commun.Societe;
import ma.zs.stocky.dao.criteria.core.commun.SocieteCriteria;
import ma.zs.stocky.service.facade.admin.commun.SocieteAdminService;
import ma.zs.stocky.ws.converter.commun.SocieteConverter;
import ma.zs.stocky.ws.dto.commun.SocieteDto;
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
@RequestMapping("/api/admin/societe/")
public class SocieteRestAdmin {




    @Operation(summary = "Finds a list of all societes")
    @GetMapping("")
    public ResponseEntity<List<SocieteDto>> findAll() throws Exception {
        ResponseEntity<List<SocieteDto>> res = null;
        List<Societe> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SocieteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a societe by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SocieteDto> findById(@PathVariable Long id) {
        Societe t = service.findById(id);
        if (t != null) {
            SocieteDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  societe")
    @PostMapping("")
    public ResponseEntity<SocieteDto> save(@RequestBody SocieteDto dto) throws Exception {
        if(dto!=null){
            Societe myT = converter.toItem(dto);
            Societe t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SocieteDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  societe")
    @PutMapping("")
    public ResponseEntity<SocieteDto> update(@RequestBody SocieteDto dto) throws Exception {
        ResponseEntity<SocieteDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Societe t = service.findById(dto.getId());
            converter.copy(dto,t);
            Societe updated = service.update(t);
            SocieteDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of societe")
    @PostMapping("multiple")
    public ResponseEntity<List<SocieteDto>> delete(@RequestBody List<SocieteDto> dtos) throws Exception {
        ResponseEntity<List<SocieteDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Societe> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified societe")
    @DeleteMapping("")
    public ResponseEntity<SocieteDto> delete(@RequestBody SocieteDto dto) throws Exception {
		ResponseEntity<SocieteDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Societe t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified societe")
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
    @Operation(summary = "Delete multiple societes by ids")
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



    @Operation(summary = "Finds a societe and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SocieteDto> findWithAssociatedLists(@PathVariable Long id) {
        Societe loaded =  service.findWithAssociatedLists(id);
        SocieteDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds societes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SocieteDto>> findByCriteria(@RequestBody SocieteCriteria criteria) throws Exception {
        ResponseEntity<List<SocieteDto>> res = null;
        List<Societe> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SocieteDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated societes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SocieteCriteria criteria) throws Exception {
        List<Societe> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SocieteDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets societe data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SocieteCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SocieteDto> findDtos(List<Societe> list){
        List<SocieteDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SocieteDto> getDtoResponseEntity(SocieteDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private SocieteAdminService service;
    @Autowired private SocieteConverter converter;





}
