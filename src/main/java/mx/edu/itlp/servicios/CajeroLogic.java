package mx.edu.itlp.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.itlp.dao.CajerosDAO;
import mx.edu.itlp.modelos.Cajeros;

@Service
public class CajeroLogic implements CajeroService {

	@Autowired
	CajerosDAO repositorio;
	
	@Override
	public List<Cajeros> consultarCajeros(){
		return repositorio.consultarCajeros(); 
	}

	@Override
	public Cajeros buscarCajeros(int id) {
		return repositorio.buscarCajeros(id);
	}
	
	@Override
	public Cajeros insertar(Cajeros cajero) {
		return repositorio.insertar(cajero);
	}

	@Override
	public void actualizar(Cajeros cajero) {
		repositorio.actualizar(cajero);
	}

	@Override
	public void borrar(int id) {
		repositorio.borrar(id);
	}
}
