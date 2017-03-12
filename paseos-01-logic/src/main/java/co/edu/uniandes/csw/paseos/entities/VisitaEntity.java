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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jma.lovera10
 */
@Entity
public class VisitaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer calificacion;
    
    private String comentario;
    
    @OneToMany
    private List<FotoEntity> fotos;
    
    @ManyToOne
    private UsuarioEntity usuario;
    
    @ManyToOne
    private OfertaEntity oferta;

    public OfertaEntity getOferta() {
        return oferta;
    }

    public void setOferta(OfertaEntity oferta) {
        this.oferta = oferta;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<FotoEntity> getFotos() {
        return fotos;
    }

    public void addFotos(FotoEntity fotos) {
        this.fotos.add(fotos);
    } 
    
    public void deleteFotos(FotoEntity foto){
        boolean encontrado = false;
        while(!encontrado){
            for(int i = 0; i<this.fotos.size(); i++){
                if(this.fotos.get(i).getId() == foto.getId()){
                    this.fotos.remove(i);
                    encontrado = true;
                }
            }
        }
    }
    
    public void setFotos(List<FotoEntity> fotos) {
        this.fotos = fotos;
    }
    
}
