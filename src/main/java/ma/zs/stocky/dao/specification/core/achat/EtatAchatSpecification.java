package  ma.zs.stocky.dao.specification.core.achat;

import ma.zs.stocky.dao.criteria.core.achat.EtatAchatCriteria;
import ma.zs.stocky.bean.core.achat.EtatAchat;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EtatAchatSpecification extends  AbstractSpecification<EtatAchatCriteria, EtatAchat>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public EtatAchatSpecification(EtatAchatCriteria criteria) {
        super(criteria);
    }

    public EtatAchatSpecification(EtatAchatCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
