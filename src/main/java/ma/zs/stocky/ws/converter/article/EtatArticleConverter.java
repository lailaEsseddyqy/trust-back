package  ma.zs.stocky.ws.converter.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.article.ArticleConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.article.EtatArticle;
import ma.zs.stocky.ws.dto.article.EtatArticleDto;

@Component
public class EtatArticleConverter {

    @Autowired
    private ArticleConverter articleConverter ;
    private boolean article;

    public  EtatArticleConverter() {
        initObject(true);
    }


    public EtatArticle toItem(EtatArticleDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatArticle item = new EtatArticle();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.article && dto.getArticle()!=null)
                item.setArticle(articleConverter.toItem(dto.getArticle())) ;




        return item;
        }
    }


    public EtatArticleDto toDto(EtatArticle item) {
        if (item == null) {
            return null;
        } else {
            EtatArticleDto dto = new EtatArticleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.article && item.getArticle()!=null) {
                articleConverter.setEtatArticle(false);
                dto.setArticle(articleConverter.toDto(item.getArticle())) ;
                articleConverter.setEtatArticle(true);

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.article = value;
    }
	
    public List<EtatArticle> toItem(List<EtatArticleDto> dtos) {
        List<EtatArticle> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatArticleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatArticleDto> toDto(List<EtatArticle> items) {
        List<EtatArticleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatArticle item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatArticleDto dto, EtatArticle t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getArticle() != null)
        articleConverter.copy(dto.getArticle(), t.getArticle());
    }

    public List<EtatArticle> copy(List<EtatArticleDto> dtos) {
        List<EtatArticle> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatArticleDto dto : dtos) {
                EtatArticle instance = new EtatArticle();
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
    public boolean  isArticle(){
        return this.article;
    }
    public void  setArticle(boolean article){
        this.article = article;
    }
}
