package  ma.zs.stocky.dao.specification.core.article;

import ma.zs.stocky.dao.criteria.core.article.EtatArticleCriteria;
import ma.zs.stocky.bean.core.article.EtatArticle;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class EtatArticleSpecification extends  AbstractSpecification<EtatArticleCriteria, EtatArticle>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("article","id", criteria.getArticle()==null?null:criteria.getArticle().getId());
        addPredicateFk("article","id", criteria.getArticles());
    }

    public EtatArticleSpecification(EtatArticleCriteria criteria) {
        super(criteria);
    }

    public EtatArticleSpecification(EtatArticleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
