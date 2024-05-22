package  ma.zs.stocky.dao.criteria.core.achat;


import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class AchatCriteria extends  BaseCriteria  {

    private String reference;
    private String referenceLike;
    private String montant;
    private String montantMin;
    private String montantMax;
    private LocalDateTime dateCommande;
    private LocalDateTime dateCommandeFrom;
    private LocalDateTime dateCommandeTo;
    private LocalDateTime dateLivraison;
    private LocalDateTime dateLivraisonFrom;
    private LocalDateTime dateLivraisonTo;

    private EtatAchatCriteria etatAchat ;
    private List<EtatAchatCriteria> etatAchats ;
    private FournisseurCriteria fournisseur ;
    private List<FournisseurCriteria> fournisseurs ;
    private FactureCriteria facture ;
    private List<FactureCriteria> factures ;


    public AchatCriteria(){}

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

    public String getMontant(){
        return this.montant;
    }
    public void setMontant(String montant){
        this.montant = montant;
    }   
    public String getMontantMin(){
        return this.montantMin;
    }
    public void setMontantMin(String montantMin){
        this.montantMin = montantMin;
    }
    public String getMontantMax(){
        return this.montantMax;
    }
    public void setMontantMax(String montantMax){
        this.montantMax = montantMax;
    }
      
    public LocalDateTime getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(LocalDateTime dateCommande){
        this.dateCommande = dateCommande;
    }
    public LocalDateTime getDateCommandeFrom(){
        return this.dateCommandeFrom;
    }
    public void setDateCommandeFrom(LocalDateTime dateCommandeFrom){
        this.dateCommandeFrom = dateCommandeFrom;
    }
    public LocalDateTime getDateCommandeTo(){
        return this.dateCommandeTo;
    }
    public void setDateCommandeTo(LocalDateTime dateCommandeTo){
        this.dateCommandeTo = dateCommandeTo;
    }
    public LocalDateTime getDateLivraison(){
        return this.dateLivraison;
    }
    public void setDateLivraison(LocalDateTime dateLivraison){
        this.dateLivraison = dateLivraison;
    }
    public LocalDateTime getDateLivraisonFrom(){
        return this.dateLivraisonFrom;
    }
    public void setDateLivraisonFrom(LocalDateTime dateLivraisonFrom){
        this.dateLivraisonFrom = dateLivraisonFrom;
    }
    public LocalDateTime getDateLivraisonTo(){
        return this.dateLivraisonTo;
    }
    public void setDateLivraisonTo(LocalDateTime dateLivraisonTo){
        this.dateLivraisonTo = dateLivraisonTo;
    }

    public EtatAchatCriteria getEtatAchat(){
        return this.etatAchat;
    }

    public void setEtatAchat(EtatAchatCriteria etatAchat){
        this.etatAchat = etatAchat;
    }
    public List<EtatAchatCriteria> getEtatAchats(){
        return this.etatAchats;
    }

    public void setEtatAchats(List<EtatAchatCriteria> etatAchats){
        this.etatAchats = etatAchats;
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
    public FactureCriteria getFacture(){
        return this.facture;
    }

    public void setFacture(FactureCriteria facture){
        this.facture = facture;
    }
    public List<FactureCriteria> getFactures(){
        return this.factures;
    }

    public void setFactures(List<FactureCriteria> factures){
        this.factures = factures;
    }
}
