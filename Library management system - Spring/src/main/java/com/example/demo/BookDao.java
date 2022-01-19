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

public class BookDao {
	private JdbcTemplate jdbcTemplate;
	public BookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Book> selectAll() {
		List<Book> results = jdbcTemplate.query("select * from BOOK", (ResultSet rs, int rowNum) -> {
			Book book = new Book( rs.getString("TITLE"), rs.getString("AUTHOR"), rs.getInt("STOCK"));
			book.setNum(rs.getLong("NUM"));
			return book;
		});
		return results;
	}
	
	public void insert(final Book book) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement(
								"insert into BOOK (TITLE, AUTHOR, STOCK) values (?,?,?)", new String[] {"Num"} );
						pstmt.setString(1, book.getTitle());
						pstmt.setString(2, book.getAuthor());
						pstmt.setInt(3, book.getStock());
						return pstmt;
					}
				}, 
				keyHolder );
				Number keyValue = keyHolder.getKey();
				book.setNum(keyValue.longValue());
	}
	
	public void delete(final Book book) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("delete from book where title=?");
						pstmt.setString(1, book.getTitle());
						return pstmt;
					}
				});
	}
	
	public void borrow(final Book book, int add) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("update BOOK set stock=? where title=?");
						book.setMinusStock(book.getStock(), add);
						pstmt.setInt(1, book.getStock());
						pstmt.setString(2, book.getTitle());
						return pstmt;
					}
				});
	}
	
	public void back(final Book book, int add) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
						PreparedStatement pstmt = con.prepareStatement("update BOOK set stock=? where title=?");
						book.setPlusStock(book.getStock(), add);
						pstmt.setInt(1, book.getStock());
						pstmt.setString(2, book.getTitle());
						return pstmt;
					}
				});
	}

	public List<Book> zeroAll() {
		List<Book> results = jdbcTemplate.query("select * from BOOK where stock <= 0", (ResultSet rs, int rowNum) -> {
			Book book = new Book( rs.getString("TITLE"), rs.getString("AUTHOR"), rs.getInt("STOCK"));
			book.setNum(rs.getLong("NUM"));
			return book;
		});
		return results;
	}
	
	public Book selectByTitle(String title) {
		List<Book> results = jdbcTemplate.query("select * from BOOK where TITLE = ?",
		new RowMapper<Book>() {		//JAVA anonymous class
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book(rs.getString("TITLE"), rs.getString("AUTHOR"),	rs.getInt("STOCK"));
				book.setNum(rs.getLong("NUM"));
				return book;
			}	
		}, title);
		return results.isEmpty() ? null : results.get(0);
	}
}