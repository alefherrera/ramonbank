package scope;

import java.io.Serializable;
import java.util.ArrayList;


import com.ramon.ramonbank.dbaccess.tables.PagoServicios;
import com.ramon.ramonbank.dbaccess.tables.Servicios;


public class ServiciosBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	ArrayList<Servicios> servicios;	
	ArrayList<PagoServicios> pagoservicios;
	
	public ServiciosBean() {
	}
	
	public ArrayList<Servicios> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicios> servicios) {
		this.servicios = servicios;
	}
	
	public ArrayList<PagoServicios> getPagoServicios() {
		return pagoservicios;
	}

	public void setPagoServicios(ArrayList<PagoServicios> pagoservicios) {
		this.pagoservicios = pagoservicios;
	}
	
}
