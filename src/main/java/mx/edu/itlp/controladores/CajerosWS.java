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

import mx.edu.itlp.modelos.Cajeros;
import mx.edu.itlp.servicios.CajeroService;

@RestController
@RequestMapping("/api/oxxo/cajeros")
public class CajerosWS {

	@Autowired
	CajeroService servicio;
	
	@GetMapping()
	public ResponseEntity<?> consultarCajeros(){
		List<Cajeros> resultado=null;
		try {
			resultado=servicio.consultarCajeros();
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<List<Cajeros>>(resultado,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarCajeros(@PathVariable int id){
		Cajeros resultado=null;
		try {
			resultado=servicio.buscarCajeros(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
			return new ResponseEntity<Cajeros>(resultado, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> insertar(@RequestBody Cajeros cajero){
		Cajeros resultado=null;
		try {
			resultado=servicio.insertar(cajero);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			return new ResponseEntity<Cajeros>(resultado, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<?> actualizar(@RequestBody Cajeros cajero){
		try {
			servicio.actualizar(cajero);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrar(@PathVariable int id){
		try {
			servicio.borrar(id);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			return new ResponseEntity<>(HttpStatus.OK);
	}
}
