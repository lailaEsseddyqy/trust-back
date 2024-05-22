package  ma.zs.stocky.ws.facade.admin.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.projet.Piece;
import ma.zs.stocky.dao.criteria.core.projet.PieceCriteria;
import ma.zs.stocky.service.facade.admin.projet.PieceAdminService;
import ma.zs.stocky.ws.converter.projet.PieceConverter;
import ma.zs.stocky.ws.dto.projet.PieceDto;
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
@RequestMapping("/api/admin/piece/")
public class PieceRestAdmin {




    @Operation(summary = "Finds a list of all pieces")
    @GetMapping("")
    public ResponseEntity<List<PieceDto>> findAll() throws Exception {
        ResponseEntity<List<PieceDto>> res = null;
        List<Piece> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PieceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all pieces")
    @GetMapping("optimized")
    public ResponseEntity<List<PieceDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PieceDto>> res = null;
        List<Piece> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PieceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a piece by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PieceDto> findById(@PathVariable Long id) {
        Piece t = service.findById(id);
        if (t != null) {
            PieceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a piece by code")
    @GetMapping("code/{code}")
    public ResponseEntity<PieceDto> findByCode(@PathVariable String code) {
	    Piece t = service.findByReferenceEntity(new Piece(code));
        if (t != null) {
            PieceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  piece")
    @PostMapping("")
    public ResponseEntity<PieceDto> save(@RequestBody PieceDto dto) throws Exception {
        if(dto!=null){
            Piece myT = converter.toItem(dto);
            Piece t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PieceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  piece")
    @PutMapping("")
    public ResponseEntity<PieceDto> update(@RequestBody PieceDto dto) throws Exception {
        ResponseEntity<PieceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Piece t = service.findById(dto.getId());
            converter.copy(dto,t);
            Piece updated = service.update(t);
            PieceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of piece")
    @PostMapping("multiple")
    public ResponseEntity<List<PieceDto>> delete(@RequestBody List<PieceDto> dtos) throws Exception {
        ResponseEntity<List<PieceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Piece> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified piece")
    @DeleteMapping("")
    public ResponseEntity<PieceDto> delete(@RequestBody PieceDto dto) throws Exception {
		ResponseEntity<PieceDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            Piece t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified piece")
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
    @Operation(summary = "Delete multiple pieces by ids")
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



    @Operation(summary = "Finds a piece and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PieceDto> findWithAssociatedLists(@PathVariable Long id) {
        Piece loaded =  service.findWithAssociatedLists(id);
        PieceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds pieces by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PieceDto>> findByCriteria(@RequestBody PieceCriteria criteria) throws Exception {
        ResponseEntity<List<PieceDto>> res = null;
        List<Piece> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PieceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated pieces by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PieceCriteria criteria) throws Exception {
        List<Piece> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PieceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets piece data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PieceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PieceDto> findDtos(List<Piece> list){
        List<PieceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PieceDto> getDtoResponseEntity(PieceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private PieceAdminService service;
    @Autowired private PieceConverter converter;





}
