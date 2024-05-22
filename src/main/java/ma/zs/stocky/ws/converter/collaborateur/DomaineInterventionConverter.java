package  ma.zs.stocky.ws.converter.collaborateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import ma.zs.stocky.ws.dto.collaborateur.DomaineInterventionDto;

@Component
public class DomaineInterventionConverter {


    public  DomaineInterventionConverter() {
    }


    public DomaineIntervention toItem(DomaineInterventionDto dto) {
        if (dto == null) {
            return null;
        } else {
        DomaineIntervention item = new DomaineIntervention();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public DomaineInterventionDto toDto(DomaineIntervention item) {
        if (item == null) {
            return null;
        } else {
            DomaineInterventionDto dto = new DomaineInterventionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<DomaineIntervention> toItem(List<DomaineInterventionDto> dtos) {
        List<DomaineIntervention> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DomaineInterventionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DomaineInterventionDto> toDto(List<DomaineIntervention> items) {
        List<DomaineInterventionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DomaineIntervention item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DomaineInterventionDto dto, DomaineIntervention t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<DomaineIntervention> copy(List<DomaineInterventionDto> dtos) {
        List<DomaineIntervention> result = new ArrayList<>();
        if (dtos != null) {
            for (DomaineInterventionDto dto : dtos) {
                DomaineIntervention instance = new DomaineIntervention();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
