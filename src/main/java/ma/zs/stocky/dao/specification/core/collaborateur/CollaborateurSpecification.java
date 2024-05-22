package  ma.zs.stocky.dao.specification.core.collaborateur;

import ma.zs.stocky.dao.criteria.core.collaborateur.CollaborateurCriteria;
import ma.zs.stocky.bean.core.collaborateur.Collaborateur;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class CollaborateurSpecification extends  AbstractSpecification<CollaborateurCriteria, Collaborateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("ref", criteria.getRef(),criteria.getRefLike());
        addPredicate("poste", criteria.getPoste(),criteria.getPosteLike());
        addPredicate("numeroTelephone", criteria.getNumeroTelephone(),criteria.getNumeroTelephoneLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
    }

    public CollaborateurSpecification(CollaborateurCriteria criteria) {
        super(criteria);
    }

    public CollaborateurSpecification(CollaborateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
