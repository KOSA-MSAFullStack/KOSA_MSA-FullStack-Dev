// ArticleController.java

package net.developia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;
import net.developia.dto.ArticleDTO;
import net.developia.service.ArticleService;

/**
 * 게시글 관련 웹 요청을 처리하는 Spring MVC 컨트롤러입니다.
 * 게시글 목록 조회, 상세 보기, 등록, 수정, 삭제 기능을 제공합니다.
 */
@Slf4j
@Controller
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	/**
	 * 게시글 등록 폼을 표시합니다.
	 * GET 요청으로 /insert 경로에 접근 시 호출됩니다.
	 *
	 * @return 게시글 등록 폼 뷰 경로 ("article/insert")
	 */
	@GetMapping("insert")
	public String insert() {
		return "article/insert";
	}


	/**
	 * 새로운 게시글을 등록합니다.
	 * POST 요청으로 /insert 경로에 접근 시 호출됩니다.
	 *
	 * @param articleDTO 등록할 게시글 정보를 담고 있는 ArticleDTO 객체
	 * @param model      View로 데이터를 전달하기 위한 Model 객체
	 * @return 등록 성공 시 게시글 목록으로 리다이렉트, 실패 시 에러 메시지와 함께 결과 페이지로 이동
	 */	@PostMapping("insert")
	public String insert(@ModelAttribute ArticleDTO articleDTO, Model model) {
		log.info(articleDTO.toString());

		try {
			articleService.insertArticle(articleDTO);
			// 입력 성공시 게시글 목록 페이지로 리다이렉트
			return "redirect:list";
		} catch (Exception e) {
			model.addAttribute("msg","게시물 등록 오류입니다.");
			model.addAttribute("url","javascript:history.back();");
			return "article/result";
		}//end try
	}//end insert..

	/**
	 * 게시글 목록을 조회하여 표시합니다.
	 * GET 요청으로 /list 경로에 접근 시 호출됩니다.
	 *
	 * @param model View로 데이터를 전달하기 위한 Model 객체
	 * @return 게시글 목록 뷰 경로 ("article/list")
	 * @throws Exception 게시글 목록 조회 중 오류 발생 시
	 */
	@GetMapping("list")
	public String list(Model model) throws Exception {
		try {
			List<ArticleDTO> list = articleService.getArticleList();
			model.addAttribute("list",list);
			return "article/list";
		} catch (Exception e) {
			throw e; // 예외를 다시 던져서 Spring의 예외 처리 메커니즘에 맡김
		}//end try
	}//end list..

	/**
	 * 특정 게시글의 상세 내용을 조회하여 표시합니다.
	 * GET 요청으로 /detail 경로에 접근 시 호출됩니다.
	 *
	 * @param no    조회할 게시글 번호 (기본값 0)
	 * @param model View로 데이터를 전달하기 위한 Model 객체
	 * @return 게시글 상세 뷰 경로 ("article/detail")
	 * @throws Exception 게시글 상세 조회 중 오류 발생 시
	 */
	@GetMapping("detail")
	public String detail(@RequestParam(defaultValue = "0") long no, Model model) throws Exception {
		try {
			ArticleDTO articleDTO = articleService.getDetail(no);
			model.addAttribute("articleDTO", articleDTO);
			return "article/detail";
		} catch (Exception e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "list"); // 오류 발생 시 목록 페이지로 이동
			return "article/result";
		}
	}

	/**
	 * 게시글 삭제 확인 폼을 표시합니다.
	 * GET 요청으로 /delete 경로에 접근 시 호출됩니다.
	 *
	 * @param no    삭제할 게시글 번호
	 * @param model View로 데이터를 전달하기 위한 Model 객체
	 * @return 게시글 삭제 확인 뷰 경로 ("article/delete")
	 */
	@GetMapping("delete")
	public String delete(@RequestParam long no, Model model) {
		try {
			ArticleDTO articleDTO = articleService.getArticle(no);
			model.addAttribute("articleDTO", articleDTO);
			return "article/delete";
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","javascript:history.back();");
			return "article/result";
		}
	}

	/**
	 * 게시글을 삭제합니다.
	 * POST 요청으로 /delete 경로에 접근 시 호출됩니다.
	 *
	 * @param articleDTO 삭제할 게시글 정보를 담고 있는 ArticleDTO 객체 (주로 게시글 번호 포함)
	 * @param model      View로 데이터를 전달하기 위한 Model 객체
	 * @return 삭제 성공 시 결과 메시지와 함께 결과 페이지로 이동
	 */
	@PostMapping("delete")
	public String delete(@ModelAttribute ArticleDTO articleDTO, Model model) {
		log.info(articleDTO.toString());
		try {
			articleService.deleteArticle(articleDTO);
			model.addAttribute("msg",articleDTO.getNo() + "번 글이 삭제 되었습니다.");
			model.addAttribute("url","list");
			return "article/result";
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","javascript:history.back();");
			return "article/result";
		}
	}
	/**
	 * 게시글 수정 폼을 표시합니다.
	 * GET 요청으로 /update 경로에 접근 시 호출됩니다.
	 *
	 * @param no    수정할 게시글 번호
	 * @param model View로 데이터를 전달하기 위한 Model 객체
	 * @return 		게시글 수정 폼 뷰 경로 ("article/update")
	 */
	@GetMapping("update")
	public String update(@RequestParam long no, Model model) {
		try {
			ArticleDTO articleDTO = articleService.getArticle(no);
			model.addAttribute("articleDTO", articleDTO);
			return "article/update";
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","javascript:history.back();");
			return "article/result";
		}
	}

	/**
	 * 게시글을 수정합니다.
	 * POST 요청으로 /update 경로에 접근 시 호출됩니다.
	 *
	 * @param articleDTO 수정할 게시글 정보를 담고 있는 ArticleDTO 객체
	 * @param model      View로 데이터를 전달하기 위한 Model 객체
	 * @return 			 수정 성공 시 해당 게시글 상세 페이지로 리다이렉트
	 */
	@PostMapping("update")
	public String update(@ModelAttribute ArticleDTO articleDTO, Model model) {
		try {
			articleService.updateArticle(articleDTO);
			return "redirect:detail?no=" + articleDTO.getNo();
		} catch (Exception e) {
			model.addAttribute("msg",e.getMessage());
			model.addAttribute("url","javascript:history.back();");
			return "article/result";
		}
	}
}