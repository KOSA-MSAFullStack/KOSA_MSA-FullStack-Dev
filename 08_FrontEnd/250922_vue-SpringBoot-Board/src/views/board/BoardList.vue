<template>
    <div class="board-list">
      <div class="common-buttons">
        <!-- 클릭 시 fnWrite 메서드를 호출하는 등록 버튼 -->
        <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnWrite">등록</button>
      </div>
      <table class="w3-table-all">
        <thead>
          <tr>
            <th>No</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일시</th>
          </tr>
        </thead>
        <tbody>
          <!-- 게시판 부분: list 배열을 반복하여 행을 생성 -->
          <tr v-for="(row, idx) in list" :key="idx">
            <td>{{ row.idx }}</td>
            <!-- 제목 클릭 시 fnView 메서드를 호출하여 상세 글로 이동 -->
            <td><a v-on:click="fnView(`${row.idx}`)">{{ row.title }}</a></td>
            <td>{{ row.author }}</td>
            <td>{{ row.created_at }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
 
  <script>
  export default {
    // 데이터 속성을 정의하는 함수
    data() {
      return {
        requestBody: {}, // 리스트 페이지 데이터 전송 객체
        list: {}, // 리스트 데이터
        no: '', // 게시판 숫자 처리
      }
    },
    // 컴포넌트가 마운트될 때 호출되는 훅
    mounted() {
      this.fnGetList() // fnGetList 메서드 호출
    },
    methods: {
      // REST 서버에서 데이터 가져오기
      fnGetList() {
        // requestBody 객체에 요청 데이터를 설정
        this.requestBody = {
          keyword: this.keyword,
          page: this.page,
          size: this.size
        }
        // 서버에 GET 요청을 보내 데이터를 가져옴
        this.$axios.get(this.$serverUrl + "/board/list", {
          params: this.requestBody,
          headers: {}
        }).then((res) => {
          // 서버에서 받은 데이터를 리스트에 할당
          this.list = res.data
        }).catch((err) => {
          // 네트워크 오류 처리
          if (err.message.indexOf('Network Error') > -1) {
            alert('네트워크가 원활하지 않습니다.\n잠시 후 다시 시도해주세요.')
          }
        })
      }, // end fnGetList
 
      // 상세 글 이동 라우터
      fnView(idx) {
        this.requestBody.idx = idx
        this.$router.push({
          path: './detail',
          query: this.requestBody
        })
      },
 
      // 글 등록 페이지로 이동
      fnWrite() {
        this.$router.push({
          path: './write'
        })
      },
 
      // 페이지 이동
      fnPage(n) {
        if (this.page !== n) {
          this.page = n
          this.fnGetList() // 새로운 페이지 번호로 리스트를 다시 가져옴
        }
      }
    } // end methods
  } // end export
  </script>  



