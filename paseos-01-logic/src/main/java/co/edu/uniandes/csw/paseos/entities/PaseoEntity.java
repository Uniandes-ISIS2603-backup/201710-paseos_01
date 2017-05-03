/* 
 * The MIT License
 *
 * Copyright 2017 jma.lovera10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Venegas
 */
@Entity
public class PaseoEntity implements Serializable{
    
    private static final long serialVersionUID = 1;
    
    /**
    *Id del Paseo. Exclude by podam
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    /**
     * temática del paseo
    */
    private String tematica;
    
    /**
     * Destino del paseo
     */
    private String destino;
    
    /**
     * condición física mínima requerida para poder ir al paseo
     * Entero entre 0 y 10
    */
    private Integer condicionFisica;
    
    /**
     * costo para cada persona que asista al paseo
     */
    private Double costo;
    
    /**
     * booleano que indica si hay transporte incluido o no
     */
    
    private Boolean transporte;
    
    /**
     * Booleano que indica si hay almuerzo íncluido en el paseo.
     */
    
    private Boolean almuerzo;
    
    /**
     * número mínimo de participantes para que se pueda realizar el paseo
     */
    
    private Integer numeroMinimo;
    
    /**
     * Número máximo de participantes aceptados
     */
    private Integer numeroMaximo;
    
    /**
     * Drescripción básica del paseo.
     */
     
    private String descripcion;
    
    @PodamExclude
    @OneToMany(mappedBy = "paseo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEntity> ofertas;
    
    @PodamExclude
    @OneToMany
    private List<FotoEntity> fotos;

    public PaseoEntity() {
    }
    

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
