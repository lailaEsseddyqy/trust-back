package  ma.zs.stocky.dao.specification.core.projet;

import ma.zs.stocky.dao.criteria.core.projet.ProjetArticleCriteria;
import ma.zs.stocky.bean.core.projet.ProjetArticle;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class ProjetArticleSpecification extends  AbstractSpecification<ProjetArticleCriteria, ProjetArticle>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("article","id", criteria.getArticle()==null?null:criteria.getArticle().getId());
        addPredicateFk("article","id", criteria.getArticles());
        addPredicateFk("projet","id", criteria.getProjet()==null?null:criteria.getProjet().getId());
        addPredicateFk("projet","id", criteria.getProjets());
        addPredicateFk("projet","reference", criteria.getProjet()==null?null:criteria.getProjet().getReference());
    }

    public ProjetArticleSpecification(ProjetArticleCriteria criteria) {
        super(criteria);
    }

    public ProjetArticleSpecification(ProjetArticleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
