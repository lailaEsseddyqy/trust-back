package ma.zs.stocky.bean.core.commun;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fournisseur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="fournisseur_seq",sequenceName="fournisseur_seq",allocationSize=1, initialValue = 1)
public class Fournisseur  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String numeroTelephone;
    @Column(length = 500)
    private String adresse;
    @Column(length = 500)
    private String email;
    @Column(length = 500)
    private String rib;

    private Societe societe ;


    public Fournisseur(){
        super();
    }

    public Fournisseur(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Fournisseur(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="fournisseur_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
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
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getRib(){
        return this.rib;
    }
    public void setRib(String rib){
        this.rib = rib;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe")
    public Societe getSociete(){
        return this.societe;
    }
    public void setSociete(Societe societe){
        this.societe = societe;
    }

    @Transient
    public String getLabel() {
        label = code;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fournisseur fournisseur = (Fournisseur) o;
        return id != null && id.equals(fournisseur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

