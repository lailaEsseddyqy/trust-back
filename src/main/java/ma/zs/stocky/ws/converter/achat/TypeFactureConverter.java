package  ma.zs.stocky.ws.converter.achat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import ma.zs.stocky.ws.dto.achat.TypeFactureDto;

@Component
public class TypeFactureConverter {


    public  TypeFactureConverter() {
    }


    public TypeFacture toItem(TypeFactureDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeFacture item = new TypeFacture();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeFactureDto toDto(TypeFacture item) {
        if (item == null) {
            return null;
        } else {
            TypeFactureDto dto = new TypeFactureDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeFacture> toItem(List<TypeFactureDto> dtos) {
        List<TypeFacture> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeFactureDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeFactureDto> toDto(List<TypeFacture> items) {
        List<TypeFactureDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeFacture item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeFactureDto dto, TypeFacture t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeFacture> copy(List<TypeFactureDto> dtos) {
        List<TypeFacture> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeFactureDto dto : dtos) {
                TypeFacture instance = new TypeFacture();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
