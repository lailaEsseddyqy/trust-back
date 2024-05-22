package  ma.zs.stocky.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zs.stocky.ws.converter.projet.ProjetConverter;
import ma.zs.stocky.ws.converter.collaborateur.CollaborateurConverter;

import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import ma.zs.stocky.bean.core.projet.Projet;


import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import ma.zs.stocky.ws.dto.projet.ProjetCollaborateurDto;

@Component
public class ProjetCollaborateurConverter {

    @Autowired
    private ProjetConverter projetConverter ;
    @Autowired
    private CollaborateurConverter collaborateurConverter ;
    private boolean collaborateur;
    private boolean projet;

    public  ProjetCollaborateurConverter() {
        initObject(true);
    }


    public ProjetCollaborateur toItem(ProjetCollaborateurDto dto) {
        if (dto == null) {
            return null;
        } else {
        ProjetCollaborateur item = new ProjetCollaborateur();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getCollaborateur() != null && dto.getCollaborateur().getId() != null){
                item.setCollaborateur(new Collaborateur());
                item.getCollaborateur().setId(dto.getCollaborateur().getId());
                item.getCollaborateur().setRef(dto.getCollaborateur().getRef());
            }

            if(dto.getProjet() != null && dto.getProjet().getId() != null){
                item.setProjet(new Projet());
                item.getProjet().setId(dto.getProjet().getId());
                item.getProjet().setReference(dto.getProjet().getReference());
            }




        return item;
        }
    }


    public ProjetCollaborateurDto toDto(ProjetCollaborateur item) {
        if (item == null) {
            return null;
        } else {
            ProjetCollaborateurDto dto = new ProjetCollaborateurDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.collaborateur && item.getCollaborateur()!=null) {
                dto.setCollaborateur(collaborateurConverter.toDto(item.getCollaborateur())) ;

            }
            if(this.projet && item.getProjet()!=null) {
                dto.setProjet(projetConverter.toDto(item.getProjet())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.collaborateur = value;
        this.projet = value;
    }
	
    public List<ProjetCollaborateur> toItem(List<ProjetCollaborateurDto> dtos) {
        List<ProjetCollaborateur> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjetCollaborateurDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjetCollaborateurDto> toDto(List<ProjetCollaborateur> items) {
        List<ProjetCollaborateurDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ProjetCollaborateur item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjetCollaborateurDto dto, ProjetCollaborateur t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getCollaborateur() != null)
        collaborateurConverter.copy(dto.getCollaborateur(), t.getCollaborateur());
        if (dto.getProjet() != null)
        projetConverter.copy(dto.getProjet(), t.getProjet());
    }

    public List<ProjetCollaborateur> copy(List<ProjetCollaborateurDto> dtos) {
        List<ProjetCollaborateur> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjetCollaborateurDto dto : dtos) {
                ProjetCollaborateur instance = new ProjetCollaborateur();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjetConverter getProjetConverter(){
        return this.projetConverter;
    }
    public void setProjetConverter(ProjetConverter projetConverter ){
        this.projetConverter = projetConverter;
    }
    public CollaborateurConverter getCollaborateurConverter(){
        return this.collaborateurConverter;
    }
    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
        this.collaborateurConverter = collaborateurConverter;
    }
    public boolean  isCollaborateur(){
        return this.collaborateur;
    }
    public void  setCollaborateur(boolean collaborateur){
        this.collaborateur = collaborateur;
    }
    public boolean  isProjet(){
        return this.projet;
    }
    public void  setProjet(boolean projet){
        this.projet = projet;
    }
}
