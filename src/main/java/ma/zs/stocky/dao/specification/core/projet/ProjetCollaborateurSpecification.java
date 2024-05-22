package  ma.zs.stocky.dao.specification.core.projet;

import ma.zs.stocky.dao.criteria.core.projet.ProjetCollaborateurCriteria;
import ma.zs.stocky.bean.core.projet.ProjetCollaborateur;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class ProjetCollaborateurSpecification extends  AbstractSpecification<ProjetCollaborateurCriteria, ProjetCollaborateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("collaborateur","id", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getId());
        addPredicateFk("collaborateur","id", criteria.getCollaborateurs());
        addPredicateFk("collaborateur","ref", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getRef());
        addPredicateFk("projet","id", criteria.getProjet()==null?null:criteria.getProjet().getId());
        addPredicateFk("projet","id", criteria.getProjets());
        addPredicateFk("projet","reference", criteria.getProjet()==null?null:criteria.getProjet().getReference());
    }

    public ProjetCollaborateurSpecification(ProjetCollaborateurCriteria criteria) {
        super(criteria);
    }

    public ProjetCollaborateurSpecification(ProjetCollaborateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
