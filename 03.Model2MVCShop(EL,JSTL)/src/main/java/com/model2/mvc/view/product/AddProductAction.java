package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Product product=new Product();
		String pc = request.getParameter("price");
		int price = Integer.parseInt(pc);
		String manuDate = request.getParameter("manuDate").substring(0,4)+request.getParameter("manuDate").substring(5,7)+request.getParameter("manuDate").substring(8);
		
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate(manuDate);
		//productVO.setPrice( Integer.parseInt(request.getParameter("price")));
		product.setPrice(price);
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setProdName(request.getParameter("prodName"));

		System.out.println("AddProductAction ::"+product);
		
		ProductServiceImpl productservice=new ProductServiceImpl();
		productservice.addProduct(product);
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		
		return "redirect:/product/addProduct.jsp";
	}
}