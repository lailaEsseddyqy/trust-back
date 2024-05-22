package  ma.zs.stocky.dao.specification.core.achat;

import ma.zs.stocky.dao.criteria.core.achat.TypeFactureCriteria;
import ma.zs.stocky.bean.core.achat.TypeFacture;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class TypeFactureSpecification extends  AbstractSpecification<TypeFactureCriteria, TypeFacture>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeFactureSpecification(TypeFactureCriteria criteria) {
        super(criteria);
    }

    public TypeFactureSpecification(TypeFactureCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
