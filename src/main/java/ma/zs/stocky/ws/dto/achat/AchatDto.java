package  ma.zs.stocky.ws.dto.achat;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.commun.FournisseurDto;
import ma.zs.stocky.ws.dto.article.ArticleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AchatDto  extends AuditBaseDto {

    private String reference  ;
    private BigDecimal montant  ;
    private String dateCommande ;
    private String dateLivraison ;

    private EtatAchatDto etatAchat ;
    private FournisseurDto fournisseur ;
    private FactureDto facture ;

    private List<AchatDetailDto> achatDetails ;


    public AchatDto(){
        super();
    }



    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    public BigDecimal getMontant(){
        return this.montant;
    }
    public void setMontant(BigDecimal montant){
        this.montant = montant;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(String dateCommande){
        this.dateCommande = dateCommande;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateLivraison(){
        return this.dateLivraison;
    }
    public void setDateLivraison(String dateLivraison){
        this.dateLivraison = dateLivraison;
    }


    public EtatAchatDto getEtatAchat(){
        return this.etatAchat;
    }

    public void setEtatAchat(EtatAchatDto etatAchat){
        this.etatAchat = etatAchat;
    }
    public FournisseurDto getFournisseur(){
        return this.fournisseur;
    }

    public void setFournisseur(FournisseurDto fournisseur){
        this.fournisseur = fournisseur;
    }
    public FactureDto getFacture(){
        return this.facture;
    }

    public void setFacture(FactureDto facture){
        this.facture = facture;
    }



    public List<AchatDetailDto> getAchatDetails(){
        return this.achatDetails;
    }

    public void setAchatDetails(List<AchatDetailDto> achatDetails){
        this.achatDetails = achatDetails;
    }



}
