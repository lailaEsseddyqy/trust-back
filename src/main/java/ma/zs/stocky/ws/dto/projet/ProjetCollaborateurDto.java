package  ma.zs.stocky.ws.dto.projet;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.collaborateur.CollaborateurDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjetCollaborateurDto  extends AuditBaseDto {


    private CollaborateurDto collaborateur ;
    private ProjetDto projet ;



    public ProjetCollaborateurDto(){
        super();
    }




    public CollaborateurDto getCollaborateur(){
        return this.collaborateur;
    }

    public void setCollaborateur(CollaborateurDto collaborateur){
        this.collaborateur = collaborateur;
    }
    public ProjetDto getProjet(){
        return this.projet;
    }

    public void setProjet(ProjetDto projet){
        this.projet = projet;
    }






}
