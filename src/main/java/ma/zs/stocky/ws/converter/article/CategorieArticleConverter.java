package  ma.zs.stocky.ws.converter.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import ma.zs.stocky.ws.dto.article.CategorieArticleDto;

@Component
public class CategorieArticleConverter {


    public  CategorieArticleConverter() {
    }


    public CategorieArticle toItem(CategorieArticleDto dto) {
        if (dto == null) {
            return null;
        } else {
        CategorieArticle item = new CategorieArticle();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public CategorieArticleDto toDto(CategorieArticle item) {
        if (item == null) {
            return null;
        } else {
            CategorieArticleDto dto = new CategorieArticleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<CategorieArticle> toItem(List<CategorieArticleDto> dtos) {
        List<CategorieArticle> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CategorieArticleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CategorieArticleDto> toDto(List<CategorieArticle> items) {
        List<CategorieArticleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (CategorieArticle item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CategorieArticleDto dto, CategorieArticle t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<CategorieArticle> copy(List<CategorieArticleDto> dtos) {
        List<CategorieArticle> result = new ArrayList<>();
        if (dtos != null) {
            for (CategorieArticleDto dto : dtos) {
                CategorieArticle instance = new CategorieArticle();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
