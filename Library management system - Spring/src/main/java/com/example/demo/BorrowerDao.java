package com.example.demo;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class BorrowerDao {
	private JdbcTemplate jdbcTemplate;
	public BorrowerDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Borrower> selectAll() {
		List<Borrower> results = jdbcTemplate.query("select * from BORROWER", (ResultSet rs, int rowNum) -> {
			Borrower borrower = new Borrower(rs.getString("EMAIL"), rs.getString("NAME"), rs.getString("TITLE"), rs.getInt("NUMBOOK"), rs.getTimestamp("REGDATE"));
			borrower.setId(rs.getLong("ID"));
			return borrower;
		});
		return results;
	}
	
	public void insert(final Borrower borrower) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement(
								"insert into BORROWER (EMAIL, NAME, TITLE, NUMBOOK, REGDATE) values (?,?,?,?,?)", new String[] {"ID"});
						pstmt.setString(1, borrower.getEmail());
						pstmt.setString(2, borrower.getName());
						pstmt.setString(3, borrower.getTitle());
						pstmt.setInt(4, borrower.getNumbook());
						pstmt.setDate(5, new java.sql.Date(borrower.getRegisterDate().getTime()));
						return pstmt;
					}
				}, 
				keyHolder );
				Number keyValue = keyHolder.getKey();
				borrower.setId(keyValue.longValue());
	}
	
	public void delete(final Borrower borrower) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("delete from borrower where title=? AND email=?");
						pstmt.setString(1, borrower.getTitle());
						pstmt.setString(2, borrower.getEmail());
						return pstmt;
					}
				});
	}
	
	public void bookback(final Borrower borrower, int add) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("update BORROWER set numbook=? where email=? AND title=?");
						borrower.setMinusNumbook(borrower.getNumbook(), add);
						pstmt.setInt(1, borrower.getNumbook());
						pstmt.setString(2, borrower.getEmail());
						pstmt.setString(3, borrower.getTitle());
						return pstmt;
					}
				});
	}
	
	public void bookcome(final Borrower borrower, int add) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("update BORROWER set numbook=? where email=? AND title=?");
						borrower.setPlusNumbook(borrower.getNumbook(), add);
						pstmt.setInt(1, borrower.getNumbook());
						pstmt.setString(2, borrower.getEmail());
						pstmt.setString(3, borrower.getTitle());
						return pstmt;
					}
				});
	}
	
	public Borrower selectByEmail(String email, String title) {
		List<Borrower> results = jdbcTemplate.query("select * from BORROWER where EMAIL = ? && TITLE = ?",
		new RowMapper<Borrower>() {		//JAVA anonymous class
			@Override
			public Borrower mapRow(ResultSet rs, int rowNum) throws SQLException {
				Borrower borrower = new Borrower(rs.getString("EMAIL"), rs.getString("NAME"), rs.getString("TITLE"), rs.getInt("NUMBOOK"), rs.getTimestamp("REGDATE"));
				borrower.setId(rs.getLong("ID"));
				return borrower;
			}	
		}, email, title);
		return results.isEmpty() ? null : results.get(0);
	}
}
