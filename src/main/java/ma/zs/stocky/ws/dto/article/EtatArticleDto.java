package  ma.zs.stocky.ws.dto.article;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtatArticleDto  extends AuditBaseDto {

    private String libelle  ;

    private ArticleDto article ;



    public EtatArticleDto(){
        super();
    }



    @Log
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public ArticleDto getArticle(){
        return this.article;
    }

    public void setArticle(ArticleDto article){
        this.article = article;
    }






}
