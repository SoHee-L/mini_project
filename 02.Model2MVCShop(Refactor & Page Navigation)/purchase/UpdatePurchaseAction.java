package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;




public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		int tranNo= Integer.parseInt(request.getParameter("tranNO"));
		//String OrderDate = request.getParameter("orderDate").replace("-", "");
		Purchase purchase=new Purchase();
		Product product = new Product();
		User user = new User();
		
/////////////////////////////////////////////
	purchaseVO.setDivyAddr(request.getParameter("divyAddr"));       
	purchaseVO.setDivyDate(request.getParameter("divyDate"));                 
	purchaseVO.setDivyRequest(request.getParameter("divyRequest"));    
	purchaseVO.setPaymentOption(request.getParameter("paymentOption")); 
	purchaseVO.setPurchaseProd(product);
	purchaseVO.setReceiverName(request.getParameter("receiverName"));           
	purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));           
	purchaseVO.setTranCode(request.getParameter("tranStatusCode"));           
	purchaseVO.setTranNo(Integer.parseInt(request.getParameter("tranNo")));           
	
		
		

	
		PurchaseService service=new PurchaseServiceImpl();
		service.updatePurchase(purchase);
		
		HttpSession session=request.getSession();
		int sessionId=((Purchase)session.getAttribute("purchase")).getTranNo();
		String id = Integer.toString(sessionId);
	
		if(id.equals(tranNo)){
			session.setAttribute("purchase", purchase);
		}
		
		return "redirect:/getPurchase.do?tranNo="+tranNo +"&menu=search";
		
	}
}