package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class AddProductAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		ProductVO productVO=new ProductVO();
		String pc = request.getParameter("price");
		int price = Integer.parseInt(pc);
		String manuDate = request.getParameter("manuDate").substring(0,4)+request.getParameter("manuDate").substring(5,7)+request.getParameter("manuDate").substring(8);
		
		productVO.setFileName(request.getParameter("fileName"));
		productVO.setManuDate(manuDate);
		//productVO.setPrice( Integer.parseInt(request.getParameter("price")));
		productVO.setPrice(price);
		productVO.setProdDetail(request.getParameter("prodDetail"));
		productVO.setProdName(request.getParameter("prodName"));

		System.out.println(productVO);
		
		ProductService service=new ProductServiceImpl();
		service.addProduct(productVO);
		HttpSession session = request.getSession();
		session.setAttribute("vo", productVO);
		
		return "redirect:/product/addProduct.jsp";
	}
}