package  ma.zs.stocky.ws.facade.comptable.achat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.achat.Facture;
import ma.zs.stocky.dao.criteria.core.achat.FactureCriteria;
import ma.zs.stocky.service.facade.comptable.achat.FactureComptableService;
import ma.zs.stocky.ws.converter.achat.FactureConverter;
import ma.zs.stocky.ws.dto.achat.FactureDto;
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
@RequestMapping("/api/comptable/facture/")
public class FactureRestComptable {




    @Operation(summary = "Finds a list of all factures")
    @GetMapping("")
    public ResponseEntity<List<FactureDto>> findAll() throws Exception {
        ResponseEntity<List<FactureDto>> res = null;
        List<Facture> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<FactureDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a facture by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FactureDto> findById(@PathVariable Long id) {
        Facture t = service.findById(id);
        if (t != null) {
            converter.init(true);
            FactureDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  facture")
    @PostMapping("")
    public ResponseEntity<FactureDto> save(@RequestBody FactureDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Facture myT = converter.toItem(dto);
            Facture t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FactureDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  facture")
    @PutMapping("")
    public ResponseEntity<FactureDto> update(@RequestBody FactureDto dto) throws Exception {
        ResponseEntity<FactureDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Facture t = service.findById(dto.getId());
            converter.copy(dto,t);
            Facture updated = service.update(t);
            FactureDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of facture")
    @PostMapping("multiple")
    public ResponseEntity<List<FactureDto>> delete(@RequestBody List<FactureDto> dtos) throws Exception {
        ResponseEntity<List<FactureDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Facture> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified facture")
    @DeleteMapping("")
    public ResponseEntity<FactureDto> delete(@RequestBody FactureDto dto) throws Exception {
		ResponseEntity<FactureDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Facture t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified facture")
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
    @Operation(summary = "Delete multiple factures by ids")
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



    @Operation(summary = "Finds a facture and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FactureDto> findWithAssociatedLists(@PathVariable Long id) {
        Facture loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        FactureDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds factures by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FactureDto>> findByCriteria(@RequestBody FactureCriteria criteria) throws Exception {
        ResponseEntity<List<FactureDto>> res = null;
        List<Facture> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<FactureDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated factures by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FactureCriteria criteria) throws Exception {
        List<Facture> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<FactureDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets facture data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FactureCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FactureDto> findDtos(List<Facture> list){
        converter.initObject(true);
        List<FactureDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FactureDto> getDtoResponseEntity(FactureDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private FactureComptableService service;
    @Autowired private FactureConverter converter;





}
