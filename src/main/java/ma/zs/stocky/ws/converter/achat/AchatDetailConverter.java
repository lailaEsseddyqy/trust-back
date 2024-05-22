package  ma.zs.stocky.ws.converter.achat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.article.ArticleConverter;
import ma.zs.stocky.ws.converter.achat.AchatConverter;

import ma.zs.stocky.bean.core.achat.Achat;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.achat.AchatDetail;
import ma.zs.stocky.ws.dto.achat.AchatDetailDto;

@Component
public class AchatDetailConverter {

    @Autowired
    private ArticleConverter articleConverter ;
    @Autowired
    private AchatConverter achatConverter ;
    private boolean article;
    private boolean achat;

    public  AchatDetailConverter() {
        initObject(true);
    }


    public AchatDetail toItem(AchatDetailDto dto) {
        if (dto == null) {
            return null;
        } else {
        AchatDetail item = new AchatDetail();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPrix()))
                item.setPrix(dto.getPrix());
            if(StringUtil.isNotEmpty(dto.getQte()))
                item.setQte(dto.getQte());
            if(this.article && dto.getArticle()!=null)
                item.setArticle(articleConverter.toItem(dto.getArticle())) ;

            if(dto.getAchat() != null && dto.getAchat().getId() != null){
                item.setAchat(new Achat());
                item.getAchat().setId(dto.getAchat().getId());
                item.getAchat().setId(dto.getAchat().getId());
            }




        return item;
        }
    }


    public AchatDetailDto toDto(AchatDetail item) {
        if (item == null) {
            return null;
        } else {
            AchatDetailDto dto = new AchatDetailDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPrix()))
                dto.setPrix(item.getPrix());
            if(StringUtil.isNotEmpty(item.getQte()))
                dto.setQte(item.getQte());
            if(this.article && item.getArticle()!=null) {
                dto.setArticle(articleConverter.toDto(item.getArticle())) ;

            }
            if(this.achat && item.getAchat()!=null) {
                dto.setAchat(achatConverter.toDto(item.getAchat())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.article = value;
        this.achat = value;
    }
	
    public List<AchatDetail> toItem(List<AchatDetailDto> dtos) {
        List<AchatDetail> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AchatDetailDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AchatDetailDto> toDto(List<AchatDetail> items) {
        List<AchatDetailDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (AchatDetail item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AchatDetailDto dto, AchatDetail t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getArticle() != null)
        articleConverter.copy(dto.getArticle(), t.getArticle());
        if (dto.getAchat() != null)
        achatConverter.copy(dto.getAchat(), t.getAchat());
    }

    public List<AchatDetail> copy(List<AchatDetailDto> dtos) {
        List<AchatDetail> result = new ArrayList<>();
        if (dtos != null) {
            for (AchatDetailDto dto : dtos) {
                AchatDetail instance = new AchatDetail();
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
    public AchatConverter getAchatConverter(){
        return this.achatConverter;
    }
    public void setAchatConverter(AchatConverter achatConverter ){
        this.achatConverter = achatConverter;
    }
    public boolean  isArticle(){
        return this.article;
    }
    public void  setArticle(boolean article){
        this.article = article;
    }
    public boolean  isAchat(){
        return this.achat;
    }
    public void  setAchat(boolean achat){
        this.achat = achat;
    }
}
