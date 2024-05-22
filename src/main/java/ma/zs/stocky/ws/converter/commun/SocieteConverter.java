package  ma.zs.stocky.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.commun.Societe;
import ma.zs.stocky.ws.dto.commun.SocieteDto;

@Component
public class SocieteConverter {


    public  SocieteConverter() {
    }


    public Societe toItem(SocieteDto dto) {
        if (dto == null) {
            return null;
        } else {
        Societe item = new Societe();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getNumeroTelephone()))
                item.setNumeroTelephone(dto.getNumeroTelephone());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());



        return item;
        }
    }


    public SocieteDto toDto(Societe item) {
        if (item == null) {
            return null;
        } else {
            SocieteDto dto = new SocieteDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getNumeroTelephone()))
                dto.setNumeroTelephone(item.getNumeroTelephone());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());


        return dto;
        }
    }


	
    public List<Societe> toItem(List<SocieteDto> dtos) {
        List<Societe> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SocieteDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SocieteDto> toDto(List<Societe> items) {
        List<SocieteDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Societe item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SocieteDto dto, Societe t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Societe> copy(List<SocieteDto> dtos) {
        List<Societe> result = new ArrayList<>();
        if (dtos != null) {
            for (SocieteDto dto : dtos) {
                Societe instance = new Societe();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
