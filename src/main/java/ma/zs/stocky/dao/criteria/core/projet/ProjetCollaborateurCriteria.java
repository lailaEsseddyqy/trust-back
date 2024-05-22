package  ma.zs.stocky.dao.criteria.core.projet;


import ma.zs.stocky.dao.criteria.core.collaborateur.CollaborateurCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ProjetCollaborateurCriteria extends  BaseCriteria  {


    private CollaborateurCriteria collaborateur ;
    private List<CollaborateurCriteria> collaborateurs ;
    private ProjetCriteria projet ;
    private List<ProjetCriteria> projets ;


    public ProjetCollaborateurCriteria(){}


    public CollaborateurCriteria getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurCriteria collaborateur){
        this.collaborateur = collaborateur;
    }
    public List<CollaborateurCriteria> getCollaborateurs(){
        return this.collaborateurs;
    }

    public void setCollaborateurs(List<CollaborateurCriteria> collaborateurs){
        this.collaborateurs = collaborateurs;
    }
    public ProjetCriteria getProjet(){
        return this.projet;
    }

    public void setProjet(ProjetCriteria projet){
        this.projet = projet;
    }
    public List<ProjetCriteria> getProjets(){
        return this.projets;
    }

    public void setProjets(List<ProjetCriteria> projets){
        this.projets = projets;
    }
}
