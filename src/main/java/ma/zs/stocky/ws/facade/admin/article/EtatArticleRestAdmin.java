package  ma.zs.stocky.ws.facade.admin.article;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.article.EtatArticle;
import ma.zs.stocky.dao.criteria.core.article.EtatArticleCriteria;
import ma.zs.stocky.service.facade.admin.article.EtatArticleAdminService;
import ma.zs.stocky.ws.converter.article.EtatArticleConverter;
import ma.zs.stocky.ws.dto.article.EtatArticleDto;
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
@RequestMapping("/api/admin/etatArticle/")
public class EtatArticleRestAdmin {




    @Operation(summary = "Finds a list of all etatArticles")
    @GetMapping("")
    public ResponseEntity<List<EtatArticleDto>> findAll() throws Exception {
        ResponseEntity<List<EtatArticleDto>> res = null;
        List<EtatArticle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<EtatArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all etatArticles")
    @GetMapping("optimized")
    public ResponseEntity<List<EtatArticleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EtatArticleDto>> res = null;
        List<EtatArticle> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EtatArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a etatArticle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EtatArticleDto> findById(@PathVariable Long id) {
        EtatArticle t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EtatArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a etatArticle by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<EtatArticleDto> findByLibelle(@PathVariable String libelle) {
	    EtatArticle t = service.findByReferenceEntity(new EtatArticle(libelle));
        if (t != null) {
            converter.init(true);
            EtatArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  etatArticle")
    @PostMapping("")
    public ResponseEntity<EtatArticleDto> save(@RequestBody EtatArticleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            EtatArticle myT = converter.toItem(dto);
            EtatArticle t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EtatArticleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  etatArticle")
    @PutMapping("")
    public ResponseEntity<EtatArticleDto> update(@RequestBody EtatArticleDto dto) throws Exception {
        ResponseEntity<EtatArticleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            EtatArticle t = service.findById(dto.getId());
            converter.copy(dto,t);
            EtatArticle updated = service.update(t);
            EtatArticleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of etatArticle")
    @PostMapping("multiple")
    public ResponseEntity<List<EtatArticleDto>> delete(@RequestBody List<EtatArticleDto> dtos) throws Exception {
        ResponseEntity<List<EtatArticleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<EtatArticle> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified etatArticle")
    @DeleteMapping("")
    public ResponseEntity<EtatArticleDto> delete(@RequestBody EtatArticleDto dto) throws Exception {
		ResponseEntity<EtatArticleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            EtatArticle t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified etatArticle")
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
    @Operation(summary = "Delete multiple etatArticles by ids")
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
    public List<EtatArticleDto> findByArticleId(@PathVariable Long id){
        return findDtos(service.findByArticleId(id));
    }
    @Operation(summary = "delete by article id")
    @DeleteMapping("article/id/{id}")
    public int deleteByArticleId(@PathVariable Long id){
        return service.deleteByArticleId(id);
    }

    @Operation(summary = "Finds a etatArticle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EtatArticleDto> findWithAssociatedLists(@PathVariable Long id) {
        EtatArticle loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EtatArticleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds etatArticles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EtatArticleDto>> findByCriteria(@RequestBody EtatArticleCriteria criteria) throws Exception {
        ResponseEntity<List<EtatArticleDto>> res = null;
        List<EtatArticle> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<EtatArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated etatArticles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EtatArticleCriteria criteria) throws Exception {
        List<EtatArticle> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<EtatArticleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets etatArticle data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EtatArticleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EtatArticleDto> findDtos(List<EtatArticle> list){
        converter.initObject(true);
        List<EtatArticleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EtatArticleDto> getDtoResponseEntity(EtatArticleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private EtatArticleAdminService service;
    @Autowired private EtatArticleConverter converter;





}
