package  ma.zs.stocky.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.commun.SocieteConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.ws.dto.commun.FournisseurDto;

@Component
public class FournisseurConverter {

    @Autowired
    private SocieteConverter societeConverter ;
    private boolean societe;

    public  FournisseurConverter() {
        initObject(true);
    }


    public Fournisseur toItem(FournisseurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Fournisseur item = new Fournisseur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getNumeroTelephone()))
                item.setNumeroTelephone(dto.getNumeroTelephone());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getRib()))
                item.setRib(dto.getRib());
            if(this.societe && dto.getSociete()!=null)
                item.setSociete(societeConverter.toItem(dto.getSociete())) ;




        return item;
        }
    }


    public FournisseurDto toDto(Fournisseur item) {
        if (item == null) {
            return null;
        } else {
            FournisseurDto dto = new FournisseurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getNumeroTelephone()))
                dto.setNumeroTelephone(item.getNumeroTelephone());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getRib()))
                dto.setRib(item.getRib());
            if(this.societe && item.getSociete()!=null) {
                dto.setSociete(societeConverter.toDto(item.getSociete())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.societe = value;
    }
	
    public List<Fournisseur> toItem(List<FournisseurDto> dtos) {
        List<Fournisseur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FournisseurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FournisseurDto> toDto(List<Fournisseur> items) {
        List<FournisseurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Fournisseur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FournisseurDto dto, Fournisseur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getSociete() != null)
        societeConverter.copy(dto.getSociete(), t.getSociete());
    }

    public List<Fournisseur> copy(List<FournisseurDto> dtos) {
        List<Fournisseur> result = new ArrayList<>();
        if (dtos != null) {
            for (FournisseurDto dto : dtos) {
                Fournisseur instance = new Fournisseur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public SocieteConverter getSocieteConverter(){
        return this.societeConverter;
    }
    public void setSocieteConverter(SocieteConverter societeConverter ){
        this.societeConverter = societeConverter;
    }
    public boolean  isSociete(){
        return this.societe;
    }
    public void  setSociete(boolean societe){
        this.societe = societe;
    }
}
