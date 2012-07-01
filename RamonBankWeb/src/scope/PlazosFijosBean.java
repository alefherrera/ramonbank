package scope;

import java.io.Serializable;
import java.util.ArrayList;


import com.ramon.ramonbank.dbaccess.tables.PlazosFijos;

public class PlazosFijosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<PlazosFijos> plazosfijos;	

	public PlazosFijosBean() {
	}

	public ArrayList<PlazosFijos> getPlazosfijos() {
		return plazosfijos;
	}

	public void setPlazosfijos(ArrayList<PlazosFijos> plazosfijos) {
		this.plazosfijos = plazosfijos;
	}
}
