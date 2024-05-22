package ma.zs.stocky.bean.core.achat;

import java.util.Objects;





import ma.zs.stocky.bean.core.article.Article;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "achat_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="achat_detail_seq",sequenceName="achat_detail_seq",allocationSize=1, initialValue = 1)
public class AchatDetail  extends BaseEntity     {

    private Long id;

    private BigDecimal prix = BigDecimal.ZERO;
    private BigDecimal qte = BigDecimal.ZERO;

    private Article article ;
    private Achat achat ;


    public AchatDetail(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="achat_detail_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public BigDecimal getPrix(){
        return this.prix;
    }
    public void setPrix(BigDecimal prix){
        this.prix = prix;
    }
    public BigDecimal getQte(){
        return this.qte;
    }
    public void setQte(BigDecimal qte){
        this.qte = qte;
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
    @JoinColumn(name = "achat")
    public Achat getAchat(){
        return this.achat;
    }
    public void setAchat(Achat achat){
        this.achat = achat;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AchatDetail achatDetail = (AchatDetail) o;
        return id != null && id.equals(achatDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

