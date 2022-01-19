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
import org.springframework.transaction.annotation.Transactional;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER", (ResultSet rs, int rowNum) -> {
			Member member = new Member( rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("NAME"), rs.getTimestamp("REGDATE"), rs.getString("ADDRESS"), rs.getInt("PHONE") );
			member.setId(rs.getLong("ID"));
			return member;
		});
		return results;
	}
	public void insert(final Member member) {
	      KeyHolder keyHolder = new GeneratedKeyHolder();
	      jdbcTemplate.update(
	            new PreparedStatementCreator() {
	               public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
	                  PreparedStatement pstmt = con.prepareStatement(
	                        "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE, ADDRESS, PHONE) values (?,?,?,?,?,?)", new String[] {"ID"} );
	                  pstmt.setString(1, member.getEmail());
	                  pstmt.setString(2, member.getPassword());
	                  pstmt.setString(3, member.getName());
	                  pstmt.setDate(4, new java.sql.Date( member.getRegisterDate().getTime()) );
	                  pstmt.setString(5, member.getAddress());
	                  pstmt.setInt(6, member.getPhone());
	                  return pstmt;
	               }
	            }, 
	            keyHolder );
	            Number keyValue = keyHolder.getKey();
	            member.setId(keyValue.longValue());
	   }
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ?, ADDRESS = ?, PHONE = ? where EMAIL = ? ", member.getName(), member.getPassword(), member.getAddress(), member.getPhone(), member.getEmail());
	}
	public void delete(String id) {
		jdbcTemplate.update("delete from member where email = ?", id);
	}
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?",
		new RowMapper<Member>() {		//JAVA anonymous class
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(rs.getString("EMAIL"), rs.getString("PASSWORD"),	rs.getString("NAME"), rs.getTimestamp("REGDATE"),  rs.getString("ADDRESS"), rs.getInt("PHONE"));
				member.setId(rs.getLong("ID"));
				return member;
			}	
		}, email);
		return results.isEmpty() ? null : results.get(0);
	}
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd, String name, String addr,int phone) {
		Member member = selectByEmail(email);
		if(member == null)
			throw new MemberNotFoundException();
		member.changePassword(oldPwd, newPwd);
		member.setName(name);
		member.setAddress(addr);
		member.setPhone(phone);
		update(member);
	}
}











