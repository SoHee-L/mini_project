package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;


public class UpdatePurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String tranNo=request.getParameter("tranNo");
		
		PurchaseService service=new PurchaseServiceImpl();
		Purchase purchase=service.getPurchase(Integer.parseInt(tranNo));
		
		HttpSession session = request.getSession();
		session.setAttribute("purchase", purchase);
		
		return "forward:/Purchase/updatePurchaseView.jsp";
	}
}
