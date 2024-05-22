package ma.zs.stocky.bean.core.projet;

import java.util.Objects;





import ma.zs.stocky.bean.core.collaborateur.Collaborateur;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "projet_collaborateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="projet_collaborateur_seq",sequenceName="projet_collaborateur_seq",allocationSize=1, initialValue = 1)
public class ProjetCollaborateur  extends BaseEntity     {

    private Long id;


    private Collaborateur collaborateur ;
    private Projet projet ;


    public ProjetCollaborateur(){
        super();
    }





    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="projet_collaborateur_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborateur")
    public Collaborateur getCollaborateur(){
        return this.collaborateur;
    }
    public void setCollaborateur(Collaborateur collaborateur){
        this.collaborateur = collaborateur;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projet")
    public Projet getProjet(){
        return this.projet;
    }
    public void setProjet(Projet projet){
        this.projet = projet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjetCollaborateur projetCollaborateur = (ProjetCollaborateur) o;
        return id != null && id.equals(projetCollaborateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

