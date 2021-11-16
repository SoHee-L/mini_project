package com.model2.mvc.web.user;

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
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
//===========================================================	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET ) //제이슨에서 이미 키밸류값을ㄹ 가지고있어서 우리가 따로 설정하지않아도됨
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}
//=========================================================	
	@RequestMapping( value="json/updateUser/{userId}", method=RequestMethod.GET ) 
	public User updateUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/updateUser : GET");
		//Business Logic
		return userService.getUser(userId);
	}
	
	@RequestMapping( value="json/updateUser/{user}", method=RequestMethod.POST ) 
	public User updateUser( @PathVariable User user) throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		
		userService.updateUser(user);
		
		//Business Logic
		return user;
	}
//========================================================
	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
//		return null;
	}
	
	@RequestMapping( value="json/logout", method=RequestMethod.POST )
	public User logout(@RequestBody User user, HttpSession session ) throws Exception{
		
		System.out.println("/user/json/logout : get");
		
		session.invalidate();
		
		return user;
	}
	//=========================================================	
	// value="json/addUser/{user }" u 
		@RequestMapping( value="json/addUser", method=RequestMethod.POST ) 
		public User addUser( @RequestBody User user) throws Exception{
			
			System.out.println("/user/json/addUser : POST");
			
			userService.addUser(user);
			
			//Business Logic
			return userService.getUser(user.getUserId());

		}	
		
		//=========================================================	
		
		@RequestMapping( value="json/checkDuplication", method=RequestMethod.POST )
		public boolean checkDuplication( @RequestBody String userId  ) throws Exception{
			
			System.out.println("/user/checkDuplication : POST");
			//Business Logic
			boolean result=userService.checkDuplication(userId);
			// Model 과 View 연결
			System.out.println("result값 :: = "+result);
			return result;
		}
		
		//=========================================================	
				
		@RequestMapping( value="json/getUserList", method=RequestMethod.POST )
		public Map<String , Object> getUserList( @RequestBody Search search ,  HttpServletRequest request) throws Exception{
			
			System.out.println("/user/listUser :POST");
			
			if(search.getCurrentPage() ==0 ){
				search.setCurrentPage(1);
			}
			search.setPageSize(pageSize);
			
			// Business logic 수행
			Map<String , Object> map=userService.getUserList(search);
			
			Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
			System.out.println(resultPage);
			
			// Model 과 View 연결
			
			return map;
		}
		
		
		
		
		
		
		
}