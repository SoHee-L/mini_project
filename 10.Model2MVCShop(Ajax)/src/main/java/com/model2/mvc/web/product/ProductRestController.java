package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


//==> ȸ������ RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method ���� ����
		
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
//===========================================================	


	//@RequestMapping("/getProduct.do")
		@RequestMapping(value = "json/getProduct/{prodNo}", method=RequestMethod.GET)
		public Product getProduct( @PathVariable int prodNo) throws Exception {
			
			System.out.println("/product/updateProduct : GET");
		
			//Business Logic
			Product product = productService.getProduct(prodNo);
			
			// Model �� View ����

			
			//String menu =request.getParameter("menu");
			//if(menu.equals("manage")) {
			//	return "forward:updateProduct";	//���� ������ ������ �̸������� ��ߵȴ�.
				
			//}
			//System.out.println("manage"+menu);
				return product;
			
		}
		
//====================================================

		//@RequestMapping("/addProduct.do") 
		@RequestMapping(value = "json/addProduct", method=RequestMethod.POST)
		public Product addProduct( @RequestBody Product product ) throws Exception {
			
			System.out.println("/product/addProduct : POST");
			//Business Logic
			productService.addProduct(product);
			
			return productService.getProduct(product.getProdNo());
		}
		//====================================================
		@RequestMapping(value = "json/updateProduct/{prodNo}", method=RequestMethod.GET)
		public Product updateProduct( @PathVariable int prodNo) throws Exception {
			
			System.out.println("/product/updateProduct : GET");
		
			//Business Logic
			
				return  productService.getProduct(prodNo);
			
		}
		
		@RequestMapping(value="json/updateProduct/{product}", method=RequestMethod.POST)
		public Product updateProduct( @RequestBody Product product) throws Exception{

			System.out.println("/updateProduct : Post");
			System.out.println("product��111::"+product);
			productService.updateProduct(product);
			
			System.out.println("prodno ��222::"+product.getProdNo());
			return productService.getProduct(product.getProdNo());
		}
	//==========================================================
		
		//@RequestMapping("/listProduct.do")
		@RequestMapping( value="json/getProductList", method=RequestMethod.POST )
		public Map<String , Object> getProductList( @RequestBody Search search ,  HttpServletRequest request) throws Exception{
		
			System.out.println("/product/getProductList :POST");
			
			if(search.getCurrentPage() ==0 ){
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			// Business logic ����
			Map<String , Object> map=productService.getProductList(search);
			
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			System.out.println(resultPage);
			
			// Model �� View ����
			
			return map;
		}
		
}