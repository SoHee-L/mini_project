package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		int prodNo=Integer.parseInt(request.getParameter("prodNo"));
		String menu = request.getParameter("menu");
		if(menu.equals("manage")) {
			return "redirect:/updateProductView.do?prodNo="+prodNo+"&menu="+menu;
		}
		
		ProductServiceImpl service=new ProductServiceImpl();
		Product product=service.getProduct(prodNo);
		
		/* if(ck==null){
		ck.setMaxAge(60*60*24);
		//ck.setPath("/"); redirect�϶� ���. �н��Ҷ� ��� ���� ����
		response.addCookie(ck); }
		else if(ck!=null){
			ck = new Cookie("history",",");
			response.addCookie(ck);
		} */
		
		String history = "";// string�� �����Ҷ� null���� ���� ���� ex) null1000,1002�׷��� ""�������� �־��ֱ�
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length > 0) { // ��Ű�� �����ϸ�
			for (int i = 0; i < cookies.length; i++) { //��Ű�� ���̸�ŭ for���� �����ڴ�. = ��Ű�� ���� �о���ڴ�.
				Cookie cookie = cookies[i]; // �ش��ϴ� ��Ű �ϳ��ϳ��� �����ϰڴ�.
				if (cookie.getName().equals("history")) { // ���� ��Ű�� �̸��� �����Դµ� ��Ű�� �̸��� history��� ��Ű�� key���� history�� ����
					//history = cookie.getValue(); // �����丮��� ��Ű�� Ű���� ������ ��. ���������� �����̵ȴٸ�
					history = cookie.getValue() + ","; //1000,10001,
				
				}
			}
		}
		//Cookie ck = new Cookie("history",);
		Cookie ck = new Cookie("history",history+Integer.toString(product.getProdNo()));
		//1000,10001,1002,11,11
		response.addCookie(ck);

		
		
		HttpSession session = request.getSession();
		session.setAttribute("product", product);
		
		
		
		
		
		
		
		return "forward:/product/getProduct.jsp";
	}
}