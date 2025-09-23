<template>
    <!-- 게시판 상세 내용을 감싸는 최상위 div 요소 -->
    <div class="board-detail">
      <!-- 공통 버튼 영역 -->
      <div class="common-buttons">
        <!-- 클릭 시 fnUpdate 메서드를 호출하는 수정 버튼 -->
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnUpdate">수정</button>&nbsp;
        <!-- 클릭 시 fnDelete 메서드를 호출하는 삭제 버튼 -->
        <button type="button" class="w3-button w3-round w3-red" v-on:click="fnDelete">삭제</button>&nbsp;
        <!-- 클릭 시 fnList 메서드를 호출하는 목록 버튼 -->
        <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
      </div>
      <!-- 게시판 내용 영역 -->
      <div class="board-contents">
        <h3>{{ title }}</h3>
        <div>
          <strong class="w3-large">{{ author }}</strong>
          <br>
          <span>{{ created_at }}</span>
        </div>
      </div>
      <div class="board-contents">
        <span>{{ contents }}</span>
      </div>
      <!-- 공통 버튼 영역 -->
      <div class="common-buttons">
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnUpdate">수정</button>&nbsp;
        <button type="button" class="w3-button w3-round w3-red" v-on:click="fnDelete">삭제</button>&nbsp;
        <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
      </div>
    </div>
  </template>
 
  <script>
  export default {
    // 데이터 속성을 정의하는 함수
    data() {
      return {
        requestBody: this.$route.query, // 라우트 쿼리 데이터를 저장
        idx: this.$route.query.idx, // 라우트 쿼리에서 idx 값을 추출
        title: '', // 게시물 제목
        author: '', // 작성자
        contents: '', // 게시물 내용
        created_at: '' // 작성일시
      }
    },
    // 컴포넌트가 마운트될 때 호출되는 훅
    mounted() {
      this.fnGetView() // fnGetView 메서드 호출
    },
    methods: {
      // 게시물 데이터를 가져오는 메서드
      fnGetView() {
        this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
          params: this.requestBody
        }).then((res) => {
          // 서버에서 받은 데이터로 속성 값 설정
          this.title = res.data.title
          this.author = res.data.author
          this.contents = res.data.contents
          this.created_at = res.data.created_at
        }).catch((err) => {
          // 네트워크 오류 처리
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      },
      // 목록으로 이동하는 메서드
      fnList() {
        delete this.requestBody.idx // idx 값을 삭제
        this.$router.push({
          path: './list',
          query: this.requestBody
        })
      },
      // 수정 페이지로 이동하는 메서드
      fnUpdate() {
        this.$router.push({
          path: './write',
          query: this.requestBody
        })
      },
      // 게시물을 삭제하는 메서드
      fnDelete() {
        if (!confirm("삭제하시겠습니까?")) return // 삭제 확인
 
        this.$axios.delete(this.$serverUrl + '/board/' + this.idx, {})
          .then(() => {
            alert('삭제되었습니다.')
            this.fnList() // 목록으로 이동
          }).catch((err) => {
            console.log(err)
          })
      }
    }
  }
  </script>
 
  <style scoped>
    /* 스타일 영역 (현재 비어 있음) */
  </style>



