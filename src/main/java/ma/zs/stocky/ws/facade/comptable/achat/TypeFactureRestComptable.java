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

import ma.zs.stocky.bean.core.achat.TypeFacture;
import ma.zs.stocky.dao.criteria.core.achat.TypeFactureCriteria;
import ma.zs.stocky.service.facade.comptable.achat.TypeFactureComptableService;
import ma.zs.stocky.ws.converter.achat.TypeFactureConverter;
import ma.zs.stocky.ws.dto.achat.TypeFactureDto;
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
@RequestMapping("/api/comptable/typeFacture/")
public class TypeFactureRestComptable {




    @Operation(summary = "Finds a list of all typeFactures")
    @GetMapping("")
    public ResponseEntity<List<TypeFactureDto>> findAll() throws Exception {
        ResponseEntity<List<TypeFactureDto>> res = null;
        List<TypeFacture> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeFactureDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all typeFactures")
    @GetMapping("optimized")
    public ResponseEntity<List<TypeFactureDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<TypeFactureDto>> res = null;
        List<TypeFacture> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeFactureDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a typeFacture by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeFactureDto> findById(@PathVariable Long id) {
        TypeFacture t = service.findById(id);
        if (t != null) {
            TypeFactureDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a typeFacture by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<TypeFactureDto> findByLibelle(@PathVariable String libelle) {
	    TypeFacture t = service.findByReferenceEntity(new TypeFacture(libelle));
        if (t != null) {
            TypeFactureDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  typeFacture")
    @PostMapping("")
    public ResponseEntity<TypeFactureDto> save(@RequestBody TypeFactureDto dto) throws Exception {
        if(dto!=null){
            TypeFacture myT = converter.toItem(dto);
            TypeFacture t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeFactureDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeFacture")
    @PutMapping("")
    public ResponseEntity<TypeFactureDto> update(@RequestBody TypeFactureDto dto) throws Exception {
        ResponseEntity<TypeFactureDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeFacture t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeFacture updated = service.update(t);
            TypeFactureDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeFacture")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeFactureDto>> delete(@RequestBody List<TypeFactureDto> dtos) throws Exception {
        ResponseEntity<List<TypeFactureDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeFacture> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified typeFacture")
    @DeleteMapping("")
    public ResponseEntity<TypeFactureDto> delete(@RequestBody TypeFactureDto dto) throws Exception {
		ResponseEntity<TypeFactureDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            TypeFacture t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeFacture")
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
    @Operation(summary = "Delete multiple typeFactures by ids")
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



    @Operation(summary = "Finds a typeFacture and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeFactureDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeFacture loaded =  service.findWithAssociatedLists(id);
        TypeFactureDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeFactures by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeFactureDto>> findByCriteria(@RequestBody TypeFactureCriteria criteria) throws Exception {
        ResponseEntity<List<TypeFactureDto>> res = null;
        List<TypeFacture> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeFactureDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeFactures by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeFactureCriteria criteria) throws Exception {
        List<TypeFacture> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeFactureDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeFacture data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeFactureCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeFactureDto> findDtos(List<TypeFacture> list){
        List<TypeFactureDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeFactureDto> getDtoResponseEntity(TypeFactureDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private TypeFactureComptableService service;
    @Autowired private TypeFactureConverter converter;





}
