<template>
  <!-- 게시판 상세 내용을 감싸는 최상위 div 요소 -->
  <div class="board-detail">
    <!-- 공통 버튼 영역 -->
    <div class="common-buttons">
      <!-- 클릭 시 fnSave 메서드를 호출하는 저장 버튼 -->
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
      <!-- 클릭 시 fnList 메서드를 호출하는 목록 버튼 -->
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
    <!-- 게시판 내용 입력 영역 -->
    <div class="board-contents">
      <!-- 제목 입력 필드 -->
      <input type="text" v-model="title" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
      <!-- 작성자 입력 필드 (idx가 undefined일 때만 표시) -->
      <input type="text" v-model="author" class="w3-input w3-border" placeholder="작성자를 입력해주세요." v-if="idx === undefined">
    </div>
    <div class="board-contents">
      <!-- 내용 입력 필드 -->
      <textarea cols="30" rows="10" v-model="contents" class="w3-input w3-border" style="resize: none;"></textarea>
    </div>
    <div class="common-buttons">
      <!-- 다시 저장 버튼과 목록 버튼 -->
      <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
      <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
    </div>
  </div>
</template>


<script>
export default {
  data() { // 변수 생성
    return {
      requestBody: this.$route.query, // 라우트 쿼리 데이터를 저장
      idx: this.$route.query.idx, // 라우트 쿼리에서 idx 값을 추출


      title: '', // 게시물 제목
      author: '', // 작성자
      contents: '', // 게시물 내용
      created_at: '' // 작성일시
    }
  },
  mounted() {
    this.fnGetView() // 컴포넌트가 마운트될 때 fnGetView 메서드 호출
  },
  methods: {
    // 게시물 데이터를 가져오는 메서드
    fnGetView() {
      if (this.idx !== undefined) {
        this.$axios.get(this.$serverUrl + '/board/' + this.idx, {
          params: this.requestBody
        }).then((res) => {
          // 서버에서 받은 데이터로 속성 값 설정
          this.title = res.data.title
          this.author = res.data.author
          this.contents = res.data.contents
          this.created_at = res.data.created_at
        }).catch((err) => {
          console.log(err)
        })
      }
    },
    // 글 리스트로 라우팅
    fnList() {
      delete this.requestBody.idx // idx 값을 삭제
      this.$router.push({
        path: './list',
        query: this.requestBody
      })
    },
    // 상세 글 페이지로 이동하는 메서드
    fnView(idx) {
      this.requestBody.idx = idx
      this.$router.push({
        path: './detail',
        query: this.requestBody
      })
    },
    // 글 저장과 변경 처리
    fnSave() {
      let apiUrl = this.$serverUrl + '/board'
      this.form = {
        "idx": this.idx,
        "title": this.title,
        "contents": this.contents,
        "author": this.author
      }


      if (this.idx === undefined) {
        // INSERT
        this.$axios.post(apiUrl, this.form)
        .then((res) => {
          alert('글이 저장되었습니다.');
          console.log(res.data.idx);
          this.fnList(); // 저장 후 리스트로 이동
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      } else {
        // UPDATE
        this.$axios.patch(apiUrl, this.form)
        .then((res) => {
          alert('글이 저장되었습니다.');
          console.log(res.data.idx);
          this.fnList(); // 저장 후 리스트로 이동
        }).catch((err) => {
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.');
          }
        }) // end catch
      } // end if
    } // end fnSave
  } // end methods
}
</script>


<style scoped>
  /* 스타일 영역 (현재 비어 있음) */
</style>



