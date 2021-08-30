-- 创建数据库
DROP DATABASE IF EXISTS `example_demo_db`;
CREATE DATABASE IF NOT EXISTS `example_demo_db` DEFAULT CHARACTER SET `utf8` COLLATE `utf8_general_ci`;

-- 使用当前数据库
USE `example_demo_db`;

-- 创建主表
CREATE TABLE IF NOT EXISTS `tb_example_info`
(
    `id`          varchar(32)                                            NOT NULL COMMENT 'UUID主键',
    `name`        varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '名称',
    `code`        char(8) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL DEFAULT '' COMMENT '编码',
    `del_flag`    tinyint unsigned                                       NOT NULL DEFAULT '0' COMMENT '逻辑标识，默认0有效，1逻辑删除',
    `insert_time` timestamp                                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time` datetime                                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3 COMMENT ='示例主表';

-- 创建从表
CREATE TABLE IF NOT EXISTS `tb_example_detail`
(
    `id`                  bigint unsigned                                         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `example_id`          varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '示例表ID',
    `type`                char(8) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL DEFAULT '' COMMENT '类型',
    `url`                 varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '宣传页',
    `publish_date`        date                                                    NOT NULL DEFAULT '1970-01-01' COMMENT '发布日期',
    `cut_off_time`        datetime                                                NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '截止时间',
    `valid`               bit(1)                                                  NOT NULL DEFAULT b'1' COMMENT '是否有效，默认1有效，0无效',
    `worth`               decimal(5, 2)                                           NOT NULL DEFAULT '0.00' COMMENT '价值，百分制，保留两位小数',
    `audiences_rate`      double(5, 2) unsigned                                   NOT NULL DEFAULT '0.00' COMMENT '受众比例，百分数，保留两位小数',
    `progress_rate`       float(5, 2) unsigned                                    NOT NULL DEFAULT '0.00' COMMENT '进度，百分数，保留两位小数',
    `locale_guest_num`    int unsigned                                            NOT NULL DEFAULT '0' COMMENT '现场嘉宾数量',
    `locale_audience_num` int unsigned                                            NOT NULL DEFAULT '0' COMMENT '现场观众数量',
    `locale_compere_num`  smallint                                                NOT NULL COMMENT '现场主持人数量',
    `locale_guest_names`  text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '嘉宾名单',
    `del_flag`            tinyint unsigned                                        NOT NULL DEFAULT '0' COMMENT '逻辑标识，默认0有效，1逻辑删除',
    `insert_time`         timestamp                                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
    `update_time`         datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`type`) USING BTREE COMMENT '编码唯一索引',
    KEY `idx_name` (`url`) USING BTREE COMMENT '名称普通索引',
    KEY `idx_date` (`publish_date`, `cut_off_time`) USING BTREE COMMENT '日期普通索引',
    KEY `fk_example_id` (`example_id`) USING BTREE COMMENT '外键普通索引',
    CONSTRAINT `fk_example_id` FOREIGN KEY (`example_id`) REFERENCES `tb_example_info` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb3 COMMENT ='示例明细表';

-- 创建试图
CREATE VIEW `tb_example_detail_view` AS
select `i`.`id`                  AS `example_id`,
       `i`.`name`                AS `name`,
       `i`.`code`                AS `code`,
       `d`.`type`                AS `type`,
       `d`.`url`                 AS `url`,
       `d`.`publish_date`        AS `publish_date`,
       `d`.`cut_off_time`        AS `cut_off_time`,
       `d`.`valid`               AS `valid`,
       `d`.`worth`               AS `worth`,
       `d`.`audiences_rate`      AS `audiences_rate`,
       `d`.`progress_rate`       AS `progress_rate`,
       `d`.`locale_guest_num`    AS `locale_guest_num`,
       `d`.`locale_audience_num` AS `locale_audience_num`,
       `d`.`locale_compere_num`  AS `locale_compere_num`,
       `d`.`locale_guest_names`  AS `locale_guest_names`,
       `i`.`del_flag`            AS `del_flag`,
       `i`.`insert_time`         AS `insert_time`,
       `i`.`update_time`         AS `update_time`
from (`tb_example_info` `i`
         left join `tb_example_detail` `d` on ((`d`.`example_id` = `i`.`id`)));

-- 插入数据
INSERT INTO `example_demo_db`.`tb_example_info`(`id`, `name`, `code`, `del_flag`, `insert_time`, `update_time`)
VALUES ('ba82b120097211ec9b81deb2e45e28b1', 'Example1', '00100101', 0, '2021-08-29 08:03:33', '2021-08-30 17:15:33');
INSERT INTO `example_demo_db`.`tb_example_detail`(`id`, `example_id`, `type`, `url`, `publish_date`, `cut_off_time`,
                                                  `valid`, `worth`, `audiences_rate`, `progress_rate`,
                                                  `locale_guest_num`, `locale_audience_num`, `locale_compere_num`,
                                                  `locale_guest_names`, `del_flag`, `insert_time`, `update_time`)
VALUES (1, 'ba82b120097211ec9b81deb2e45e28b1', '00100101', 'https://www.baidu.com', '2021-09-01', '2021-12-31 23:59:59',
        b'1', 60.58, 32.00, 15.00, 5, 1000, 2, '张三、李四、王二、麻子、TOM', 0, '2021-08-30 17:04:16', '2021-08-30 17:25:55');
