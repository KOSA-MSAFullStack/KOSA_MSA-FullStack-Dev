// ############################################################################
// 텍스트 대화와 관련된 코드
// ############################################################################
window.springai = window.springai || {};

// ##### 사용자 질문을 보여줄 엘리먼트를 채팅 패널에 추가하는 함수 #####
springai.addUserQuestion = function (question, chatPanelId) {
  const html = `
    <div class="d-flex justify-content-end m-2">
      <table>
        <tr>
          <td><img src="/image/user.png" width="30"/></td>
          <td><span>${question}</span></td>
        </tr>
      </table>
    </div>
  `;
  document.getElementById(chatPanelId).innerHTML += html;
  springai.scrollToHeight(chatPanelId);
};

// ##### 응답을 보여줄 엘리먼트를 채팅 패널에 추가하는 함수 #####
springai.addAnswerPlaceHolder = function (chatPanelId) {
  //id-를 붙이는 이유: 숫자로 시작하면 CSS 선택자 문법 에러 날 수 있음
  let uuid = "id-" + crypto.randomUUID();
  let html = `
    <div class="d-flex justify-content-start border-bottom m-2">
      <table>
        <tr>
          <td><img src="/image/assistant.png" width="50"/></td>
          <td><span id="${uuid}"></span></td>
        </tr>
      </table>       
    </div>
  `;
  document.getElementById(chatPanelId).innerHTML += html;
  return uuid;
};

// ##### 텍스트 응답을 출력하는 함수 #####
springai.printAnswerText = async function (responseBody, targetId, chatPanelId) {
  springai.printAnswerStreamText(responseBody, targetId, chatPanelId);
}

// ##### 스트리밍 텍스트 응답을 출력하는 함수 #####
springai.printAnswerStreamText = async function (responseBody, targetId, chatPanelId) {
  const targetElement = document.getElementById(targetId);
  const reader = responseBody.getReader();
  const decoder = new TextDecoder("utf-8");
	let content = "";
  while (true) {
    const { value, done } = await reader.read();
    if (done) break;
    let chunk = decoder.decode(value);
		content += chunk;
		if(!springai.isOpenTagIncomplete(chunk)) {
	    targetElement.innerHTML = content;
		}
    springai.scrollToHeight(chatPanelId);
  }
};

// ##### 태그가 정상적으로 <>으로 구성되어 있는지 체크하는 함수 #####
// innerHTML은 <div 같이 텍스트가 추가되면 무시해버리기 때문에 다음 청크의 >까지
// 결합해서 innerHTML에 추가해야 함
springai.isOpenTagIncomplete = function(str) {
  // 1) 문자열 안에 '<'가 하나라도 있는지 확인
  const lastLt = str.lastIndexOf("<");
  if (lastLt === -1) {
    // '<' 자체가 없으면 “시작은 되지 않은 상태”이므로 false
    return false;
  }
  // 2) 문자열 안에 '>'가 하나라도 있는지 확인
  const lastGt = str.lastIndexOf(">");
  if (lastGt === -1) {
    // '>'가 아예 없으면, '<'만 있는 상태 → 무조건 미완성
    return true;
  }
  // 3) “마지막 '<' 인덱스”가 “마지막 '>' 인덱스”보다 크면
  //    그 이후로 닫힘 기호가 없다는 의미 → 미완성
  return lastLt > lastGt;
};

// ##### JSON을 이쁘게 출력하는 함수 #####
springai.printAnswerJson = async function(jsonString, uuid, chatPanelId) {
  const jsonObject = JSON.parse(jsonString);
  // 들여쓰기를 2로 설정해서 이쁘게 문자열로 만듬
  const prettyJson = JSON.stringify(jsonObject, null, 2);
  document.getElementById(uuid).innerHTML = "<pre>" + prettyJson + "</pre>";
  springai.scrollToHeight(chatPanelId);
};

// ##### 채팅 패널의 스크롤을 제일 아래로 내려주는 함수 #####
springai.scrollToHeight = function (chatPanelId) {
  //DOM 업데이트보다 스크롤 이동이 먼저 되면 안되므로
  //스크롤 이동을 0.1초간 딜레이 시킴
  setTimeout(() => {
    const chatPanelElement = document.getElementById(chatPanelId);
    chatPanelElement.scrollTop = chatPanelElement.scrollHeight;
  }, 100);
};

// ##### 진행중임을 표시하는 함수 #####
springai.setSpinner = function(spinnerId, status) {
  if(status) {
    document.getElementById(spinnerId).classList.remove("d-none");
  } else {
    document.getElementById(spinnerId).classList.add("d-none");
  }
}

