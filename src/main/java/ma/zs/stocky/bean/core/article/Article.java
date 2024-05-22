package ma.zs.stocky.bean.core.article;

import java.util.Objects;





import ma.zs.stocky.bean.core.commun.Fournisseur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "article")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="article_seq",sequenceName="article_seq",allocationSize=1, initialValue = 1)
public class Article  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String type;
    @Column(length = 500)
    private String reference;
    @Column(length = 500)
    private String uniteVente;
    private BigDecimal quantite = BigDecimal.ZERO;
    private BigDecimal quantiteAlerte = BigDecimal.ZERO;
    private BigDecimal prixAchat = BigDecimal.ZERO;
    private BigDecimal prixVente = BigDecimal.ZERO;
    @Column(length = 500)
    private String description;

    private CategorieArticle categorieArticle ;
    private EtatArticle etatArticle ;
    private Marque marque ;
    private Fournisseur fournisseur ;


    public Article(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="article_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getUniteVente(){
        return this.uniteVente;
    }
    public void setUniteVente(String uniteVente){
        this.uniteVente = uniteVente;
    }
    public BigDecimal getQuantite(){
        return this.quantite;
    }
    public void setQuantite(BigDecimal quantite){
        this.quantite = quantite;
    }
    public BigDecimal getQuantiteAlerte(){
        return this.quantiteAlerte;
    }
    public void setQuantiteAlerte(BigDecimal quantiteAlerte){
        this.quantiteAlerte = quantiteAlerte;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_article")
    public CategorieArticle getCategorieArticle(){
        return this.categorieArticle;
    }
    public void setCategorieArticle(CategorieArticle categorieArticle){
        this.categorieArticle = categorieArticle;
    }
    public BigDecimal getPrixAchat(){
        return this.prixAchat;
    }
    public void setPrixAchat(BigDecimal prixAchat){
        this.prixAchat = prixAchat;
    }
    public BigDecimal getPrixVente(){
        return this.prixVente;
    }
    public void setPrixVente(BigDecimal prixVente){
        this.prixVente = prixVente;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_article")
    public EtatArticle getEtatArticle(){
        return this.etatArticle;
    }
    public void setEtatArticle(EtatArticle etatArticle){
        this.etatArticle = etatArticle;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marque")
    public Marque getMarque(){
        return this.marque;
    }
    public void setMarque(Marque marque){
        this.marque = marque;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur")
    public Fournisseur getFournisseur(){
        return this.fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur){
        this.fournisseur = fournisseur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

