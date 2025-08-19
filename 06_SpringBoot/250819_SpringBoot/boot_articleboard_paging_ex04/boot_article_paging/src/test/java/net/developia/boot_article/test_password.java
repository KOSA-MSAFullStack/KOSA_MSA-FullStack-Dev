package net.developia.boot_article;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test_password {
	
	@Test
	public void passwordtest() throws Exception {
		String pw="1111";
		String pw_encryption=DigestUtils.sha512Hex(pw);
		//String pw_decryption = 
		
		
	}

}//end class
