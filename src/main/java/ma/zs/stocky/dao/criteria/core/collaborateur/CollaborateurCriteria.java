package  ma.zs.stocky.dao.criteria.core.collaborateur;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class CollaborateurCriteria extends  BaseCriteria  {

    private String nom;
    private String nomLike;
    private String ref;
    private String refLike;
    private String poste;
    private String posteLike;
    private String numeroTelephone;
    private String numeroTelephoneLike;
    private String adresse;
    private String adresseLike;
    private String email;
    private String emailLike;



    public CollaborateurCriteria(){}

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

    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRefLike(){
        return this.refLike;
    }
    public void setRefLike(String refLike){
        this.refLike = refLike;
    }

    public String getPoste(){
        return this.poste;
    }
    public void setPoste(String poste){
        this.poste = poste;
    }
    public String getPosteLike(){
        return this.posteLike;
    }
    public void setPosteLike(String posteLike){
        this.posteLike = posteLike;
    }

    public String getNumeroTelephone(){
        return this.numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone){
        this.numeroTelephone = numeroTelephone;
    }
    public String getNumeroTelephoneLike(){
        return this.numeroTelephoneLike;
    }
    public void setNumeroTelephoneLike(String numeroTelephoneLike){
        this.numeroTelephoneLike = numeroTelephoneLike;
    }

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }


}
