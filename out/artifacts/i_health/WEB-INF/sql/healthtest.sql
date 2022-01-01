-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-11-21 15:46:24
-- 服务器版本： 5.6.50-log
-- PHP 版本： 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `healthtest`
--

DELIMITER $$
--
-- 存储过程
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_user` (IN `username` VARCHAR(20), IN `password` VARCHAR(20), IN `type` VARCHAR(20), IN `phone` VARCHAR(15), OUT `count` INT)  begin
		insert into user (username,password,type) values (username,password,type);
		SELECT ROW_COUNT() into count;
		if type='patient' then
			insert into patient (username,phone) values (username,phone);
		elseif type='doctor' then
			insert into doctor (username,phone) values (username,phone);
		elseif type='admin' then
			insert into admin (username,phone) values (username,phone);
		end if;
		SELECT ROW_COUNT() into count;
		
	end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `admin`
--

INSERT INTO `admin` (`id`, `username`, `phone`) VALUES
(1, 'admin', '13381822369');

-- --------------------------------------------------------

--
-- 表的结构 `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `doctor`
--

INSERT INTO `doctor` (`id`, `username`, `phone`) VALUES
(1, 'doctor', '19121533697');

-- --------------------------------------------------------

--
-- 表的结构 `operate_record`
--

CREATE TABLE `operate_record` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `operate_record`
--

INSERT INTO `operate_record` (`id`, `username`, `ip`, `time`, `type`) VALUES
(1, 'admin', '101.88.36.136', '2021-11-21 00:40:28', 'login'),
(2, 'admin', '101.88.36.136', '2021-11-21 09:09:03', 'login'),
(3, 'admin', '101.88.36.136', '2021-11-21 09:09:22', 'logout'),
(4, 'admin', '220.196.194.106', '2021-11-21 09:09:36', 'login'),
(5, 'admin', '220.196.194.106', '2021-11-21 09:10:04', 'logout'),
(6, 'patient', '220.196.194.106', '2021-11-21 09:10:14', 'login'),
(7, 'patient', '220.196.194.106', '2021-11-21 09:10:17', 'logout'),
(8, 'admin', '220.196.194.106', '2021-11-21 09:10:26', 'login'),
(9, 'admin', '114.91.1.136', '2021-11-21 12:07:35', 'login'),
(10, 'admin', '117.136.8.202', '2021-11-21 12:07:51', 'login'),
(11, 'admin', '114.91.1.136', '2021-11-21 12:11:32', 'logout'),
(12, 'admin', '112.65.48.7', '2021-11-21 12:32:38', 'login'),
(13, 'admin', '112.65.48.7', '2021-11-21 12:36:33', 'logout'),
(14, '19121542079', '112.65.48.7', '2021-11-21 12:41:25', 'regist'),
(15, '19121542079', '112.65.48.7', '2021-11-21 12:41:41', 'login'),
(16, '19121542079', '112.65.48.7', '2021-11-21 12:41:46', 'logout'),
(17, 'admin', '112.65.48.7', '2021-11-21 12:41:56', 'login'),
(18, 'admin', '223.167.175.162', '2021-11-21 14:56:24', 'login'),
(19, 'admin', '127.0.0.1', '2021-11-21 15:01:05', 'login'),
(20, 'admin', '127.0.0.1', '2021-11-21 15:03:40', 'login'),
(21, 'admin', '127.0.0.1', '2021-11-21 15:04:48', 'login'),
(22, 'admin', '127.0.0.1', '2021-11-21 15:05:45', 'login');

-- --------------------------------------------------------

--
-- 表的结构 `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `patient`
--

INSERT INTO `patient` (`id`, `username`, `phone`) VALUES
(1, 'patient', '19121542079'),
(2, '19121542079', '19121544567');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `patient_info`
-- （参见下面的实际视图）
--
CREATE TABLE `patient_info` (
`username` varchar(20)
,`password` varchar(20)
,`phone` varchar(15)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `record_view`
-- （参见下面的实际视图）
--
CREATE TABLE `record_view` (
`id` int(11)
,`username` varchar(20)
,`ip` varchar(20)
,`time` datetime
,`operate` varchar(20)
,`type` varchar(10)
);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `type`) VALUES
(1, 'patient', '12345678', 'patient'),
(2, 'doctor', '12345678', 'doctor'),
(3, 'admin', '12345678', 'admin'),
(5, '19121542079', '12345678', 'patient');

-- --------------------------------------------------------

--
-- 视图结构 `patient_info`
--
DROP TABLE IF EXISTS `patient_info`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `patient_info`  AS SELECT `patient`.`username` AS `username`, `user`.`password` AS `password`, `patient`.`phone` AS `phone` FROM (`patient` join `user`) WHERE (`patient`.`username` = `user`.`username`) ;

-- --------------------------------------------------------

--
-- 视图结构 `record_view`
--
DROP TABLE IF EXISTS `record_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `record_view`  AS SELECT `a`.`id` AS `id`, `a`.`username` AS `username`, `a`.`ip` AS `ip`, `a`.`time` AS `time`, `a`.`type` AS `operate`, `b`.`type` AS `type` FROM (`operate_record` `a` left join `user` `b` on((`a`.`username` = `b`.`username`))) ;

--
-- 转储表的索引
--

--
-- 表的索引 `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`,`username`) USING BTREE;

--
-- 表的索引 `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`,`username`) USING BTREE;

--
-- 表的索引 `operate_record`
--
ALTER TABLE `operate_record`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`,`username`) USING BTREE,
  ADD KEY `username` (`username`) USING BTREE;

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`,`username`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `operate_record`
--
ALTER TABLE `operate_record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- 使用表AUTO_INCREMENT `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
