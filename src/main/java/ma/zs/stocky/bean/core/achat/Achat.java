package ma.zs.stocky.bean.core.achat;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.bean.core.article.Article;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "achat")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="achat_seq",sequenceName="achat_seq",allocationSize=1, initialValue = 1)
public class Achat  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String reference;
    private BigDecimal montant = BigDecimal.ZERO;
    private LocalDateTime dateCommande ;
    private LocalDateTime dateLivraison ;

    private EtatAchat etatAchat ;
    private Fournisseur fournisseur ;
    private Facture facture ;

    private List<AchatDetail> achatDetails ;

    public Achat(){
        super();
    }



    
    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="achat_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public BigDecimal getMontant(){
        return this.montant;
    }
    public void setMontant(BigDecimal montant){
        this.montant = montant;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_achat")
    public EtatAchat getEtatAchat(){
        return this.etatAchat;
    }
    public void setEtatAchat(EtatAchat etatAchat){
        this.etatAchat = etatAchat;
    }
    public LocalDateTime getDateCommande(){
        return this.dateCommande;
    }
    public void setDateCommande(LocalDateTime dateCommande){
        this.dateCommande = dateCommande;
    }
    public LocalDateTime getDateLivraison(){
        return this.dateLivraison;
    }
    public void setDateLivraison(LocalDateTime dateLivraison){
        this.dateLivraison = dateLivraison;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur")
    public Fournisseur getFournisseur(){
        return this.fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }
    @OneToMany(mappedBy = "achat")

    public List<AchatDetail> getAchatDetails(){
        return this.achatDetails;
    }
    public void setAchatDetails(List<AchatDetail> achatDetails){
        this.achatDetails = achatDetails;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facture")
    public Facture getFacture(){
        return this.facture;
    }
    public void setFacture(Facture facture){
        this.facture = facture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achat achat = (Achat) o;
        return id != null && id.equals(achat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

