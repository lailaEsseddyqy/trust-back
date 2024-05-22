package  ma.zs.stocky.dao.specification.core.achat;

import ma.zs.stocky.dao.criteria.core.achat.AchatCriteria;
import ma.zs.stocky.bean.core.achat.Achat;
import ma.zs.stocky.zynerator.specification.AbstractSpecification;


public class AchatSpecification extends  AbstractSpecification<AchatCriteria, Achat>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("reference", criteria.getReference(),criteria.getReferenceLike());
        addPredicateBigDecimal("montant", criteria.getMontant(), criteria.getMontantMin(), criteria.getMontantMax());
        addPredicate("dateCommande", criteria.getDateCommande(), criteria.getDateCommandeFrom(), criteria.getDateCommandeTo());
        addPredicate("dateLivraison", criteria.getDateLivraison(), criteria.getDateLivraisonFrom(), criteria.getDateLivraisonTo());
        addPredicateFk("etatAchat","id", criteria.getEtatAchat()==null?null:criteria.getEtatAchat().getId());
        addPredicateFk("etatAchat","id", criteria.getEtatAchats());
        addPredicateFk("etatAchat","code", criteria.getEtatAchat()==null?null:criteria.getEtatAchat().getCode());
        addPredicateFk("fournisseur","id", criteria.getFournisseur()==null?null:criteria.getFournisseur().getId());
        addPredicateFk("fournisseur","id", criteria.getFournisseurs());
        addPredicateFk("fournisseur","code", criteria.getFournisseur()==null?null:criteria.getFournisseur().getCode());
        addPredicateFk("facture","id", criteria.getFacture()==null?null:criteria.getFacture().getId());
        addPredicateFk("facture","id", criteria.getFactures());
    }

    public AchatSpecification(AchatCriteria criteria) {
        super(criteria);
    }

    public AchatSpecification(AchatCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
