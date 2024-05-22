package  ma.zs.stocky.dao.criteria.core.achat;


import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class AchatDetailCriteria extends  BaseCriteria  {

    private String prix;
    private String prixMin;
    private String prixMax;
    private String qte;
    private String qteMin;
    private String qteMax;

    private ArticleCriteria article ;
    private List<ArticleCriteria> articles ;
    private AchatCriteria achat ;
    private List<AchatCriteria> achats ;


    public AchatDetailCriteria(){}

    public String getPrix(){
        return this.prix;
    }
    public void setPrix(String prix){
        this.prix = prix;
    }   
    public String getPrixMin(){
        return this.prixMin;
    }
    public void setPrixMin(String prixMin){
        this.prixMin = prixMin;
    }
    public String getPrixMax(){
        return this.prixMax;
    }
    public void setPrixMax(String prixMax){
        this.prixMax = prixMax;
    }
      
    public String getQte(){
        return this.qte;
    }
    public void setQte(String qte){
        this.qte = qte;
    }   
    public String getQteMin(){
        return this.qteMin;
    }
    public void setQteMin(String qteMin){
        this.qteMin = qteMin;
    }
    public String getQteMax(){
        return this.qteMax;
    }
    public void setQteMax(String qteMax){
        this.qteMax = qteMax;
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
    public AchatCriteria getAchat(){
        return this.achat;
    }

    public void setAchat(AchatCriteria achat){
        this.achat = achat;
    }
    public List<AchatCriteria> getAchats(){
        return this.achats;
    }

    public void setAchats(List<AchatCriteria> achats){
        this.achats = achats;
    }
}
