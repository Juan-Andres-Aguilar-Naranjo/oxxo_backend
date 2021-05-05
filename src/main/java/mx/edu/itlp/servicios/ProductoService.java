package mx.edu.itlp.servicios;

import java.util.List;

import mx.edu.itlp.modelos.Producto;

public interface ProductoService {

	Producto buscar(int id);
	List<Producto> consultar();

}
