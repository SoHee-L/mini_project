package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class AddPurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prod_no");
		System.out.println(prodNo+"prodNo 실행~~~");
		
		ProductService service=new ProductServiceImpl();
		ProductVO productVO=service.getProduct(Integer.parseInt(prodNo));
		
		System.out.println("addPurchaseAction productVO" + productVO);
		
		HttpSession session = request.getSession();
		session.setAttribute("vo", productVO);
		/// return값이랑 같은 정보를 가져온다고 생각하면 안됨.
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
