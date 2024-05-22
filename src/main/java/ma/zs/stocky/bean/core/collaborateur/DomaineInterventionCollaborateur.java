package ma.zs.stocky.bean.core.collaborateur;

import java.util.Objects;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "domaine_intervention_collaborateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="domaine_intervention_collaborateur_seq",sequenceName="domaine_intervention_collaborateur_seq",allocationSize=1, initialValue = 1)
public class DomaineInterventionCollaborateur  extends BaseEntity     {

    private Long id;


    private DomaineIntervention domaineIntervention ;
    private Collaborateur collaborateur ;


    public DomaineInterventionCollaborateur(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="domaine_intervention_collaborateur_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domaine_intervention")
    public DomaineIntervention getDomaineIntervention(){
        return this.domaineIntervention;
    }
    public void setDomaineIntervention(DomaineIntervention domaineIntervention){
        this.domaineIntervention = domaineIntervention;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborateur")
    public Collaborateur getCollaborateur(){
        return this.collaborateur;
    }
    public void setCollaborateur(Collaborateur collaborateur){
        this.collaborateur = collaborateur;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomaineInterventionCollaborateur domaineInterventionCollaborateur = (DomaineInterventionCollaborateur) o;
        return id != null && id.equals(domaineInterventionCollaborateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

