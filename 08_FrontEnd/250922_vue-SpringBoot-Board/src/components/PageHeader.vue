<!-- PageHeader.vue -->
<template>
    <!-- 템플릿 섹션에는 Vue 컴포넌트의 HTML 구조가 포함됩니다 -->
    <header>
        <div id="nav">
            <!-- router-link를 사용하여 다른 페이지로 이동할 수 있는 네비게이션 링크 생성 -->
            <router-link to="/">Home</router-link> |
            <router-link to="/about">About</router-link> |
            <router-link to="/board/list">게시판</router-link>
        </div>
    </header>
    <!-- 수평선(hr)을 사용하여 헤더와 본문 사이에 구분선을 추가 -->
    <hr/>
</template>


<script>
export default {
    // 이 컴포넌트에는 현재 별도의 데이터나 메소드가 없습니다
}
</script>


<style scoped>
    /* 현재는 스타일이 정의되지 않았습니다 */
</style>

