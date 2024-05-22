package  ma.zs.stocky.ws.dto.article;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.commun.FournisseurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDto  extends AuditBaseDto {

    private String type  ;
    private String reference  ;
    private String uniteVente  ;
    private BigDecimal quantite  ;
    private BigDecimal quantiteAlerte  ;
    private BigDecimal prixAchat  ;
    private BigDecimal prixVente  ;
    private String description  ;

    private CategorieArticleDto categorieArticle ;
    private EtatArticleDto etatArticle ;
    private MarqueDto marque ;
    private FournisseurDto fournisseur ;



    public ArticleDto(){
        super();
    }



    @Log
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    public String getUniteVente(){
        return this.uniteVente;
    }
    public void setUniteVente(String uniteVente){
        this.uniteVente = uniteVente;
    }

    @Log
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }

    @Log
    public BigDecimal getQuantiteAlerte(){
        return this.quantiteAlerte;
    }
    public void setQuantiteAlerte(BigDecimal quantiteAlerte){
        this.quantiteAlerte = quantiteAlerte;
    }

    @Log
    public BigDecimal getPrixAchat(){
        return this.prixAchat;
    }
    public void setPrixAchat(BigDecimal prixAchat){
        this.prixAchat = prixAchat;
    }

    @Log
    public BigDecimal getPrixVente(){
        return this.prixVente;
    }
    public void setPrixVente(BigDecimal prixVente){
        this.prixVente = prixVente;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public CategorieArticleDto getCategorieArticle(){
        return this.categorieArticle;
    }

    public void setCategorieArticle(CategorieArticleDto categorieArticle){
        this.categorieArticle = categorieArticle;
    }
    public EtatArticleDto getEtatArticle(){
        return this.etatArticle;
    }

    public void setEtatArticle(EtatArticleDto etatArticle){
        this.etatArticle = etatArticle;
    }
    public MarqueDto getMarque(){
        return this.marque;
    }

    public void setMarque(MarqueDto marque){
        this.marque = marque;
    }
    public FournisseurDto getFournisseur(){
        return this.fournisseur;
    }

    public void setFournisseur(FournisseurDto fournisseur){
        this.fournisseur = fournisseur;
    }






}
