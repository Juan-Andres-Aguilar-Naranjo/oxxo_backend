package mx.edu.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mx.edu.itlp.modelos.Producto;

@Repository
public class ProductoJDBC implements ProductoDAO {

	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public Producto buscar(int id) {
		String sql_query="SELECT * FROM productos WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Producto>() {
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException{
				Producto producto=new Producto();
				producto.setId(rs.getInt("id"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getInt("precio"));
				producto.setCodigo_barras(rs.getString("codigo_barras"));
				producto.setExistencia(rs.getInt("existencia"));
				return producto;
			}
		},
				id);
	}	
}
	

