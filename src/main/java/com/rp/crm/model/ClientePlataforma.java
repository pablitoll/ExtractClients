package com.rp.crm.model;

public class ClientePlataforma {

	
	
	
	
	
	private String plataforma_id;		//  , CLIE_CLIENTE as plataforma_id
	private String nombre;    			//  CLIE_NOMBRE as NAME
	private String razonSocial;
	private String telefono;    		//  , CLIE_TELEFONO as 'Office Phone'
	private String domicilio;			//, CLIE_DOMICILIO as 'Billing Street'
	private String localidad;			//, CLIE_LOCALIDAD as 'Billing City'
	private String provincia;			//, PCIA_NOMBRE as 'Billing State' 
	private String codigoPostal;		//, CLIE_COD_POSTAL as 'Billing Postal Code'
	private String pais;				//, pais.PAIS_NOMBRE  as 'Billing Country' 
	private String asignadoA;
	private String tipoCliente;			//, CLIE_TIPO_CLI as 'Type' --CUSTOM 3
	private String industria;			// , CLIE_ACTIVIDAD_CLI as 'Industry_rp_c'
	private String frecuenciaCompra; 	// ,CLIE_CLASIF_2 as 'frecuencia compra'
	public String getPlataforma_id() {
		return plataforma_id;
	}
	public void setPlataforma_id(String plataforma_id) {
		this.plataforma_id = plataforma_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getAsignadoA() {
		return asignadoA;
	}
	public void setAsignadoA(String asignadoA) {
		this.asignadoA = asignadoA;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public String getFrecuenciaCompra() {
		return frecuenciaCompra;
	}
	public void setFrecuenciaCompra(String frecuenciaCompra) {
		this.frecuenciaCompra = frecuenciaCompra;
	}
		 
}
