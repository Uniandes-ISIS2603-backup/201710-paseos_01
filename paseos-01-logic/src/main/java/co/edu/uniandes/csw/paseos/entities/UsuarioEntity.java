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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author n.acevedos
 */
@Entity
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude
    private Long id;
    
    private String nombres;
    
    private String apellidos;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNaciemiento; 
    
    private Integer condicionFisica;
    
    private String login;
    
    private Boolean guia;
    
    private String formacion;
    
    private String experiencia;
    
    private Double calificacionPromedio;
    
    private int cuantasCalificaciones; 
    
    private Boolean admin; 
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)    
    private List<VisitaEntity> visitas;
    
    @OneToMany(mappedBy = "guia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OfertaEntity> ofertas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    public Integer getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(Integer condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getGuia() {
        if (guia== null)
        {
            return false; 
        }
        return guia;
    }

    public void setGuia(Boolean guia) {
        this.guia = guia;
    }

    public String getFormacion() {
        return formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public List<VisitaEntity> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<VisitaEntity> visitas) {
        this.visitas = visitas;
    }
    public void addVisita(VisitaEntity visita)
    {
        visitas.add(visita);
    }

    public List<OfertaEntity> getOfertas() {
        return ofertas;
    }
     public void addOferta(OfertaEntity oferta)
    {
        ofertas.add(oferta);
    }
    public void deleteVisita(VisitaEntity visita){
        boolean encontrado = false;
        while(!encontrado){
            for(int i = 0; i<visitas.size(); i++){
                if(this.visitas.get(i).getId() == visita.getId()){
                    this.visitas.remove(i);
                    encontrado = true;
                }
            }
        }
    }
    public void deleteOferta(OfertaEntity oferta){
        boolean encontrado = false;
        while(!encontrado){
            for(int i = 0; i<ofertas.size(); i++){
                if(this.ofertas.get(i).getId() == oferta.getId()){
                    this.ofertas.remove(i);
                    encontrado = true;
                }
            }
        }
    }

    public Date getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public int getCuantasCalificaciones() {
        return cuantasCalificaciones;
    }

    public void setFechaNaciemiento(Date fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public void setCuantasCalificaciones(int cuantasCalificaciones) {
        this.cuantasCalificaciones = cuantasCalificaciones;
    }
    
    public void setOfertas(List<OfertaEntity> ofertas) {
        this.ofertas = ofertas;
    }
    public void recalcularPromedio(int calificacion)
    {
        double total = calificacionPromedio*cuantasCalificaciones; 
        total += calificacion;
        cuantasCalificaciones++; 
        calificacionPromedio = total/cuantasCalificaciones; 
    }
    
    
}
