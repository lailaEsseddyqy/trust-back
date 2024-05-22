package  ma.zs.stocky.ws.facade.responsableachat.achat;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.achat.EtatAchat;
import ma.zs.stocky.dao.criteria.core.achat.EtatAchatCriteria;
import ma.zs.stocky.service.facade.responsableachat.achat.EtatAchatResponsableachatService;
import ma.zs.stocky.ws.converter.achat.EtatAchatConverter;
import ma.zs.stocky.ws.dto.achat.EtatAchatDto;
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
@RequestMapping("/api/responsableachat/etatAchat/")
public class EtatAchatRestResponsableachat {




    @Operation(summary = "Finds a list of all etatAchats")
    @GetMapping("")
    public ResponseEntity<List<EtatAchatDto>> findAll() throws Exception {
        ResponseEntity<List<EtatAchatDto>> res = null;
        List<EtatAchat> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAchatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatAchats")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatAchatDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EtatAchatDto>> res = null;
        List<EtatAchat> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAchatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatAchat by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatAchatDto> findById(@PathVariable Long id) {
        EtatAchat t = service.findById(id);
        if (t != null) {
            EtatAchatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatAchat by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatAchatDto> findByLibelle(@PathVariable String libelle) {
	    EtatAchat t = service.findByReferenceEntity(new EtatAchat(libelle));
        if (t != null) {
            EtatAchatDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatAchat")
    @PostMapping("")
    public ResponseEntity<EtatAchatDto> save(@RequestBody EtatAchatDto dto) throws Exception {
        if(dto!=null){
            EtatAchat myT = converter.toItem(dto);
            EtatAchat t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatAchatDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatAchat")
    @PutMapping("")
    public ResponseEntity<EtatAchatDto> update(@RequestBody EtatAchatDto dto) throws Exception {
        ResponseEntity<EtatAchatDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatAchat t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatAchat updated = service.update(t);
            EtatAchatDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatAchat")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatAchatDto>> delete(@RequestBody List<EtatAchatDto> dtos) throws Exception {
        ResponseEntity<List<EtatAchatDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<EtatAchat> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified etatAchat")
    @DeleteMapping("")
    public ResponseEntity<EtatAchatDto> delete(@RequestBody EtatAchatDto dto) throws Exception {
		ResponseEntity<EtatAchatDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            EtatAchat t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatAchat")
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
    @Operation(summary = "Delete multiple etatAchats by ids")
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



    @Operation(summary = "Finds a etatAchat and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatAchatDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatAchat loaded =  service.findWithAssociatedLists(id);
        EtatAchatDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatAchats by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatAchatDto>> findByCriteria(@RequestBody EtatAchatCriteria criteria) throws Exception {
        ResponseEntity<List<EtatAchatDto>> res = null;
        List<EtatAchat> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EtatAchatDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatAchats by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatAchatCriteria criteria) throws Exception {
        List<EtatAchat> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EtatAchatDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatAchat data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatAchatCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatAchatDto> findDtos(List<EtatAchat> list){
        List<EtatAchatDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatAchatDto> getDtoResponseEntity(EtatAchatDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EtatAchatResponsableachatService service;
    @Autowired private EtatAchatConverter converter;





}
