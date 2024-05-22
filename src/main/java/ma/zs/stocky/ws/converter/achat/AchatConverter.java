package  ma.zs.stocky.ws.converter.achat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zs.stocky.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zs.stocky.zynerator.util.ListUtil;

import ma.zs.stocky.ws.converter.achat.AchatDetailConverter;
import ma.zs.stocky.ws.converter.commun.FournisseurConverter;
import ma.zs.stocky.ws.converter.article.ArticleConverter;
import ma.zs.stocky.ws.converter.achat.FactureConverter;
import ma.zs.stocky.ws.converter.achat.EtatAchatConverter;



import ma.zs.stocky.zynerator.util.StringUtil;
import ma.zs.stocky.zynerator.converter.AbstractConverter;
import ma.zs.stocky.zynerator.util.DateUtil;
import ma.zs.stocky.bean.core.achat.Achat;
import ma.zs.stocky.ws.dto.achat.AchatDto;

@Component
public class AchatConverter {

    @Autowired
    private AchatDetailConverter achatDetailConverter ;
    @Autowired
    private FournisseurConverter fournisseurConverter ;
    @Autowired
    private ArticleConverter articleConverter ;
    @Autowired
    private FactureConverter factureConverter ;
    @Autowired
    private EtatAchatConverter etatAchatConverter ;
    private boolean etatAchat;
    private boolean fournisseur;
    private boolean facture;
    private boolean achatDetails;

    public  AchatConverter() {
        init(true);
    }


    public Achat toItem(AchatDto dto) {
        if (dto == null) {
            return null;
        } else {
        Achat item = new Achat();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getReference()))
                item.setReference(dto.getReference());
            if(StringUtil.isNotEmpty(dto.getMontant()))
                item.setMontant(dto.getMontant());
            if(StringUtil.isNotEmpty(dto.getDateCommande()))
                item.setDateCommande(DateUtil.stringEnToDate(dto.getDateCommande()));
            if(StringUtil.isNotEmpty(dto.getDateLivraison()))
                item.setDateLivraison(DateUtil.stringEnToDate(dto.getDateLivraison()));
            if(this.etatAchat && dto.getEtatAchat()!=null)
                item.setEtatAchat(etatAchatConverter.toItem(dto.getEtatAchat())) ;

            if(this.fournisseur && dto.getFournisseur()!=null)
                item.setFournisseur(fournisseurConverter.toItem(dto.getFournisseur())) ;

            if(this.facture && dto.getFacture()!=null)
                item.setFacture(factureConverter.toItem(dto.getFacture())) ;


            if(this.achatDetails && ListUtil.isNotEmpty(dto.getAchatDetails()))
                item.setAchatDetails(achatDetailConverter.toItem(dto.getAchatDetails()));


        return item;
        }
    }


    public AchatDto toDto(Achat item) {
        if (item == null) {
            return null;
        } else {
            AchatDto dto = new AchatDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getReference()))
                dto.setReference(item.getReference());
            if(StringUtil.isNotEmpty(item.getMontant()))
                dto.setMontant(item.getMontant());
            if(item.getDateCommande()!=null)
                dto.setDateCommande(DateUtil.dateTimeToString(item.getDateCommande()));
            if(item.getDateLivraison()!=null)
                dto.setDateLivraison(DateUtil.dateTimeToString(item.getDateLivraison()));
            if(this.etatAchat && item.getEtatAchat()!=null) {
                dto.setEtatAchat(etatAchatConverter.toDto(item.getEtatAchat())) ;

            }
            if(this.fournisseur && item.getFournisseur()!=null) {
                dto.setFournisseur(fournisseurConverter.toDto(item.getFournisseur())) ;

            }
            if(this.facture && item.getFacture()!=null) {
                dto.setFacture(factureConverter.toDto(item.getFacture())) ;

            }
        if(this.achatDetails && ListUtil.isNotEmpty(item.getAchatDetails())){
            achatDetailConverter.init(true);
            achatDetailConverter.setAchat(false);
            dto.setAchatDetails(achatDetailConverter.toDto(item.getAchatDetails()));
            achatDetailConverter.setAchat(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.achatDetails = value;
    }
    public void initObject(boolean value) {
        this.etatAchat = value;
        this.fournisseur = value;
        this.facture = value;
    }
	
    public List<Achat> toItem(List<AchatDto> dtos) {
        List<Achat> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AchatDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AchatDto> toDto(List<Achat> items) {
        List<AchatDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Achat item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AchatDto dto, Achat t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if (dto.getEtatAchat() != null)
        etatAchatConverter.copy(dto.getEtatAchat(), t.getEtatAchat());
        if (dto.getFournisseur() != null)
        fournisseurConverter.copy(dto.getFournisseur(), t.getFournisseur());
        if (dto.getAchatDetails() != null)
            t.setAchatDetails(achatDetailConverter.copy(dto.getAchatDetails()));
        if (dto.getFacture() != null)
        factureConverter.copy(dto.getFacture(), t.getFacture());
    }

    public List<Achat> copy(List<AchatDto> dtos) {
        List<Achat> result = new ArrayList<>();
        if (dtos != null) {
            for (AchatDto dto : dtos) {
                Achat instance = new Achat();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public AchatDetailConverter getAchatDetailConverter(){
        return this.achatDetailConverter;
    }
    public void setAchatDetailConverter(AchatDetailConverter achatDetailConverter ){
        this.achatDetailConverter = achatDetailConverter;
    }
    public FournisseurConverter getFournisseurConverter(){
        return this.fournisseurConverter;
    }
    public void setFournisseurConverter(FournisseurConverter fournisseurConverter ){
        this.fournisseurConverter = fournisseurConverter;
    }
    public ArticleConverter getArticleConverter(){
        return this.articleConverter;
    }
    public void setArticleConverter(ArticleConverter articleConverter ){
        this.articleConverter = articleConverter;
    }
    public FactureConverter getFactureConverter(){
        return this.factureConverter;
    }
    public void setFactureConverter(FactureConverter factureConverter ){
        this.factureConverter = factureConverter;
    }
    public EtatAchatConverter getEtatAchatConverter(){
        return this.etatAchatConverter;
    }
    public void setEtatAchatConverter(EtatAchatConverter etatAchatConverter ){
        this.etatAchatConverter = etatAchatConverter;
    }
    public boolean  isEtatAchat(){
        return this.etatAchat;
    }
    public void  setEtatAchat(boolean etatAchat){
        this.etatAchat = etatAchat;
    }
    public boolean  isFournisseur(){
        return this.fournisseur;
    }
    public void  setFournisseur(boolean fournisseur){
        this.fournisseur = fournisseur;
    }
    public boolean  isFacture(){
        return this.facture;
    }
    public void  setFacture(boolean facture){
        this.facture = facture;
    }
    public boolean  isAchatDetails(){
        return this.achatDetails ;
    }
    public void  setAchatDetails(boolean achatDetails ){
        this.achatDetails  = achatDetails ;
    }
}
