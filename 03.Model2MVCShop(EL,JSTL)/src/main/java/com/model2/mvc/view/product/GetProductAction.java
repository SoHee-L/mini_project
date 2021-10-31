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
		//ck.setPath("/"); redirect일때 사용. 패스할때 경로 같이 잡힘
		response.addCookie(ck); }
		else if(ck!=null){
			ck = new Cookie("history",",");
			response.addCookie(ck);
		} */
		
		String history = "";// string을 연산할때 null쓰면 같이 계산됨 ex) null1000,1002그래서 ""공백으로 넣어주기
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length > 0) { // 쿠키가 존재하면
			for (int i = 0; i < cookies.length; i++) { //쿠키가 길이만큼 for문을 돌리겠다. = 쿠키를 전부 읽어오겠다.
				Cookie cookie = cookies[i]; // 해당하는 쿠키 하나하나를 저장하겠다.
				if (cookie.getName().equals("history")) { // 내가 쿠키의 이름을 가져왔는데 쿠키의 이름이 history라면 쿠키의 key값을 history로 저장
					//history = cookie.getValue(); // 히스토리라는 쿠키의 키값을 가진게 들어감. 정상적으로 실행이된다면
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