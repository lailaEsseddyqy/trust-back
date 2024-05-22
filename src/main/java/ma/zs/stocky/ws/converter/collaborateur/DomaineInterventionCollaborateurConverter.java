package  ma.zs.stocky.ws.converter.collaborateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.collaborateur.DomaineInterventionConverter;
import ma.zs.stocky.ws.converter.collaborateur.CollaborateurConverter;

import ma.zs.stocky.bean.core.collaborateur.Collaborateur;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import ma.zs.stocky.ws.dto.collaborateur.DomaineInterventionCollaborateurDto;

@Component
public class DomaineInterventionCollaborateurConverter {

    @Autowired
    private DomaineInterventionConverter domaineInterventionConverter ;
    @Autowired
    private CollaborateurConverter collaborateurConverter ;
    private boolean domaineIntervention;
    private boolean collaborateur;

    public  DomaineInterventionCollaborateurConverter() {
        initObject(true);
    }


    public DomaineInterventionCollaborateur toItem(DomaineInterventionCollaborateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        DomaineInterventionCollaborateur item = new DomaineInterventionCollaborateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(this.domaineIntervention && dto.getDomaineIntervention()!=null)
                item.setDomaineIntervention(domaineInterventionConverter.toItem(dto.getDomaineIntervention())) ;

            if(dto.getCollaborateur() != null && dto.getCollaborateur().getId() != null){
                item.setCollaborateur(new Collaborateur());
                item.getCollaborateur().setId(dto.getCollaborateur().getId());
                item.getCollaborateur().setRef(dto.getCollaborateur().getRef());
            }




        return item;
        }
    }


    public DomaineInterventionCollaborateurDto toDto(DomaineInterventionCollaborateur item) {
        if (item == null) {
            return null;
        } else {
            DomaineInterventionCollaborateurDto dto = new DomaineInterventionCollaborateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.domaineIntervention && item.getDomaineIntervention()!=null) {
                dto.setDomaineIntervention(domaineInterventionConverter.toDto(item.getDomaineIntervention())) ;

            }
            if(this.collaborateur && item.getCollaborateur()!=null) {
                dto.setCollaborateur(collaborateurConverter.toDto(item.getCollaborateur())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.domaineIntervention = value;
        this.collaborateur = value;
    }
	
    public List<DomaineInterventionCollaborateur> toItem(List<DomaineInterventionCollaborateurDto> dtos) {
        List<DomaineInterventionCollaborateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DomaineInterventionCollaborateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DomaineInterventionCollaborateurDto> toDto(List<DomaineInterventionCollaborateur> items) {
        List<DomaineInterventionCollaborateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (DomaineInterventionCollaborateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DomaineInterventionCollaborateurDto dto, DomaineInterventionCollaborateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getDomaineIntervention() != null)
        domaineInterventionConverter.copy(dto.getDomaineIntervention(), t.getDomaineIntervention());
        if (dto.getCollaborateur() != null)
        collaborateurConverter.copy(dto.getCollaborateur(), t.getCollaborateur());
    }

    public List<DomaineInterventionCollaborateur> copy(List<DomaineInterventionCollaborateurDto> dtos) {
        List<DomaineInterventionCollaborateur> result = new ArrayList<>();
        if (dtos != null) {
            for (DomaineInterventionCollaborateurDto dto : dtos) {
                DomaineInterventionCollaborateur instance = new DomaineInterventionCollaborateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public DomaineInterventionConverter getDomaineInterventionConverter(){
        return this.domaineInterventionConverter;
    }
    public void setDomaineInterventionConverter(DomaineInterventionConverter domaineInterventionConverter ){
        this.domaineInterventionConverter = domaineInterventionConverter;
    }
    public CollaborateurConverter getCollaborateurConverter(){
        return this.collaborateurConverter;
    }
    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
        this.collaborateurConverter = collaborateurConverter;
    }
    public boolean  isDomaineIntervention(){
        return this.domaineIntervention;
    }
    public void  setDomaineIntervention(boolean domaineIntervention){
        this.domaineIntervention = domaineIntervention;
    }
    public boolean  isCollaborateur(){
        return this.collaborateur;
    }
    public void  setCollaborateur(boolean collaborateur){
        this.collaborateur = collaborateur;
    }
}
