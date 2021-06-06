package mx.edu.itlp.dao;

import java.util.List;

import mx.edu.itlp.modelos.Importe;
import mx.edu.itlp.modelos.TicketRenglon;

public interface TicketRenglonDAO {

	List<TicketRenglon> consultarRenglones();
	TicketRenglon buscarRenglon(int id);
	TicketRenglon insertar(TicketRenglon renglon);
	void actualizar(TicketRenglon renglon);
	void desactivar(int id);
	List<Importe> obtenerImportes(int cajero_id);
	
}
