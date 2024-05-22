package  ma.zs.stocky.dao.specification.core.article;

import ma.zs.stocky.dao.criteria.core.article.MarqueCriteria;
import ma.zs.stocky.bean.core.article.Marque;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class MarqueSpecification extends  AbstractSpecification<MarqueCriteria, Marque>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public MarqueSpecification(MarqueCriteria criteria) {
        super(criteria);
    }

    public MarqueSpecification(MarqueCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
