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

import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;
import ma.zs.stocky.service.facade.chefprojet.article.ArticleChefprojetService;
import ma.zs.stocky.ws.converter.article.ArticleConverter;
import ma.zs.stocky.ws.dto.article.ArticleDto;
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
@RequestMapping("/api/chefprojet/article/")
public class ArticleRestChefprojet {




    @Operation(summary = "Finds a list of all articles")
    @GetMapping("")
    public ResponseEntity<List<ArticleDto>> findAll() throws Exception {
        ResponseEntity<List<ArticleDto>> res = null;
        List<Article> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a article by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ArticleDto> findById(@PathVariable Long id) {
        Article t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  article")
    @PostMapping("")
    public ResponseEntity<ArticleDto> save(@RequestBody ArticleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Article myT = converter.toItem(dto);
            Article t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ArticleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  article")
    @PutMapping("")
    public ResponseEntity<ArticleDto> update(@RequestBody ArticleDto dto) throws Exception {
        ResponseEntity<ArticleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Article t = service.findById(dto.getId());
            converter.copy(dto,t);
            Article updated = service.update(t);
            ArticleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of article")
    @PostMapping("multiple")
    public ResponseEntity<List<ArticleDto>> delete(@RequestBody List<ArticleDto> dtos) throws Exception {
        ResponseEntity<List<ArticleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Article> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified article")
    @DeleteMapping("")
    public ResponseEntity<ArticleDto> delete(@RequestBody ArticleDto dto) throws Exception {
		ResponseEntity<ArticleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            Article t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified article")
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
    @Operation(summary = "Delete multiple articles by ids")
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


    @Operation(summary = "find by categorieArticle id")
    @GetMapping("categorieArticle/id/{id}")
    public List<ArticleDto> findByCategorieArticleId(@PathVariable Long id){
        return findDtos(service.findByCategorieArticleId(id));
    }
    @Operation(summary = "delete by categorieArticle id")
    @DeleteMapping("categorieArticle/id/{id}")
    public int deleteByCategorieArticleId(@PathVariable Long id){
        return service.deleteByCategorieArticleId(id);
    }

    @Operation(summary = "Finds a article and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ArticleDto> findWithAssociatedLists(@PathVariable Long id) {
        Article loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ArticleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds articles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ArticleDto>> findByCriteria(@RequestBody ArticleCriteria criteria) throws Exception {
        ResponseEntity<List<ArticleDto>> res = null;
        List<Article> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated articles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ArticleCriteria criteria) throws Exception {
        List<Article> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ArticleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets article data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ArticleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ArticleDto> findDtos(List<Article> list){
        converter.initObject(true);
        List<ArticleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ArticleDto> getDtoResponseEntity(ArticleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ArticleChefprojetService service;
    @Autowired private ArticleConverter converter;





}
