package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;


public class AddPurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String prodNo=request.getParameter("prod_no");
		System.out.println(prodNo+"prodNo ����~~~");
		
		ProductService service=new ProductServiceImpl();
		Product product=service.getProduct(Integer.parseInt(prodNo));
		
		System.out.println("addPurchaseAction product" + product);
		
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		/// return���̶� ���� ������ �����´ٰ� �����ϸ� �ȵ�.
		return "forward:/purchase/addPurchaseView.jsp";
	}
}
