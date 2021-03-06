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

import mx.edu.itlp.modelos.Producto;
import mx.edu.itlp.servicios.ProductoService;

@RestController
@RequestMapping("/api/oxxo/productos")

public class ProductoWS {
		
		@Autowired
		ProductoService servicio;
		
		@GetMapping ("/{id}")
		public ResponseEntity<?> buscar(@PathVariable int id){
			Producto resultado=null; 
			try {
				resultado=servicio.buscar(id);
			} catch (DataAccessException e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return new ResponseEntity<Producto>(resultado,HttpStatus.OK);
		}
		
		@GetMapping()
		public ResponseEntity<?> consultar(){
		    List<Producto> resultado = servicio.consultar();
			return new ResponseEntity<List<Producto>>(resultado,HttpStatus.OK);
		}
		
		@PostMapping()
		public ResponseEntity<?> insertar(@RequestBody Producto producto){
			Producto resultado = null;
			try {
				resultado = servicio.insertar(producto);
			} catch (DataAccessException e) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
			
			return new ResponseEntity<Producto>(resultado, HttpStatus.CREATED); 
		}

		@PutMapping()
		public ResponseEntity<?> actualizar(@RequestBody Producto producto){
			try {
				servicio.actualizar(producto);
			} catch(DataAccessException e){
				return new ResponseEntity<> (HttpStatus.CONFLICT);
			}
				return new ResponseEntity<>(HttpStatus.CREATED);
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<?> eliminar(@PathVariable int id){
			try {
				servicio.eliminar(id);
			} catch (DataAccessException e) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
				return new ResponseEntity<Producto>(HttpStatus.OK);
		}
} 
