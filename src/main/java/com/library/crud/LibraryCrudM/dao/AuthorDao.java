package com.library.crud.LibraryCrudM.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.library.crud.LibraryCrudM.bean.author;

@Repository
public class AuthorDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public List<author> findAll() 
	{
		String sql = "select * from user";
		return getJdbcTemplate().query(sql, new RowMapper<author>() {
			@Override
			public author mapRow(ResultSet rs, int rowNum) throws SQLException {
				author author = new author();
				author.setId(rs.getLong(1));
				author.setName(rs.getString(2));
				return author;
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public author findById(Long userId) {
		String sql = "select * \r\n"
				+ "from author where id=?";
		try {
		return getJdbcTemplate().queryForObject(sql, new Object[] { userId }, new RowMapper<author>() {
			@Override
			public author mapRow(ResultSet rs, int rowNum) throws SQLException {
				author a = new author();
				a.setId(userId);
				a.setName(rs.getString(1));
				a.setEmail(rs.getString(2));
				return a;
			}
		});
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public author save(author author) {
		
		final String sql1 = "insert into author(name, email) values(?, ?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
				ps.setString(1, author.getName());
				ps.setString(2, author.getEmail());
				return ps;
			}
		}, keyHolder);
		author Author=findById(author.getId());
		return Author;
	}
	
	public void delete(author author) {
		final String sql1 = "delete from author where id=?";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
				ps.setLong(1, author.getId());
				return ps;
			}
		}, keyHolder);
	}
}
