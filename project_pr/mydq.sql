-- �Խñ��̹���
CREATE TABLE `MY_SCHEMA`.`green_board_photos` (
	`board_photo_id` INTEGER      NOT NULL COMMENT '�̹�����ȣ', -- �̹�����ȣ
	`boards_id`      INTEGER      NOT NULL COMMENT '�Խ��ǹ�ȣ', -- �Խ��ǹ�ȣ
	`photo_path`     VARCHAR(255) NOT NULL COMMENT '�̹������' -- �̹������
)
COMMENT '�Խñ��̹���';

-- �Խñ��̹���
ALTER TABLE `MY_SCHEMA`.`green_board_photos`
	ADD CONSTRAINT `PK_green_board_photos` -- �Խñ��̹��� �⺻Ű
		PRIMARY KEY (
			`board_photo_id` -- �̹�����ȣ
		);

-- �Խñ��̹��� ����ũ �ε���
CREATE UNIQUE INDEX `UIX_green_board_photos`
	ON `MY_SCHEMA`.`green_board_photos` ( -- �Խñ��̹���
		`photo_path` ASC -- �̹������
	);

ALTER TABLE `MY_SCHEMA`.`green_board_photos`
	MODIFY COLUMN `board_photo_id` INTEGER NOT NULL AUTO_INCREMENT COMMENT '�̹�����ȣ';

-- �Խ���
CREATE TABLE `MY_SCHEMA`.`green_boards` (
	`boards_id`    INTEGER       NOT NULL COMMENT '�Խ��ǹ�ȣ', -- �Խ��ǹ�ȣ
	`created_date` DATE          NOT NULL COMMENT '�ۼ���', -- �ۼ���
	`title`        VARCHAR(255)  NOT NULL COMMENT '����', -- ����
	`contents`     VARCHAR(2000) NOT NULL COMMENT '����', -- ����
	`vw_count`     INTEGER       NULL     COMMENT '��ȸ��' -- ��ȸ��
)
COMMENT '�Խ���';

-- �Խ���
ALTER TABLE `MY_SCHEMA`.`green_boards`
	ADD CONSTRAINT `PK_green_boards` -- �Խ��� �⺻Ű
		PRIMARY KEY (
			`boards_id` -- �Խ��ǹ�ȣ
		);

ALTER TABLE `MY_SCHEMA`.`green_boards`
	MODIFY COLUMN `boards_id` INTEGER NOT NULL AUTO_INCREMENT COMMENT '�Խ��ǹ�ȣ';

-- ��õ
CREATE TABLE `MY_SCHEMA`.`green_recommends` (
	`boards_id` INTEGER NOT NULL COMMENT '�Խ��ǹ�ȣ' -- �Խ��ǹ�ȣ
)
COMMENT '��õ';

-- ��õ
ALTER TABLE `MY_SCHEMA`.`green_recommends`
	ADD CONSTRAINT `PK_green_recommends` -- ��õ �⺻Ű
		PRIMARY KEY (
			`boards_id` -- �Խ��ǹ�ȣ
		);

-- �Խñ��̹���
ALTER TABLE `MY_SCHEMA`.`green_board_photos`
	ADD CONSTRAINT `FK_green_boards_TO_green_board_photos` -- �Խ��� -> �Խñ��̹���
		FOREIGN KEY (
			`boards_id` -- �Խ��ǹ�ȣ
		)
		REFERENCES `MY_SCHEMA`.`green_boards` ( -- �Խ���
			`boards_id` -- �Խ��ǹ�ȣ
		);

-- ��õ
ALTER TABLE `MY_SCHEMA`.`green_recommends`
	ADD CONSTRAINT `FK_green_boards_TO_green_recommends` -- �Խ��� -> ��õ
		FOREIGN KEY (
			`boards_id` -- �Խ��ǹ�ȣ
		)
		REFERENCES `MY_SCHEMA`.`green_boards` ( -- �Խ���
			`boards_id` -- �Խ��ǹ�ȣ
		);