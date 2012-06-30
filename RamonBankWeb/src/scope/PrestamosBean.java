package scope;

import java.io.Serializable;
import java.util.ArrayList;

import com.ramon.ramonbank.dbaccess.tables.Prestamos;

public class PrestamosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<Prestamos> prestamos;
	Prestamos prestamo;

	public Prestamos getPrestamo() {
		return this.prestamo;
	}

	public void setPrestamo(Prestamos prestamo) {
		this.prestamo = prestamo;
	}

	public PrestamosBean() {
	}

	public ArrayList<Prestamos> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(ArrayList<Prestamos> prestamos) {
		this.prestamos = prestamos;
	}

}
