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
		
		//구매라는 테이블에 값을 넣어주는 역할이라 그밖에 product값들은 필요없다.
		//넘버포맷 = 형변환할때 남. java.lang.NumberFormatException: null = null값으로 형변환을 했다는 뜻 price로 형변환을 했는데 price는 값이 없으니까 에러가 남.. 난 price를 넣었었고...
		user.setUserId(request.getParameter("buyerId"));
		purchase.setBuyer(user);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setTranCode("001"); // addpurchase.jap에서 구매를 누르면 구매완료
		
		System.out.println(request.getParameter("receiverPhone")+"receiverPhone실행~~~");
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