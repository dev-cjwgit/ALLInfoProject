CREATE TABLE `user`
(
    `uid`           BIGINT(19)                      NOT NULL AUTO_INCREMENT,
    `email`         VARCHAR(50)                     NOT NULL COLLATE 'utf8mb4_general_ci',
    `id`            VARCHAR(50)                     NOT NULL COLLATE 'utf8mb4_general_ci',
    `pw`            VARCHAR(200)                    NOT NULL COLLATE 'utf8mb4_general_ci',
    `name`          VARCHAR(50)                     NOT NULL COLLATE 'utf8mb4_general_ci',
    `nickname`      VARCHAR(50)                     NOT NULL COLLATE 'utf8mb4_general_ci',
    `role`          ENUM ('ROLE_USER','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_USER' COLLATE 'utf8mb4_general_ci',
    `refresh_token` VARCHAR(200)                    NULL     DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `sdate`         TIMESTAMP                       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `salt`          VARCHAR(100)                    NULL     DEFAULT NULL COLLATE 'utf8mb4_general_ci',
    `level`         TINYINT(3)                      NOT NULL DEFAULT '0',
    PRIMARY KEY (`uid`) USING BTREE,
    UNIQUE INDEX `email` (`email`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE,
    UNIQUE INDEX `nickname` (`nickname`) USING BTREE
)
    COLLATE = 'utf8mb4_general_ci'
    ENGINE = InnoDB
    AUTO_INCREMENT = 22
;
