package ma.zs.stocky.bean.core.article;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "etat_article")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="etat_article_seq",sequenceName="etat_article_seq",allocationSize=1, initialValue = 1)
public class EtatArticle  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String libelle;

    private Article article ;


    public EtatArticle(){
        super();
    }

    public EtatArticle(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public EtatArticle(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="etat_article_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article")
    public Article getArticle(){
        return this.article;
    }
    public void setArticle(Article article){
        this.article = article;
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
        EtatArticle etatArticle = (EtatArticle) o;
        return id != null && id.equals(etatArticle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

