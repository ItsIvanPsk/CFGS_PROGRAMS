import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Autor")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
        name = "autorId",
        unique = true,
        nullable = false
    )
    private long autorId;

    @Column(name = "nom")
    private String nom; 

    @OneToMany
    @JoinColumn(name = "autorId")
    private Set<Llibre> llibres;

    public Autor() {}

    public Autor(String nom) {
    this.nom = nom;
    }

    public long getAutorId() {
        return autorId;
    }

    public void setAutorId(long autorId) {
        this.autorId = autorId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return this.getAutorId() + ": " + this.getNom() + ", Items: " + queryLlibres() ;
    }
    
    public List<Object[]> queryLlibres() {
        return Manager.queryTable(
            "Select DISTINCT l.nom FROM Llibre l, Autor c WHERE c.autorId = l.autorId AND c.autorId= " + this.getAutorId()
        );
    }

}
