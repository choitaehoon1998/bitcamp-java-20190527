-- 게시글이미지
CREATE TABLE green_board_photos (
  board_photo_id INTEGER      NOT NULL COMMENT '이미지번호', -- 이미지번호
  boards_id      INTEGER      NOT NULL COMMENT '게시판번호', -- 게시판번호
  photo_path     VARCHAR(255) NOT NULL COMMENT '이미지경로' -- 이미지경로
)
COMMENT '게시글이미지';

-- 게시글이미지
ALTER TABLE green_board_photos
  ADD CONSTRAINT PK_green_board_photos -- 게시글이미지 기본키
    PRIMARY KEY (
      board_photo_id -- 이미지번호
    );

-- 게시글이미지 유니크 인덱스
CREATE UNIQUE INDEX UIX_green_board_photos
  ON green_board_photos ( -- 게시글이미지
    photo_path ASC -- 이미지경로
  );

ALTER TABLE green_board_photos
  MODIFY COLUMN board_photo_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '이미지번호';

-- 게시판
CREATE TABLE green_boards (
  boards_id    INTEGER       NOT NULL COMMENT '게시판번호', -- 게시판번호
  created_date DATE          NOT NULL COMMENT '작성일', -- 작성일
  title        VARCHAR(255)  NOT NULL COMMENT '제목', -- 제목
  contents     VARCHAR(2000) NOT NULL COMMENT '내용', -- 내용
  vw_count     INTEGER       NULL     COMMENT '조회수' -- 조회수
)
COMMENT '게시판';

-- 게시판
ALTER TABLE green_boards
  ADD CONSTRAINT PK_green_boards -- 게시판 기본키
    PRIMARY KEY (
      boards_id -- 게시판번호
    );

ALTER TABLE green_boards
  MODIFY COLUMN boards_id INTEGER NOT NULL AUTO_INCREMENT COMMENT '게시판번호';

-- 추천
CREATE TABLE green_recommends (
  boards_id INTEGER NOT NULL COMMENT '게시판번호' -- 게시판번호
)
COMMENT '추천';

-- 추천
ALTER TABLE green_recommends
  ADD CONSTRAINT PK_green_recommends -- 추천 기본키
    PRIMARY KEY (
      boards_id -- 게시판번호
    );

-- 게시글이미지
ALTER TABLE green_board_photos
  ADD CONSTRAINT FK_green_boards_TO_green_board_photos -- 게시판 -> 게시글이미지
    FOREIGN KEY (
      boards_id -- 게시판번호
    )
    REFERENCES green_boards ( -- 게시판
      boards_id -- 게시판번호
    );

-- 추천
ALTER TABLE green_recommends
  ADD CONSTRAINT FK_green_boards_TO_green_recommends -- 게시판 -> 추천
    FOREIGN KEY (
      boards_id -- 게시판번호
    )
    REFERENCES green_boards ( -- 게시판
      boards_id -- 게시판번호
    );