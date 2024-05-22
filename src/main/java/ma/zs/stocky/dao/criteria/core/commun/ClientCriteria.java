package  ma.zs.stocky.dao.criteria.core.commun;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ClientCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String nom;
    private String nomLike;
    private String numeroTelephone;
    private String numeroTelephoneLike;
    private String adresse;
    private String adresseLike;
    private String email;
    private String emailLike;
    private String poste;
    private String posteLike;

    private SocieteCriteria societe ;
    private List<SocieteCriteria> societes ;


    public ClientCriteria(){}

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
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


    public SocieteCriteria getSociete(){
        return this.societe;
    }

    public void setSociete(SocieteCriteria societe){
        this.societe = societe;
    }
    public List<SocieteCriteria> getSocietes(){
        return this.societes;
    }

    public void setSocietes(List<SocieteCriteria> societes){
        this.societes = societes;
    }
}
