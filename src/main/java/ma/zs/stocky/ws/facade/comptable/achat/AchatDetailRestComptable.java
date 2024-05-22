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

import ma.zs.stocky.bean.core.achat.AchatDetail;
import ma.zs.stocky.dao.criteria.core.achat.AchatDetailCriteria;
import ma.zs.stocky.service.facade.comptable.achat.AchatDetailComptableService;
import ma.zs.stocky.ws.converter.achat.AchatDetailConverter;
import ma.zs.stocky.ws.dto.achat.AchatDetailDto;
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
@RequestMapping("/api/comptable/achatDetail/")
public class AchatDetailRestComptable {




    @Operation(summary = "Finds a list of all achatDetails")
    @GetMapping("")
    public ResponseEntity<List<AchatDetailDto>> findAll() throws Exception {
        ResponseEntity<List<AchatDetailDto>> res = null;
        List<AchatDetail> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AchatDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a achatDetail by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AchatDetailDto> findById(@PathVariable Long id) {
        AchatDetail t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AchatDetailDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  achatDetail")
    @PostMapping("")
    public ResponseEntity<AchatDetailDto> save(@RequestBody AchatDetailDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            AchatDetail myT = converter.toItem(dto);
            AchatDetail t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AchatDetailDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  achatDetail")
    @PutMapping("")
    public ResponseEntity<AchatDetailDto> update(@RequestBody AchatDetailDto dto) throws Exception {
        ResponseEntity<AchatDetailDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            AchatDetail t = service.findById(dto.getId());
            converter.copy(dto,t);
            AchatDetail updated = service.update(t);
            AchatDetailDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of achatDetail")
    @PostMapping("multiple")
    public ResponseEntity<List<AchatDetailDto>> delete(@RequestBody List<AchatDetailDto> dtos) throws Exception {
        ResponseEntity<List<AchatDetailDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<AchatDetail> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified achatDetail")
    @DeleteMapping("")
    public ResponseEntity<AchatDetailDto> delete(@RequestBody AchatDetailDto dto) throws Exception {
		ResponseEntity<AchatDetailDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            AchatDetail t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified achatDetail")
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
    @Operation(summary = "Delete multiple achatDetails by ids")
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


    @Operation(summary = "find by article id")
    @GetMapping("article/id/{id}")
    public List<AchatDetailDto> findByArticleId(@PathVariable Long id){
        return findDtos(service.findByArticleId(id));
    }
    @Operation(summary = "delete by article id")
    @DeleteMapping("article/id/{id}")
    public int deleteByArticleId(@PathVariable Long id){
        return service.deleteByArticleId(id);
    }
    @Operation(summary = "find by achat id")
    @GetMapping("achat/id/{id}")
    public List<AchatDetailDto> findByAchatId(@PathVariable Long id){
        return findDtos(service.findByAchatId(id));
    }
    @Operation(summary = "delete by achat id")
    @DeleteMapping("achat/id/{id}")
    public int deleteByAchatId(@PathVariable Long id){
        return service.deleteByAchatId(id);
    }

    @Operation(summary = "Finds a achatDetail and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AchatDetailDto> findWithAssociatedLists(@PathVariable Long id) {
        AchatDetail loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AchatDetailDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds achatDetails by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AchatDetailDto>> findByCriteria(@RequestBody AchatDetailCriteria criteria) throws Exception {
        ResponseEntity<List<AchatDetailDto>> res = null;
        List<AchatDetail> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AchatDetailDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated achatDetails by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AchatDetailCriteria criteria) throws Exception {
        List<AchatDetail> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AchatDetailDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets achatDetail data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AchatDetailCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AchatDetailDto> findDtos(List<AchatDetail> list){
        converter.initObject(true);
        List<AchatDetailDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AchatDetailDto> getDtoResponseEntity(AchatDetailDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private AchatDetailComptableService service;
    @Autowired private AchatDetailConverter converter;





}
