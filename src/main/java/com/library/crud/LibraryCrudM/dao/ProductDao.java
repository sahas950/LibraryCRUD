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

import com.library.crud.LibraryCrudM.bean.Product;

@Repository
public class ProductDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	public String createProduct(Product product)
	{
		if(product.getAuthor()=="")
		{
			return "noauthor";
		}
		final String sql1 = "insert into product(bookCode,bookName,author,date,user) values(?,?,?,?,?);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			getJdbcTemplate().update(new PreparedStatementCreator() {
	
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
					ps.setString(1, product.getBookCode());
					ps.setString(2, product.getBookName());
					ps.setString(3, product.getAuthor());
					ps.setString(4, product.getDate());
					ps.setString(5, product.getUser());
					return ps;
				}
			}, keyHolder);
		}
		catch(Exception e)
		{
			return null;
		}
		return "success";
	}
		
	public String updateProduct(Product product,int pid)
	{
		
		final String sql1 = "UPDATE product\r\n"
				+ "SET bookName = ? , author = ?\r\n"
				+ "WHERE id=?;";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
		getJdbcTemplate().update(new PreparedStatementCreator() 
		{
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
				ps.setString(1, product.getBookName());
				ps.setString(2, product.getAuthor());
				ps.setInt(3, pid);
				return ps;
			}
		}, keyHolder);
		}
		catch(Exception e)
		{
			return null;
		}
		return "success";
		
	}

	public  List<Product> getProductList(String name)
	{
		String sql = "select * from product where user='"+name+"'";
		return getJdbcTemplate().query(sql, new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt(1));
				product.setBookCode(rs.getString(3));
				product.setBookName(rs.getString(4));
				product.setAuthor(rs.getString(2));
				product.setDate(rs.getString(5));
				product.setUser(rs.getString(6));
				return product;
			}
		});
	}

	@SuppressWarnings("deprecation")
	public Product getProduct(int pid) {
		String sql = "select * from product where id=?";
		try {
			return getJdbcTemplate().queryForObject(sql, new Object[] { pid }, new RowMapper<Product>() {
				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product product = new Product();
					product.setId(pid);
					product.setBookCode(rs.getString(3));
					product.setBookName(rs.getString(4));
					product.setAuthor(rs.getString(2));
					product.setDate(rs.getString(5));
					product.setUser(rs.getString(6));
					return product;
				}
			});
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public void deleteProduct(Product product,int pid) 
	{
		final String sql1 = "delete from product where id=?";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql1, new String[] { "id" });
				ps.setInt(1, pid);
				return ps;
			}
		}, keyHolder);
	}
}


