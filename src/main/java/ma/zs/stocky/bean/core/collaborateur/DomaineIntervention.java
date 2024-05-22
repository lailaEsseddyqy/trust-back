package ma.zs.stocky.bean.core.collaborateur;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domaine_intervention")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="domaine_intervention_seq",sequenceName="domaine_intervention_seq",allocationSize=1, initialValue = 1)
public class DomaineIntervention  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String libelle;



    public DomaineIntervention(){
        super();
    }

    public DomaineIntervention(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public DomaineIntervention(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="domaine_intervention_seq")
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
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomaineIntervention domaineIntervention = (DomaineIntervention) o;
        return id != null && id.equals(domaineIntervention.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

