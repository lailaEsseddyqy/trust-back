package  ma.zs.stocky.dao.criteria.core.achat;



import ma.zs.stocky.zynerator.criteria.BaseCriteria;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class FactureCriteria extends  BaseCriteria  {

    private String reference;
    private String referenceLike;
    private LocalDateTime dateFacture;
    private LocalDateTime dateFactureFrom;
    private LocalDateTime dateFactureTo;
    private LocalDateTime dateLimite;
    private LocalDateTime dateLimiteFrom;
    private LocalDateTime dateLimiteTo;
    private String montantHt;
    private String montantHtMin;
    private String montantHtMax;
    private String montantTtc;
    private String montantTtcMin;
    private String montantTtcMax;
    private String montantTva;
    private String montantTvaMin;
    private String montantTvaMax;
    private String remise;
    private String remiseMin;
    private String remiseMax;

    private TypeFactureCriteria typeFacture ;
    private List<TypeFactureCriteria> typeFactures ;


    public FactureCriteria(){}

    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public String getReferenceLike(){
        return this.referenceLike;
    }
    public void setReferenceLike(String referenceLike){
        this.referenceLike = referenceLike;
    }

    public LocalDateTime getDateFacture(){
        return this.dateFacture;
    }
    public void setDateFacture(LocalDateTime dateFacture){
        this.dateFacture = dateFacture;
    }
    public LocalDateTime getDateFactureFrom(){
        return this.dateFactureFrom;
    }
    public void setDateFactureFrom(LocalDateTime dateFactureFrom){
        this.dateFactureFrom = dateFactureFrom;
    }
    public LocalDateTime getDateFactureTo(){
        return this.dateFactureTo;
    }
    public void setDateFactureTo(LocalDateTime dateFactureTo){
        this.dateFactureTo = dateFactureTo;
    }
    public LocalDateTime getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(LocalDateTime dateLimite){
        this.dateLimite = dateLimite;
    }
    public LocalDateTime getDateLimiteFrom(){
        return this.dateLimiteFrom;
    }
    public void setDateLimiteFrom(LocalDateTime dateLimiteFrom){
        this.dateLimiteFrom = dateLimiteFrom;
    }
    public LocalDateTime getDateLimiteTo(){
        return this.dateLimiteTo;
    }
    public void setDateLimiteTo(LocalDateTime dateLimiteTo){
        this.dateLimiteTo = dateLimiteTo;
    }
    public String getMontantHt(){
        return this.montantHt;
    }
    public void setMontantHt(String montantHt){
        this.montantHt = montantHt;
    }   
    public String getMontantHtMin(){
        return this.montantHtMin;
    }
    public void setMontantHtMin(String montantHtMin){
        this.montantHtMin = montantHtMin;
    }
    public String getMontantHtMax(){
        return this.montantHtMax;
    }
    public void setMontantHtMax(String montantHtMax){
        this.montantHtMax = montantHtMax;
    }
      
    public String getMontantTtc(){
        return this.montantTtc;
    }
    public void setMontantTtc(String montantTtc){
        this.montantTtc = montantTtc;
    }   
    public String getMontantTtcMin(){
        return this.montantTtcMin;
    }
    public void setMontantTtcMin(String montantTtcMin){
        this.montantTtcMin = montantTtcMin;
    }
    public String getMontantTtcMax(){
        return this.montantTtcMax;
    }
    public void setMontantTtcMax(String montantTtcMax){
        this.montantTtcMax = montantTtcMax;
    }
      
    public String getMontantTva(){
        return this.montantTva;
    }
    public void setMontantTva(String montantTva){
        this.montantTva = montantTva;
    }   
    public String getMontantTvaMin(){
        return this.montantTvaMin;
    }
    public void setMontantTvaMin(String montantTvaMin){
        this.montantTvaMin = montantTvaMin;
    }
    public String getMontantTvaMax(){
        return this.montantTvaMax;
    }
    public void setMontantTvaMax(String montantTvaMax){
        this.montantTvaMax = montantTvaMax;
    }
      
    public String getRemise(){
        return this.remise;
    }
    public void setRemise(String remise){
        this.remise = remise;
    }   
    public String getRemiseMin(){
        return this.remiseMin;
    }
    public void setRemiseMin(String remiseMin){
        this.remiseMin = remiseMin;
    }
    public String getRemiseMax(){
        return this.remiseMax;
    }
    public void setRemiseMax(String remiseMax){
        this.remiseMax = remiseMax;
    }
      

    public TypeFactureCriteria getTypeFacture(){
        return this.typeFacture;
    }

    public void setTypeFacture(TypeFactureCriteria typeFacture){
        this.typeFacture = typeFacture;
    }
    public List<TypeFactureCriteria> getTypeFactures(){
        return this.typeFactures;
    }

    public void setTypeFactures(List<TypeFactureCriteria> typeFactures){
        this.typeFactures = typeFactures;
    }
}
