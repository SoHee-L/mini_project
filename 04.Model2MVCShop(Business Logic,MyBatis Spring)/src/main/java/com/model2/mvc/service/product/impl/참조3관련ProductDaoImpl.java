package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;


//==> ȸ������ DAO CRUD ����
//@Repository("userDaoImpl")
public class ����3����ProductDaoImpl implements ProductDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ����3����ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addProduct(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProduct", product);
	}

	public Product getProduct(String prodNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	public void updateProduct(Product prodNo) throws Exception {
		sqlSession.update("ProductMapper.updateProduct", prodNo);
	}

	public List<Product> getProductList(Search search) throws Exception {
		//return sqlSession.selectList("UserMapper.getUserList", search);
		
		// �پ��� parameterType ��� 1
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchCondition", search.getSearchCondition() );
		map.put("searchKeyword",  search.getSearchKeyword() );
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		return sqlSession.selectList("ProductMapper.getProductList", map);
		
		// �پ��� parameterType ��� 2
		//Map<Stirng, Object> map = new HashMap<String, Object>();
		//map("object",search);
		//return sqlSession.selectList("UserMapper.getUserList", map);
	}

	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}