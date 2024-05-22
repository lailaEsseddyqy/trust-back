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
import ma.zs.stocky.bean.core.achat.EtatAchat;
import ma.zs.stocky.ws.dto.achat.EtatAchatDto;

@Component
public class EtatAchatConverter {


    public  EtatAchatConverter() {
    }


    public EtatAchat toItem(EtatAchatDto dto) {
        if (dto == null) {
            return null;
        } else {
        EtatAchat item = new EtatAchat();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public EtatAchatDto toDto(EtatAchat item) {
        if (item == null) {
            return null;
        } else {
            EtatAchatDto dto = new EtatAchatDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<EtatAchat> toItem(List<EtatAchatDto> dtos) {
        List<EtatAchat> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EtatAchatDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EtatAchatDto> toDto(List<EtatAchat> items) {
        List<EtatAchatDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (EtatAchat item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EtatAchatDto dto, EtatAchat t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<EtatAchat> copy(List<EtatAchatDto> dtos) {
        List<EtatAchat> result = new ArrayList<>();
        if (dtos != null) {
            for (EtatAchatDto dto : dtos) {
                EtatAchat instance = new EtatAchat();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
