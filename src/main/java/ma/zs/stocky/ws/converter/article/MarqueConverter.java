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
import ma.zs.stocky.bean.core.article.Marque;
import ma.zs.stocky.ws.dto.article.MarqueDto;

@Component
public class MarqueConverter {


    public  MarqueConverter() {
    }


    public Marque toItem(MarqueDto dto) {
        if (dto == null) {
            return null;
        } else {
        Marque item = new Marque();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public MarqueDto toDto(Marque item) {
        if (item == null) {
            return null;
        } else {
            MarqueDto dto = new MarqueDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Marque> toItem(List<MarqueDto> dtos) {
        List<Marque> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MarqueDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MarqueDto> toDto(List<Marque> items) {
        List<MarqueDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Marque item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MarqueDto dto, Marque t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Marque> copy(List<MarqueDto> dtos) {
        List<Marque> result = new ArrayList<>();
        if (dtos != null) {
            for (MarqueDto dto : dtos) {
                Marque instance = new Marque();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
