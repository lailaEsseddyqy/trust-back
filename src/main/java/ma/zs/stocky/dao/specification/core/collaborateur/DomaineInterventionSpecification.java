package  ma.zs.stocky.dao.specification.core.collaborateur;

import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCriteria;
import ma.zs.stocky.bean.core.collaborateur.DomaineIntervention;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class DomaineInterventionSpecification extends  AbstractSpecification<DomaineInterventionCriteria, DomaineIntervention>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public DomaineInterventionSpecification(DomaineInterventionCriteria criteria) {
        super(criteria);
    }

    public DomaineInterventionSpecification(DomaineInterventionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
