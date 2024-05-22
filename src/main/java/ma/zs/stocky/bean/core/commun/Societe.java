package ma.zs.stocky.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "societe")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="societe_seq",sequenceName="societe_seq",allocationSize=1, initialValue = 1)
public class Societe  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String numeroTelephone;
    @Column(length = 500)
    private String adresse;



    public Societe(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="societe_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getNumeroTelephone(){
        return this.numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone){
        this.numeroTelephone = numeroTelephone;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Societe societe = (Societe) o;
        return id != null && id.equals(societe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

