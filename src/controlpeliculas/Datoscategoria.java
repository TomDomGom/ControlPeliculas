/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "DATOSCATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datoscategoria.findAll", query = "SELECT d FROM Datoscategoria d")
    , @NamedQuery(name = "Datoscategoria.findById", query = "SELECT d FROM Datoscategoria d WHERE d.id = :id")
    , @NamedQuery(name = "Datoscategoria.findByNombre", query = "SELECT d FROM Datoscategoria d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Datoscategoria.findByDescripcion", query = "SELECT d FROM Datoscategoria d WHERE d.descripcion = :descripcion")})
public class Datoscategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "categoria")
    private Collection<Datospeliculas> datospeliculasCollection;

    public Datoscategoria() {
    }

    public Datoscategoria(Integer id) {
        this.id = id;
    }

    public Datoscategoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Datospeliculas> getDatospeliculasCollection() {
        return datospeliculasCollection;
    }

    public void setDatospeliculasCollection(Collection<Datospeliculas> datospeliculasCollection) {
        this.datospeliculasCollection = datospeliculasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datoscategoria)) {
            return false;
        }
        Datoscategoria other = (Datoscategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlpeliculas.Datoscategoria[ id=" + id + " ]";
    }
    
}
