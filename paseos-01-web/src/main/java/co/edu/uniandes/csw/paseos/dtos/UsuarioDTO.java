/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        }
    }
    
    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setNombres(this.nombres);
        entity.setApellidos(this.apellidos);
        entity.setFechaNaciemiento(this.fechaNacimiento);
        entity.setCondicionFisica(this.condicionFisica);
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
    
    
}
