package org.zerock.club.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
   private OAuth2User oAuth2User;

   public CustomOAuth2User(OAuth2User oAuth2User) {
       this.oAuth2User = oAuth2User;
   }

   @Override
   public Map<String, Object> getAttributes() {
       return oAuth2User.getAttributes();
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       // 여기에서 사용자에게 ROLE_USER 권한을 부여합니다.
       return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
   }

   @Override
   public String getName() {
       return oAuth2User.getName();
   }
}
