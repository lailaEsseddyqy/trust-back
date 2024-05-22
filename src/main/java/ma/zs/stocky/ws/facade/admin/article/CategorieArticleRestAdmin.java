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

import ma.zs.stocky.bean.core.article.CategorieArticle;
import ma.zs.stocky.dao.criteria.core.article.CategorieArticleCriteria;
import ma.zs.stocky.service.facade.admin.article.CategorieArticleAdminService;
import ma.zs.stocky.ws.converter.article.CategorieArticleConverter;
import ma.zs.stocky.ws.dto.article.CategorieArticleDto;
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
@RequestMapping("/api/admin/categorieArticle/")
public class CategorieArticleRestAdmin {




    @Operation(summary = "Finds a list of all categorieArticles")
    @GetMapping("")
    public ResponseEntity<List<CategorieArticleDto>> findAll() throws Exception {
        ResponseEntity<List<CategorieArticleDto>> res = null;
        List<CategorieArticle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all categorieArticles")
    @GetMapping("optimized")
    public ResponseEntity<List<CategorieArticleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<CategorieArticleDto>> res = null;
        List<CategorieArticle> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a categorieArticle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<CategorieArticleDto> findById(@PathVariable Long id) {
        CategorieArticle t = service.findById(id);
        if (t != null) {
            CategorieArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a categorieArticle by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<CategorieArticleDto> findByLibelle(@PathVariable String libelle) {
	    CategorieArticle t = service.findByReferenceEntity(new CategorieArticle(libelle));
        if (t != null) {
            CategorieArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  categorieArticle")
    @PostMapping("")
    public ResponseEntity<CategorieArticleDto> save(@RequestBody CategorieArticleDto dto) throws Exception {
        if(dto!=null){
            CategorieArticle myT = converter.toItem(dto);
            CategorieArticle t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                CategorieArticleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  categorieArticle")
    @PutMapping("")
    public ResponseEntity<CategorieArticleDto> update(@RequestBody CategorieArticleDto dto) throws Exception {
        ResponseEntity<CategorieArticleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            CategorieArticle t = service.findById(dto.getId());
            converter.copy(dto,t);
            CategorieArticle updated = service.update(t);
            CategorieArticleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of categorieArticle")
    @PostMapping("multiple")
    public ResponseEntity<List<CategorieArticleDto>> delete(@RequestBody List<CategorieArticleDto> dtos) throws Exception {
        ResponseEntity<List<CategorieArticleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<CategorieArticle> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified categorieArticle")
    @DeleteMapping("")
    public ResponseEntity<CategorieArticleDto> delete(@RequestBody CategorieArticleDto dto) throws Exception {
		ResponseEntity<CategorieArticleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            CategorieArticle t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified categorieArticle")
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
    @Operation(summary = "Delete multiple categorieArticles by ids")
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



    @Operation(summary = "Finds a categorieArticle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<CategorieArticleDto> findWithAssociatedLists(@PathVariable Long id) {
        CategorieArticle loaded =  service.findWithAssociatedLists(id);
        CategorieArticleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds categorieArticles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<CategorieArticleDto>> findByCriteria(@RequestBody CategorieArticleCriteria criteria) throws Exception {
        ResponseEntity<List<CategorieArticleDto>> res = null;
        List<CategorieArticle> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<CategorieArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated categorieArticles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody CategorieArticleCriteria criteria) throws Exception {
        List<CategorieArticle> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<CategorieArticleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets categorieArticle data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody CategorieArticleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<CategorieArticleDto> findDtos(List<CategorieArticle> list){
        List<CategorieArticleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<CategorieArticleDto> getDtoResponseEntity(CategorieArticleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private CategorieArticleAdminService service;
    @Autowired private CategorieArticleConverter converter;





}
