package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class PurchaseDAO {
	
	public PurchaseDAO(){
	}

	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into TRANSACTION values (seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
			
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("pucahseDAO.java 값 왜안나와"+purchaseVO);
		
		System.out.println(purchaseVO.getPurchaseProd()+"PurchaseDAO.java에 purchasVO값");
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		
		stmt.setString(9, purchaseVO.getDivyDate());
		
		int tran = stmt.executeUpdate();

		//executeUpdate 는 리턴값이 0(fail) 혹은  1(success)이다.

		if(tran ==1){

			System.out.println("insert success");

		}else{

			System.out.println("insert err");

		}
		
		
		con.close();
	}

		public PurchaseVO findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from TRANSACTION where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();

		PurchaseVO purchaseVO = null;
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();
		while (rs.next()) {
			purchaseVO = new PurchaseVO();
			purchaseVO.setTranNo(rs.getInt("TRAN_NO"));
			productVO.setProdNo(rs.getInt("PROD_NO"));
			userVO.setUserId(rs.getString("BUYER_ID"));
			purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));
			purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));
			purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
			purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));
			purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));
			purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));
			purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));
			purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
		}
		
		con.close();

		return purchaseVO;
	}

	
	
	public HashMap<String,Object> getPurchasetList(SearchVO searchVO, String buyerId) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from TRANSACTION where buyer_id= ?";
 
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		stmt.setString(1, buyerId);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());

		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				ProductVO productVO = new ProductVO();
				UserVO userVO = new UserVO();
				
				purchaseVO.setTranNo(rs.getInt("TRAN_NO")); 
				purchaseVO.setPurchaseProd(productVO);
				productVO.setProdNo(rs.getInt("PROD_NO"));
				purchaseVO.setBuyer(userVO);
				userVO.setUserId(rs.getString("BUYER_ID"));       
				purchaseVO.setPaymentOption(rs.getString("PAYMENT_OPTION"));           
				purchaseVO.setReceiverName(rs.getString("RECEIVER_NAME"));           
				purchaseVO.setReceiverPhone(rs.getString("RECEIVER_PHONE"));           
				purchaseVO.setDivyAddr(rs.getString("DEMAILADDR"));       
				purchaseVO.setTranCode(rs.getString("TRAN_STATUS_CODE"));                      
				purchaseVO.setDivyRequest(rs.getString("DLVY_REQUEST"));    
				purchaseVO.setOrderDate(rs.getDate("ORDER_DATA"));    
				purchaseVO.setDivyDate(rs.getString("DLVY_DATE"));      
				
				
				
				
				          

				list.add(purchaseVO);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}

	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTIONS set PROD_NAME=?, PROD_DETAIL=?, MANUFACTURE_DAY =?, PRICE =?, IMAGE_FILE  =? where PROD_NO  =?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		//String OrderDate = request.getParameter("orderDate").replace("-", "");
		
		stmt.setInt(1, purchaseVO.getTranNo());
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(3, purchaseVO.getBuyer().getUserId());
		stmt.setString(4, purchaseVO.getPaymentOption());
		stmt.setString(5, purchaseVO.getReceiverName());
		stmt.setString(6, purchaseVO.getReceiverPhone());
		stmt.setString(7, purchaseVO.getDivyAddr());
		stmt.setString(8, purchaseVO.getDivyRequest());
		stmt.setString(9, purchaseVO.getTranCode());
		stmt.setDate(10, purchaseVO.getOrderDate());
		
		
		stmt.executeUpdate();
		
		con.close();
	}
	
		public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update transaction set tran_status_code= ? where prod_no =?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		System.out.println("PurcahseDao prodNo 값여기"+purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		
		stmt.executeUpdate();
		
		con.close();
	}
}