package mx.edu.itlp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlp.dao.TicketDAO;
import mx.edu.itlp.modelos.Ticket;

@Service
public class TicketLogic implements TicketService{

	@Autowired
	TicketDAO repositorio;
	
	@Override 
	public Ticket buscarTicket(int id) {
		return repositorio.buscarTicket(id);
	}
	
	@Override
	public List<Ticket> consultarTickets() {
		return repositorio.consultarTickets();
	}

	@Override
	public Ticket insertarTicket(Ticket ticket) {		
		return repositorio.insertarTicket(ticket);
	}

	@Override
	public void actualizarTicket(Ticket ticket) {
		repositorio.actualizarTicket(ticket);
		
	}

	@Override
	public void eliminarTicket(int id) {
		repositorio.eliminarTicket(id);
		
	}
}
