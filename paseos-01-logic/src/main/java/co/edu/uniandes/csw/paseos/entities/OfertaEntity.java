package co.edu.uniandes.csw.paseos.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class OfertaEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PodamExclude 
    private Long id;
    
    
    //@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    @PodamExclude
    private Integer inscritos;
    
    
    @OneToMany (cascade =CascadeType.PERSIST)
    @PodamExclude
    private List <VisitaEntity> visitas;
    
    @ManyToOne (cascade =CascadeType.PERSIST)
    @PodamExclude
    private UsuarioEntity guia;
    
    @ManyToOne (cascade =CascadeType.PERSIST)
    @PodamExclude
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
