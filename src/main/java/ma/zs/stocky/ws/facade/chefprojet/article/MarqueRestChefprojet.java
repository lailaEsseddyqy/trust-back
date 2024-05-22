package  ma.zs.stocky.ws.facade.chefprojet.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.article.Marque;
import ma.zs.stocky.dao.criteria.core.article.MarqueCriteria;
import ma.zs.stocky.service.facade.chefprojet.article.MarqueChefprojetService;
import ma.zs.stocky.ws.converter.article.MarqueConverter;
import ma.zs.stocky.ws.dto.article.MarqueDto;
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
@RequestMapping("/api/chefprojet/marque/")
public class MarqueRestChefprojet {




    @Operation(summary = "Finds a list of all marques")
    @GetMapping("")
    public ResponseEntity<List<MarqueDto>> findAll() throws Exception {
        ResponseEntity<List<MarqueDto>> res = null;
        List<Marque> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MarqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all marques")
    @GetMapping("optimized")
    public ResponseEntity<List<MarqueDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MarqueDto>> res = null;
        List<Marque> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MarqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a marque by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MarqueDto> findById(@PathVariable Long id) {
        Marque t = service.findById(id);
        if (t != null) {
            MarqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a marque by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MarqueDto> findByLibelle(@PathVariable String libelle) {
	    Marque t = service.findByReferenceEntity(new Marque(libelle));
        if (t != null) {
            MarqueDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  marque")
    @PostMapping("")
    public ResponseEntity<MarqueDto> save(@RequestBody MarqueDto dto) throws Exception {
        if(dto!=null){
            Marque myT = converter.toItem(dto);
            Marque t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MarqueDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  marque")
    @PutMapping("")
    public ResponseEntity<MarqueDto> update(@RequestBody MarqueDto dto) throws Exception {
        ResponseEntity<MarqueDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Marque t = service.findById(dto.getId());
            converter.copy(dto,t);
            Marque updated = service.update(t);
            MarqueDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of marque")
    @PostMapping("multiple")
    public ResponseEntity<List<MarqueDto>> delete(@RequestBody List<MarqueDto> dtos) throws Exception {
        ResponseEntity<List<MarqueDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Marque> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified marque")
    @DeleteMapping("")
    public ResponseEntity<MarqueDto> delete(@RequestBody MarqueDto dto) throws Exception {
		ResponseEntity<MarqueDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Marque t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified marque")
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
    @Operation(summary = "Delete multiple marques by ids")
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



    @Operation(summary = "Finds a marque and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MarqueDto> findWithAssociatedLists(@PathVariable Long id) {
        Marque loaded =  service.findWithAssociatedLists(id);
        MarqueDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds marques by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MarqueDto>> findByCriteria(@RequestBody MarqueCriteria criteria) throws Exception {
        ResponseEntity<List<MarqueDto>> res = null;
        List<Marque> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MarqueDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated marques by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MarqueCriteria criteria) throws Exception {
        List<Marque> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<MarqueDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets marque data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MarqueCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MarqueDto> findDtos(List<Marque> list){
        List<MarqueDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MarqueDto> getDtoResponseEntity(MarqueDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private MarqueChefprojetService service;
    @Autowired private MarqueConverter converter;





}
