package  ma.zs.stocky.dao.specification.core.article;

import ma.zs.stocky.dao.criteria.core.article.CategorieArticleCriteria;
import ma.zs.stocky.bean.core.article.CategorieArticle;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class CategorieArticleSpecification extends  AbstractSpecification<CategorieArticleCriteria, CategorieArticle>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public CategorieArticleSpecification(CategorieArticleCriteria criteria) {
        super(criteria);
    }

    public CategorieArticleSpecification(CategorieArticleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
