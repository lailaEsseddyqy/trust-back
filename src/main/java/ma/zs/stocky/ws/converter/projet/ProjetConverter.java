package  ma.zs.stocky.ws.converter.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.stocky.zynerator.util.ListUtil;

import ma.zs.stocky.ws.converter.projet.ProjetArticleConverter;
import ma.zs.stocky.ws.converter.article.ArticleConverter;
import ma.zs.stocky.ws.converter.projet.PieceConverter;
import ma.zs.stocky.ws.converter.projet.ProjetCollaborateurConverter;
import ma.zs.stocky.ws.converter.projet.EtatAvancementConverter;
import ma.zs.stocky.ws.converter.commun.ClientConverter;
import ma.zs.stocky.ws.converter.collaborateur.CollaborateurConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.ws.dto.projet.ProjetDto;

@Component
public class ProjetConverter {

    @Autowired
    private ProjetArticleConverter projetArticleConverter ;
    @Autowired
    private ArticleConverter articleConverter ;
    @Autowired
    private PieceConverter pieceConverter ;
    @Autowired
    private ProjetCollaborateurConverter projetCollaborateurConverter ;
    @Autowired
    private EtatAvancementConverter etatAvancementConverter ;
    @Autowired
    private ClientConverter clientConverter ;
    @Autowired
    private CollaborateurConverter collaborateurConverter ;
    private boolean client;
    private boolean pieceJoint;
    private boolean etatAvancement;
    private boolean projetCollaborateurs;
    private boolean projetArticles;

    public  ProjetConverter() {
        init(true);
    }


    public Projet toItem(ProjetDto dto) {
        if (dto == null) {
            return null;
        } else {
        Projet item = new Projet();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getBudet()))
                item.setBudet(dto.getBudet());
            if(StringUtil.isNotEmpty(dto.getDateDebut()))
                item.setDateDebut(DateUtil.stringEnToDate(dto.getDateDebut()));
            if(StringUtil.isNotEmpty(dto.getDateFin()))
                item.setDateFin(DateUtil.stringEnToDate(dto.getDateFin()));
            if(StringUtil.isNotEmpty(dto.getNomChefProjet()))
                item.setNomChefProjet(dto.getNomChefProjet());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.client && dto.getClient()!=null)
                item.setClient(clientConverter.toItem(dto.getClient())) ;

            if(this.pieceJoint && dto.getPieceJoint()!=null)
                item.setPieceJoint(pieceConverter.toItem(dto.getPieceJoint())) ;

            if(this.etatAvancement && dto.getEtatAvancement()!=null)
                item.setEtatAvancement(etatAvancementConverter.toItem(dto.getEtatAvancement())) ;


            if(this.projetCollaborateurs && ListUtil.isNotEmpty(dto.getProjetCollaborateurs()))
                item.setProjetCollaborateurs(projetCollaborateurConverter.toItem(dto.getProjetCollaborateurs()));
            if(this.projetArticles && ListUtil.isNotEmpty(dto.getProjetArticles()))
                item.setProjetArticles(projetArticleConverter.toItem(dto.getProjetArticles()));


        return item;
        }
    }


    public ProjetDto toDto(Projet item) {
        if (item == null) {
            return null;
        } else {
            ProjetDto dto = new ProjetDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getBudet()))
                dto.setBudet(item.getBudet());
            if(item.getDateDebut()!=null)
                dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            if(item.getDateFin()!=null)
                dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            if(StringUtil.isNotEmpty(item.getNomChefProjet()))
                dto.setNomChefProjet(item.getNomChefProjet());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.client && item.getClient()!=null) {
                dto.setClient(clientConverter.toDto(item.getClient())) ;

            }
            if(this.pieceJoint && item.getPieceJoint()!=null) {
                dto.setPieceJoint(pieceConverter.toDto(item.getPieceJoint())) ;

            }
            if(this.etatAvancement && item.getEtatAvancement()!=null) {
                dto.setEtatAvancement(etatAvancementConverter.toDto(item.getEtatAvancement())) ;

            }
        if(this.projetCollaborateurs && ListUtil.isNotEmpty(item.getProjetCollaborateurs())){
            projetCollaborateurConverter.init(true);
            projetCollaborateurConverter.setProjet(false);
            dto.setProjetCollaborateurs(projetCollaborateurConverter.toDto(item.getProjetCollaborateurs()));
            projetCollaborateurConverter.setProjet(true);

        }
        if(this.projetArticles && ListUtil.isNotEmpty(item.getProjetArticles())){
            projetArticleConverter.init(true);
            projetArticleConverter.setProjet(false);
            dto.setProjetArticles(projetArticleConverter.toDto(item.getProjetArticles()));
            projetArticleConverter.setProjet(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.projetCollaborateurs = value;
        this.projetArticles = value;
    }
    public void initObject(boolean value) {
        this.client = value;
        this.pieceJoint = value;
        this.etatAvancement = value;
    }
	
    public List<Projet> toItem(List<ProjetDto> dtos) {
        List<Projet> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ProjetDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ProjetDto> toDto(List<Projet> items) {
        List<ProjetDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Projet item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ProjetDto dto, Projet t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getClient() != null)
        clientConverter.copy(dto.getClient(), t.getClient());
        if (dto.getProjetCollaborateurs() != null)
            t.setProjetCollaborateurs(projetCollaborateurConverter.copy(dto.getProjetCollaborateurs()));
        if (dto.getPieceJoint() != null)
        pieceConverter.copy(dto.getPieceJoint(), t.getPieceJoint());
        if (dto.getEtatAvancement() != null)
        etatAvancementConverter.copy(dto.getEtatAvancement(), t.getEtatAvancement());
        if (dto.getProjetArticles() != null)
            t.setProjetArticles(projetArticleConverter.copy(dto.getProjetArticles()));
    }

    public List<Projet> copy(List<ProjetDto> dtos) {
        List<Projet> result = new ArrayList<>();
        if (dtos != null) {
            for (ProjetDto dto : dtos) {
                Projet instance = new Projet();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProjetArticleConverter getProjetArticleConverter(){
        return this.projetArticleConverter;
    }
    public void setProjetArticleConverter(ProjetArticleConverter projetArticleConverter ){
        this.projetArticleConverter = projetArticleConverter;
    }
    public ArticleConverter getArticleConverter(){
        return this.articleConverter;
    }
    public void setArticleConverter(ArticleConverter articleConverter ){
        this.articleConverter = articleConverter;
    }
    public PieceConverter getPieceConverter(){
        return this.pieceConverter;
    }
    public void setPieceConverter(PieceConverter pieceConverter ){
        this.pieceConverter = pieceConverter;
    }
    public ProjetCollaborateurConverter getProjetCollaborateurConverter(){
        return this.projetCollaborateurConverter;
    }
    public void setProjetCollaborateurConverter(ProjetCollaborateurConverter projetCollaborateurConverter ){
        this.projetCollaborateurConverter = projetCollaborateurConverter;
    }
    public EtatAvancementConverter getEtatAvancementConverter(){
        return this.etatAvancementConverter;
    }
    public void setEtatAvancementConverter(EtatAvancementConverter etatAvancementConverter ){
        this.etatAvancementConverter = etatAvancementConverter;
    }
    public ClientConverter getClientConverter(){
        return this.clientConverter;
    }
    public void setClientConverter(ClientConverter clientConverter ){
        this.clientConverter = clientConverter;
    }
    public CollaborateurConverter getCollaborateurConverter(){
        return this.collaborateurConverter;
    }
    public void setCollaborateurConverter(CollaborateurConverter collaborateurConverter ){
        this.collaborateurConverter = collaborateurConverter;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
    public boolean  isPieceJoint(){
        return this.pieceJoint;
    }
    public void  setPieceJoint(boolean pieceJoint){
        this.pieceJoint = pieceJoint;
    }
    public boolean  isEtatAvancement(){
        return this.etatAvancement;
    }
    public void  setEtatAvancement(boolean etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public boolean  isProjetCollaborateurs(){
        return this.projetCollaborateurs ;
    }
    public void  setProjetCollaborateurs(boolean projetCollaborateurs ){
        this.projetCollaborateurs  = projetCollaborateurs ;
    }
    public boolean  isProjetArticles(){
        return this.projetArticles ;
    }
    public void  setProjetArticles(boolean projetArticles ){
        this.projetArticles  = projetArticles ;
    }
}
