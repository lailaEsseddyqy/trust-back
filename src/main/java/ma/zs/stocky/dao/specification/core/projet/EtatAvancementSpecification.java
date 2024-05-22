package  ma.zs.stocky.dao.specification.core.projet;

import ma.zs.stocky.dao.criteria.core.projet.EtatAvancementCriteria;
import ma.zs.stocky.bean.core.projet.EtatAvancement;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EtatAvancementSpecification extends  AbstractSpecification<EtatAvancementCriteria, EtatAvancement>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EtatAvancementSpecification(EtatAvancementCriteria criteria) {
        super(criteria);
    }

    public EtatAvancementSpecification(EtatAvancementCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
