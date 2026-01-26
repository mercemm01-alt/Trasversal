
import java.io.Serializable;
import java.util.Date;
public class PedidoDTO {

	private long idPedido;
	private ClienteEntity cliente;
	private ProductoEntity producto;
	private int cantidad;
	private Date fechaInicio;
	private Date fechaEntrega;
	private Estado estado;
	private double precioFinal;
	
	public PedidoDTO() {
		
	}

	public PedidoDTO(long idPedido, ClienteEntity cliente, ProductoEntity producto, int cantidad, Date fechaInicio,
			Date fechaEntrega, Estado estado, double precioFinal) {
		super();
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.producto = producto;
		this.cantidad = cantidad;
		this.fechaInicio = fechaInicio;
		this.fechaEntrega = fechaEntrega;
		this.estado = estado;
		this.precioFinal = precioFinal;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	@Override
	public String toString() {
		return "PedidoDTO [idPedido=" + idPedido + ", cantidad=" + cantidad + ", fechaInicio=" + fechaInicio
				+ ", fechaEntrega=" + fechaEntrega + ", precioFinal=" + precioFinal + "]";
	}
	
	
	
}
