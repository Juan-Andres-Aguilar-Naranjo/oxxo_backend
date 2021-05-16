package mx.edu.itlp.dao;

import java.util.List;
import mx.edu.itlp.modelos.Cajeros;

public interface CajerosDAO {

	List<Cajeros> consultarCajeros();
	Cajeros buscarCajeros(int id);
	Cajeros insertar(Cajeros cajero);
	void actualizar(Cajeros cajero);
	void borrar(int id);

}
