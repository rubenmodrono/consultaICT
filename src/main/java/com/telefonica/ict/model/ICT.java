package com.telefonica.ict.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_ICTS")
public class ICT {

	@Id @GeneratedValue
	private Long ictId;
	
	/** Campos propios del KML **/
	private String longitude;
	private String latitude;
	private String altitude;
	/**private String heading;
	private String tilt;
	private String range;
	private String altitudeMode;
	private String styleURL;**/
	/** Campos de ICT ***/
	private String nombre;
	private String localidad;
	private String codigoPostal;
	private String lugar;
	private String promotora;
	private Integer viviendas;
	private Integer oficinas;
	private Integer locales;
	private Integer plantas;
	private Integer escaleras;
	private Date fechaIni;
	private Date fechaFin;
	private Boolean modiArqueta;
	private Boolean servicion;
	@ManyToOne
	private Province province;
	
	public Long getIctId() {
		return ictId;
	}
	
	
	
	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getAltitude() {
		return altitude;
	}



	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getLocalidad() {
		return localidad;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	public String getCodigoPostal() {
		return codigoPostal;
	}



	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}



	public String getLugar() {
		return lugar;
	}



	public void setLugar(String lugar) {
		this.lugar = lugar;
	}



	public String getPromotora() {
		return promotora;
	}



	public void setPromotora(String promotora) {
		this.promotora = promotora;
	}



	public Integer getViviendas() {
		return viviendas;
	}



	public void setViviendas(Integer viviendas) {
		this.viviendas = viviendas;
	}



	public Integer getOficinas() {
		return oficinas;
	}



	public void setOficinas(Integer oficinas) {
		this.oficinas = oficinas;
	}



	public Integer getLocales() {
		return locales;
	}



	public void setLocales(Integer locales) {
		this.locales = locales;
	}



	public Integer getPlantas() {
		return plantas;
	}



	public void setPlantas(Integer plantas) {
		this.plantas = plantas;
	}



	public Integer getEscaleras() {
		return escaleras;
	}



	public void setEscaleras(Integer escaleras) {
		this.escaleras = escaleras;
	}



	public Date getFechaIni() {
		return fechaIni;
	}



	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}



	public Date getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}



	public Boolean getModiArqueta() {
		return modiArqueta;
	}



	public void setModiArqueta(Boolean modiArqueta) {
		this.modiArqueta = modiArqueta;
	}



	public Boolean getServicion() {
		return servicion;
	}



	public void setServicion(Boolean servicion) {
		this.servicion = servicion;
	}



	public void setIctId(Long ictId) {
		this.ictId = ictId;
	}



	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((altitude == null) ? 0 : altitude.hashCode());
		result = prime * result
				+ ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ICT other = (ICT) obj;
		if (altitude == null) {
			if (other.altitude != null)
				return false;
		} else if (!altitude.equals(other.altitude))
			return false;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ICT [ictId=" + ictId + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", nombre=" + nombre
				+ ", localidad=" + localidad + ", codigoPostal=" + codigoPostal
				+ "]";
	}

	
}
