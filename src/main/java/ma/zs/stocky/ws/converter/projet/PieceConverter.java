package  ma.zs.stocky.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.projet.Piece;
import ma.zs.stocky.ws.dto.projet.PieceDto;

@Component
public class PieceConverter {


    public  PieceConverter() {
    }


    public Piece toItem(PieceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Piece item = new Piece();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getPath()))
                item.setPath(dto.getPath());



        return item;
        }
    }


    public PieceDto toDto(Piece item) {
        if (item == null) {
            return null;
        } else {
            PieceDto dto = new PieceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getPath()))
                dto.setPath(item.getPath());


        return dto;
        }
    }


	
    public List<Piece> toItem(List<PieceDto> dtos) {
        List<Piece> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PieceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PieceDto> toDto(List<Piece> items) {
        List<PieceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Piece item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PieceDto dto, Piece t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Piece> copy(List<PieceDto> dtos) {
        List<Piece> result = new ArrayList<>();
        if (dtos != null) {
            for (PieceDto dto : dtos) {
                Piece instance = new Piece();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
