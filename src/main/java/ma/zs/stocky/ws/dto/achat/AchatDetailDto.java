package  ma.zs.stocky.ws.dto.achat;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zs.stocky.ws.dto.article.ArticleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AchatDetailDto  extends AuditBaseDto {

    private BigDecimal prix  ;
    private BigDecimal qte  ;

    private ArticleDto article ;
    private AchatDto achat ;



    public AchatDetailDto(){
        super();
    }



    @Log
    public BigDecimal getPrix(){
        return this.prix;
    }
    public void setPrix(BigDecimal prix){
        this.prix = prix;
    }

    @Log
    public BigDecimal getQte(){
        return this.qte;
    }
    public void setQte(BigDecimal qte){
        this.qte = qte;
    }


    public ArticleDto getArticle(){
        return this.article;
    }

    public void setArticle(ArticleDto article){
        this.article = article;
    }
    public AchatDto getAchat(){
        return this.achat;
    }

    public void setAchat(AchatDto achat){
        this.achat = achat;
    }






}
