package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VerPedidoDTO {
	
	 private Long idPedido;
	 private String nombreCliente;
	 private String apellidosCliente;
	 private LocalDateTime fechaPedido;
	 private LocalDateTime fechaEntrega;
	 private BigDecimal total;
	 private String estado;
	 
	 public VerPedidoDTO() {
		super();
	}

	 public VerPedidoDTO(Long idPedido, String nombreCliente, String apellidosCliente, LocalDateTime fechaPedido,
			LocalDateTime fechaEntrega, BigDecimal total, String estado) {
		super();
		this.idPedido = idPedido;
		this.nombreCliente = nombreCliente;
		this.apellidosCliente = apellidosCliente;
		this.fechaPedido = fechaPedido;
		this.fechaEntrega = fechaEntrega;
		this.total = total;
		this.estado = estado;
	 }

	 public Long getIdPedido() {
		 return idPedido;
	 }

	 public void setIdPedido(Long idPedido) {
		 this.idPedido = idPedido;
	 }

	 public String getNombreCliente() {
		 return nombreCliente;
	 }

	 public void setNombreCliente(String nombreCliente) {
		 this.nombreCliente = nombreCliente;
	 }

	 public String getApellidosCliente() {
		 return apellidosCliente;
	 }

	 public void setApellidosCliente(String apellidosCliente) {
		 this.apellidosCliente = apellidosCliente;
	 }

	 public LocalDateTime getFechaPedido() {
		 return fechaPedido;
	 }

	 public void setFechaPedido(LocalDateTime fechaPedido) {
		 this.fechaPedido = fechaPedido;
	 }

	 public LocalDateTime getFechaEntrega() {
		 return fechaEntrega;
	 }

	 public void setFechaEntrega(LocalDateTime fechaEntrega) {
		 this.fechaEntrega = fechaEntrega;
	 }

	 public BigDecimal getTotal() {
		 return total;
	 }

	 public void setTotal(BigDecimal total) {
		 this.total = total;
	 }

	 public String getEstado() {
		 return estado;
	 }

	 public void setEstado(String estado) {
		 this.estado = estado;
	 }

	 @Override
	 public String toString() {
		return "VerPedidoDTO [idPedido=" + idPedido + ", nombreCliente=" + nombreCliente + ", apellidosCliente="
				+ apellidosCliente + ", fechaPedido=" + fechaPedido + ", fechaEntrega=" + fechaEntrega + ", total="
				+ total + ", estado=" + estado + "]";
	 }
	 
}
