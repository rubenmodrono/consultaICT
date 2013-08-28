package com.telefonica.ict.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.telefonica.ict.tools.COP;
import com.telefonica.ict.tools.CoordinateConversion;
import com.telefonica.ict.tools.COP.Consulta;
import com.telefonica.ict.tools.COP.Consulta.Descripcion;
import com.telefonica.ict.tools.COP.Consulta.Situacion.Coordenadas;
import com.telefonica.ict.tools.COP.Consulta.Situacion.Direccion;
import com.telefonica.ict.tools.Utils;

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
	private Boolean modiArqueta = false;
	private Boolean servicio = false;
	@ManyToOne
	private Province province;
	

	
	public ICT(){
	}
	
	
	public ICT(COP cop) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Consulta cons = cop.getConsulta();
		Coordenadas coord = cons.getSituacion().getCoordenadas();
		Direccion dir = cons.getSituacion().getDireccion();
		Descripcion desc = cons.getDescripcion();

		//Coordenadas		
		setCoordenadas(coord);
		
		this.altitude = "0";
		
		
		
		this.localidad = dir.getLocalidad();
		this.codigoPostal = ((Short)dir.getCP()).toString();
		while (codigoPostal.length()<5){
			codigoPostal = "0"+codigoPostal;
		}
		this.lugar = dir.getTipoVia()+" "+dir.getNombreVia();
		
		
		this.promotora = desc.getNombrePromotor();
		this.viviendas = (int)desc.getNumeroViviendas();
		this.oficinas = (int)desc.getNumeroOficinas();
		this.locales = (int)desc.getNumeroLocales();
		this.plantas = (int)desc.getNumeroPlantas();
		this.escaleras = (int)desc.getNumeroEscaleras();
		this.fechaIni = sdf.parse(desc.getFechaInicio());
		this.fechaFin = sdf.parse(desc.getFechaFin());
		//this.modiArqueta = ;
		//this.servicio = servicio;
		//this.province = province;
	}


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
		return servicio;
	}



	public void setServicion(Boolean servicion) {
		this.servicio = servicion;
	}

	public Province getProvince() {
		return province;
	}
	public void setProvince(Province province) {
		this.province = province;
	}


	private void setCoordenadas (Coordenadas coord) {
		
		if (coord.getLatitud() != null && coord.getLongitud()!= null){
			this.latitude = Utils.codeCoordinate((int)coord.getLatitud().getGrados(), 
					(int)coord.getLatitud().getMinutos(), 
					(int)coord.getLatitud().getSegundos(), 
					(int)coord.getLatitud().getDecimales(),
					(String)coord.getLatitud().getDireccion());
			
			this.longitude = Utils.codeCoordinate((int)coord.getLongitud().getGrados(), 
					(int)coord.getLongitud().getMinutos(), 
					(int)coord.getLongitud().getSegundos(), 
					(int)coord.getLongitud().getDecimales(),
					(String)coord.getLongitud().getDireccion());
		}else{

			CoordinateConversion cc = new CoordinateConversion();
			StringBuilder utm = new StringBuilder();
			utm.append(coord.getHuso());
			//Aquí lo hemos "trucado" ya que siempre nos encontraremos en el 
			//hemisferio norte y de este modo reutilzamos la clase
			//de conversión creada por IBM
			utm.append(" N ");
			utm.append(coord.getX());
			utm.append(" ");
			utm.append(coord.getY());
			double[] latLon = cc.utm2LatLon(utm.toString());
			this.latitude = ((Double)latLon[0]).toString();
			this.longitude =((Double)latLon[1]).toString();
		}
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result
				+ ((fechaFin == null) ? 0 : fechaFin.hashCode());
		result = prime * result
				+ ((fechaIni == null) ? 0 : fechaIni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (fechaFin == null) {
			if (other.fechaFin != null)
				return false;
		} else if (!fechaFin.equals(other.fechaFin))
			return false;
		if (fechaIni == null) {
			if (other.fechaIni != null)
				return false;
		} else if (!fechaIni.equals(other.fechaIni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	@Override
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		return "<![CDATA[<strong>Nombre:" + nombre + "</strong></ br>" +
			   "<strong>Localidad:</strong>" + localidad + "</ br>" +
			   "<strong>Código Postal:</strong>" + codigoPostal + "</ br>" +
			   "<strong>Lugar:</strong>" + lugar + "</ br>" +
			   "<strong>Promotora:</strong>" + promotora + "</ br>" +
			   "<strong>Viviendas:</strong>" + viviendas + "</ br>" +
			   "<strong>Oficinas:</strong>" + oficinas + "</ br>" +
			   "<strong>Locales:</strong>" + locales + "</ br>" +
			   "<strong>Plantas:</strong>" + plantas + "</ br>" +
			   "<strong>Escaleras:</strong>" + escaleras + "</ br>" +
			   "<strong>Fecha Prevista Inicio Obra:</strong>" + sdf.format(fechaIni) + "</ br>" +
			   "<strong>Fecha Prevista Fin Obra:</strong>" + sdf.format(fechaFin) + "</ br>" +
			   "<strong>Modificación Arqueta:</strong>" + booleanTransformer(modiArqueta) + "</ br>" +
			   "<strong>Servicio:</strong>" + booleanTransformer(servicio)+ "</ br>]]>";
		
	}
	
	private String booleanTransformer(Boolean el){
		
		return el!=null && el.booleanValue()?"SI":"NO";
	}

	
}
