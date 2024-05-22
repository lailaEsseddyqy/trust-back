package  ma.zs.stocky.ws.dto.commun;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocieteDto  extends AuditBaseDto {

    private String nom  ;
    private String code  ;
    private String numeroTelephone  ;
    private String adresse  ;




    public SocieteDto(){
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
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
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








}
