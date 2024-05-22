package  ma.zs.stocky.dao.criteria.core.article;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class EtatArticleCriteria extends  BaseCriteria  {

    private String libelle;
    private String libelleLike;

    private ArticleCriteria article ;
    private List<ArticleCriteria> articles ;


    public EtatArticleCriteria(){}

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


    public ArticleCriteria getArticle(){
        return this.article;
    }

    public void setArticle(ArticleCriteria article){
        this.article = article;
    }
    public List<ArticleCriteria> getArticles(){
        return this.articles;
    }

    public void setArticles(List<ArticleCriteria> articles){
        this.articles = articles;
    }
}
