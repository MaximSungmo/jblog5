package com.cafe24.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.repository.BlogDAO;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PageVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/jblog-uploads";
//	private static final String PREFIX_URL = "/jblog-uploads/";

	@Autowired
	private BlogDAO blogDAO;

	// 관리자 카테고리 등록
	public boolean addCategory(CategoryVo categoryVo) {
		categoryVo.setName(categoryVo.getName().replaceAll("(?i)<script", "&lt;script"));
		categoryVo.setDescription((categoryVo.getDescription().replaceAll("(?i)<script", "&lt;script")));
		return blogDAO.insertCategory(categoryVo);
	}

	// 카테고리 리스트 뽑기
	public List<CategoryVo> getCategoryList(String id) {
		return blogDAO.getCategoryList(id);
	}

	// 게시물 리스트 뽑기
	public List<PostVo> getPostList(String id, Long categoryNo, PageVo pagevo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		map.put("startContentNo", pagevo.getStartContentNo());
		map.put("contentPerPage", pagevo.getContentPerPage());
		return blogDAO.getPostList(map);
	}

	// 게시물 확인하기
	public PostVo getPost(String id, Long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("postNo", postNo);
		return blogDAO.getPost(map);
	}

	// 메인블로그 정보 최신으로 가져오기
	public BlogVo getBlog(String id) {
		return blogDAO.getBlog(id);
	}

	// 카테고리 접속 시 첫 게시글 보여주기
	public Long getFirstPostByCategory(Long categoryNo) {
		Long count = blogDAO.getFirstPostNoByCategory(categoryNo);
		System.out.println(count);
		return count;
	}

	// Id로 접속 시 첫 카테고리 연결하기
	public Long getFirstCategoryNoById(String id) {

		return blogDAO.getFirstCategoryNoById(id);
	}

	// 게시글 등록
	public boolean writePost(PostVo postVo) {

		postVo.setTitle(postVo.getTitle().replaceAll("(?i)<script", "&lt;script"));
		postVo.setContent(postVo.getContent().replaceAll("(?i)<script", "&lt;script"));
		return blogDAO.writePost(postVo);
	}

	// 블로그정보 업데이트
	public boolean updateBlog(BlogVo blogVo) {

		blogVo.setTitle((blogVo.getTitle().replaceAll("(?i)<script", "&lt;script")));
		return blogDAO.updateBlog(blogVo);
	}

	public boolean updateBlogTitle(BlogVo blogVo) {
		blogVo.setTitle((blogVo.getTitle().replaceAll("(?i)<script", "&lt;script")));
		return blogDAO.updateBlogTitle(blogVo);
	}

	// 카테고리 삭제
	public boolean deleteCategory(Map<String, Long> map) {
		long no = map.get("no");
		boolean updatePostCategory = blogDAO.updatePostCategory(map);
		boolean deleteCategory = blogDAO.deleteCategory(no);
		boolean result = deleteCategory && updatePostCategory;
		return result;
	}

	public int getTotalContentCount(Long categoryNo) {
		return blogDAO.totalContentCount(categoryNo);
	}

	/*
	 * 파일업로드
	 */
	public String restore(MultipartFile multipartFile) {
		String url = null;
		try {
			// 파일 정보
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = multipartFile.getSize();

			// 서버에서 저장 할 파일 이름
			String saveFileName = genSaveFileName(extName);

			writeFile(multipartFile, saveFileName);
			url = saveFileName;
		} catch (IOException e) {
			// 원래라면 RuntimeException 을 상속받은 예외가 처리되어야 하지만
			// 편의상 RuntimeException을 던진다.
			// throw new FileUploadException();
			throw new RuntimeException(e);
		}
		
		return url;
	}

	// 현재 시간을 기준으로 파일 이름 생성
	private String genSaveFileName(String extName) {
		String fileName = "";

		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += extName;

		return fileName;
	}

	// 파일을 실제로 write 하는 메서드
	private boolean writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		boolean result = false;

		byte[] data = multipartFile.getBytes();

		File folder = new File(SAVE_PATH);
		if (!folder.exists()) {
			try {
				folder.mkdir(); // 폴더 생성합니다.
				System.out.println("폴더가 생성되었습니다.");
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("이미 폴더가 생성되어 있습니다.");
		}

		FileOutputStream fos = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
		fos.write(data);
		fos.close();
		return result;
	}
}
