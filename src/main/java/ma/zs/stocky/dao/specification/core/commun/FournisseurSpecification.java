package  ma.zs.stocky.dao.specification.core.commun;

import ma.zs.stocky.dao.criteria.core.commun.FournisseurCriteria;
import ma.zs.stocky.bean.core.commun.Fournisseur;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class FournisseurSpecification extends  AbstractSpecification<FournisseurCriteria, Fournisseur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("numeroTelephone", criteria.getNumeroTelephone(),criteria.getNumeroTelephoneLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("rib", criteria.getRib(),criteria.getRibLike());
        addPredicateFk("societe","id", criteria.getSociete()==null?null:criteria.getSociete().getId());
        addPredicateFk("societe","id", criteria.getSocietes());
    }

    public FournisseurSpecification(FournisseurCriteria criteria) {
        super(criteria);
    }

    public FournisseurSpecification(FournisseurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
