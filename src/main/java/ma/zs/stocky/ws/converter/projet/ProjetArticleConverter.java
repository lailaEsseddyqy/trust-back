package  ma.zs.stocky.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.article.ArticleConverter;
import ma.zs.stocky.ws.converter.projet.ProjetConverter;

import ma.zs.stocky.bean.core.projet.Projet;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.ws.dto.projet.ProjetArticleDto;

@Component
public class ProjetArticleConverter {

    @Autowired
    private ArticleConverter articleConverter ;
    @Autowired
    private ProjetConverter projetConverter ;
    private boolean article;
    private boolean projet;

    public  ProjetArticleConverter() {
        initObject(true);
    }


    public ProjetArticle toItem(ProjetArticleDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjetArticle item = new ProjetArticle();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.article && dto.getArticle()!=null)
                item.setArticle(articleConverter.toItem(dto.getArticle())) ;

            if(dto.getProjet() != null && dto.getProjet().getId() != null){
                item.setProjet(new Projet());
                item.getProjet().setId(dto.getProjet().getId());
                item.getProjet().setReference(dto.getProjet().getReference());
            }




        return item;
        }
    }


    public ProjetArticleDto toDto(ProjetArticle item) {
        if (item == null) {
            return null;
        } else {
            ProjetArticleDto dto = new ProjetArticleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.article && item.getArticle()!=null) {
                dto.setArticle(articleConverter.toDto(item.getArticle())) ;

            }
            if(this.projet && item.getProjet()!=null) {
                dto.setProjet(projetConverter.toDto(item.getProjet())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.article = value;
        this.projet = value;
    }
	
    public List<ProjetArticle> toItem(List<ProjetArticleDto> dtos) {
        List<ProjetArticle> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjetArticleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjetArticleDto> toDto(List<ProjetArticle> items) {
        List<ProjetArticleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjetArticle item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjetArticleDto dto, ProjetArticle t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getArticle() != null)
        articleConverter.copy(dto.getArticle(), t.getArticle());
        if (dto.getProjet() != null)
        projetConverter.copy(dto.getProjet(), t.getProjet());
    }

    public List<ProjetArticle> copy(List<ProjetArticleDto> dtos) {
        List<ProjetArticle> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjetArticleDto dto : dtos) {
                ProjetArticle instance = new ProjetArticle();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ArticleConverter getArticleConverter(){
        return this.articleConverter;
    }
    public void setArticleConverter(ArticleConverter articleConverter ){
        this.articleConverter = articleConverter;
    }
    public ProjetConverter getProjetConverter(){
        return this.projetConverter;
    }
    public void setProjetConverter(ProjetConverter projetConverter ){
        this.projetConverter = projetConverter;
    }
    public boolean  isArticle(){
        return this.article;
    }
    public void  setArticle(boolean article){
        this.article = article;
    }
    public boolean  isProjet(){
        return this.projet;
    }
    public void  setProjet(boolean projet){
        this.projet = projet;
    }
}
