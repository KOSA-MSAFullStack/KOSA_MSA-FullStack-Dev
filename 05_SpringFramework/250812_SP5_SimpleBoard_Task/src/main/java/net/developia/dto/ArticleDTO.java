package net.developia.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArticleDTO implements Serializable {
	//DTO 작성시 Serializable 은 권장사항
	private static final long serialVersionUID = 1L;
	private long no;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	private int readcount;
	private String password;

}
