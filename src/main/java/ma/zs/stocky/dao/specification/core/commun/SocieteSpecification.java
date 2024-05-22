package  ma.zs.stocky.dao.specification.core.commun;

import ma.zs.stocky.dao.criteria.core.commun.SocieteCriteria;
import ma.zs.stocky.bean.core.commun.Societe;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class SocieteSpecification extends  AbstractSpecification<SocieteCriteria, Societe>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("numeroTelephone", criteria.getNumeroTelephone(),criteria.getNumeroTelephoneLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
    }

    public SocieteSpecification(SocieteCriteria criteria) {
        super(criteria);
    }

    public SocieteSpecification(SocieteCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
