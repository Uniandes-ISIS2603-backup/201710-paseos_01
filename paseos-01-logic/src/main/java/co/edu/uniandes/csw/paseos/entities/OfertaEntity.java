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
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

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
