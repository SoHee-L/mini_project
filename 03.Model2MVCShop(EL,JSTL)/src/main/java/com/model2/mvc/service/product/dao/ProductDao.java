package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;


public class ProductDao {
	
	///Field
	
	///Constructor
	public ProductDao() {
	}

	///Method
	public void insertProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = 	"INSERT "+ 
								"INTO PRODUCT "+ 
								"VALUES (seq_product_prod_no.nextval,?,?,?,?,?,SYSDATE)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, product.getProdName());   
		pStmt.setString(2, product.getProdDetail()); 
		pStmt.setString(3, product.getManuDate());   
		pStmt.setInt(4, product.getPrice());         
		pStmt.setString(5, product.getFileName());   
		pStmt.executeUpdate();                         
		
		pStmt.close();
		con.close();
	}

	public Product findProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
			
		String sql = 	"SELECT "+
								"image_file ,  manufacture_day ,  price , prod_detail , prod_name ,  prod_no , reg_date " + 
								"FROM product WHERE prod_no = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, prodNo);

		ResultSet rs = pStmt.executeQuery();

		Product product = null;

		while (rs.next()) {
			product = new Product();
			product.setFileName(rs.getString("image_file"));        
			product.setManuDate(rs.getString("manufacture_day"));   
			product.setPrice(rs.getInt("price"));                   
			product.setProdDetail(rs.getString("prod_detail"));     
			product.setProdName(rs.getString("prod_name"));         
			product.setProdNo(rs.getInt("prod_no"));                
			product.setRegDate(rs.getDate("reg_date"));                                                        
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		return product;
	}

	public Map<String , Object> getProductList(Search search) throws Exception {
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT prod_no ,  prod_name , price  FROM  product ";
		// query 구성 맞는지. userDao는 id하고 name으로 검색하는데 왜 email도 검색에 들어가는지
		
		if (search.getSearchCondition() != null) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE prod_no = '" + search.getSearchKeyword()+"'";
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE  prod_name ='" + search.getSearchKeyword()+"'";
			}else if ( search.getSearchCondition().equals("2") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE price ='" + search.getSearchKeyword()+"'";
			}
		}
		sql += " ORDER BY prod_no";
	
		System.out.println("ProductDAO::Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		System.out.println("ProductDAO :: totalCount  :: " + totalCount);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);
		
		//질문 userDAO에는 list갯수가 많고 dao에는 적어졌음. 왜지?..
		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setPrice(rs.getInt("PRICE"));                 
			product.setProdName(rs.getString("PROD_NAME"));    
			product.setProdNo(rs.getInt("PROD_NO"));              

			list.add(product);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		return map;
	}

	public void updateProduct(Product vo) throws Exception {

		Connection con = DBUtil.getConnection();

		String sql = 	"UPDATE product "+
								"SET prod_name = ?, prod_detail = ? , manufacture_day = ? , price = ?, image_file = ? "+ 
								"WHERE prod_no = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, vo.getProdName());   
		pStmt.setString(2, vo.getProdDetail()); 
		pStmt.setString(3, vo.getManuDate());   
		pStmt.setInt(4, vo.getPrice());         
		pStmt.setString(5, vo.getFileName());   
		pStmt.setInt(6, vo.getProdNo());        
		
		pStmt.close();
		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}