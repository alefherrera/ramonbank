package scope;

import java.io.Serializable;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensaje;

	public MessageBean() {		
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
