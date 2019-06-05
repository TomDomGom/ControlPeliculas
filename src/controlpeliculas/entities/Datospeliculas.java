/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlpeliculas.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tomas
 */
@Entity
@Table(name = "DATOSPELICULAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datospeliculas.findAll", query = "SELECT d FROM Datospeliculas d")
    , @NamedQuery(name = "Datospeliculas.findById", query = "SELECT d FROM Datospeliculas d WHERE d.id = :id")
    , @NamedQuery(name = "Datospeliculas.findByTitulo", query = "SELECT d FROM Datospeliculas d WHERE d.titulo = :titulo")
    , @NamedQuery(name = "Datospeliculas.findByDirector", query = "SELECT d FROM Datospeliculas d WHERE d.director = :director")
    , @NamedQuery(name = "Datospeliculas.findByFechaestreno", query = "SELECT d FROM Datospeliculas d WHERE d.fechaestreno = :fechaestreno")
    , @NamedQuery(name = "Datospeliculas.findByProductora", query = "SELECT d FROM Datospeliculas d WHERE d.productora = :productora")
    , @NamedQuery(name = "Datospeliculas.findByCartel", query = "SELECT d FROM Datospeliculas d WHERE d.cartel = :cartel")
    , @NamedQuery(name = "Datospeliculas.findByCalificacion", query = "SELECT d FROM Datospeliculas d WHERE d.calificacion = :calificacion")
    , @NamedQuery(name = "Datospeliculas.findByRecaudacion", query = "SELECT d FROM Datospeliculas d WHERE d.recaudacion = :recaudacion")
    , @NamedQuery(name = "Datospeliculas.findByProyectada", query = "SELECT d FROM Datospeliculas d WHERE d.proyectada = :proyectada")})
public class Datospeliculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Basic(optional = false)
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "FECHAESTRENO")
    @Temporal(TemporalType.DATE)
    private Date fechaestreno;
    @Column(name = "PRODUCTORA")
    private String productora;
    @Column(name = "CARTEL")
    private String cartel;
    @Column(name = "CALIFICACION")
    private String calificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RECAUDACION")
    private BigDecimal recaudacion;
    @Column(name = "PROYECTADA")
    private Boolean proyectada;
    @JoinColumn(name = "CATEGORIA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Datoscategoria categoria;

    public Datospeliculas() {
    }

    public Datospeliculas(Integer id) {
        this.id = id;
    }

    public Datospeliculas(Integer id, String titulo, String director) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getFechaestreno() {
        return fechaestreno;
    }

    public void setFechaestreno(Date fechaestreno) {
        this.fechaestreno = fechaestreno;
    }

    public String getProductora() {
        return productora;
    }

    public void setProductora(String productora) {
        this.productora = productora;
    }

    public String getCartel() {
        return cartel;
    }

    public void setCartel(String cartel) {
        this.cartel = cartel;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public BigDecimal getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(BigDecimal recaudacion) {
        this.recaudacion = recaudacion;
    }

    public Boolean getProyectada() {
        return proyectada;
    }

    public void setProyectada(Boolean proyectada) {
        this.proyectada = proyectada;
    }

    public Datoscategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Datoscategoria categoria) {
        this.categoria = categoria;
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
        if (!(object instanceof Datospeliculas)) {
            return false;
        }
        Datospeliculas other = (Datospeliculas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controlpeliculas.entities.Datospeliculas[ id=" + id + " ]";
    }
    
}
