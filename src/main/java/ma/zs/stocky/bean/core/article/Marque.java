package ma.zs.stocky.bean.core.article;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marque")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="marque_seq",sequenceName="marque_seq",allocationSize=1, initialValue = 1)
public class Marque  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String libelle;



    public Marque(){
        super();
    }

    public Marque(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Marque(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="marque_seq")
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
        Marque marque = (Marque) o;
        return id != null && id.equals(marque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

