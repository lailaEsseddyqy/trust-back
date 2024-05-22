package  ma.zs.stocky.ws.dto.projet;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class PieceDto  extends AuditBaseDto {

    private String code  ;
    private String path  ;




    public PieceDto(){
        super();
    }



    @Log
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Log
    public String getPath(){
        return this.path;
    }
    public void setPath(String path){
        this.path = path;
    }








}
