
public class ClienteLoginDTO {
	
	private String usuario;
	private String contraseña;
	
	public ClienteLoginDTO() {
		
	}

	public ClienteLoginDTO(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "ClienteLoginDTO [usuario=" + usuario + ", contraseña=" + contraseña + "]";
	}
	
	

}
