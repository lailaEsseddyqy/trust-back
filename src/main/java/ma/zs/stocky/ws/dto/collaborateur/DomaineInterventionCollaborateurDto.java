package  ma.zs.stocky.ws.dto.collaborateur;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomaineInterventionCollaborateurDto  extends AuditBaseDto {


    private DomaineInterventionDto domaineIntervention ;
    private CollaborateurDto collaborateur ;



    public DomaineInterventionCollaborateurDto(){
        super();
    }




    public DomaineInterventionDto getDomaineIntervention(){
        return this.domaineIntervention;
    }

    public void setDomaineIntervention(DomaineInterventionDto domaineIntervention){
        this.domaineIntervention = domaineIntervention;
    }
    public CollaborateurDto getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurDto collaborateur){
        this.collaborateur = collaborateur;
    }






}
