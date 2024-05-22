package  ma.zs.stocky.dao.criteria.core.projet;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;

public class PieceCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String path;
    private String pathLike;



    public PieceCriteria(){}

    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }
    public String getPathLike(){
        return this.pathLike;
    }
    public void setPathLike(String pathLike){
        this.pathLike = pathLike;
    }


}
