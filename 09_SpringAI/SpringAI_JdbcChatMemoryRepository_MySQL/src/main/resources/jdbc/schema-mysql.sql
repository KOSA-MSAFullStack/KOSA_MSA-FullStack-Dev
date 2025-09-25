-- Spring AI JDBC Chat Memory 기본 스키마 (MySQL)
CREATE TABLE IF NOT EXISTS ai_chat_memory (
  conversation_id VARCHAR(190) NOT NULL,     -- 대화 ID (세션/사용자 기준)
  content         MEDIUMTEXT   NOT NULL,     -- 메시지 내용
  type            VARCHAR(20)  NOT NULL,     -- USER / ASSISTANT / SYSTEM / TOOL
  timestamp       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 생성 시각
  INDEX idx_conv_time (conversation_id, timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
