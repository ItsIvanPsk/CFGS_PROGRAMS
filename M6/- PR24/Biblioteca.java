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
@Table(name = "Biblioteca")
public class Biblioteca implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bibliotecaId", unique = true, 
    nullable = false)
    private long bibliotecaId;

    @Column(name = "nom")
    private String nom; 

    @Column(name = "ciutat")
    private String ciutat; 

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Llibre_Biblioteca", 
		joinColumns = {
            @JoinColumn(referencedColumnName = "bibliotecaId")
        }, 
		inverseJoinColumns = {
            @JoinColumn(referencedColumnName = "llibreId")
        })
    private Set<Llibre> llibres;

    public Biblioteca() {}

    public Biblioteca(String nom,String ciutat) {
    this.nom = nom;
    this.ciutat=ciutat;
    }

    public long getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(long bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Set<Llibre> getLlibres() {
        return llibres;
    }

    public void setLlibres(Set<Llibre> llibres) {
        this.llibres = llibres;
    }

    @Override
    public String toString() {
        return this.getBibliotecaId() + ": " + this.getNom() + ", " + this.getCiutat() + ", llibres " + queryItems() ;
    }
    
    public List<Object[]> queryItems () {
        return Manager.queryTable(
            "Select DISTINCT l.nom FROM Llibre_Biblioteca lb, Llibre l WHERE l.llibreId = lb.llibres_llibreId AND lb.biblioteques_bibliotecaId=" + this.getBibliotecaId()
        );
    }

}