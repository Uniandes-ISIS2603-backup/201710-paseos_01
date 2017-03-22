// TODO: eliminar los comentarios por defecto
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.FotoEntity;
import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class PaseoDetailDTO extends PaseoDTO{
    private List<OfertaDTO> ofertas;
    private List<FotoDTO> fotos ;
    private String descripcion;

    public PaseoDetailDTO() {
    }

    
    public PaseoDetailDTO(PaseoEntity entity) {
        super(entity);
        
        ofertas=null;
       
        fotos=null;
        
    }
    
    public void llenarListas(PaseoEntity entity){
        ofertas=new ArrayList<OfertaDTO>();
        List<OfertaEntity> ofertasEntities = entity.getOfertas();
        if(ofertasEntities==null) return;
        for (OfertaEntity of : ofertasEntities) {
            ofertas.add(new OfertaDTO(of));
        }
   
            if (entity==null) return;
       
            fotos= new ArrayList<FotoDTO>();
        if (entity.getOfertas()==null || entity.getOfertas().isEmpty() || entity.getOfertas().get(0)==null || entity.getOfertas().get(0).getVisitas()==null || entity.getOfertas().get(0).getVisitas().isEmpty()) return;
            List<FotoEntity> lista = entity.getOfertas().get(0).getVisitas().get(0).getFotos();
            if (lista==null) return;
            for (FotoEntity fotoEntity : lista) {
                fotos.add( new FotoDTO(fotoEntity));
            }
                         
        
        
    }


    public List<FotoDTO> getFotos() {
        return fotos;
    }

    public void setFotos(List<FotoDTO> fotos) {
        this.fotos = fotos;
    }

    /*public List<OfertaDTO> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<OfertaDTO> ofertas) {
        this.ofertas = ofertas;
    }*/
    public PaseoEntity toEntity(){
        PaseoEntity entity = new PaseoEntity();
        entity.setId(id);
        entity.setTematica(tematica);
        entity.setDestino(destino);
        entity.setCondicionFisica(condicionFisica);
        entity.setCosto(costo);
        entity.setTransporte(transporte);
        entity.setAlmuerzo(almuerzo);
        entity.setNumeroMinimo(numeroMinimo);
        entity.setNumeroMaximo(numeroMaximo);
        entity.setDescripcion(descripcion);
        ArrayList<OfertaEntity> list = new ArrayList<OfertaEntity>();
        for (OfertaDTO ofertaDTO : ofertas) {
            list.add(ofertaDTO.toEntity());
        }
        entity.setOfertas(list);
        return entity;
    }
    
        
    
    
    
    
    
    
}
