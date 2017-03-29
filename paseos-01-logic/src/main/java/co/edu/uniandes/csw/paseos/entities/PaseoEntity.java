// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jma.lovera10
 */
@Entity
public class PaseoEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tematica;
    
    private String destino;
    
    private Integer condicionFisica;
    
    private Double costo;
    
    private Boolean transporte;
    
    private Boolean almuerzo;
    
    private Integer numeroMinimo;
    
    private Integer numeroMaximo;
     
    private String descripcion;
    
    @OneToMany(mappedBy = "paseo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEntity> ofertas;
    
    @OneToMany
    private List<FotoEntity> fotos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(Integer condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Boolean getTransporte() {
        return transporte;
    }

    public void setTransporte(Boolean transporte) {
        this.transporte = transporte;
    }

    public Boolean getAlmuerzo() {
        return almuerzo;
    }

    public void setAlmuerzo(Boolean almuerzo) {
        this.almuerzo = almuerzo;
    }

    public Integer getNumeroMinimo() {
        return numeroMinimo;
    }

    public void setNumeroMinimo(Integer numeroMinimo) {
        this.numeroMinimo = numeroMinimo;
    }

    public Integer getNumeroMaximo() {
        return numeroMaximo;
    }

    public void setNumeroMaximo(Integer numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OfertaEntity> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<OfertaEntity> ofertas) {
        this.ofertas = ofertas;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }   
    
}
