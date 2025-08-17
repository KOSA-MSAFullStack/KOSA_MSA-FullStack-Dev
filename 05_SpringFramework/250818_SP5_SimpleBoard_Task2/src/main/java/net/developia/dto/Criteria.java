package net.developia.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 기준 정보를 담는 클래스
@ToString
@Setter
@Getter
public class Criteria {
	// 현재 페이지 번호
	private int pageNum;
	// 한 페이지당 보여줄 게시물 수
	private int amount;
	
	// 기본 생성자. 1페이지, 10개씩 보기로 초기화
	public Criteria() {
		this(1,10);
	}
	
	// 페이지 번호와 수량을 인자로 받는 생성자
	// * @param pageNum 현재 페이지 번호
	// * @param amount 한 페이지당 보여줄 게시물 수
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
