package  ma.zs.stocky.ws.converter.collaborateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.stocky.zynerator.util.ListUtil;

import ma.zs.stocky.ws.converter.collaborateur.DomaineInterventionCollaborateurConverter;
import ma.zs.stocky.ws.converter.collaborateur.DomaineInterventionConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import ma.zs.stocky.ws.dto.collaborateur.CollaborateurDto;

@Component
public class CollaborateurConverter {

    @Autowired
    private DomaineInterventionCollaborateurConverter domaineInterventionCollaborateurConverter ;
    @Autowired
    private DomaineInterventionConverter domaineInterventionConverter ;
    private boolean domaineInterventionCollaborateurs;

    public  CollaborateurConverter() {
        initList(true);
    }


    public Collaborateur toItem(CollaborateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        Collaborateur item = new Collaborateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getRef()))
                item.setRef(dto.getRef());
            if(StringUtil.isNotEmpty(dto.getPoste()))
                item.setPoste(dto.getPoste());
            if(StringUtil.isNotEmpty(dto.getNumeroTelephone()))
                item.setNumeroTelephone(dto.getNumeroTelephone());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());

            if(this.domaineInterventionCollaborateurs && ListUtil.isNotEmpty(dto.getDomaineInterventionCollaborateurs()))
                item.setDomaineInterventionCollaborateurs(domaineInterventionCollaborateurConverter.toItem(dto.getDomaineInterventionCollaborateurs()));


        return item;
        }
    }


    public CollaborateurDto toDto(Collaborateur item) {
        if (item == null) {
            return null;
        } else {
            CollaborateurDto dto = new CollaborateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getRef()))
                dto.setRef(item.getRef());
            if(StringUtil.isNotEmpty(item.getPoste()))
                dto.setPoste(item.getPoste());
            if(StringUtil.isNotEmpty(item.getNumeroTelephone()))
                dto.setNumeroTelephone(item.getNumeroTelephone());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
        if(this.domaineInterventionCollaborateurs && ListUtil.isNotEmpty(item.getDomaineInterventionCollaborateurs())){
            domaineInterventionCollaborateurConverter.init(true);
            domaineInterventionCollaborateurConverter.setCollaborateur(false);
            dto.setDomaineInterventionCollaborateurs(domaineInterventionCollaborateurConverter.toDto(item.getDomaineInterventionCollaborateurs()));
            domaineInterventionCollaborateurConverter.setCollaborateur(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.domaineInterventionCollaborateurs = value;
    }
	
    public List<Collaborateur> toItem(List<CollaborateurDto> dtos) {
        List<Collaborateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (CollaborateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<CollaborateurDto> toDto(List<Collaborateur> items) {
        List<CollaborateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Collaborateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(CollaborateurDto dto, Collaborateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDomaineInterventionCollaborateurs() != null)
            t.setDomaineInterventionCollaborateurs(domaineInterventionCollaborateurConverter.copy(dto.getDomaineInterventionCollaborateurs()));
    }

    public List<Collaborateur> copy(List<CollaborateurDto> dtos) {
        List<Collaborateur> result = new ArrayList<>();
        if (dtos != null) {
            for (CollaborateurDto dto : dtos) {
                Collaborateur instance = new Collaborateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DomaineInterventionCollaborateurConverter getDomaineInterventionCollaborateurConverter(){
        return this.domaineInterventionCollaborateurConverter;
    }
    public void setDomaineInterventionCollaborateurConverter(DomaineInterventionCollaborateurConverter domaineInterventionCollaborateurConverter ){
        this.domaineInterventionCollaborateurConverter = domaineInterventionCollaborateurConverter;
    }
    public DomaineInterventionConverter getDomaineInterventionConverter(){
        return this.domaineInterventionConverter;
    }
    public void setDomaineInterventionConverter(DomaineInterventionConverter domaineInterventionConverter ){
        this.domaineInterventionConverter = domaineInterventionConverter;
    }
    public boolean  isDomaineInterventionCollaborateurs(){
        return this.domaineInterventionCollaborateurs ;
    }
    public void  setDomaineInterventionCollaborateurs(boolean domaineInterventionCollaborateurs ){
        this.domaineInterventionCollaborateurs  = domaineInterventionCollaborateurs ;
    }
}
