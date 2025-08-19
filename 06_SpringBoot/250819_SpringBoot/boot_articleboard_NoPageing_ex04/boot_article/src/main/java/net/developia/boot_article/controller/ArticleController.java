package net.developia.boot_article.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.log4j.Log4j2;
import net.developia.boot_article.dto.ArticleDTO;
import net.developia.boot_article.service.ArticleService;

@Controller
@Log4j2
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@GetMapping("insert")
	public String insert() {
		return "article/insert";
	}// end insert

	@PostMapping("insert")
	public String insert(@ModelAttribute ArticleDTO articleDTO, Model model) {
		log.info(articleDTO.toString());

		try {
			articleService.insertArticle(articleDTO);
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg", "게시물 등록 오류입니다.");
			model.addAttribute("url", "javascript:history.back();");
			return "article/result";
		} // end try
	}// end insert..

	@GetMapping("list")
	public String list(Model model) throws Exception {
		try {
			List<ArticleDTO> list = articleService.getArticleList();
			model.addAttribute("list", list);
			return "article/list";
		} catch (Exception e) {
			throw e;
		} // end try
	}// end list

	@GetMapping("detail")
	public String detail(@RequestParam(defaultValue = "0") long no, Model model) throws Exception {
		try {
			ArticleDTO articleDTO = articleService.getDetail(no);
			model.addAttribute("articleDTO", articleDTO);
			return "article/detail";
		} catch (Exception e) {
			model.addAttribute("msg", "접근할 수 없는 게시물이거나 시스템 오류입니다.");
			model.addAttribute("url", "list");
			return "article/result";
		} // end try
	}// end detell..

	@GetMapping("delete")
	public String delete(@RequestParam long no, Model model) {
		try {
			model.addAttribute("no", no);
			return "article/delete";
		} catch (Exception e) {
			throw e;
		} // end try
	}// end delete

	@PostMapping("delete")
	public String delete(@ModelAttribute ArticleDTO articleDTO, Model model) {
		log.info(articleDTO.toString());
		try {
			articleService.deleteArticle(articleDTO);
			model.addAttribute("msg", articleDTO.getNo() + "번 글이 삭제 되었습니다.");
			model.addAttribute("url", "list");
			return "article/result";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "article/result";
		} // end try
	}// end delete..

	@GetMapping("update")
	public String update(@RequestParam long no, Model model) {
		try {
			ArticleDTO articleDTO = articleService.getArticle(no);
			model.addAttribute("articleDTO", articleDTO);
			return "article/update";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "article/result";
		} // end try
	}// end update

	@PostMapping("update")
	public String update(@ModelAttribute ArticleDTO articleDTO, Model model) {
		try {
			articleService.updateArticle(articleDTO);
			return "redirect:detail?no=" + articleDTO.getNo();
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "article/result";
		}//end try
	}//end update

}// end class

