package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Purchase purchase=new Purchase();
		//String pc = request.getParameter("price");
		//int price = Integer.parseInt(pc);
		//String manuDate = request.getParameter("manuDate").substring(0,4)+request.getParameter("manuDate").substring(5,7)+request.getParameter("manuDate").substring(8);
		
		//productVO.setPrice( Integer.parseInt(request.getParameter("price")));
		
		//////////////////////////////////////////////////////////////////////
		Product product = new Product();
		User user = new User();
		
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		purchase.setPurchaseProd(product);
		
		//���Ŷ�� ���̺� ���� �־��ִ� �����̶� �׹ۿ� product������ �ʿ����.
		//�ѹ����� = ����ȯ�Ҷ� ��. java.lang.NumberFormatException: null = null������ ����ȯ�� �ߴٴ� �� price�� ����ȯ�� �ߴµ� price�� ���� �����ϱ� ������ ��.. �� price�� �־�����...
		user.setUserId(request.getParameter("buyerId"));
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setTranCode("001"); // addpurchase.jap���� ���Ÿ� ������ ���ſϷ�
		
		System.out.println(request.getParameter("receiverPhone")+"receiverPhone����~~~");
		//////////////////////////////////////

		System.out.println("addPurchaseAction product" + product);
		System.out.println("addPurchaseAction user" + user);
		System.out.println("addPurchaseAction purchase" + purchase);
		
		
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchase);
		HttpSession session = request.getSession();
		session.setAttribute("purchase", purchase);
		
		return "redirect:/purchase/addPurchase.jsp";
	}
}