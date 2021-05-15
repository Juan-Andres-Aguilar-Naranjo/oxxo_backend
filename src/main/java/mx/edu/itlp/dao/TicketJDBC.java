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

import mx.edu.itlp.modelos.Ticket;

@Repository
public class TicketJDBC implements TicketDAO {

	@Autowired
	JdbcTemplate conexion;
	
	@Override
	public Ticket buscarTicket(int id) {
		String sql_query="SELECT * FROM tickets WHERE id=?";
		return conexion.queryForObject(sql_query, new RowMapper<Ticket>() {
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException{
				Ticket ticket=new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setFecha_hora(rs.getString("fecha_hora"));
				ticket.setTotal(rs.getInt("total"));
				ticket.setCajero_id(rs.getInt("cajero_id"));
				ticket.setActivo(rs.getInt("activo"));
				return ticket;
			}
		},
				id);
	}
	
	@Override
	public List<Ticket> consultarTickets() {
		String sql_query = "SELECT * FROM tickets WHERE activo=1";
		return conexion.query(sql_query, new RowMapper<Ticket>(){
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket ticket=new Ticket();
				ticket.setId(rs.getInt("id"));
				ticket.setFecha_hora(rs.getString("fecha_hora"));
				ticket.setTotal(rs.getInt("total"));
				ticket.setCajero_id(rs.getInt("cajero_id"));
				ticket.setActivo(rs.getInt("activo"));
				return ticket;
        }
    });
	}

	@Override
	public Ticket insertarTicket(Ticket ticket) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(conexion)
				.withTableName("tickets")
				.usingColumns("fecha_hora","total","cajero_id")
				.usingGeneratedKeyColumns("id");
				
		Map<String, Object> datos = new HashMap<>();
		datos.put("fecha_hora", ticket.getFecha_hora());
		datos.put("total", ticket.getTotal());
		datos.put("cajero_id", ticket.getCajero_id());
			
		Number id = insert.executeAndReturnKey(datos);
				
		ticket.setId(id.intValue());
		
		ticket.setActivo(1);
		
		return ticket;
	}

	@Override
	public void actualizarTicket(Ticket ticket) {
		String sql_update="UPDATE tickets SET fecha_hora=?, total=?,"
				+ "cajero_id=? WHERE id=?";
		conexion.update(sql_update, ticket.getFecha_hora(),
				ticket.getTotal(),
				ticket.getCajero_id(),
				ticket.getId());
	}
	

	@Override
	public void eliminarTicket(int id) {
		String sql_update="UPDATE tickets SET activo=0 WHERE id=?";
		conexion.update(sql_update,id);
	}
}
