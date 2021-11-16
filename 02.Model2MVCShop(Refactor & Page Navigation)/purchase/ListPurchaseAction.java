package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		
		int page=1;
		if(request.getParameter("page") != null)
			page=Integer.parseInt(request.getParameter("page"));
		
		search.setPage(page);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		search.setPageUnit(Integer.parseInt(pageUnit));
		System.out.println("serchVO출력~~"+search);
		//세션에 있는거 불러와서 넣어주기
		
	
		HttpSession session = request.getSession();
		User user = new User();
		user= (User) session.getAttribute("user");
		System.out.println("::listpurchaseAction : user~~"+user);
		String buyerId = user.getUserId();
		System.out.println("listpurchase.do 받은거"+buyerId);
		
		PurchaseService service = new PurchaseServiceImpl();
		Map<String,Object> map=service.getPurchaseList(search, user.getUserId());
		
		request.setAttribute("map", map);
		request.setAttribute("search", search);
		
		//return "forward:/purchase/listPurchase.jsp?menu="+request.getParameter("menu");
		return "forward:/purchase/listPurchase.jsp";
	}
}