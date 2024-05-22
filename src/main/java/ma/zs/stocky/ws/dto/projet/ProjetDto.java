package  ma.zs.stocky.ws.dto.projet;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.article.ArticleDto;
import ma.zs.stocky.ws.dto.commun.ClientDto;
import ma.zs.stocky.ws.dto.collaborateur.CollaborateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjetDto  extends AuditBaseDto {

    private String reference  ;
    private String nom  ;
    private BigDecimal budet  ;
    private String dateDebut ;
    private String dateFin ;
    private String nomChefProjet  ;
    private String description  ;

    private ClientDto client ;
    private PieceDto pieceJoint ;
    private EtatAvancementDto etatAvancement ;

    private List<ProjetCollaborateurDto> projetCollaborateurs ;
    private List<ProjetArticleDto> projetArticles ;


    public ProjetDto(){
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
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public BigDecimal getBudet(){
        return this.budet;
    }
    public void setBudet(BigDecimal budet){
        this.budet = budet;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(String dateDebut){
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(String dateFin){
        this.dateFin = dateFin;
    }

    @Log
    public String getNomChefProjet(){
        return this.nomChefProjet;
    }
    public void setNomChefProjet(String nomChefProjet){
        this.nomChefProjet = nomChefProjet;
    }

    @Log
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public ClientDto getClient(){
        return this.client;
    }

    public void setClient(ClientDto client){
        this.client = client;
    }
    public PieceDto getPieceJoint(){
        return this.pieceJoint;
    }

    public void setPieceJoint(PieceDto pieceJoint){
        this.pieceJoint = pieceJoint;
    }
    public EtatAvancementDto getEtatAvancement(){
        return this.etatAvancement;
    }

    public void setEtatAvancement(EtatAvancementDto etatAvancement){
        this.etatAvancement = etatAvancement;
    }



    public List<ProjetCollaborateurDto> getProjetCollaborateurs(){
        return this.projetCollaborateurs;
    }

    public void setProjetCollaborateurs(List<ProjetCollaborateurDto> projetCollaborateurs){
        this.projetCollaborateurs = projetCollaborateurs;
    }
    public List<ProjetArticleDto> getProjetArticles(){
        return this.projetArticles;
    }

    public void setProjetArticles(List<ProjetArticleDto> projetArticles){
        this.projetArticles = projetArticles;
    }



}
