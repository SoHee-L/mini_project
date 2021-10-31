package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		PurchaseVO purchaseVO=new PurchaseVO();
		//String pc = request.getParameter("price");
		//int price = Integer.parseInt(pc);
		//String manuDate = request.getParameter("manuDate").substring(0,4)+request.getParameter("manuDate").substring(5,7)+request.getParameter("manuDate").substring(8);
		
		//productVO.setPrice( Integer.parseInt(request.getParameter("price")));
		
		//////////////////////////////////////////////////////////////////////
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();
		
		productVO.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		purchaseVO.setPurchaseProd(productVO);
		
		//구매라는 테이블에 값을 넣어주는 역할이라 그밖에 product값들은 필요없다.
		//넘버포맷 = 형변환할때 남. java.lang.NumberFormatException: null = null값으로 형변환을 했다는 뜻 price로 형변환을 했는데 price는 값이 없으니까 에러가 남.. 난 price를 넣었었고...
		userVO.setUserId(request.getParameter("buyerId"));
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setTranCode("001"); // addpurchase.jap에서 구매를 누르면 구매완료
		
		System.out.println(request.getParameter("receiverPhone")+"receiverPhone실행~~~");
		//////////////////////////////////////

		System.out.println("addPurchaseAction productVO" + productVO);
		System.out.println("addPurchaseAction userVO" + userVO);
		System.out.println("addPurchaseAction purchaseVO" + purchaseVO);
		
		
		
		PurchaseService service=new PurchaseServiceImpl();
		service.addPurchase(purchaseVO);
		HttpSession session = request.getSession();
		session.setAttribute("purchaseVO", purchaseVO);
		
		return "redirect:/purchase/addPurchase.jsp";
	}
}