package mx.edu.itlp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlp.dao.TicketRenglonDAO;
import mx.edu.itlp.modelos.Importe;
import mx.edu.itlp.modelos.TicketRenglon;

@Service
public class TicketRenglonLogic implements TicketRenglonService {

	@Autowired
	TicketRenglonDAO repositorio;
	
	@Override
	public List<TicketRenglon> consultarRenglones() {
		return repositorio.consultarRenglones();
	}

	@Override
	public TicketRenglon buscarRenglon(int id) {		
		return repositorio.buscarRenglon(id);
	}

	@Override
	public TicketRenglon insertar(TicketRenglon renglon) {
		return repositorio.insertar(renglon);
	}

	@Override
	public void actualizar(TicketRenglon renglon) {		
		repositorio.actualizar(renglon);
	}

	@Override
	public void desactivar(int id) {		
		repositorio.desactivar(id);
	}

	@Override
	public List<Importe> obtenerImportes(int cajero_id) {		
		return repositorio.obtenerImportes(cajero_id);
	}
}
