package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.OfertaEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OfertaDTO {

    protected Long id;

    protected Date fecha;

    protected Integer inscritos;

    public OfertaDTO() {
    }

    public OfertaDTO(OfertaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fecha = entity.getFecha();
            this.inscritos = entity.getInscritos();
        }
    }

    public OfertaEntity toEntity() {
        OfertaEntity entity = new OfertaEntity();
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setInscritos(this.getInscritos());
        return entity;
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
}
