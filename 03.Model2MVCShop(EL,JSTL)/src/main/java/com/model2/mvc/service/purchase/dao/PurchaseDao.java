package com.model2.mvc.service.purchase.dao;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;


public class PurchaseDao {
	
	public PurchaseDao(){
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
			
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("pucahseDAO.java 값 왜안나와"+purchase);
		
		System.out.println(purchase.getPurchaseProd()+"PurchaseDAO.java에 purchasVO값");
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, purchase.getTranCode());
		
		stmt.setString(9, purchase.getDivyDate());
		
		int tran = stmt.executeUpdate();

		//executeUpdate 는 리턴값이 0(fail) 혹은  1(success)이다.

		if(tran ==1){

			System.out.println("insert success");

		}else{

			System.out.println("insert err");

		}
		
		
		con.close();
	}

		public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		Purchase purchase = null;
		Product product = new Product();
		User user = new User();
		while (rs.next()) {
			purchase = new Purchase();
			purchase.setTranNo(rs.getInt("TRAN_NO"));
			product.setProdNo(rs.getInt("PROD_NO"));
			user.setUserId(rs.getString("BUYER_ID"));
			purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchase.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchase.setDivyAddr(rs.getString("DEMAILADDR"));
			purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchase.setOrderDate(rs.getDate("ORDER_DATA"));
			purchase.setDivyDate(rs.getString("DLVY_DATE"));
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
		}
		
		con.close();

		return purchase;
	}

	
	
	public Map<String,Object> getPurchasetList(Search search, String buyerId) throws Exception {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION where buyer_id ='"+buyerId+"'";
		System.out.println("sql~~~~"+sql);
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		//stmt.setString(1, buyerId);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);
		int totalCount = this.getTotalCount(sql);
		
		map.put("totalCount", new Integer(totalCount));

		rs.absolute(search.getCurrentPage() * search.getPageSize() - search.getPageSize()+1);
		System.out.println("search.currentPage():" + search.getCurrentPage());
		System.out.println("search.pageSize():" + search.getPageSize());

		List<Purchase> list = new ArrayList<Purchase>();
		if (total > 0) {
			for (int i = 0; i < search.getPageSize(); i++) {
				Purchase purchase = new Purchase();
				Product product = new Product();
				User user = new User();
				
				purchase.setTranNo(rs.getInt("TRAN_NO")); 
				purchase.setPurchaseProd(product);
				product.setProdNo(rs.getInt("PROD_NO"));
				purchase.setBuyer(user);
				user.setUserId(rs.getString("BUYER_ID"));       
				purchase.setPaymentOption(rs.getString("PAYMENT_OPTION"));           
				purchase.setReceiverName(rs.getString("RECEIVER_NAME"));           
				purchase.setReceiverPhone(rs.getString("RECEIVER_PHONE"));           
				purchase.setDivyAddr(rs.getString("DEMAILADDR"));       
				purchase.setTranCode(rs.getString("TRAN_STATUS_CODE"));                      
				purchase.setDivyRequest(rs.getString("DLVY_REQUEST"));    
				purchase.setOrderDate(rs.getDate("ORDER_DATA"));    
				purchase.setDivyDate(rs.getString("DLVY_DATE"));      
				
				
				
				
				          

				list.add(purchase);
				if (!rs.next())
					break;
			}
		}
		
		//==> totalCount 정보 저장
			//	map.put("totalCount", new Integer(totalCount));
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}

	public void updatePurchase(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTIONS set PROD_NAME=?, PROD_DETAIL=?, MANUFACTURE_DAY =?, PRICE =?, IMAGE_FILE  =? where PROD_NO  =?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		//String OrderDate = request.getParameter("orderDate").replace("-", "");
		
		stmt.setInt(1, purchase.getTranNo());
		stmt.setInt(2, purchase.getPurchaseProd().getProdNo());
		stmt.setString(3, purchase.getBuyer().getUserId());
		stmt.setString(4, purchase.getPaymentOption());
		stmt.setString(5, purchase.getReceiverName());
		stmt.setString(6, purchase.getReceiverPhone());
		stmt.setString(7, purchase.getDivyAddr());
		stmt.setString(8, purchase.getDivyRequest());
		stmt.setString(9, purchase.getTranCode());
		stmt.setDate(10, purchase.getOrderDate());
		
		
		stmt.executeUpdate();
		
		con.close();
	}
	
		public void updateTranCode(Purchase purchase) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update transaction set tran_status_code= ? where prod_no =?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("PurcahseDao prodNo 값여기"+purchase.getPurchaseProd().getProdNo());
		stmt.setString(1, purchase.getTranCode());
		stmt.setInt(2, purchase.getPurchaseProd().getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
		
		
		
		// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
		private int getTotalCount(String sql) throws Exception {
			
			sql = "SELECT COUNT(*) "+
			          "FROM ( " +sql+ ") countTable";
			System.out.println("spl Purcahse"+sql);
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
			
			System.out.println("PurchaseDAO :: make SQL :: "+ sql);	
			
			return sql;
		}
	
		
}