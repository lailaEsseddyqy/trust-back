package ma.zs.stocky.bean.core.projet;

import java.math.BigDecimal;
import java.util.Objects;





import ma.zs.stocky.bean.core.article.Article;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "projet_article")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="projet_article_seq",sequenceName="projet_article_seq",allocationSize=1, initialValue = 1)
public class ProjetArticle  extends BaseEntity     {

    private Long id;


    private Article article ;
    private Projet projet ;



    public ProjetArticle(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="projet_article_seq")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projet")
    public Projet getProjet(){
        return this.projet;
    }
    public void setProjet(Projet projet){
        this.projet = projet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetArticle projetArticle = (ProjetArticle) o;
        return id != null && id.equals(projetArticle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

