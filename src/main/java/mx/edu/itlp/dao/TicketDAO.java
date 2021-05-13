package mx.edu.itlp.dao;

import java.util.List;

import mx.edu.itlp.modelos.Ticket;

public interface TicketDAO {
	
	Ticket buscarTicket(int id);
	List<Ticket> consultarTickets();
	Ticket insertarTicket(Ticket ticket);
	void actualizarTicket(Ticket ticket);
	void eliminarTicket(int id); 
}
