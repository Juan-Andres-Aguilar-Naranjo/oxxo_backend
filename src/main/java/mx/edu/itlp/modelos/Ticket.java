package mx.edu.itlp.modelos;

public class Ticket {

	private int id;
	private String fecha_hora;
	private int total;
	private int cajero_id;
	private int activo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCajero_id() {
		return cajero_id;
	}
	public void setCajero_id(int cajero_id) {
		this.cajero_id = cajero_id;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
