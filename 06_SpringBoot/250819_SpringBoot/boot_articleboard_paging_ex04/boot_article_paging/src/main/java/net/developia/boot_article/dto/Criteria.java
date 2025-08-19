package net.developia.boot_article.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {	
	
	private int pageNum;	
	private int amount;
	
	public Criteria() {
		this(1,10);
	}//end cri...	

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}//end Cri..	

}//end class
