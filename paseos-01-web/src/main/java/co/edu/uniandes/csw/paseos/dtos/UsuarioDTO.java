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
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.acevedos
 */
@XmlRootElement
public class UsuarioDTO {
    
    /**
     * Atributo que modela el id generado del usuario
     */
    protected Long id;
    
    /**
     * Atributo que modela los nombres del usuario
     */
    protected String nombres;
    
    /**
     * Atributo que modela los apellidos del usuario
     */
    protected String apellidos;
    
    /**
     * Atributo que modela la edad del usuario
     */
    protected Date fechaNacimiento; 
    
    /**
     * Atributo que modela la condición física del usuario
     */
    protected Integer condicionFisica;
    
    /**
     * Atributo que modela el login del usuario
     */
    protected String login; 
    
    /**
     * Atributo que modela el login del usuario
     */
    protected Boolean guia; 

    /**
     * Constructor por defecto
     */
    public UsuarioDTO() {
    }
    
    public UsuarioDTO(UsuarioEntity entity){
        if(entity!=null){
            this.id = entity.getId();
            this.nombres = entity.getNombres();
            this.apellidos = entity.getApellidos();
            this.fechaNacimiento = entity.getFechaNaciemiento();
            this.condicionFisica = entity.getCondicionFisica();
            this.login = entity.getLogin(); 
            this.guia = entity.getGuia();
        }
    }
    
    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setNombres(this.nombres);
        entity.setApellidos(this.apellidos);
        entity.setFechaNaciemiento(this.fechaNacimiento);
        entity.setCondicionFisica(this.condicionFisica);
        entity.setLogin(this.login);
        entity.setGuia(this.guia);
        return entity;
    }

    /**
     * Obtiene el id del usuario
     * @return Id del usuario
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id del usuario
     * @param id del usuario
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene los nombres del usuario
     * @return nombres del usuario
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario
     * @param nombres del usuario
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario
     * @return apellidos del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario
     * @param apellidos del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getLogin (){
        return login; 
    }
    public void setLogin(String login)
    {
        this.login = login; 
    }
    
    /**
     * Obtiene la condición física del usuario
     * @return condición física del usuario
     */
    public Integer getCondicionFisica() {
        return condicionFisica;
    }

    /**
     * Establece la condición física del usuario
     * @param condicionFisica del usuario
     */
    public void setCondicionFisica(Integer condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public Boolean getGuia() {
        if (guia == null) return false; 
        return guia;
    }

    public void setGuia(Boolean guia) {
        this.guia = guia;
    }
    
    
}
