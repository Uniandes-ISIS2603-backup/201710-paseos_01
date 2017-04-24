// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.UsuarioEntity;
import co.edu.uniandes.csw.paseos.entities.VisitaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author n.acevedos
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    
    
   
    
    private String formacion; 
    
    private String experiencia; 
    
    private Double calificacionPromedio; 
    
    private Boolean admin; 
    
    private List<OfertaDetailDTO> ofertas; 
    
    private List<VisitaDetailDTO> visitas; 
    
    public UsuarioDetailDTO(){
        
    }
    public UsuarioDetailDTO(UsuarioEntity entity){
        
        super(entity); 
        List<OfertaEntity> ofertasEntities = entity.getOfertas();
        List<VisitaEntity> visitasEntities = entity.getVisitas();
       
        formacion = entity.getFormacion(); 
        experiencia = entity.getExperiencia();
        calificacionPromedio = entity.getCalificacionPromedio(); 
        ofertas=new ArrayList<OfertaDetailDTO>();
        visitas= new ArrayList<VisitaDetailDTO>(); 
        admin = entity.getAdmin(); 
        
        for (OfertaEntity of : ofertasEntities) {
            ofertas.add(new OfertaDetailDTO(of));
        }
        
        for (VisitaEntity vi : visitasEntities)
        {
            visitas.add(new VisitaDetailDTO(vi)); 
        }        
        
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setCalificacionPromedio(this.calificacionPromedio);
        entity.setCondicionFisica(this.condicionFisica);
        entity.setFechaNaciemiento(this.fechaNacimiento);
        entity.setExperiencia(this.experiencia);
        entity.setGuia(this.guia);
        entity.setNombres(this.nombres);
        entity.setLogin(this.login);
        List<OfertaEntity> ofertas1 = new ArrayList<OfertaEntity>(); 
        List<VisitaEntity> visitas1 = new ArrayList<VisitaEntity>(); 
        
        entity.setAdmin(this.admin);
        if (ofertas != null)
        {
        for (OfertaDetailDTO of : ofertas)
        {
            ofertas1.add(of.toEntity());
        }
        }
        if (visitas != null)
        {
        for (VisitaDetailDTO vi : visitas)
        {
            visitas1.add(vi.toEntity());
        }
        }
        
        entity.setOfertas(ofertas1);
        entity.setVisitas(visitas1);
        return entity; 
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public void setCalificacionPromedio(Double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setOfertas(List<OfertaDetailDTO> ofertas) {
        this.ofertas = ofertas;
    }

    public void setVisitas(List<VisitaDetailDTO> visitas) {
        this.visitas = visitas;
    }

   

    public String getFormacion() {
        return formacion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public Double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public List<OfertaDetailDTO> getOfertas() {
        return ofertas;
    }

    public List<VisitaDetailDTO> getVisitas() {
        return visitas;
    }

    public Boolean getAdmin() {
        return admin;
    }
    
    
    
}
