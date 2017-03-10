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
    private String path;
    
    @ManyToOne
    private VisitaEntity visita;
    
    @ManyToOne
    private PaseoEntity paseo;

    public PaseoEntity getPaseo() {
        return paseo;
    }

    public void setPaseo(PaseoEntity paseo) {
        this.paseo = paseo;
    }

    public VisitaEntity getVisita() {
        return visita;
    }

    public void setVisita(VisitaEntity visita) {
        this.visita = visita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
