package ma.zs.stocky.bean.core.achat;

import java.util.Objects;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "facture")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="facture_seq",sequenceName="facture_seq",allocationSize=1, initialValue = 1)
public class Facture  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String reference;
    private LocalDateTime dateFacture ;
    private LocalDateTime dateLimite ;
    private BigDecimal montantHt = BigDecimal.ZERO;
    private BigDecimal montantTtc = BigDecimal.ZERO;
    private BigDecimal montantTva = BigDecimal.ZERO;
    private BigDecimal remise = BigDecimal.ZERO;

    private TypeFacture typeFacture ;


    public Facture(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="facture_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getReference(){
        return this.reference;
    }
    public void setReference(String reference){
        this.reference = reference;
    }
    public LocalDateTime getDateFacture(){
        return this.dateFacture;
    }
    public void setDateFacture(LocalDateTime dateFacture){
        this.dateFacture = dateFacture;
    }
    public LocalDateTime getDateLimite(){
        return this.dateLimite;
    }
    public void setDateLimite(LocalDateTime dateLimite){
        this.dateLimite = dateLimite;
    }
    public BigDecimal getMontantHt(){
        return this.montantHt;
    }
    public void setMontantHt(BigDecimal montantHt){
        this.montantHt = montantHt;
    }
    public BigDecimal getMontantTtc(){
        return this.montantTtc;
    }
    public void setMontantTtc(BigDecimal montantTtc){
        this.montantTtc = montantTtc;
    }
    public BigDecimal getMontantTva(){
        return this.montantTva;
    }
    public void setMontantTva(BigDecimal montantTva){
        this.montantTva = montantTva;
    }
    public BigDecimal getRemise(){
        return this.remise;
    }
    public void setRemise(BigDecimal remise){
        this.remise = remise;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_facture")
    public TypeFacture getTypeFacture(){
        return this.typeFacture;
    }
    public void setTypeFacture(TypeFacture typeFacture){
        this.typeFacture = typeFacture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facture facture = (Facture) o;
        return id != null && id.equals(facture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

