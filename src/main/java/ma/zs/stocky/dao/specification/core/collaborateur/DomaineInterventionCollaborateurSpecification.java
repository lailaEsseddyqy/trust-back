package  ma.zs.stocky.dao.specification.core.collaborateur;

import ma.zs.stocky.dao.criteria.core.collaborateur.DomaineInterventionCollaborateurCriteria;
import ma.zs.stocky.bean.core.collaborateur.DomaineInterventionCollaborateur;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class DomaineInterventionCollaborateurSpecification extends  AbstractSpecification<DomaineInterventionCollaborateurCriteria, DomaineInterventionCollaborateur>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("domaineIntervention","id", criteria.getDomaineIntervention()==null?null:criteria.getDomaineIntervention().getId());
        addPredicateFk("domaineIntervention","id", criteria.getDomaineInterventions());
        addPredicateFk("domaineIntervention","code", criteria.getDomaineIntervention()==null?null:criteria.getDomaineIntervention().getCode());
        addPredicateFk("collaborateur","id", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getId());
        addPredicateFk("collaborateur","id", criteria.getCollaborateurs());
        addPredicateFk("collaborateur","ref", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getRef());
    }

    public DomaineInterventionCollaborateurSpecification(DomaineInterventionCollaborateurCriteria criteria) {
        super(criteria);
    }

    public DomaineInterventionCollaborateurSpecification(DomaineInterventionCollaborateurCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
