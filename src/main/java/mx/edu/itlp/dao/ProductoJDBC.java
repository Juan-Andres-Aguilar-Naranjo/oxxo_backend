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
				producto.setActivo(rs.getInt("activo"));
				return producto;
			}
		},
				id);
	}
	
	@Override
	public List<Producto> consultar() {
		String sql_query = "SELECT * FROM productos WHERE activo=1";
		return conexion.query(sql_query, new RowMapper<Producto>(){
			public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Producto producto=new Producto();
				producto.setId(rs.getInt("id"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getInt("precio"));
				producto.setCodigo_barras(rs.getString("codigo_barras"));
				producto.setExistencia(rs.getInt("existencia"));
				producto.setActivo(rs.getInt("activo"));
				return producto;
        }
    });
	}

	@Override
	public Producto insertar(Producto producto) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion)
				.withTableName("productos")
				.usingColumns("descripcion","precio","codigo_barras","existencia")
				.usingGeneratedKeyColumns("id");
				
		Map<String, Object> datos = new HashMap<>();
		datos.put("descripcion", producto.getDescripcion());
		datos.put("precio", producto.getPrecio());
		datos.put("codigo_barras", producto.getCodigo_barras());
		datos.put("existencia", producto.getExistencia());
			
		Number id = insert.executeAndReturnKey(datos);
				
		producto.setId(id.intValue());
		
		producto.setActivo(1);
		
		return producto;
	}

	@Override
	public void actualizar(Producto producto) {
		String sql_update="UPDATE productos SET descripcion=?, precio=?,"
				+ "codigo_barras=?, existencia=? WHERE id=?";
		conexion.update(sql_update, producto.getDescripcion(),
				producto.getPrecio(),
				producto.getCodigo_barras(),
				producto.getExistencia(),
				producto.getId());
	}

	@Override
	public void eliminar(int id) {
		String sql_update="UPDATE productos SET activo=0 WHERE id=?";
		conexion.update(sql_update,id);
	}
}
	

