package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

//prodNo�� �ʿ��Ѱ� �׻�ǰ������ Ʈ���ڵ� ���� trancode ��ǰ������ ���� ���¸� �����ڵ�. �����ڵ�� ������� a�� �ϵ簡 ���ڷ��ϵ簡.
public class UpdateTranCodeAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int prodNo= Integer.parseInt(request.getParameter("prodNo"));
		System.out.println("updateTranCode prodNo"+prodNo);
		String tranCode = request.getParameter("tranCode");
		System.out.println("updateTranCode tranCode"+tranCode);
		
		PurchaseVO purchaseVO=new PurchaseVO();
		ProductVO productVO = new ProductVO();
		System.out.println("updatetranCode Action1"+purchaseVO);
		/* �ش��ǰ������ ��ǰ��ȣ�� �����ߵ� */
		productVO.setProdNo(prodNo);
		
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode(tranCode);
	
		//purchaseVO.setTranCode(request.getParameter("tranCode"));           
		
	
		PurchaseService service=new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		System.out.println("updatetranCode Action2"+purchaseVO);
		if(purchaseVO.getTranCode().equals("003") )	{
			return "forward:/listPurchase.do";
		}
		return "redirect:/listProduct.do?prodNo="+prodNo +"&menu=manage";
		
	}
}