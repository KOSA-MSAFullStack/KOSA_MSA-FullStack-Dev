package net.developia.boot_article.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTD {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	
	public PageDTD( Criteria cri,int total) {
		
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int) (Math.ceil( cri.getPageNum() /10.0)) * 10;
		
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)  Math.ceil( (total * 1.0) / cri.getAmount() ) ;
		
		//totol을 통한 endPage 재계산
		if ( realEnd < this.endPage) {
			this.endPage = realEnd;
		}//end if
		
		
		//페이지 기능 활성화 여부 저장
		this.prev = (this.startPage > 1);		
		this.next = (this.endPage < realEnd);		
	}//end PageDTD
}//end class
