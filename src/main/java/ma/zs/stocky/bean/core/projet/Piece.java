package ma.zs.stocky.bean.core.projet;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "piece")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="piece_seq",sequenceName="piece_seq",allocationSize=1, initialValue = 1)
public class Piece  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String code;
    @Column(length = 500)
    private String path;



    public Piece(){
        super();
    }

    public Piece(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public Piece(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="piece_seq")
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
    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
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
        Piece piece = (Piece) o;
        return id != null && id.equals(piece.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

