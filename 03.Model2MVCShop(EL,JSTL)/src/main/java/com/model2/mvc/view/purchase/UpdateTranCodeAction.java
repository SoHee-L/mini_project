package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;


//prodNo�� �ʿ��Ѱ� �׻�ǰ������ Ʈ���ڵ� ���� trancode ��ǰ������ ���� ���¸� �����ڵ�. �����ڵ�� ������� a�� �ϵ簡 ���ڷ��ϵ簡.
public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("updateTranCode prodNo"+prodNo);
		String tranCode = request.getParameter("tranCode");
		System.out.println("updateTranCode tranCode"+tranCode);
		
		Purchase purchase=new Purchase();
		Product product = new Product();
		System.out.println("updatetranCode Action1"+purchase);
		/* �ش��ǰ������ ��ǰ��ȣ�� �����ߵ� */
		product.setProdNo(prodNo);
		
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
	
		//purchaseVO.setTranCode(request.getParameter("tranCode"));           
		
	
		PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(purchase);
		
		request.setAttribute("purchase", purchase);
		
		System.out.println("updatetranCode Action2"+purchase);
		if(purchase.getTranCode().equals("003") )	{
			return "forward:/listPurchase.do";
		}
		return "redirect:/listProduct.do?prodNo="+prodNo +"&menu=manage";
		
	}
}