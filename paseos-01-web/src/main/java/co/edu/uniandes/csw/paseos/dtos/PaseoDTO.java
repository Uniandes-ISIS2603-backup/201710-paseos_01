

package co.edu.uniandes.csw.paseos.dtos;

import co.edu.uniandes.csw.paseos.entities.PaseoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jma.lovera10
 */
@XmlRootElement
public class PaseoDTO {
    
    /**
     * Atributo que modela el id generado del paseo
     */
    protected Long id;
    
    /**
     * Atributo que modela la temática del paseo
     */
    protected String tematica;
    
    /**
     * Atributo que modela el destino del paseo
     */
    protected String destino;
    
    /**
     * Atributo que modela la condicion fisica para el paseo
     */
    protected Integer condicionFisica;
    
    /**
     * Atributo que modela el número minimo de integtrantes
     */  
    protected Integer numeroMinimo;
    
    /**
     * Atributo que modela el número máximo de integtrantes
     */
    protected Integer numeroMaximo;
    
    /**
     * Atributo que modela el costo del paseo
     */
    protected Double costo;
    
    /**
     * Atributo que modela si hay transporte incluido para el paseo
     */
    protected Boolean transporte;
    
    /**
     * Atributo que modela si hay almuerzo incluido para el paseo
     */
    protected Boolean almuerzo;

    public PaseoDTO() {
    }
    
    public PaseoDTO(PaseoEntity entity){
        if(entity!=null){
            this.id = entity.getId();
            this.tematica = entity.getTematica();
            this.destino = entity.getDestino();
            this.condicionFisica = entity.getCondicionFisica();
            this.costo = entity.getCosto();
            this.transporte = entity.getTransporte();
            this.almuerzo = entity.getAlmuerzo();
            this.numeroMaximo=entity.getNumeroMaximo();
            this.numeroMinimo=entity.getNumeroMinimo();
        }
    }
    
    public PaseoEntity toEntity(){
        PaseoEntity entity = new PaseoEntity();
        entity.setId(id);
        entity.setTematica(tematica);
        entity.setDestino(destino);
        entity.setCondicionFisica(condicionFisica);
        entity.setCosto(costo);
        entity.setTransporte(transporte);
        entity.setAlmuerzo(almuerzo);
        entity.setNumeroMaximo(numeroMaximo);
        entity.setNumeroMinimo(numeroMinimo);
        return entity;
    }

    /**
     * Obtiene el id del paseo
     * @return id del paseo 
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id del paseo
     * @param id del paseo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la tematica del paseo
     * @return tematica del paseo 
     */
    public String getTematica() {
        return tematica;
    }

    /**
     * Establece la temática del paseo
     * @param tematica del paseo
     */
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    /**
     * Obtiene el destino del paseo
     * @return destino del paseo 
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del paseo
     * @param destino del paseo
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Obtiene la condicion física para el paseo
     * @return condicion física para el paseo 
     */
    public Integer getCondicionFisica() {
        return condicionFisica;
    }

    /**
     * Establece la condición física para el paseo
     * @param condicionFisica del paseo
     */
    public void setCondicionFisica(Integer condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    /**
     * Obtiene el costo del paseo
     * @return costo del paseo 
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * Establece el costo del paseo
     * @param costo del paseo
     */
    public void setCosto(Double costo) {
        this.costo = costo;
    }

    /**
     * Obtiene si hay tranporte para el paseo
     * @return tranporte para el paseo 
     */
    public Boolean getTransporte() {
        return transporte;
    }

    /**
     * Establece si hay tansporte para el paseo
     * @param transporte del paseo
     */
    public void setTransporte(Boolean transporte) {
        this.transporte = transporte;
    }

    /**
     * Obtiene si hay almuerzo para el paseo
     * @return almuerzo para el paseo 
     */
    public Boolean getAlmuerzo() {
        return almuerzo;
    }

    /**
     * Establece si hay almuerzo para el paseo
     * @param almuerzo del paseo
     */
    public void setAlmuerzo(Boolean almuerzo) {
        this.almuerzo = almuerzo;
    }

    public Integer getNumeroMaximo() {
        return numeroMaximo;
    }

    public Integer getNumeroMinimo() {
        return numeroMinimo;
    }

    public void setNumeroMaximo(Integer numeroMaximo) {
        this.numeroMaximo = numeroMaximo;
    }

    public void setNumeroMinimo(Integer numeroMinimo) {
        this.numeroMinimo = numeroMinimo;
    }
    
    
    
    
}
