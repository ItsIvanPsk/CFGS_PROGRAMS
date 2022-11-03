import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Ciutada")
public class Ciutada implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    long id; // PK
    @Column(name = "ciutatId")
    long ciutatId; // FK
    @Column(name = "nom")
    String nom;
    @Column(name = "cognom")
    String cognom;
    @Column(name = "edat")
    int edat;

    public Ciutada(){}

    public Ciutada(long _ciutatId, String _nom, String _cognom, int _edat) {
        this.ciutatId = _ciutatId;
        this.nom = _nom;
        this.cognom = _cognom;
        this.edat = _edat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCiutatId() {
        return ciutatId;
    }

    public void setCiutatId(long ciutatId) {
        this.ciutatId = ciutatId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    @Override
    public String toString() {
        return "Ciutada{" +
                "id=" + id +
                ", ciutatId=" + ciutatId +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", edat=" + edat +
                '}';
    }
}
