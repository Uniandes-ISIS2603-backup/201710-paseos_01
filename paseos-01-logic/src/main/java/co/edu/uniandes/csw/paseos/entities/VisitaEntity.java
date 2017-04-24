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
// TODO: eliminar los import que no son usados
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
