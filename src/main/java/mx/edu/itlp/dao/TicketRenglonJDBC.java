package mx.edu.itlp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import mx.edu.itlp.modelos.Importe;
import mx.edu.itlp.modelos.TicketRenglon;

@Repository
public class TicketRenglonJDBC implements TicketRenglonDAO {

	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public List<TicketRenglon> consultarRenglones() {
		String sql_query = "SELECT * FROM ticket_renglones WHERE activo = 1";
		return conexion.query(sql_query, new RowMapper<TicketRenglon>() {
            public TicketRenglon mapRow(ResultSet rs, int rowNum) throws SQLException {
                TicketRenglon renglon = new TicketRenglon();
                renglon.setId(rs.getInt("id"));
                renglon.setTICKET_id(rs.getInt("TICKET_id"));
                renglon.setPRODUCTO_id(rs.getInt("PRODUCTO_id"));
                renglon.setCantidad(rs.getInt("cantidad"));
                renglon.setPrecio(rs.getFloat("precio"));
                renglon.setImporte(rs.getFloat("importe"));
                renglon.setActivo(rs.getInt("activo"));
                
                return renglon;
            }
        });	
	}

	@Override
	public TicketRenglon buscarRenglon(int id) {
		String sql_query = "SELECT * FROM ticket_renglones WHERE id = ?";
		return conexion.queryForObject(sql_query, new RowMapper<TicketRenglon>() {
            public TicketRenglon mapRow(ResultSet rs, int rowNum) throws SQLException {
                TicketRenglon renglon = new TicketRenglon();
                renglon.setId(rs.getInt("id"));
                renglon.setTICKET_id(rs.getInt("TICKET_id"));
                renglon.setPRODUCTO_id(rs.getInt("PRODUCTO_id"));
                renglon.setCantidad(rs.getInt("cantidad"));
                renglon.setPrecio(rs.getFloat("precio"));
                renglon.setImporte(rs.getFloat("importe"));
                renglon.setActivo(rs.getInt("activo"));
                
                return renglon;
            }
        }, id);	
	}

	@Override
	public TicketRenglon insertar(TicketRenglon renglon) {		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion)
				.withTableName("ticket_renglones")
				.usingColumns("TICKET_id","PRODUCTO_id","cantidad","precio","activo")
				.usingGeneratedKeyColumns("id");
		
		Map<String, Object> datos = new HashMap<>();
		datos.put("TICKET_id", renglon.getTICKET_id());
		datos.put("PRODUCTO_id", renglon.getPRODUCTO_id());
		datos.put("cantidad", renglon.getCantidad());
		datos.put("precio", renglon.getPrecio());
		datos.put("activo", 1);
		
		Number id = insert.executeAndReturnKey(datos);		
		renglon.setId(id.intValue());
		renglon.setActivo(1);
		
		return renglon;
	}

	@Override
	public void actualizar(TicketRenglon renglon) {
		String sql_update = "UPDATE ticket_renglones "
				  + "SET TICKET_id = ?, PRODUCTO_id = ?, cantidad = ?, precio = ? "
				  + "WHERE id = ?";

		conexion.update(sql_update,
						renglon.getTICKET_id(),
						renglon.getPRODUCTO_id(),
						renglon.getCantidad(),
						renglon.getPrecio(),						
						renglon.getId());
	}

	@Override
	public void desactivar(int id) {
		String sql_update = "UPDATE ticket_renglones SET activo = 0 WHERE id = ?";
		
		conexion.update(sql_update, id);
	}
	
	@Override
	public List<Importe> obtenerImportes(int cajero_id, Timestamp fecha_inicial, Timestamp fecha_final) {
		String sql_query = "SELECT SUM(importe)importe, cajero_id "
				+ "FROM tickets T "
				+ "JOIN ticket_renglones TR ON (TR.ticket_id = T.id) "
				+ "WHERE cajero_id = ? "
				+ "AND T.fecha_hora BETWEEN ? AND ?";
		return conexion.query(sql_query, new RowMapper<Importe>() {
			
			@Override
			public Importe mapRow(ResultSet rs, int rowNum) throws SQLException {
	           Importe importe = new Importe();	               
	           importe.setImporte(rs.getFloat("importe"));	           
	           importe.setCajero_id(rs.getInt("cajero_id"));
	           
	           return importe; 
	       }
	    }, cajero_id, fecha_inicial, fecha_final);
	}

}
