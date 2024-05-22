package  ma.zs.stocky.dao.criteria.core.projet;


import ma.zs.stocky.dao.criteria.core.commun.ClientCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ProjetCriteria extends  BaseCriteria  {

    private String reference;
    private String referenceLike;
    private String nom;
    private String nomLike;
    private String budet;
    private String budetMin;
    private String budetMax;
    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;
    private String nomChefProjet;
    private String nomChefProjetLike;
    private String description;
    private String descriptionLike;

    private ClientCriteria client ;
    private List<ClientCriteria> clients ;
    private PieceCriteria pieceJoint ;
    private List<PieceCriteria> pieceJoints ;
    private EtatAvancementCriteria etatAvancement ;
    private List<EtatAvancementCriteria> etatAvancements ;


    public ProjetCriteria(){}

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

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getBudet(){
        return this.budet;
    }
    public void setBudet(String budet){
        this.budet = budet;
    }   
    public String getBudetMin(){
        return this.budetMin;
    }
    public void setBudetMin(String budetMin){
        this.budetMin = budetMin;
    }
    public String getBudetMax(){
        return this.budetMax;
    }
    public void setBudetMax(String budetMax){
        this.budetMax = budetMax;
    }
      
    public LocalDateTime getDateDebut(){
        return this.dateDebut;
    }
    public void setDateDebut(LocalDateTime dateDebut){
        this.dateDebut = dateDebut;
    }
    public LocalDateTime getDateDebutFrom(){
        return this.dateDebutFrom;
    }
    public void setDateDebutFrom(LocalDateTime dateDebutFrom){
        this.dateDebutFrom = dateDebutFrom;
    }
    public LocalDateTime getDateDebutTo(){
        return this.dateDebutTo;
    }
    public void setDateDebutTo(LocalDateTime dateDebutTo){
        this.dateDebutTo = dateDebutTo;
    }
    public LocalDateTime getDateFin(){
        return this.dateFin;
    }
    public void setDateFin(LocalDateTime dateFin){
        this.dateFin = dateFin;
    }
    public LocalDateTime getDateFinFrom(){
        return this.dateFinFrom;
    }
    public void setDateFinFrom(LocalDateTime dateFinFrom){
        this.dateFinFrom = dateFinFrom;
    }
    public LocalDateTime getDateFinTo(){
        return this.dateFinTo;
    }
    public void setDateFinTo(LocalDateTime dateFinTo){
        this.dateFinTo = dateFinTo;
    }
    public String getNomChefProjet(){
        return this.nomChefProjet;
    }
    public void setNomChefProjet(String nomChefProjet){
        this.nomChefProjet = nomChefProjet;
    }
    public String getNomChefProjetLike(){
        return this.nomChefProjetLike;
    }
    public void setNomChefProjetLike(String nomChefProjetLike){
        this.nomChefProjetLike = nomChefProjetLike;
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


    public ClientCriteria getClient(){
        return this.client;
    }

    public void setClient(ClientCriteria client){
        this.client = client;
    }
    public List<ClientCriteria> getClients(){
        return this.clients;
    }

    public void setClients(List<ClientCriteria> clients){
        this.clients = clients;
    }
    public PieceCriteria getPieceJoint(){
        return this.pieceJoint;
    }

    public void setPieceJoint(PieceCriteria pieceJoint){
        this.pieceJoint = pieceJoint;
    }
    public List<PieceCriteria> getPieceJoints(){
        return this.pieceJoints;
    }

    public void setPieceJoints(List<PieceCriteria> pieceJoints){
        this.pieceJoints = pieceJoints;
    }
    public EtatAvancementCriteria getEtatAvancement(){
        return this.etatAvancement;
    }

    public void setEtatAvancement(EtatAvancementCriteria etatAvancement){
        this.etatAvancement = etatAvancement;
    }
    public List<EtatAvancementCriteria> getEtatAvancements(){
        return this.etatAvancements;
    }

    public void setEtatAvancements(List<EtatAvancementCriteria> etatAvancements){
        this.etatAvancements = etatAvancements;
    }
}
