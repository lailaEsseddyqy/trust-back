package  ma.zs.stocky.dao.criteria.core.projet;


import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;

import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class ProjetArticleCriteria extends  BaseCriteria  {


    private ArticleCriteria article ;
    private List<ArticleCriteria> articles ;
    private ProjetCriteria projet ;
    private List<ProjetCriteria> projets ;


    public ProjetArticleCriteria(){}


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
    public ProjetCriteria getProjet(){
        return this.projet;
    }

    public void setProjet(ProjetCriteria projet){
        this.projet = projet;
    }
    public List<ProjetCriteria> getProjets(){
        return this.projets;
    }

    public void setProjets(List<ProjetCriteria> projets){
        this.projets = projets;
    }
}
