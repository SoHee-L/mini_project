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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Search search=new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null)
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
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
		System.out.println("map / listpurchasee Action ::"+map);
		System.out.println("map.get() / listpurchasee Action ::"+map.get("totalCount"));
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListUserAction ::"+resultPage);
		
		//request.setAttribute("map", map);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		//return "forward:/purchase/listPurchase.jsp?menu="+request.getParameter("menu");
		return "forward:/purchase/listPurchase.jsp";
	}
}