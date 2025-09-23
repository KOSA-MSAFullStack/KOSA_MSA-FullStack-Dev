<!-- PageHome.vue -->
<template>
    <!-- 템플릿 섹션에는 Vue 컴포넌트의 HTML 구조가 포함됩니다 -->
    <div class="home">
        <!-- Vue 로고 이미지를 표시 -->
        <img alt="Vue logo" src="../assets/logo.png">
        <!-- HelloWorld 컴포넌트를 사용하고 msg 속성에 값을 전달 -->
        <HelloWorld msg="Welcome to Your Vue.js App"/>
    </div>
</template>


<script>
// @는 /src 경로의 별칭입니다
import HelloWorld from '@/components/HelloWorld.vue'


export default {
    name: 'PageHome', // 컴포넌트 이름
    components: {
        // HelloWorld 컴포넌트를 등록
        HelloWorld
    }
}
</script>

