package  ma.zs.stocky.dao.criteria.core.article;


import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ArticleCriteria extends  BaseCriteria  {

    private String type;
    private String typeLike;
    private String reference;
    private String referenceLike;
    private String uniteVente;
    private String uniteVenteLike;
    private String quantite;
    private String quantiteMin;
    private String quantiteMax;
    private String quantiteAlerte;
    private String quantiteAlerteMin;
    private String quantiteAlerteMax;
    private String prixAchat;
    private String prixAchatMin;
    private String prixAchatMax;
    private String prixVente;
    private String prixVenteMin;
    private String prixVenteMax;
    private String description;
    private String descriptionLike;

    private CategorieArticleCriteria categorieArticle ;
    private List<CategorieArticleCriteria> categorieArticles ;
    private EtatArticleCriteria etatArticle ;
    private List<EtatArticleCriteria> etatArticles ;
    private MarqueCriteria marque ;
    private List<MarqueCriteria> marques ;
    private FournisseurCriteria fournisseur ;
    private List<FournisseurCriteria> fournisseurs ;


    public ArticleCriteria(){}

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getTypeLike(){
        return this.typeLike;
    }
    public void setTypeLike(String typeLike){
        this.typeLike = typeLike;
    }

    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReferenceLike(){
        return this.referenceLike;
    }
    public void setReferenceLike(String referenceLike){
        this.referenceLike = referenceLike;
    }

    public String getUniteVente(){
        return this.uniteVente;
    }
    public void setUniteVente(String uniteVente){
        this.uniteVente = uniteVente;
    }
    public String getUniteVenteLike(){
        return this.uniteVenteLike;
    }
    public void setUniteVenteLike(String uniteVenteLike){
        this.uniteVenteLike = uniteVenteLike;
    }

    public String getQuantite(){
        return this.quantite;
    }
    public void setQuantite(String quantite){
        this.quantite = quantite;
    }   
    public String getQuantiteMin(){
        return this.quantiteMin;
    }
    public void setQuantiteMin(String quantiteMin){
        this.quantiteMin = quantiteMin;
    }
    public String getQuantiteMax(){
        return this.quantiteMax;
    }
    public void setQuantiteMax(String quantiteMax){
        this.quantiteMax = quantiteMax;
    }
      
    public String getQuantiteAlerte(){
        return this.quantiteAlerte;
    }
    public void setQuantiteAlerte(String quantiteAlerte){
        this.quantiteAlerte = quantiteAlerte;
    }   
    public String getQuantiteAlerteMin(){
        return this.quantiteAlerteMin;
    }
    public void setQuantiteAlerteMin(String quantiteAlerteMin){
        this.quantiteAlerteMin = quantiteAlerteMin;
    }
    public String getQuantiteAlerteMax(){
        return this.quantiteAlerteMax;
    }
    public void setQuantiteAlerteMax(String quantiteAlerteMax){
        this.quantiteAlerteMax = quantiteAlerteMax;
    }
      
    public String getPrixAchat(){
        return this.prixAchat;
    }
    public void setPrixAchat(String prixAchat){
        this.prixAchat = prixAchat;
    }   
    public String getPrixAchatMin(){
        return this.prixAchatMin;
    }
    public void setPrixAchatMin(String prixAchatMin){
        this.prixAchatMin = prixAchatMin;
    }
    public String getPrixAchatMax(){
        return this.prixAchatMax;
    }
    public void setPrixAchatMax(String prixAchatMax){
        this.prixAchatMax = prixAchatMax;
    }
      
    public String getPrixVente(){
        return this.prixVente;
    }
    public void setPrixVente(String prixVente){
        this.prixVente = prixVente;
    }   
    public String getPrixVenteMin(){
        return this.prixVenteMin;
    }
    public void setPrixVenteMin(String prixVenteMin){
        this.prixVenteMin = prixVenteMin;
    }
    public String getPrixVenteMax(){
        return this.prixVenteMax;
    }
    public void setPrixVenteMax(String prixVenteMax){
        this.prixVenteMax = prixVenteMax;
    }
      
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public CategorieArticleCriteria getCategorieArticle(){
        return this.categorieArticle;
    }

    public void setCategorieArticle(CategorieArticleCriteria categorieArticle){
        this.categorieArticle = categorieArticle;
    }
    public List<CategorieArticleCriteria> getCategorieArticles(){
        return this.categorieArticles;
    }

    public void setCategorieArticles(List<CategorieArticleCriteria> categorieArticles){
        this.categorieArticles = categorieArticles;
    }
    public EtatArticleCriteria getEtatArticle(){
        return this.etatArticle;
    }

    public void setEtatArticle(EtatArticleCriteria etatArticle){
        this.etatArticle = etatArticle;
    }
    public List<EtatArticleCriteria> getEtatArticles(){
        return this.etatArticles;
    }

    public void setEtatArticles(List<EtatArticleCriteria> etatArticles){
        this.etatArticles = etatArticles;
    }
    public MarqueCriteria getMarque(){
        return this.marque;
    }

    public void setMarque(MarqueCriteria marque){
        this.marque = marque;
    }
    public List<MarqueCriteria> getMarques(){
        return this.marques;
    }

    public void setMarques(List<MarqueCriteria> marques){
        this.marques = marques;
    }
    public FournisseurCriteria getFournisseur(){
        return this.fournisseur;
    }

    public void setFournisseur(FournisseurCriteria fournisseur){
        this.fournisseur = fournisseur;
    }
    public List<FournisseurCriteria> getFournisseurs(){
        return this.fournisseurs;
    }

    public void setFournisseurs(List<FournisseurCriteria> fournisseurs){
        this.fournisseurs = fournisseurs;
    }
}
