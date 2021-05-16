package mx.edu.itlp.servicios;

import java.util.List;

import mx.edu.itlp.modelos.Cajeros;

public interface CajeroService {

	List<Cajeros> consultarCajeros();
	Cajeros buscarCajeros(int id);
	Cajeros insertar(Cajeros cajero);
	void actualizar(Cajeros cajero);
	void borrar(int id);
	
}
