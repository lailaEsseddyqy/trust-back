package ma.zs.stocky.bean.core.collaborateur;

import java.util.Objects;
import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.stocky.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "collaborateur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="collaborateur_seq",sequenceName="collaborateur_seq",allocationSize=1, initialValue = 1)
public class Collaborateur  extends BaseEntity     {

    private Long id;

    @Column(length = 500)
    private String nom;
    @Column(length = 500)
    private String ref;
    @Column(length = 500)
    private String poste;
    @Column(length = 500)
    private String numeroTelephone;
    @Column(length = 500)
    private String adresse;
    @Column(length = 500)
    private String email;


    private List<DomaineInterventionCollaborateur> domaineInterventionCollaborateurs ;

    public Collaborateur(){
        super();
    }

    public Collaborateur(Long id,String ref){
        this.id = id;
        this.ref = ref ;
    }
    public Collaborateur(String ref){
        this.ref = ref ;
    }




    @Id
    @Column(name = "id")
        @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="collaborateur_seq")
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getRef(){
        return this.ref;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getPoste(){
        return this.poste;
    }
    public void setPoste(String poste){
        this.poste = poste;
    }
    public String getNumeroTelephone(){
        return this.numeroTelephone;
    }
    public void setNumeroTelephone(String numeroTelephone){
        this.numeroTelephone = numeroTelephone;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    @OneToMany(mappedBy = "collaborateur")

    public List<DomaineInterventionCollaborateur> getDomaineInterventionCollaborateurs(){
        return this.domaineInterventionCollaborateurs;
    }
    public void setDomaineInterventionCollaborateurs(List<DomaineInterventionCollaborateur> domaineInterventionCollaborateurs){
        this.domaineInterventionCollaborateurs = domaineInterventionCollaborateurs;
    }

    @Transient
    public String getLabel() {
        label = ref;
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collaborateur collaborateur = (Collaborateur) o;
        return id != null && id.equals(collaborateur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

