package ma.zs.stocky.bean.core.projet;

import java.util.Objects;
import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.bean.core.commun.Client;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "projet")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="projet_seq",sequenceName="projet_seq",allocationSize=1, initialValue = 1)
public class Projet  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String reference;
    @Column(length = 500)
    private String nom;
    private BigDecimal budet = BigDecimal.ZERO;
    private LocalDateTime dateDebut ;
    private LocalDateTime dateFin ;
    @Column(length = 500)
    private String nomChefProjet;
    @Column(length = 500)
    private String description;

    private Client client ;
    private Piece pieceJoint ;
    private EtatAvancement etatAvancement ;

    private List<ProjetCollaborateur> projetCollaborateurs ;
    private List<ProjetArticle> projetArticles ;

    public Projet(){
        super();
    }

    public Projet(Long id,String reference){
        this.id = id;
        this.reference = reference ;
    }
    public Projet(String reference){
        this.reference = reference ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="projet_seq")
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
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public BigDecimal getBudet(){
        return this.budet;
    }
    public void setBudet(BigDecimal budet){
        this.budet = budet;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client")
    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }
    @OneToMany(mappedBy = "projet")

    public List<ProjetCollaborateur> getProjetCollaborateurs(){
        return this.projetCollaborateurs;
    }
    public void setProjetCollaborateurs(List<ProjetCollaborateur> projetCollaborateurs){
        this.projetCollaborateurs = projetCollaborateurs;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "piece_joint")
    public Piece getPieceJoint(){
        return this.pieceJoint;
    }
    public void setPieceJoint(Piece pieceJoint){
        this.pieceJoint = pieceJoint;
    }
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public String getNomChefProjet(){
        return this.nomChefProjet;
    }
    public void setNomChefProjet(String nomChefProjet){
        this.nomChefProjet = nomChefProjet;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etat_avancement")
    public EtatAvancement getEtatAvancement(){
        return this.etatAvancement;
    }
    public void setEtatAvancement(EtatAvancement etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @OneToMany(mappedBy = "projet")

    public List<ProjetArticle> getProjetArticles(){
        return this.projetArticles;
    }
    public void setProjetArticles(List<ProjetArticle> projetArticles){
        this.projetArticles = projetArticles;
    }

    @Transient
    public String getLabel() {
        label = reference;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projet projet = (Projet) o;
        return id != null && id.equals(projet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

