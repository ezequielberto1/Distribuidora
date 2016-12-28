package entidades;

import java.math.BigDecimal;
import java.util.Vector;

public class Articulo_Venta {
	private int codigo;
	private String nombre;
	private float cantidad;
	private float costo;
	private float precio;
	private float subtotalcosto;
	private float subtotalventa;
	
	public Articulo_Venta(){
	}
	
	public Articulo_Venta(Vector<Object> v) {
		this.setCodigo((Integer)v.get(0));
		this.setNombre((String)v.get(1));
		this.setCosto(Float.parseFloat(v.get(2).toString()));
		this.setPrecio(Float.parseFloat(v.get(3).toString()));
		this.setCantidad(Float.parseFloat(v.get(4).toString()));
		this.setSubtotalcosto(Float.parseFloat(v.get(5).toString()));
		this.setSubtotalventa(Float.parseFloat(v.get(6).toString()));
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public float getSubtotalcosto() {
		return subtotalcosto;
	}
	public void setSubtotalcosto(float subtotalcosto) {
		this.subtotalcosto = subtotalcosto;
	}
	public float getSubtotalventa() {
		return subtotalventa;
	}
	public void setSubtotalventa(float subtotalventa) {
		this.subtotalventa = subtotalventa;
	}

}
