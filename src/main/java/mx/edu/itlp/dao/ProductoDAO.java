package mx.edu.itlp.dao;

import java.util.List;

import mx.edu.itlp.modelos.Producto;

public interface ProductoDAO {

	Producto buscar(int id);
	List<Producto> consultar();
	Producto insertar(Producto producto);
	void actualizar(Producto producto);
	void eliminar(int id);
}

