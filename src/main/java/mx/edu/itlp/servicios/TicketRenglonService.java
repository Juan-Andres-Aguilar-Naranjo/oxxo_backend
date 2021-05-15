package mx.edu.itlp.servicios;

import java.util.List;

import mx.edu.itlp.modelos.TicketRenglon;

public interface TicketRenglonService {

	List<TicketRenglon> consultarRenglones();
	TicketRenglon buscarRenglon(int id);
	TicketRenglon insertar(TicketRenglon renglon);
	void actualizar(TicketRenglon renglon);
	void desactivar(int id);

}
