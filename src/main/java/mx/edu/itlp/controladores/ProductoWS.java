package mx.edu.itlp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlp.modelos.Producto;
import mx.edu.itlp.servicios.ProductoService;

@RestController
@RequestMapping("/devops")

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
} 
