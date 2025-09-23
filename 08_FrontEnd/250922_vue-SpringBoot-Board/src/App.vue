<template>
  <!-- 헤더 컴포넌트 -->
  <PageHeader/>
  <!-- 페이지 이동이 표시될 곳 -->
  <router-view/>
  <!-- 푸터 컴포넌트 -->
  <PageFooter/>
</template>


<script>
// PageHeader와 PageFooter 컴포넌트를 가져옴
import PageHeader from '@/components/PageHeader'
import PageFooter from '@/components/PageFooter'


export default {
  name: 'App', // 컴포넌트 이름
  components: {
    // 가져온 컴포넌트를 등록
    PageFooter,
    PageHeader
  }
}
</script>


<style>
/* #app ID 스타일 정의 */
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased; /* 웹킷 폰트 스무딩 */
  -moz-osx-font-smoothing: grayscale; /* 모질라 OS X 폰트 스무딩 */
  text-align: center; /* 텍스트 가운데 정렬 */
  color: #2c3e50; /* 텍스트 색상 */
}


/* #nav ID 스타일 정의 */
#nav {
  padding: 30px; /* 내부 여백 설정 */
}


/* #nav a 요소 스타일 정의 */
#nav a {
  font-weight: bold; /* 텍스트 굵게 */
  color: #2c3e50; /* 텍스트 색상 */
}


/* 활성화된 라우터 링크 스타일 정의 */
#nav a.router-link-exact-active {
  color: #42b983; /* 텍스트 색상 */
}
</style>

