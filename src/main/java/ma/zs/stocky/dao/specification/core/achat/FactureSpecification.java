package  ma.zs.stocky.dao.specification.core.achat;

import ma.zs.stocky.dao.criteria.core.achat.FactureCriteria;
import ma.zs.stocky.bean.core.achat.Facture;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class FactureSpecification extends  AbstractSpecification<FactureCriteria, Facture>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("dateFacture", criteria.getDateFacture(), criteria.getDateFactureFrom(), criteria.getDateFactureTo());
        addPredicate("dateLimite", criteria.getDateLimite(), criteria.getDateLimiteFrom(), criteria.getDateLimiteTo());
        addPredicateBigDecimal("montantHt", criteria.getMontantHt(), criteria.getMontantHtMin(), criteria.getMontantHtMax());
        addPredicateBigDecimal("montantTtc", criteria.getMontantTtc(), criteria.getMontantTtcMin(), criteria.getMontantTtcMax());
        addPredicateBigDecimal("montantTva", criteria.getMontantTva(), criteria.getMontantTvaMin(), criteria.getMontantTvaMax());
        addPredicateBigDecimal("remise", criteria.getRemise(), criteria.getRemiseMin(), criteria.getRemiseMax());
        addPredicateFk("typeFacture","id", criteria.getTypeFacture()==null?null:criteria.getTypeFacture().getId());
        addPredicateFk("typeFacture","id", criteria.getTypeFactures());
        addPredicateFk("typeFacture","code", criteria.getTypeFacture()==null?null:criteria.getTypeFacture().getCode());
    }

    public FactureSpecification(FactureCriteria criteria) {
        super(criteria);
    }

    public FactureSpecification(FactureCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
