/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jma.lovera10
 */
@Entity
public class FotoEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Valor codificado de la foto
     */
    private String valor;
    
    /**
     * Relación muchos a uno con una visita
     */
    @ManyToOne
    private VisitaEntity visita;
    
    /**
     * Relación muchos a uno con un paseo
     */
    @ManyToOne
    private PaseoEntity paseo;

    /**
     * Método que retorna el paseo
     * @return paseo
     */
    public PaseoEntity getPaseo() {
        return paseo;
    }

    /**
     * Método que establece el paseo
     * @param paseo establecido
     */
    public void setPaseo(PaseoEntity paseo) {
        this.paseo = paseo;
    }

    /**
     * Método que obtiene la visita
     * @return visita
     */
    public VisitaEntity getVisita() {
        return visita;
    }

    /**
     * Método que establece la visita
     * @param visita de la foto
     */
    public void setVisita(VisitaEntity visita) {
        this.visita = visita;
    }

    /**
     * Método que obtiene el id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Método que establece el id de la foto
     * @param id de la foto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Método que obtiene el valor codificado
     * @return valor de la foto
     */
    public String getValor() {
        return valor;
    }

    /**
     * Método que establece el valor codificado de la foto
     * @param valor de la foto
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
