package mx.edu.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import mx.edu.itlp.modelos.Cajeros;

@Repository
public class CajerosJDBC implements CajerosDAO {

	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public List<Cajeros> consultarCajeros() {
		String sql_query="SELECT * FROM cajeros WHERE activo=1";
		return conexion.query(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cajeros cajero=new Cajeros();
				cajero.setId(rs.getInt("id"));
				cajero.setNombre(rs.getString("nombre"));
				cajero.setActivo(rs.getInt("activo"));
				return cajero;
			}
		});
	}

	@Override
	public Cajeros buscarCajeros(int id) {
		String sql_query="SELECT * FROM cajeros WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Cajeros>() {
			public Cajeros mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cajeros cajero=new Cajeros();
				cajero.setId(rs.getInt("id"));
				cajero.setNombre(rs.getString("nombre"));
				cajero.setActivo(rs.getInt("activo"));
				return cajero;
			}
		}, id);
	}

	@Override
	public Cajeros insertar(Cajeros cajero) {
		SimpleJdbcInsert insert=new SimpleJdbcInsert(conexion)
			.withTableName("cajeros")
			.usingColumns("nombre","activo")
			.usingGeneratedKeyColumns("id");
		Map<String,Object> datos=new HashMap<>();
		datos.put("nombre", cajero.getNombre());
		datos.put("activo", 1);
		Number id=insert.executeAndReturnKey(datos);
		cajero.setId(id.intValue());
		cajero.setActivo(1);
		return cajero;
		}

	@Override
	public void actualizar(Cajeros cajero) {
		String sql_update="UPDATE cajeros SET nombre=? WHERE id=?";
		conexion.update(sql_update,
				cajero.getNombre(),
				cajero.getId());
	}

	@Override
	public void borrar(int id) {
		String sql_update="UPDATE cajeros SET activo=0 WHERE id=?";
		conexion.update(sql_update, id);
	}
}
