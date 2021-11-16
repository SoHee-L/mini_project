package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp_Product {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp_Product.getProductTest_Codehaus();

//		System.out.println("\n====================================\n");
//		// 2.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp_Product.addProductTest_Codehaus();
		

//		System.out.println("\n====================================\n");
//		// 2.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp_Product.updateProductTest_Codehaus();				
		

//		System.out.println("\n====================================\n");
//		// 3.2 Http Get ��� Request : CodeHaus lib ���
		RestHttpClientApp_Product.getProductListTest_Codehaus();		
		
	}
	
	
//================================================================//
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/getProduct/10011";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//Product product01 = new Product();
		//product01.setProdNo(10011);
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
//================================================================//	
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void addProductTest_Codehaus() throws Exception{
	
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/product/json/addProduct";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		Product product01 =  new Product();
		product01.setProdNo(10013);
		product01.setProdName("�޺���");
		product01.setPrice(10000);
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(product01);

		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}	
	
		
//==============================================================
		
	//2.2 Http Protocol POST ��� Request : FromData���� 
		//==> JsonSimple + codehaus 3rd party lib ���
		public static void updateProductTest_Codehaus() throws Exception{
		
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:8080/product/json/updateProduct/10013";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
			//[ ��� 3 : codehaus ���]
			
			Product product01 =  new Product();
		
			product01.setProdNo(10013);
			product01.setProdName("�޺���");
			product01.setPrice(5000);
			product01.setProdDetail("�޺������̾�Ʈ");
			product01.setFileName("���� ��ŭ�ϴ�g......");
			product01.setManuDate("20211101");
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(product01);

			HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
			
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			//String serverData = br.readLine();
			//System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
			 System.out.println(product);
		}	
		
	//===========================================================	
		//2.2 Http Protocol POST ��� Request : FromData���� 
				//==> JsonSimple + codehaus 3rd party lib ���
				public static void getProductListTest_Codehaus() throws Exception{
				
					// HttpClient : Http Protocol �� client �߻�ȭ 
					HttpClient httpClient = new DefaultHttpClient();
					
					String url = "http://127.0.0.1:8080/product/json/getProductList/";
					HttpPost httpPost = new HttpPost(url);
					httpPost.setHeader("Accept", "application/json");
					httpPost.setHeader("Content-Type", "application/json");
					
					//[ ��� 3 : codehaus ���]
					
					JSONObject json = new JSONObject();
					json.put("currentPage", 1);
					json.put("pageSize", 3);
					HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

					httpPost.setEntity(httpEntity01);
					HttpResponse httpResponse = httpClient.execute(httpPost);
					
					//==> Response Ȯ��
					System.out.println(httpResponse);
					System.out.println();

					//==> Response �� entity(DATA) Ȯ��
					HttpEntity httpEntity = httpResponse.getEntity();
					
					//==> InputStream ����
					InputStream is = httpEntity.getContent();
					BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
					
					//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
					String serverData = br.readLine();
					System.out.println(serverData);
					//������� parsing
					
					//==> �����б�(JSON Value Ȯ��)
					JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
					System.out.println(jsonobj);
					
					ObjectMapper objectMapper = new ObjectMapper();
					 List list = objectMapper.readValue(jsonobj.get("list").toString(), ArrayList.class); //class Ÿ������ arraylist�� �޾ƿ��ڴٶ�� ��
					//Ű���� ����Ȱ��� ���� ������ ����. return���� map�ε� map�� Ű��� ���¿��� Ű���� �����°�
					 System.out.println(list);
				
				}
//========================================================		
	}