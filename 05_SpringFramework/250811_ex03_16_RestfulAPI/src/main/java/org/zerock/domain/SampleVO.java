package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //비어있는 Constructor 만들려고
@AllArgsConstructor //모든 field 사용하는 Constructor 생성
public class SampleVO {
	
	private Integer mno; //Null  값 처리 때문에
	private String firstName;
	private String lastName;	

}//end class