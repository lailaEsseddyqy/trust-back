package  ma.zs.stocky.ws.facade.comptable.collaborateur;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCriteria;
import ma.zs.stocky.service.facade.comptable.collaborateur.DomaineInterventionComptableService;
import ma.zs.stocky.ws.converter.collaborateur.DomaineInterventionConverter;
import ma.zs.stocky.ws.dto.collaborateur.DomaineInterventionDto;
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
@RequestMapping("/api/comptable/domaineIntervention/")
public class DomaineInterventionRestComptable {




    @Operation(summary = "Finds a list of all domaineInterventions")
    @GetMapping("")
    public ResponseEntity<List<DomaineInterventionDto>> findAll() throws Exception {
        ResponseEntity<List<DomaineInterventionDto>> res = null;
        List<DomaineIntervention> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineInterventionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all domaineInterventions")
    @GetMapping("optimized")
    public ResponseEntity<List<DomaineInterventionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<DomaineInterventionDto>> res = null;
        List<DomaineIntervention> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineInterventionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a domaineIntervention by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DomaineInterventionDto> findById(@PathVariable Long id) {
        DomaineIntervention t = service.findById(id);
        if (t != null) {
            DomaineInterventionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a domaineIntervention by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<DomaineInterventionDto> findByLibelle(@PathVariable String libelle) {
	    DomaineIntervention t = service.findByReferenceEntity(new DomaineIntervention(libelle));
        if (t != null) {
            DomaineInterventionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  domaineIntervention")
    @PostMapping("")
    public ResponseEntity<DomaineInterventionDto> save(@RequestBody DomaineInterventionDto dto) throws Exception {
        if(dto!=null){
            DomaineIntervention myT = converter.toItem(dto);
            DomaineIntervention t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DomaineInterventionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  domaineIntervention")
    @PutMapping("")
    public ResponseEntity<DomaineInterventionDto> update(@RequestBody DomaineInterventionDto dto) throws Exception {
        ResponseEntity<DomaineInterventionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            DomaineIntervention t = service.findById(dto.getId());
            converter.copy(dto,t);
            DomaineIntervention updated = service.update(t);
            DomaineInterventionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of domaineIntervention")
    @PostMapping("multiple")
    public ResponseEntity<List<DomaineInterventionDto>> delete(@RequestBody List<DomaineInterventionDto> dtos) throws Exception {
        ResponseEntity<List<DomaineInterventionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<DomaineIntervention> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified domaineIntervention")
    @DeleteMapping("")
    public ResponseEntity<DomaineInterventionDto> delete(@RequestBody DomaineInterventionDto dto) throws Exception {
		ResponseEntity<DomaineInterventionDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            DomaineIntervention t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified domaineIntervention")
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
    @Operation(summary = "Delete multiple domaineInterventions by ids")
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



    @Operation(summary = "Finds a domaineIntervention and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DomaineInterventionDto> findWithAssociatedLists(@PathVariable Long id) {
        DomaineIntervention loaded =  service.findWithAssociatedLists(id);
        DomaineInterventionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds domaineInterventions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DomaineInterventionDto>> findByCriteria(@RequestBody DomaineInterventionCriteria criteria) throws Exception {
        ResponseEntity<List<DomaineInterventionDto>> res = null;
        List<DomaineIntervention> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<DomaineInterventionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated domaineInterventions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DomaineInterventionCriteria criteria) throws Exception {
        List<DomaineIntervention> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<DomaineInterventionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets domaineIntervention data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DomaineInterventionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DomaineInterventionDto> findDtos(List<DomaineIntervention> list){
        List<DomaineInterventionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DomaineInterventionDto> getDtoResponseEntity(DomaineInterventionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private DomaineInterventionComptableService service;
    @Autowired private DomaineInterventionConverter converter;





}
