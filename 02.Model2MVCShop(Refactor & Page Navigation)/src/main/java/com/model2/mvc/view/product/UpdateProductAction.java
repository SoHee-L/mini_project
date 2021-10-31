package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		int price= Integer.parseInt(request.getParameter("price"));
		String manuDate = request.getParameter("manuDate").replace("-", "");
		
		Product product=new Product();
		product.setFileName(request.getParameter("fileName"));
		product.setManuDate(manuDate);
		product.setPrice(price);
		product.setProdDetail(request.getParameter("prodDetail"));
		product.setProdName(request.getParameter("prodName"));
		product.setProdNo(prodNo);
	
	
		ProductService productService=new ProductServiceImpl();
		productService.updateProduct(product);
		
		HttpSession session=request.getSession();
		int sessionId=((Product)session.getAttribute("product")).getProdNo();
		String id = Integer.toString(sessionId);
	
		if(id.equals(prodNo)){
			session.setAttribute("product", product);
		}
		
		return "redirect:/getProduct.do?prodNo="+prodNo +"&menu=search";
		
	}
}