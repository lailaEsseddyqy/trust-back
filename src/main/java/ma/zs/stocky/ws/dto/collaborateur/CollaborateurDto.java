package  ma.zs.stocky.ws.dto.collaborateur;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class CollaborateurDto  extends AuditBaseDto {

    private String nom  ;
    private String ref  ;
    private String poste  ;
    private String numeroTelephone  ;
    private String adresse  ;
    private String email  ;


    private List<DomaineInterventionCollaborateurDto> domaineInterventionCollaborateurs ;


    public CollaborateurDto(){
        super();
    }



    @Log
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    @Log
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }

    @Log
    public String getPoste(){
        return this.poste;
    }
    public void setPoste(String poste){
        this.poste = poste;
    }

    @Log
    public String getNumeroTelephone(){
        return this.numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone){
        this.numeroTelephone = numeroTelephone;
    }

    @Log
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    @Log
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }





    public List<DomaineInterventionCollaborateurDto> getDomaineInterventionCollaborateurs(){
        return this.domaineInterventionCollaborateurs;
    }

    public void setDomaineInterventionCollaborateurs(List<DomaineInterventionCollaborateurDto> domaineInterventionCollaborateurs){
        this.domaineInterventionCollaborateurs = domaineInterventionCollaborateurs;
    }



}
