package  ma.zs.stocky.dao.specification.core.projet;

import ma.zs.stocky.dao.criteria.core.projet.ProjetCriteria;
import ma.zs.stocky.bean.core.projet.Projet;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class ProjetSpecification extends  AbstractSpecification<ProjetCriteria, Projet>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicateBigDecimal("budet", criteria.getBudet(), criteria.getBudetMin(), criteria.getBudetMax());
        addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
        addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
        addPredicate("nomChefProjet", criteria.getNomChefProjet(),criteria.getNomChefProjetLike());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","code", criteria.getClient()==null?null:criteria.getClient().getCode());
        addPredicateFk("pieceJoint","id", criteria.getPieceJoint()==null?null:criteria.getPieceJoint().getId());
        addPredicateFk("pieceJoint","id", criteria.getPieceJoints());
        addPredicateFk("pieceJoint","code", criteria.getPieceJoint()==null?null:criteria.getPieceJoint().getCode());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getId());
        addPredicateFk("etatAvancement","id", criteria.getEtatAvancements());
        addPredicateFk("etatAvancement","code", criteria.getEtatAvancement()==null?null:criteria.getEtatAvancement().getCode());
    }

    public ProjetSpecification(ProjetCriteria criteria) {
        super(criteria);
    }

    public ProjetSpecification(ProjetCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
