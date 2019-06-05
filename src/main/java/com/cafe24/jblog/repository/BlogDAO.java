package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PostVo;

@Repository
public class BlogDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insertCategory(CategoryVo categoryVo) {
		int count = sqlSession.insert("blog.insertCategory", categoryVo);
		return count == 1;
	}
	public boolean writePost(PostVo postVo) {
		int count = sqlSession.insert("blog.insertPost", postVo);
		return count == 1;
	}
	
	public List<CategoryVo> getCategoryList(String id){
		List<CategoryVo> result = sqlSession.selectList("blog.getCategoryList", id);
		return result;
		
	}
	public List<PostVo> getPostList(Map<String, Object> map){
		List<PostVo> result = sqlSession.selectList("blog.getPostList", map);
		return result;
	}
	public BlogVo getBlog(String id){
		BlogVo result = sqlSession.selectOne("blog.getBlog", id);
		return result;
	}
	public boolean updateBlog(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlog",blogVo) == 1;
	}
	public boolean updateBlogTitle(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlogTitle",blogVo)==1;
	}
	
	
	public PostVo getPost(Map<String, Object> map) {
		PostVo result = sqlSession.selectOne("blog.getPost", map);
		return result;
	}
	
	public Long getFirstPostNoByCategory(Long categoryNo) {
		Long result = sqlSession.selectOne("blog.getFirstPostNoByCategory", categoryNo);
		return result;
	}
	
	public Long getFirstCategoryNoById(String id) {
		Long result = sqlSession.selectOne("getFirstCategoryNoById", id);
		return result;
	}
	
	public boolean updatePostCategory(Map<String, Long> map) {
		return sqlSession.update("blog.updatePostCategoryNo", map)==1;
	}
	

	public boolean deleteCategory(Long no) {
		return 	sqlSession.delete("blog.deleteCategory", no) ==1;
	}
	
	public int totalContentCount(Long category_no) {
		return sqlSession.selectOne("blog.totalContentCount", category_no);
	}
	
}
