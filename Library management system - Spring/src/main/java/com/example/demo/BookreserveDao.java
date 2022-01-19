package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class BookreserveDao {
	private JdbcTemplate jdbcTemplate;
	public BookreserveDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(final Bookreserve br) {
		jdbcTemplate.update("insert into RESERVE (EMAIL, NAME, BOOKNAME, BOOKNUM, REGDATE) values (?,?,?,?,now())" ,br.getEmail(), br.getName(), br.getBookname(), br.getBooknum());

	}
	
	public void update(Bookreserve br) {		//미완
		jdbcTemplate.update("update RESERVE set EMAIL = ?, NAME= ?, BOOKNAME = ?, BOOKNUM= ? where EMAIL = ? ", br.getEmail(), br.getName(), br.getBookname(), br.getBooknum());
	}
	
	public void delete(String email) {
		jdbcTemplate.update("delete from RESERVE where email = ?", email);
	}
	
	public Bookreserve selectBK(String bookname) {
		List<Bookreserve> results = jdbcTemplate.query("select * from RESERVE where BOOKNAME = ?",
		new RowMapper<Bookreserve>() {		//JAVA anonymous class
			@Override
			public Bookreserve mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bookreserve bkr = new Bookreserve(rs.getString("EMAIL"), rs.getString("NAME"), rs.getString("BOOKNAME"), rs.getInt("BOOKNUM"));
				bkr.setNum(rs.getLong("Num"));
				return bkr;
			}	
		}, bookname);
		return results.isEmpty() ? null : results.get(0);
	}
	
	public int bookcount(String bookname) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM RESERVE WHERE BOOKNAME = ?", Integer.class, bookname);
		
	}
}