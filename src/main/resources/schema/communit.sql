-- --------------------------------------------------------
-- 호스트:                          183.97.128.41
-- 서버 버전:                        8.0.31-0ubuntu0.22.04.1 - (Ubuntu)
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- comunit 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `comunit` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `comunit`;

-- 테이블 comunit.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
                                       `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '번호',
                                       `board_kind_uid` bigint NOT NULL,
                                       `user_uid` bigint DEFAULT NULL COMMENT '작성자 회원번호',
                                       `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '제목',
                                       `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '내용',
                                       `sdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '게시일',
                                       `udate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정일',
                                       PRIMARY KEY (`uid`) USING BTREE,
                                       KEY `quest_user_uid_FK` (`user_uid`) USING BTREE,
                                       KEY `board_board_kind_uid_FK` (`board_kind_uid`),
                                       CONSTRAINT `board_board_kind_uid_FK` FOREIGN KEY (`board_kind_uid`) REFERENCES `board_kind` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                       CONSTRAINT `board_user_uid_FK` FOREIGN KEY (`user_uid`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=546 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 comunit.board_comment 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_comment` (
                                               `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '번호',
                                               `board_uid` bigint NOT NULL COMMENT '게시글 번호',
                                               `user_uid` bigint DEFAULT NULL COMMENT '작성자 번호',
                                               `body` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '내용',
                                               `sdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
                                               `udate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '최종 수정일',
                                               PRIMARY KEY (`uid`) USING BTREE,
                                               KEY `board_comment_user_uid_FK` (`user_uid`) USING BTREE,
                                               KEY `board_comment_quest_uid_FK` (`board_uid`) USING BTREE,
                                               CONSTRAINT `board_comment_quest_uid_FK` FOREIGN KEY (`board_uid`) REFERENCES `board` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                               CONSTRAINT `board_comment_user_uid_FK` FOREIGN KEY (`user_uid`) REFERENCES `user` (`uid`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시글 댓글';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 comunit.board_kind 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_kind` (
                                            `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '번호',
                                            `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '이름',
                                            PRIMARY KEY (`uid`),
                                            UNIQUE KEY `title` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='게시판 정보';

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 comunit.board_view 구조 내보내기
CREATE TABLE IF NOT EXISTS `board_view` (
                                            `board_uid` bigint NOT NULL,
                                            `user_uid` bigint NOT NULL,
                                            `view` int NOT NULL DEFAULT '1',
                                            `sdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                            `udate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                            PRIMARY KEY (`board_uid`,`user_uid`),
                                            KEY `board_view_user_uid_FK` (`user_uid`),
                                            KEY `board_uid` (`board_uid`),
                                            KEY `udate` (`udate`),
                                            CONSTRAINT `board_view_board_uid_FK` FOREIGN KEY (`board_uid`) REFERENCES `board` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT,
                                            CONSTRAINT `board_view_user_uid_FK` FOREIGN KEY (`user_uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 comunit.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
                                      `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '번호',
                                      `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '이메일',
                                      `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '아이디',
                                      `pw` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '비밀번호',
                                      `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '이름',
                                      `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '별명',
                                      `role` enum('ROLE_USER','ROLE_ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'ROLE_USER' COMMENT '시큐리티',
                                      `refresh_token` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                      `sdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입날짜',
                                      `salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
                                      `level` tinyint NOT NULL DEFAULT '0' COMMENT '권한',
                                      PRIMARY KEY (`uid`),
                                      UNIQUE KEY `email` (`email`),
                                      UNIQUE KEY `id` (`id`),
                                      UNIQUE KEY `nickname` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
