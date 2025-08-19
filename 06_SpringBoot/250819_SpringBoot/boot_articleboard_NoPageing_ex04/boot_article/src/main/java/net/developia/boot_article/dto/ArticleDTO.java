package net.developia.boot_article.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO  {	
	private long no;
	private String name;
	private String title;
	private String content;
	private Date regdate;
	private int readcount;
	private String password;

	public void setPassword(String password) {
		this.password = DigestUtils.sha512Hex(password);
	}//setP...
}//end class




