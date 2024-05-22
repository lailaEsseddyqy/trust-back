package  ma.zs.stocky.dao.specification.core.projet;

import ma.zs.stocky.dao.criteria.core.projet.PieceCriteria;
import ma.zs.stocky.bean.core.projet.Piece;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class PieceSpecification extends  AbstractSpecification<PieceCriteria, Piece>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("path", criteria.getPath(),criteria.getPathLike());
    }

    public PieceSpecification(PieceCriteria criteria) {
        super(criteria);
    }

    public PieceSpecification(PieceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
