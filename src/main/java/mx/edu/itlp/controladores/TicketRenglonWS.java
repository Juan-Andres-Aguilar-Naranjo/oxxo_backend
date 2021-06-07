package mx.edu.itlp.controladores;

import java.sql.Timestamp;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlp.modelos.Importe;
import mx.edu.itlp.modelos.TicketRenglon;
import mx.edu.itlp.servicios.TicketRenglonService;

@RestController
@RequestMapping("/api/oxxo/ticket_renglones")
public class TicketRenglonWS {
	
	@Autowired
	TicketRenglonService servicio;
	
	@GetMapping()
	public ResponseEntity<?> consultarRenglones(){
		List<TicketRenglon> resultado = null;
		try {
			resultado = servicio.consultarRenglones();
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<TicketRenglon>>(resultado, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarRenglon(@PathVariable int id){
		TicketRenglon resultado = null;
		try {
			resultado = servicio.buscarRenglon(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<TicketRenglon>(resultado, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody TicketRenglon renglon){
		TicketRenglon resultado = null;
		try {
			resultado = servicio.insertar(renglon);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<TicketRenglon>(resultado, HttpStatus.CREATED); 
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody TicketRenglon renglon){
		try {
			servicio.actualizar(renglon);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> desactivar(@PathVariable int id){
		try {
			servicio.desactivar(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/cajero/{cajero_id}")
	public ResponseEntity<?> obtenerImportes(@PathVariable int cajero_id, @RequestParam Timestamp fecha_inicial, Timestamp fecha_final){
		List<Importe> resultado = null;
		try {
			resultado = servicio.obtenerImportes(cajero_id, fecha_inicial, fecha_final);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Importe>>(resultado, HttpStatus.OK);
	}
	
}
