package  ma.zs.stocky.dao.specification.core.article;

import ma.zs.stocky.dao.criteria.core.article.ArticleCriteria;
import ma.zs.stocky.bean.core.article.Article;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class ArticleSpecification extends  AbstractSpecification<ArticleCriteria, Article>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("type", criteria.getType(),criteria.getTypeLike());
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("uniteVente", criteria.getUniteVente(),criteria.getUniteVenteLike());
        addPredicateBigDecimal("quantite", criteria.getQuantite(), criteria.getQuantiteMin(), criteria.getQuantiteMax());
        addPredicateBigDecimal("quantiteAlerte", criteria.getQuantiteAlerte(), criteria.getQuantiteAlerteMin(), criteria.getQuantiteAlerteMax());
        addPredicateBigDecimal("prixAchat", criteria.getPrixAchat(), criteria.getPrixAchatMin(), criteria.getPrixAchatMax());
        addPredicateBigDecimal("prixVente", criteria.getPrixVente(), criteria.getPrixVenteMin(), criteria.getPrixVenteMax());
        addPredicate("description", criteria.getDescription(),criteria.getDescriptionLike());
        addPredicateFk("categorieArticle","id", criteria.getCategorieArticle()==null?null:criteria.getCategorieArticle().getId());
        addPredicateFk("categorieArticle","id", criteria.getCategorieArticles());
        addPredicateFk("categorieArticle","code", criteria.getCategorieArticle()==null?null:criteria.getCategorieArticle().getCode());
        addPredicateFk("etatArticle","id", criteria.getEtatArticle()==null?null:criteria.getEtatArticle().getId());
        addPredicateFk("etatArticle","id", criteria.getEtatArticles());
        addPredicateFk("marque","id", criteria.getMarque()==null?null:criteria.getMarque().getId());
        addPredicateFk("marque","id", criteria.getMarques());
        addPredicateFk("marque","code", criteria.getMarque()==null?null:criteria.getMarque().getCode());
        addPredicateFk("fournisseur","id", criteria.getFournisseur()==null?null:criteria.getFournisseur().getId());
        addPredicateFk("fournisseur","id", criteria.getFournisseurs());
        addPredicateFk("fournisseur","code", criteria.getFournisseur()==null?null:criteria.getFournisseur().getCode());
    }

    public ArticleSpecification(ArticleCriteria criteria) {
        super(criteria);
    }

    public ArticleSpecification(ArticleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
