package  ma.zs.stocky.ws.facade.chefprojet.projet;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.dao.criteria.core.projet.ProjetArticleCriteria;
import ma.zs.stocky.service.facade.chefprojet.projet.ProjetArticleChefprojetService;
import ma.zs.stocky.ws.converter.projet.ProjetArticleConverter;
import ma.zs.stocky.ws.dto.projet.ProjetArticleDto;
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
@RequestMapping("/api/chefprojet/projetArticle/")
public class ProjetArticleRestChefprojet {




    @Operation(summary = "Finds a list of all projetArticles")
    @GetMapping("")
    public ResponseEntity<List<ProjetArticleDto>> findAll() throws Exception {
        ResponseEntity<List<ProjetArticleDto>> res = null;
        List<ProjetArticle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<ProjetArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a projetArticle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProjetArticleDto> findById(@PathVariable Long id) {
        ProjetArticle t = service.findById(id);
        if (t != null) {
            converter.init(true);
            ProjetArticleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  projetArticle")
    @PostMapping("")
    public ResponseEntity<ProjetArticleDto> save(@RequestBody ProjetArticleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            ProjetArticle myT = converter.toItem(dto);
            ProjetArticle t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProjetArticleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  projetArticle")
    @PutMapping("")
    public ResponseEntity<ProjetArticleDto> update(@RequestBody ProjetArticleDto dto) throws Exception {
        ResponseEntity<ProjetArticleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ProjetArticle t = service.findById(dto.getId());
            converter.copy(dto,t);
            ProjetArticle updated = service.update(t);
            ProjetArticleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of projetArticle")
    @PostMapping("multiple")
    public ResponseEntity<List<ProjetArticleDto>> delete(@RequestBody List<ProjetArticleDto> dtos) throws Exception {
        ResponseEntity<List<ProjetArticleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<ProjetArticle> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }
    @Operation(summary = "Delete the specified projetArticle")
    @DeleteMapping("")
    public ResponseEntity<ProjetArticleDto> delete(@RequestBody ProjetArticleDto dto) throws Exception {
		ResponseEntity<ProjetArticleDto> res;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dto != null) {
            converter.init(false);
            ProjetArticle t = converter.toItem(dto);
            service.delete(Arrays.asList(t));
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dto, status);
        return res;
    }

    @Operation(summary = "Delete the specified projetArticle")
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
    @Operation(summary = "Delete multiple projetArticles by ids")
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
    public List<ProjetArticleDto> findByArticleId(@PathVariable Long id){
        return findDtos(service.findByArticleId(id));
    }
    @Operation(summary = "delete by article id")
    @DeleteMapping("article/id/{id}")
    public int deleteByArticleId(@PathVariable Long id){
        return service.deleteByArticleId(id);
    }
    @Operation(summary = "find by projet id")
    @GetMapping("projet/id/{id}")
    public List<ProjetArticleDto> findByProjetId(@PathVariable Long id){
        return findDtos(service.findByProjetId(id));
    }
    @Operation(summary = "delete by projet id")
    @DeleteMapping("projet/id/{id}")
    public int deleteByProjetId(@PathVariable Long id){
        return service.deleteByProjetId(id);
    }

    @Operation(summary = "Finds a projetArticle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProjetArticleDto> findWithAssociatedLists(@PathVariable Long id) {
        ProjetArticle loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        ProjetArticleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds projetArticles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProjetArticleDto>> findByCriteria(@RequestBody ProjetArticleCriteria criteria) throws Exception {
        ResponseEntity<List<ProjetArticleDto>> res = null;
        List<ProjetArticle> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<ProjetArticleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated projetArticles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProjetArticleCriteria criteria) throws Exception {
        List<ProjetArticle> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<ProjetArticleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets projetArticle data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProjetArticleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProjetArticleDto> findDtos(List<ProjetArticle> list){
        converter.initObject(true);
        List<ProjetArticleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProjetArticleDto> getDtoResponseEntity(ProjetArticleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




    @Autowired private ProjetArticleChefprojetService service;
    @Autowired private ProjetArticleConverter converter;





}
