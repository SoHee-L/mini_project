package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;


public class UpdateProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		int price= Integer.parseInt(request.getParameter("price"));
		String manuDate = request.getParameter("manuDate").replace("-", "");
		ProductVO productVO=new ProductVO();
		
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate(manuDate);
		productVO.setPrice(price);
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setProdName(request.getParameter("prodName"));
		productVO.setProdNo(prodNo);
	
	
		ProductService service=new ProductServiceImpl();
		service.updateProduct(productVO);
		
		HttpSession session=request.getSession();
		int sessionId=((ProductVO)session.getAttribute("vo")).getProdNo();
		String id = Integer.toString(sessionId);
	
		if(id.equals(prodNo)){
			session.setAttribute("vo", productVO);
		}
		
		return "redirect:/getProduct.do?prodNo="+prodNo +"&menu=search";
		
	}
}