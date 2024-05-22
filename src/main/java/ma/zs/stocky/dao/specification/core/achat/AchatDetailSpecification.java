package  ma.zs.stocky.dao.specification.core.achat;

import ma.zs.stocky.dao.criteria.core.achat.AchatDetailCriteria;
import ma.zs.stocky.bean.core.achat.AchatDetail;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class AchatDetailSpecification extends  AbstractSpecification<AchatDetailCriteria, AchatDetail>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateBigDecimal("prix", criteria.getPrix(), criteria.getPrixMin(), criteria.getPrixMax());
        addPredicateBigDecimal("qte", criteria.getQte(), criteria.getQteMin(), criteria.getQteMax());
        addPredicateFk("article","id", criteria.getArticle()==null?null:criteria.getArticle().getId());
        addPredicateFk("article","id", criteria.getArticles());
        addPredicateFk("achat","id", criteria.getAchat()==null?null:criteria.getAchat().getId());
        addPredicateFk("achat","id", criteria.getAchats());
    }

    public AchatDetailSpecification(AchatDetailCriteria criteria) {
        super(criteria);
    }

    public AchatDetailSpecification(AchatDetailCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
