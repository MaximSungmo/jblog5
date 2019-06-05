package com.cafe24.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.PageVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.security.AuthAdmin;



@Controller
@RequestMapping(value = { "/{id:(?!assets).*}" })
public class BlogController {

	@Autowired

	private BlogService blogService;

	@RequestMapping(value = { "", "/{path1}", "/{path1}/{path2}" }, method = RequestMethod.GET)
	public String blogMain(@PathVariable String id, 
			@PathVariable Optional<Long> path1,
			@PathVariable Optional<Long> path2, 
			@RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
			Model model) {
		PageVo pagevo;
		Long categoryNo = path1.isPresent() ? path1.get() : blogService.getFirstCategoryNoById(id);
		Long postNo = path2.isPresent() ? path2.get() : blogService.getFirstPostByCategory(categoryNo);
		final int CONTENT_PER_PAGE = 5;
		if(postNo == null) {
			pagevo = new PageVo(pageNo, CONTENT_PER_PAGE, 1);
		}else {
			int totalCount = blogService.getTotalContentCount(categoryNo);
			pagevo = new PageVo(pageNo, CONTENT_PER_PAGE, totalCount);
		}
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		List<PostVo> postList = blogService.getPostList(id, categoryNo, pagevo);
		BlogVo blogvo = blogService.getBlog(id);
		PostVo postvo = blogService.getPost(id, postNo);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pagevo", pagevo);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("postList", postList);
		model.addAttribute("blogvo", blogvo);
		model.addAttribute("postvo", postvo);
		model.addAttribute("id", id);
		model.addAttribute("categoryNo", categoryNo);
		model.addAttribute("postNo", postNo);	
		return "blog/blog-main";
	}

	@AuthAdmin
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model) {
		BlogVo blogvo = blogService.getBlog(id);
		model.addAttribute("blogvo", blogvo);
		model.addAttribute("id", id);
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basicform(@PathVariable String id, 
			@RequestParam(value = "logo_file") MultipartFile logo_file,
			@RequestParam(value = "title") String title,
			Model model) {
		BlogVo blogvo= new BlogVo();
		blogvo.setId(id);
		blogvo.setTitle(title);

		if(logo_file.isEmpty()) {
			blogService.updateBlogTitle(blogvo);
		}else {
			String url = blogService.restore(logo_file);
			blogvo.setLogo(url);
			blogService.updateBlog(blogvo);
			model.addAttribute("blogvo", blogvo);
		}
		return "redirect:/" + id + "/admin/basic";
		
	}

	@AuthAdmin
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, @ModelAttribute("categoryvo") CategoryVo categoryVo,
			Model model) {

		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("id", id);

		return "blog/blog-admin-category";
	}

	@AuthAdmin
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String addCategory(@PathVariable("id") String id, @ModelAttribute CategoryVo categoryVo, Model model) {
		System.out.println("addCategory Mehod :" + categoryVo);
		blogService.addCategory(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}

	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no, Model model) {
		
		long categoryNo = blogService.getFirstCategoryNoById(id);
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("categoryNo", categoryNo);
		map.put("no", no);
		
		blogService.deleteCategory(map);
		return "redirect:/" + id + "/admin/category";
	}

	@AuthAdmin
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("id", id);
		return "blog/blog-admin-write";
	}

	@AuthAdmin
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, @ModelAttribute("postvo") @Valid PostVo postVo, BindingResult result, Model model) {
		
		if( result.hasErrors() ) {
			// 에러를 List로 저장
			List<ObjectError> list = result.getAllErrors();
			for( ObjectError error : list ) {
				System.out.println(error);
			}
			return "blog/blog-admin-write";
		}
		
		System.out.println(postVo);
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);

		blogService.writePost(postVo);
		model.addAttribute("id", id);
		return "redirect:write";
	}

}
