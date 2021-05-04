package mx.edu.itlp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlp.dao.ProductoDAO;
import mx.edu.itlp.modelos.Producto;

@Service
public class ProductoLogic implements ProductoService{

	@Autowired
	ProductoDAO repositorio;
	
	@Override 
	public Producto buscar(int id) {
		return repositorio.buscar(id);
	}
	
	@Override
	public List<Producto> consultar() {
		return repositorio.consultar();
	}
	
}
