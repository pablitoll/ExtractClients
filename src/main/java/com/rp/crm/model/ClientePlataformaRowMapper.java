package com.rp.crm.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientePlataformaRowMapper implements RowMapper<ClientePlataforma> {

	@Override
	public ClientePlataforma mapRow(ResultSet rs, int rowNum) throws SQLException {
		ClientePlataforma clientePlataforma = new ClientePlataforma();
		clientePlataforma.setPlataforma_id(rs.getString("plataforma_id"));//; as plataforma_id
		clientePlataforma.setNombre(rs.getString("NAME"));// as NAME
		clientePlataforma.setRazonSocial(rs.getString("razon_social_c"));// as NAME
		clientePlataforma.setTelefono(rs.getString("phone_office"));// as 'Office Phone'
		clientePlataforma.setDomicilio(rs.getString("billing_address_street"));// as 'Billing Street'
		clientePlataforma.setLocalidad(rs.getString("billing_address_city"));// as 'Billing City'
		clientePlataforma.setProvincia(rs.getString("billing_address_state"));// as 'Billing State' 
		clientePlataforma.setCodigoPostal(rs.getString("billing_address_postalcode"));// as 'Billing Postal Code'
		clientePlataforma.setPais(rs.getString("billing_address_country"));//  as 'Billing Country' 
		clientePlataforma.setAsignadoA(rs.getString("assinged_to"));
		clientePlataforma.setTipoCliente(rs.getString("tipo_rp_c"));// as 'Type' --CUSTOM 3
		clientePlataforma.setIndustria(rs.getString("industria_rp_c"));// as 'Industry_rp_c'
		clientePlataforma.setFrecuenciaCompra(rs.getString("frecuencia_compra_c"));// as 'frecuencia compra'
		
		return clientePlataforma;
	}

} 
