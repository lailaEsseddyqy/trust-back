package  ma.zs.stocky.dao.criteria.core.collaborateur;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class DomaineInterventionCollaborateurCriteria extends  BaseCriteria  {


    private DomaineInterventionCriteria domaineIntervention ;
    private List<DomaineInterventionCriteria> domaineInterventions ;
    private CollaborateurCriteria collaborateur ;
    private List<CollaborateurCriteria> collaborateurs ;


    public DomaineInterventionCollaborateurCriteria(){}


    public DomaineInterventionCriteria getDomaineIntervention(){
        return this.domaineIntervention;
    }

    public void setDomaineIntervention(DomaineInterventionCriteria domaineIntervention){
        this.domaineIntervention = domaineIntervention;
    }
    public List<DomaineInterventionCriteria> getDomaineInterventions(){
        return this.domaineInterventions;
    }

    public void setDomaineInterventions(List<DomaineInterventionCriteria> domaineInterventions){
        this.domaineInterventions = domaineInterventions;
    }
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
}
