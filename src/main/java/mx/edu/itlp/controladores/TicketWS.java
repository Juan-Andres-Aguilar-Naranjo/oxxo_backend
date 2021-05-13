package mx.edu.itlp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlp.modelos.Ticket;
import mx.edu.itlp.servicios.TicketService;

@RestController
@RequestMapping("/api/oxxo/tickets")

public class TicketWS {
	
	@Autowired
	TicketService servicio;
	
	@GetMapping ("/{id}")
	public ResponseEntity<?> buscarTicket(@PathVariable int id){
		Ticket resultado=null; 
		try {
			resultado=servicio.buscarTicket(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<Ticket>(resultado,HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<?> consultarTickets(){
	    List<Ticket> resultado = servicio.consultarTickets();
		return new ResponseEntity<List<Ticket>>(resultado,HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> insertarTicket(@RequestBody Ticket ticket){
		Ticket resultado = null;
		try {
			resultado = servicio.insertarTicket(ticket);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<Ticket>(resultado, HttpStatus.CREATED); 
	}

	@PutMapping()
	public ResponseEntity<?> actualizarTicket(@RequestBody Ticket ticket){
		try {
			servicio.actualizarTicket(ticket);
		} catch(DataAccessException e){
			return new ResponseEntity<> (HttpStatus.CONFLICT);
		}
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarTicket(@PathVariable int id){
		try {
			servicio.eliminarTicket(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<Ticket>(HttpStatus.OK);
	}
}
