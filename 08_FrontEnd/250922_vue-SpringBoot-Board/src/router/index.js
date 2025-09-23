import { createRouter, createWebHistory } from 'vue-router'
import PageHome from '@/views/PageHome.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from '@/views/board/BoardDetail.vue'
import BoardWrite from '@/views/board/BoardWrite.vue'


// 라우트 설정 배열
const routes = [
  {
    path: '/',    // 루트 경로
    name: 'PageHome',    // 라우트 이름
    component: PageHome  // 컴포넌트 지정
  },
  {
    path: '/about',    // /about 경로
    name: 'About',  
    // 동적 임포트로 컴포넌트를 로드 (웹팩 청크 이름을 "about"으로 설정)
    component: () => import(/* webpackChunkName: "about" */ '../views/PageAbout.vue')
  },
  {
    path: '/board/list',    // /board/list 경로
    name: 'BoardList',    // 라우트 이름
    component: BoardList  // 컴포넌트 지정
  },
  {
    path: '/board/detail',    // /board/detail 경로
    name: 'BoardDetail',    // 라우트 이름
    component: BoardDetail  // 컴포넌트 지정
  },
  {
    path: '/board/write',    // /board/write 경로
    name: 'BoardWrite',    // 라우트 이름
    component: BoardWrite  // 컴포넌트 지정
  }
]


// 라우터 생성
const router = createRouter({
  // HTML5 히스토리 모드를 사용하여 브라우저 히스토리를 관리
  history: createWebHistory(process.env.BASE_URL),
  routes // 라우트 설정 배열을 라우터에 전달
})


// 라우터를 기본 내보내기로 내보냄
export default router

