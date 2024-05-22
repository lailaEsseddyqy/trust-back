package ma.zs.stocky.bean.core.achat;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "etat_achat")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etat_achat_seq",sequenceName="etat_achat_seq",allocationSize=1, initialValue = 1)
public class EtatAchat  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String libelle;



    public EtatAchat(){
        super();
    }

    public EtatAchat(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public EtatAchat(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etat_achat_seq")
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
        EtatAchat etatAchat = (EtatAchat) o;
        return id != null && id.equals(etatAchat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

