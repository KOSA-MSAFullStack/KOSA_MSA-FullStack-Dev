package net.developia.dto;

import lombok.Getter;
import lombok.ToString;

// 페이징 처리에 필요한 정보들을 계산하고 저장하는 클래스
@Getter
@ToString
public class PageDTO {

	// 화면에 보여질 시작 페이지 번호
	private int startPage;
	// 화면에 보여질 마지막 페이지 번호
	private int endPage;

	// 전체 게시물 수
	private int total;
	// 페이징 기준 정보
	private Criteria cri;

	// 페이징 정보를 초기화하는 생성자
	// * @param cri 페이징 기준 정보
	// * @param total 전체 게시물 수
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

	}
}
