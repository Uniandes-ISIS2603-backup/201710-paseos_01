// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
// TODO: eliminar los import que no son usados
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author jma.lovera10
 */
@Entity
public class OfertaEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    private Integer inscritos;
    
    @OneToMany
    private List <VisitaEntity> visitas;
    
    @ManyToOne
    private UsuarioEntity guia;
    
    @ManyToOne
    private PaseoEntity paseo;

    public PaseoEntity getPaseo() {
        return paseo;
    }

    public void setPaseo(PaseoEntity paseo) {
        this.paseo = paseo;
    }

    public UsuarioEntity getGuia() {
        return guia;
    }

    public void setGuia(UsuarioEntity usuario) {
        this.guia = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getInscritos() {
        return inscritos;
    }

    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }

    public List <VisitaEntity> getVisitas() {
        return visitas;
    }

    public void setVisitas(List <VisitaEntity> visitas) {
        this.visitas = visitas;
    }
    
    public void addtVisita(VisitaEntity visita) {
        this.visitas.add(visita);
        inscritos++;
    }
    
    public void deleteVisita(VisitaEntity visita){
        boolean encontrado = false;
        while(!encontrado){
            for(int i = 0; i<inscritos; i++){
                if(this.visitas.get(i).getId() == visita.getId()){
                    this.visitas.remove(i);
                    encontrado = true;
                }
            }
        }
        inscritos--;
    }
    
}
