package  ma.zs.stocky.ws.dto.projet;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zs.stocky.ws.dto.article.ArticleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjetArticleDto  extends AuditBaseDto {


    private ArticleDto article ;
    private ProjetDto projet ;



    public ProjetArticleDto(){
        super();
    }




    public ArticleDto getArticle(){
        return this.article;
    }

    public void setArticle(ArticleDto article){
        this.article = article;
    }
    public ProjetDto getProjet(){
        return this.projet;
    }

    public void setProjet(ProjetDto projet){
        this.projet = projet;
    }






}
