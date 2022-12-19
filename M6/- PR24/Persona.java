import java.io.Serializable;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Persona" )
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
        name = "personaId",
        unique = true, 
        nullable = false
    )

    private long personaId;

    @Column(name = "dni")
    private String dni; 

    @Column(name = "nom")
    private String nom; 

    @Column(name = "telefon")
    private String telefon; 

    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "Llibre_Persona", 
		joinColumns = {
            @JoinColumn(referencedColumnName = "personaId")
        }, 
		inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "llibreId")
        }
    )
    private Set<Llibre> llibres;

    public Persona(){}

    public Persona(String nom,String dni,String telefon){ 
        this.nom=nom;
        this.telefon=telefon;
        this.dni=dni;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long Id) {
        this.personaId = Id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return "Persona [autorId=" + personaId + ", dni=" + dni + ", nom=" + nom + ", telefon=" + telefon + ", llibres=" + queryItems();
    }

    public List<Object[]> queryItems () {
        return Manager.queryTable(
            "Select DISTINCT l.nom FROM Llibre_Persona lb, Llibre l WHERE l.llibreId = lb.llibres_llibreId AND lb.persones_personaId=" + this.getPersonaId()
        );
    }

}
