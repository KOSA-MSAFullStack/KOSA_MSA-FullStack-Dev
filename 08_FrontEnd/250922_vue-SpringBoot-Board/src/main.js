import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
// CSS 파일 임포트
import './assets/common.css'
// axios 임포트
import axios from 'axios'


// Vue 애플리케이션 인스턴스를 생성
const app = createApp(App)


// 전역 변수로 설정하여 컴포넌트에서 this.$axios를 호출할 수 있게 함
app.config.globalProperties.$axios = axios
// API 서버 URL을 전역 변수로 설정
app.config.globalProperties.$serverUrl = '//localhost:8081'


// 라우터를 애플리케이션에 사용하고, 애플리케이션을 '#app' 요소에 마운트
app.use(router).mount('#app')



