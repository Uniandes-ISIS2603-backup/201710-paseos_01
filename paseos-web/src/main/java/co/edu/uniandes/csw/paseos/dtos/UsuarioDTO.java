/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class UsuarioDTO {
    
    /**
     * Atributo que modela el id generado del usuario
     */
    private Long id;
    
    /**
     * Atributo que modela los nombres del usuario
     */
    private String nombres;
    
    /**
     * Atributo que modela los apellidos del usuario
     */
    private String apellidos;
    
    /**
     * Atributo que modela la edad del usuario
     */
    private Integer edad;
    
    /**
     * Atributo que modela la condición física del usuario
     */
    private Integer condicionFisica;

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
            this.edad = entity.getEdad();
            this.condicionFisica = entity.getCondicionFisica();
        }
    }
    
    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(id);
        entity.setNombres(nombres);
        entity.setApellidos(apellidos);
        entity.setEdad(edad);
        entity.setCondicionFisica(condicionFisica);
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

    /**
     * Obtiene la edad del usuario
     * @return edad del usuario
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * Establece la edad del usuario
     * @param edad del usuario
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
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
