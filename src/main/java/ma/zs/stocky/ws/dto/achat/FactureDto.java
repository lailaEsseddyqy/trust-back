package  ma.zs.stocky.ws.dto.achat;

import ma.zs.stocky.zynerator.audit.Log;
import ma.zs.stocky.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class FactureDto  extends AuditBaseDto {

    private String reference  ;
    private String dateFacture ;
    private String dateLimite ;
    private BigDecimal montantHt  ;
    private BigDecimal montantTtc  ;
    private BigDecimal montantTva  ;
    private BigDecimal remise  ;

    private TypeFactureDto typeFacture ;
    private boolean paid;



    public FactureDto(){
        super();
    }



    @Log
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFacture(){
        return this.dateFacture;
    }
    public void setDateFacture(String dateFacture){
        this.dateFacture = dateFacture;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(String dateLimite){
        this.dateLimite = dateLimite;
    }

    @Log
    public BigDecimal getMontantHt(){
        return this.montantHt;
    }
    public void setMontantHt(BigDecimal montantHt){
        this.montantHt = montantHt;
    }

    @Log
    public BigDecimal getMontantTtc(){
        return this.montantTtc;
    }
    public void setMontantTtc(BigDecimal montantTtc){
        this.montantTtc = montantTtc;
    }

    @Log
    public BigDecimal getMontantTva(){
        return this.montantTva;
    }
    public void setMontantTva(BigDecimal montantTva){
        this.montantTva = montantTva;
    }

    @Log
    public BigDecimal getRemise(){
        return this.remise;
    }
    public void setRemise(BigDecimal remise){
        this.remise = remise;
    }

    @Log
    public boolean isPaid() {return paid;}
    public void setPaid(boolean paid) {this.paid = paid;}




    public TypeFactureDto getTypeFacture(){
        return this.typeFacture;
    }

    public void setTypeFacture(TypeFactureDto typeFacture){
        this.typeFacture = typeFacture;
    }






}
