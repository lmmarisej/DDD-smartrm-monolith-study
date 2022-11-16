-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.4.6-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 smartrm_monolith 的数据库结构
CREATE DATABASE IF NOT EXISTS `smartrm_monolith` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `smartrm_monolith`;

-- 导出  表 smartrm_monolith.inventory_snapshot 结构
CREATE TABLE IF NOT EXISTS `inventory_snapshot` (
  `machine_id` bigint(20) NOT NULL COMMENT '售卖机id',
  `commodity_id` varchar(50) NOT NULL COMMENT '商品Id',
  `count` int(10) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`machine_id`,`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='柜门机的库存快照，打开柜门时保存，关闭柜门时删除';

-- 正在导出表  smartrm_monolith.inventory_snapshot 的数据：~0 rows (大约)
DELETE FROM `inventory_snapshot`;
/*!40000 ALTER TABLE `inventory_snapshot` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory_snapshot` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.order 结构
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `machine_id` bigint(20) NOT NULL COMMENT '所在售卖机id',
  `state` tinyint(3) NOT NULL COMMENT '订单状态',
  `type` tinyint(3) NOT NULL COMMENT '订单类型：0：扫码支付订单，1：免密支付订单',
  `payment_id` bigint(20) NOT NULL COMMENT '支付id',
  `commodities` varchar(4096) NOT NULL COMMENT '交易商品，json数组格式',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '上次状态变化时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- 正在导出表  smartrm_monolith.order 的数据：~89 rows (大约)
DELETE FROM `order`;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` (`order_id`, `machine_id`, `state`, `type`, `payment_id`, `commodities`, `create_time`, `last_update_time`) VALUES
	(23, 1, 1, 0, 0, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-09-30 23:58:36', '2021-09-30 23:58:55'),
	(24, 1, 1, 0, 2, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-01 01:47:17', '2021-10-01 01:47:32'),
	(25, 1, 2, 0, 3, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-01 15:05:58', '2021-10-02 11:24:44'),
	(46, 1, 0, 0, 4, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 14:51:16', '2021-10-02 14:51:16'),
	(47, 1, 2, 0, 5, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 15:23:31', '2021-10-02 15:24:29'),
	(48, 1, 2, 0, 6, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 15:42:15', '2021-10-02 15:43:24'),
	(49, 1, 2, 0, 7, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 16:04:26', '2021-10-02 16:05:09'),
	(50, 1, 2, 0, 8, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 16:22:52', '2021-10-02 16:23:39'),
	(51, 1, 2, 0, 9, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 16:32:28', '2021-10-02 16:33:05'),
	(52, 1, 2, 0, 10, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-02 17:01:17', '2021-10-02 17:01:38'),
	(53, 1, 2, 0, 11, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 16:22:23', '2021-10-09 16:22:54'),
	(55, 1, 2, 0, 13, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 19:29:14', '2021-10-09 19:29:44'),
	(56, 1, 2, 0, 14, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 19:30:37', '2021-10-09 19:31:07'),
	(58, 1, 2, 0, 16, '[{"commodityId":"1000001","name":"星华源 纯燕麦速食面140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:09:00', '2021-10-09 21:11:53'),
	(59, 1, 2, 0, 17, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:18:19', '2021-10-09 21:18:49'),
	(60, 1, 2, 0, 18, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:21:07', '2021-10-09 21:21:37'),
	(61, 1, 2, 0, 19, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:35:46', '2021-10-09 21:36:16'),
	(62, 1, 2, 0, 20, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:49:33', '2021-10-09 21:55:42'),
	(63, 1, 2, 0, 21, '[{"commodityId":"1000001","name":"鏄熷崕婧� 绾嚂楹﹂�熼闈�140g","imageUrl":"1000001.jpg","price":10.0,"count":1}]', '2021-10-09 21:55:53', '2021-10-09 21:56:23'),
	(73, 2, 0, 1, 0, '[{"commodityId":"1000001","name":"星华源 纯燕麦速食面140g","imageUrl":"1000001.jpg","price":10.0,"count":1},{"commodityId":"1000002","name":"寿桃牌儿童面 260g","imageUrl":"1000002.jpg","price":5.5,"count":1}]', '2021-10-13 12:05:33', '2021-10-13 12:05:33'),
	(77, 1, 2, 0, 23, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 17:31:56', '2021-10-21 17:32:26'),
	(78, 1, 2, 0, 24, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 17:46:12', '2021-10-21 17:51:06'),
	(79, 1, 1, 0, 25, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 17:53:28', '2021-10-21 17:53:56'),
	(80, 1, 1, 0, 26, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 17:54:07', '2021-10-21 17:54:15'),
	(81, 1, 2, 0, 27, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 18:41:00', '2021-10-21 18:41:30'),
	(82, 1, 2, 0, 28, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 18:46:32', '2021-10-21 18:47:02'),
	(83, 1, 2, 0, 29, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 18:51:19', '2021-10-21 18:51:49'),
	(84, 1, 2, 0, 30, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 18:56:45', '2021-10-21 18:57:15'),
	(85, 1, 2, 0, 31, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 18:57:20', '2021-10-21 18:57:50'),
	(86, 1, 2, 0, 32, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-21 19:04:49', '2021-10-21 19:05:20'),
	(87, 1, 2, 0, 33, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-21 19:22:43', '2021-10-21 19:23:13'),
	(88, 1, 2, 0, 34, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 20:19:39', '2021-10-21 20:20:09'),
	(89, 1, 2, 0, 35, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 21:45:11', '2021-10-21 21:45:41'),
	(90, 1, 2, 0, 36, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 21:45:51', '2021-10-21 21:46:22'),
	(91, 1, 2, 0, 37, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 21:46:31', '2021-10-21 21:47:01'),
	(92, 1, 2, 0, 38, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 21:47:13', '2021-10-21 21:47:43'),
	(93, 1, 2, 0, 39, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 21:48:11', '2021-10-21 21:48:41'),
	(94, 1, 2, 0, 40, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-21 21:56:23', '2021-10-21 21:56:53'),
	(95, 1, 2, 0, 41, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-21 21:57:30', '2021-10-21 21:58:00'),
	(96, 1, 2, 0, 42, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:01:22', '2021-10-21 22:01:53'),
	(97, 1, 2, 0, 43, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:02:06', '2021-10-21 22:02:36'),
	(98, 1, 2, 0, 44, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:03:38', '2021-10-21 22:04:08'),
	(99, 1, 1, 0, 45, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:14:55', '2021-10-21 22:15:02'),
	(100, 1, 1, 0, 46, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:15:44', '2021-10-21 22:15:52'),
	(101, 1, 1, 0, 47, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:16:19', '2021-10-21 22:16:20'),
	(102, 1, 2, 0, 48, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 22:17:09', '2021-10-21 22:17:39'),
	(103, 1, 1, 0, 49, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 22:24:29', '2021-10-21 22:24:45'),
	(104, 1, 1, 0, 50, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 22:25:19', '2021-10-21 22:25:29'),
	(105, 1, 1, 0, 51, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 22:26:19', '2021-10-21 22:26:20'),
	(106, 1, 1, 0, 52, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 22:26:39', '2021-10-21 22:26:39'),
	(107, 1, 1, 0, 53, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 22:50:47', '2021-10-21 22:50:59'),
	(108, 1, 1, 0, 54, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 23:30:11', '2021-10-21 23:30:18'),
	(109, 1, 2, 0, 55, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-21 23:30:22', '2021-10-21 23:30:52'),
	(110, 1, 1, 0, 56, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-21 23:30:57', '2021-10-21 23:30:58'),
	(111, 1, 1, 0, 57, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-21 23:31:14', '2021-10-21 23:31:23'),
	(112, 1, 2, 0, 58, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 23:32:06', '2021-10-21 23:32:36'),
	(113, 1, 1, 0, 59, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-21 23:34:45', '2021-10-21 23:34:46'),
	(114, 1, 2, 0, 60, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 00:45:39', '2021-10-24 00:46:22'),
	(115, 1, 2, 0, 61, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 00:48:14', '2021-10-24 00:48:45'),
	(116, 1, 2, 0, 62, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 00:50:27', '2021-10-24 00:54:32'),
	(117, 1, 2, 0, 63, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 00:54:47', '2021-10-24 00:56:31'),
	(118, 1, 2, 0, 64, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:00:29', '2021-10-24 01:02:26'),
	(119, 1, 2, 0, 65, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:04:15', '2021-10-24 01:05:04'),
	(120, 1, 2, 0, 66, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:05:46', '2021-10-24 01:06:16'),
	(121, 1, 2, 0, 67, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:08:45', '2021-10-24 01:12:35'),
	(122, 1, 2, 0, 68, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:13:55', '2021-10-24 01:14:25'),
	(124, 1, 2, 0, 70, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:30:41', '2021-10-24 01:31:11'),
	(126, 1, 2, 0, 72, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 01:40:47', '2021-10-24 01:41:17'),
	(127, 1, 2, 0, 73, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 11:04:21', '2021-10-24 11:04:51'),
	(128, 1, 2, 0, 74, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 11:19:43', '2021-10-24 11:20:13'),
	(129, 1, 2, 0, 75, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 11:28:03', '2021-10-24 11:28:33'),
	(130, 1, 2, 0, 76, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 11:28:50', '2021-10-24 11:29:20'),
	(131, 1, 2, 0, 77, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 11:47:11', '2021-10-24 11:48:16'),
	(134, 1, 2, 0, 80, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 15:27:34', '2021-10-24 15:31:34'),
	(135, 1, 2, 0, 81, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 15:43:33', '2021-10-24 15:44:33'),
	(136, 1, 2, 0, 82, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 16:00:02', '2021-10-24 16:00:56'),
	(137, 1, 2, 0, 83, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 16:03:57', '2021-10-24 16:06:00'),
	(138, 1, 2, 0, 84, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 16:11:26', '2021-10-24 19:49:29'),
	(139, 1, 2, 0, 85, '[{"commodityId":"1000009","name":"上海美味肉蓉面100g","imageUrl":"1000009.png","price":6.5,"count":1}]', '2021-10-24 19:50:22', '2021-10-24 19:50:31'),
	(141, 1, 2, 0, 87, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-24 19:58:36', '2021-10-24 19:58:40'),
	(142, 1, 2, 0, 88, '[{"commodityId":"1000003","name":"【咸阳馆】宝鸡岐山擀面皮280g","imageUrl":"1000003.jpg","price":3.0,"count":1}]', '2021-10-24 20:03:28', '2021-10-24 20:05:55'),
	(143, 1, 1, 0, 89, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-24 20:09:56', '2021-10-24 20:09:58'),
	(144, 1, 1, 0, 90, '[{"commodityId":"1000006","name":"古龙食品 鱼罐头 丁香鱼110g","imageUrl":"1000006.jpg","price":3.5,"count":1}]', '2021-10-24 20:10:00', '2021-10-24 20:10:01'),
	(145, 1, 1, 0, 91, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-24 20:10:04', '2021-10-24 20:10:06'),
	(148, 1, 2, 0, 94, '[{"commodityId":"1000006","name":"古龙食品 鱼罐头 丁香鱼110g","imageUrl":"1000006.jpg","price":3.5,"count":1}]', '2021-10-24 22:01:32', '2021-10-24 22:01:38'),
	(149, 1, 1, 0, 95, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-24 22:04:04', '2021-10-24 22:04:16'),
	(152, 1, 2, 0, 98, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-24 22:33:01', '2021-10-24 22:33:10'),
	(153, 1, 2, 0, 99, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-24 22:36:48', '2021-10-24 22:42:39'),
	(154, 1, 2, 0, 100, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-25 10:29:37', '2021-10-25 10:30:08'),
	(155, 1, 2, 0, 101, '[{"commodityId":"1000005","name":"老刘头淮南牛肉汤粉丝原味","imageUrl":"1000005.jpg","price":9.5,"count":1}]', '2021-10-25 10:33:42', '2021-10-25 10:33:43'),
	(156, 1, 1, 0, 102, '[{"commodityId":"1000004","name":"老刘头淮南牛肉汤粉丝香辣味","imageUrl":"1000004.jpg","price":5.5,"count":1}]', '2021-10-25 10:55:48', '2021-10-25 10:55:52'),
	(157, 1, 2, 0, 103, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-25 12:31:28', '2021-10-25 12:31:59'),
	(158, 1, 2, 0, 104, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-25 12:33:10', '2021-10-25 12:33:41'),
	(159, 1, 2, 0, 105, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-25 12:33:47', '2021-10-26 11:15:41'),
	(160, 1, 2, 0, 106, '[{"commodityId":"1000010","name":"大汉口 热干面408g","imageUrl":"1000010.jpg","price":4.5,"count":1}]', '2021-10-25 12:34:47', '2021-10-25 12:38:14');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.payment 结构
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支付id',
  `platform_type` tinyint(3) NOT NULL COMMENT '平台类型，默认微信',
  `account_id` bigint(20) DEFAULT NULL COMMENT '账户id',
  `contract_id` varchar(50) DEFAULT NULL COMMENT '扣费支付协议id',
  `payment_type` tinyint(3) NOT NULL COMMENT '支付类型，0：扫码支付，1：免密支付（自动扣费）',
  `machine_id` bigint(20) NOT NULL COMMENT '售卖机id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `order_type` tinyint(3) NOT NULL COMMENT '订单类型',
  `state` tinyint(3) NOT NULL COMMENT '支付状态',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `retried_times` tinyint(3) NOT NULL COMMENT '已重试次数',
  `last_retry_time` timestamp NULL DEFAULT NULL COMMENT '上次重试时间',
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.payment 的数据：~92 rows (大约)
DELETE FROM `payment`;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` (`payment_id`, `platform_type`, `account_id`, `contract_id`, `payment_type`, `machine_id`, `order_id`, `order_type`, `state`, `amount`, `retried_times`, `last_retry_time`) VALUES
	(1, 0, NULL, NULL, 0, 1, 23, 0, 1, 10.00, 0, '2021-09-30 23:58:55'),
	(2, 0, NULL, NULL, 0, 1, 24, 0, 1, 10.00, 0, '2021-10-01 01:47:32'),
	(3, 0, NULL, NULL, 0, 1, 25, 0, 4, 10.00, 1, '2021-10-02 12:12:13'),
	(4, 0, NULL, NULL, 0, 1, 46, 0, 1, 10.00, 0, '2021-10-02 14:51:35'),
	(5, 0, NULL, NULL, 0, 1, 47, 0, 4, 10.00, 1, '2021-10-02 15:24:29'),
	(6, 0, NULL, NULL, 0, 1, 48, 0, 4, 10.00, 1, '2021-10-02 15:43:24'),
	(7, 0, NULL, NULL, 0, 1, 49, 0, 4, 10.00, 1, '2021-10-02 16:05:09'),
	(8, 0, NULL, NULL, 0, 1, 50, 0, 4, 10.00, 1, '2021-10-02 16:23:39'),
	(9, 0, NULL, NULL, 0, 1, 51, 0, 4, 10.00, 1, '2021-10-02 16:33:05'),
	(10, 0, NULL, NULL, 0, 1, 52, 0, 4, 10.00, 1, '2021-10-02 17:01:38'),
	(11, 0, NULL, NULL, 0, 1, 53, 0, 0, 10.00, 0, '2021-10-09 16:22:23'),
	(13, 0, NULL, NULL, 0, 1, 55, 0, 0, 10.00, 0, '2021-10-09 19:29:14'),
	(14, 0, NULL, NULL, 0, 1, 56, 0, 0, 10.00, 0, '2021-10-09 19:30:37'),
	(16, 0, NULL, NULL, 0, 1, 58, 0, 0, 10.00, 0, '2021-10-09 21:09:00'),
	(17, 0, NULL, NULL, 0, 1, 59, 0, 0, 10.00, 0, '2021-10-09 21:18:19'),
	(18, 0, NULL, NULL, 0, 1, 60, 0, 0, 10.00, 0, '2021-10-09 21:21:07'),
	(19, 0, NULL, NULL, 0, 1, 61, 0, 0, 10.00, 0, '2021-10-09 21:35:46'),
	(20, 0, NULL, NULL, 0, 1, 62, 0, 0, 10.00, 0, '2021-10-09 21:49:33'),
	(21, 0, NULL, NULL, 0, 1, 63, 0, 0, 10.00, 0, '2021-10-09 21:55:53'),
	(22, 0, 9, 'contrace_id_123', 1, 2, 73, 1, 1, 15.50, 1, '2021-10-13 12:05:36'),
	(23, 0, NULL, NULL, 0, 1, 77, 0, 1, 5.50, 0, '2021-10-21 17:40:29'),
	(24, 0, NULL, NULL, 0, 1, 78, 0, 1, 3.00, 0, '2021-10-21 17:51:07'),
	(25, 0, NULL, NULL, 0, 1, 79, 0, 4, 3.00, 1, '2021-10-21 17:53:56'),
	(26, 0, NULL, NULL, 0, 1, 80, 0, 4, 5.50, 1, '2021-10-21 17:54:15'),
	(27, 0, NULL, NULL, 0, 1, 81, 0, 0, 3.00, 0, '2021-10-21 18:41:00'),
	(28, 0, NULL, NULL, 0, 1, 82, 0, 0, 3.00, 0, '2021-10-21 18:46:32'),
	(29, 0, NULL, NULL, 0, 1, 83, 0, 0, 3.00, 0, '2021-10-21 18:51:19'),
	(30, 0, NULL, NULL, 0, 1, 84, 0, 0, 3.00, 0, '2021-10-21 18:56:45'),
	(31, 0, NULL, NULL, 0, 1, 85, 0, 1, 3.00, 0, '2021-10-21 18:58:20'),
	(32, 0, NULL, NULL, 0, 1, 86, 0, 0, 9.50, 0, '2021-10-21 19:04:49'),
	(33, 0, NULL, NULL, 0, 1, 87, 0, 0, 9.50, 0, '2021-10-21 19:22:43'),
	(34, 0, NULL, NULL, 0, 1, 88, 0, 0, 3.00, 0, '2021-10-21 20:19:39'),
	(35, 0, NULL, NULL, 0, 1, 89, 0, 0, 3.00, 0, '2021-10-21 21:45:11'),
	(36, 0, NULL, NULL, 0, 1, 90, 0, 0, 3.00, 0, '2021-10-21 21:45:51'),
	(37, 0, NULL, NULL, 0, 1, 91, 0, 0, 3.00, 0, '2021-10-21 21:46:31'),
	(38, 0, NULL, NULL, 0, 1, 92, 0, 0, 3.00, 0, '2021-10-21 21:47:13'),
	(39, 0, NULL, NULL, 0, 1, 93, 0, 0, 3.00, 0, '2021-10-21 21:48:11'),
	(40, 0, NULL, NULL, 0, 1, 94, 0, 0, 4.50, 0, '2021-10-21 21:56:23'),
	(41, 0, NULL, NULL, 0, 1, 95, 0, 0, 4.50, 0, '2021-10-21 21:57:30'),
	(42, 0, NULL, NULL, 0, 1, 96, 0, 0, 5.50, 0, '2021-10-21 22:01:22'),
	(43, 0, NULL, NULL, 0, 1, 97, 0, 0, 5.50, 0, '2021-10-21 22:02:06'),
	(44, 0, NULL, NULL, 0, 1, 98, 0, 0, 5.50, 0, '2021-10-21 22:03:38'),
	(45, 0, NULL, NULL, 0, 1, 99, 0, 4, 5.50, 1, '2021-10-21 22:15:02'),
	(46, 0, NULL, NULL, 0, 1, 100, 0, 4, 5.50, 1, '2021-10-21 22:15:52'),
	(47, 0, NULL, NULL, 0, 1, 101, 0, 4, 5.50, 1, '2021-10-21 22:16:20'),
	(48, 0, NULL, NULL, 0, 1, 102, 0, 1, 3.00, 0, '2021-10-21 22:18:15'),
	(49, 0, NULL, NULL, 0, 1, 103, 0, 4, 3.00, 1, '2021-10-21 22:24:46'),
	(50, 0, NULL, NULL, 0, 1, 104, 0, 4, 3.00, 1, '2021-10-21 22:25:29'),
	(51, 0, NULL, NULL, 0, 1, 105, 0, 4, 3.00, 1, '2021-10-21 22:26:20'),
	(52, 0, NULL, NULL, 0, 1, 106, 0, 4, 3.00, 1, '2021-10-21 22:26:40'),
	(53, 0, NULL, NULL, 0, 1, 107, 0, 1, 5.50, 0, '2021-10-21 22:50:59'),
	(54, 0, NULL, NULL, 0, 1, 108, 0, 1, 3.00, 0, '2021-10-21 23:30:18'),
	(55, 0, NULL, NULL, 0, 1, 109, 0, 0, 9.50, 0, '2021-10-21 23:30:22'),
	(56, 0, NULL, NULL, 0, 1, 110, 0, 1, 9.50, 0, '2021-10-21 23:30:58'),
	(57, 0, NULL, NULL, 0, 1, 111, 0, 1, 5.50, 0, '2021-10-21 23:31:23'),
	(58, 0, NULL, NULL, 0, 1, 112, 0, 0, 3.00, 0, '2021-10-21 23:32:06'),
	(59, 0, NULL, NULL, 0, 1, 113, 0, 1, 3.00, 0, '2021-10-21 23:34:46'),
	(60, 0, NULL, NULL, 0, 1, 114, 0, 4, 3.00, 1, '2021-10-24 00:46:22'),
	(61, 0, NULL, NULL, 0, 1, 115, 0, 4, 3.00, 1, '2021-10-24 00:48:45'),
	(62, 0, NULL, NULL, 0, 1, 116, 0, 0, 3.00, 0, '2021-10-24 00:50:15'),
	(63, 0, NULL, NULL, 0, 1, 117, 0, 4, 3.00, 1, '2021-10-24 00:55:48'),
	(64, 0, NULL, NULL, 0, 1, 118, 0, 4, 3.00, 1, '2021-10-24 01:00:59'),
	(65, 0, NULL, NULL, 0, 1, 119, 0, 4, 3.00, 1, '2021-10-24 01:04:45'),
	(66, 0, NULL, NULL, 0, 1, 120, 0, 4, 3.00, 1, '2021-10-24 01:06:16'),
	(67, 0, NULL, NULL, 0, 1, 121, 0, 4, 3.00, 1, '2021-10-24 01:09:15'),
	(68, 0, NULL, NULL, 0, 1, 122, 0, 0, 3.00, 0, '2021-10-24 01:13:55'),
	(70, 0, NULL, NULL, 0, 1, 124, 0, 4, 3.00, 1, '2021-10-24 01:31:11'),
	(72, 0, NULL, NULL, 0, 1, 126, 0, 4, 3.00, 1, '2021-10-24 01:41:17'),
	(73, 0, NULL, NULL, 0, 1, 127, 0, 1, 3.00, 0, '2021-10-24 11:04:58'),
	(74, 0, NULL, NULL, 0, 1, 128, 0, 4, 3.00, 1, '2021-10-24 11:20:13'),
	(75, 0, NULL, NULL, 0, 1, 129, 0, 4, 3.00, 1, '2021-10-24 11:28:33'),
	(76, 0, NULL, NULL, 0, 1, 130, 0, 4, 3.00, 1, '2021-10-24 11:29:20'),
	(77, 0, NULL, NULL, 0, 1, 131, 0, 4, 3.00, 1, '2021-10-24 11:48:16'),
	(78, 0, NULL, NULL, 0, 1, 132, 0, 1, 3.00, 0, '2021-10-24 12:47:16'),
	(79, 0, NULL, NULL, 0, 1, 133, 0, 1, 6.50, 0, '2021-10-24 15:00:21'),
	(80, 0, NULL, NULL, 0, 1, 134, 0, 4, 6.50, 1, '2021-10-24 15:31:01'),
	(81, 0, NULL, NULL, 0, 1, 135, 0, 4, 6.50, 1, '2021-10-24 15:44:33'),
	(82, 0, NULL, NULL, 0, 1, 136, 0, 4, 6.50, 1, '2021-10-24 16:00:56'),
	(83, 0, NULL, NULL, 0, 1, 137, 0, 4, 6.50, 1, '2021-10-24 16:06:00'),
	(84, 0, NULL, NULL, 0, 1, 138, 0, 4, 6.50, 0, '2021-10-24 19:49:29'),
	(85, 0, NULL, NULL, 0, 1, 139, 0, 4, 6.50, 0, '2021-10-24 19:50:31'),
	(87, 0, NULL, NULL, 0, 1, 141, 0, 4, 9.50, 0, '2021-10-24 19:58:40'),
	(88, 0, NULL, NULL, 0, 1, 142, 0, 4, 3.00, 0, '2021-10-24 20:05:55'),
	(89, 0, NULL, NULL, 0, 1, 143, 0, 1, 9.50, 0, '2021-10-24 20:09:57'),
	(90, 0, NULL, NULL, 0, 1, 144, 0, 1, 3.50, 0, '2021-10-24 20:10:00'),
	(91, 0, NULL, NULL, 0, 1, 145, 0, 1, 4.50, 0, '2021-10-24 20:10:05'),
	(92, 0, NULL, NULL, 0, 1, 146, 0, 1, 9.50, 0, '2021-10-24 21:47:45'),
	(94, 0, NULL, NULL, 0, 1, 148, 0, 4, 3.50, 0, '2021-10-24 22:01:38'),
	(95, 0, NULL, NULL, 0, 1, 149, 0, 4, 9.50, 0, '2021-10-24 22:17:42'),
	(96, 0, NULL, NULL, 0, 1, 150, 0, 1, 5.50, 0, '2021-10-24 22:20:58'),
	(98, 0, NULL, NULL, 0, 1, 152, 0, 4, 5.50, 0, '2021-10-24 22:33:10'),
	(99, 0, NULL, NULL, 0, 1, 153, 0, 4, 5.50, 0, '2021-10-24 22:42:39'),
	(100, 0, NULL, NULL, 0, 1, 154, 0, 4, 9.50, 0, '2021-10-25 10:30:07'),
	(101, 0, NULL, NULL, 0, 1, 155, 0, 4, 9.50, 0, '2021-10-25 10:33:43'),
	(102, 0, NULL, NULL, 0, 1, 156, 0, 1, 5.50, 0, '2021-10-25 10:55:52'),
	(103, 0, NULL, NULL, 0, 1, 157, 0, 5, 4.50, 0, '2021-10-25 12:31:59'),
	(104, 0, NULL, NULL, 0, 1, 158, 0, 5, 4.50, 0, '2021-10-25 12:33:40'),
	(105, 0, NULL, NULL, 0, 1, 159, 0, 4, 4.50, 0, '2021-10-26 11:15:41'),
	(106, 0, NULL, NULL, 0, 1, 160, 0, 4, 4.50, 0, '2021-10-25 12:38:14');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_blob_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `BLOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_blob_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_blob_triggers`;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_calendars 结构
CREATE TABLE IF NOT EXISTS `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(190) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_calendars 的数据：~0 rows (大约)
DELETE FROM `qrtz_calendars`;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_cron_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_cron_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_cron_triggers`;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_fired_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(190) DEFAULT NULL,
  `JOB_GROUP` varchar(190) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_fired_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_fired_triggers`;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_job_details 结构
CREATE TABLE IF NOT EXISTS `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_job_details 的数据：~2 rows (大约)
DELETE FROM `qrtz_job_details`;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES
	('clusteredScheduler', '6da64b5bd2ee-10018dca-facc-4ec2-ba19-8a695c120b61', 'DEFAULT', NULL, 'com.smartrm.smartrmmonolith.payment.application.executor.RefundExecutor', '0', '0', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61703FE8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D6170133F3F760A3F00025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC13F603F000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400076F7264657249647372000E6A6176612E6C616E672E4C6F6E673B8BE490CC3F3F00014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC3F0B94E03F00007870000000000000008A7800),
	('clusteredScheduler', '6da64b5bd2ee-4e5edcde-b28d-4a75-a969-5f9d4b1c6a4c', 'DEFAULT', NULL, 'com.smartrm.smartrmmonolith.payment.application.executor.RefundExecutor', '0', '0', '0', '0', _binary 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61703FE8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D6170133F3F760A3F00025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC13F603F000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C770800000010000000017400076F7264657249647372000E6A6176612E6C616E672E4C6F6E673B8BE490CC3F3F00014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC3F0B94E03F00007870000000000000008A7800);
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_locks 结构
CREATE TABLE IF NOT EXISTS `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_locks 的数据：~2 rows (大约)
DELETE FROM `qrtz_locks`;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` (`SCHED_NAME`, `LOCK_NAME`) VALUES
	('clusteredScheduler', 'STATE_ACCESS'),
	('clusteredScheduler', 'TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_paused_trigger_grps 结构
CREATE TABLE IF NOT EXISTS `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_paused_trigger_grps 的数据：~0 rows (大约)
DELETE FROM `qrtz_paused_trigger_grps`;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_scheduler_state 结构
CREATE TABLE IF NOT EXISTS `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(190) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_scheduler_state 的数据：~2 rows (大约)
DELETE FROM `qrtz_scheduler_state`;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
INSERT INTO `qrtz_scheduler_state` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES
	('clusteredScheduler', 'guoliangyu-NB11635217778510', 1635218179087, 10000);
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_simple_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_simple_triggers 的数据：~2 rows (大约)
DELETE FROM `qrtz_simple_triggers`;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_simple_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `REPEAT_COUNT`, `REPEAT_INTERVAL`, `TIMES_TRIGGERED`) VALUES
	('clusteredScheduler', '6da64b5bd2ee-c42cce2f-92a4-40b7-a504-a41553e504d7', 'DEFAULT', -1, 10000, 1),
	('clusteredScheduler', '6da64b5bd2ee-f1566184-e211-4f70-bbd1-c44d0303cb18', 'DEFAULT', -1, 10000, 1);
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_simprop_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_simprop_triggers 的数据：~0 rows (大约)
DELETE FROM `qrtz_simprop_triggers`;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.qrtz_triggers 结构
CREATE TABLE IF NOT EXISTS `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(190) NOT NULL,
  `TRIGGER_GROUP` varchar(190) NOT NULL,
  `JOB_NAME` varchar(190) NOT NULL,
  `JOB_GROUP` varchar(190) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(190) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.qrtz_triggers 的数据：~2 rows (大约)
DELETE FROM `qrtz_triggers`;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES
	('clusteredScheduler', '6da64b5bd2ee-c42cce2f-92a4-40b7-a504-a41553e504d7', 'DEFAULT', '6da64b5bd2ee-4e5edcde-b28d-4a75-a969-5f9d4b1c6a4c', 'DEFAULT', NULL, 1635075229716, 1635075219716, 5, 'ERROR', 'SIMPLE', 1635075219716, 0, NULL, 0, _binary ''),
	('clusteredScheduler', '6da64b5bd2ee-f1566184-e211-4f70-bbd1-c44d0303cb18', 'DEFAULT', '6da64b5bd2ee-10018dca-facc-4ec2-ba19-8a695c120b61', 'DEFAULT', NULL, 1635075163712, 1635075153712, 5, 'ERROR', 'SIMPLE', 1635075153712, 0, NULL, 0, _binary '');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.slot_commodity 结构
CREATE TABLE IF NOT EXISTS `slot_commodity` (
  `machine_id` bigint(20) NOT NULL,
  `slot_id` int(10) NOT NULL,
  `commodity_id` varchar(64) NOT NULL,
  PRIMARY KEY (`machine_id`,`slot_id`),
  KEY `idx_machineCommodity` (`machine_id`,`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货道售卖机内货道与商品的映射关系';

-- 正在导出表  smartrm_monolith.slot_commodity 的数据：~10 rows (大约)
DELETE FROM `slot_commodity`;
/*!40000 ALTER TABLE `slot_commodity` DISABLE KEYS */;
INSERT INTO `slot_commodity` (`machine_id`, `slot_id`, `commodity_id`) VALUES
	(1, 1, '1000001'),
	(1, 2, '1000002'),
	(1, 3, '1000003'),
	(1, 4, '1000004'),
	(1, 5, '1000005'),
	(1, 6, '1000006'),
	(1, 7, '1000007'),
	(1, 8, '1000008'),
	(1, 9, '1000009'),
	(1, 10, '1000010');
/*!40000 ALTER TABLE `slot_commodity` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.trade_cabinet_vending_machine 结构
CREATE TABLE IF NOT EXISTS `trade_cabinet_vending_machine` (
  `machine_id` bigint(20) NOT NULL COMMENT '售卖机id',
  `state` tinyint(4) NOT NULL COMMENT '状态，0：打开，1：关闭（锁定）',
  `cur_user_open_id` varchar(50) NOT NULL DEFAULT '' COMMENT '当前用户openId'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易上下文货柜式售卖机';

-- 正在导出表  smartrm_monolith.trade_cabinet_vending_machine 的数据：~0 rows (大约)
DELETE FROM `trade_cabinet_vending_machine`;
/*!40000 ALTER TABLE `trade_cabinet_vending_machine` DISABLE KEYS */;
INSERT INTO `trade_cabinet_vending_machine` (`machine_id`, `state`, `cur_user_open_id`) VALUES
	(2, 0, 'open_id_123');
/*!40000 ALTER TABLE `trade_cabinet_vending_machine` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.trade_slot_vending_machine 结构
CREATE TABLE IF NOT EXISTS `trade_slot_vending_machine` (
  `machine_id` bigint(20) NOT NULL COMMENT '售卖机id',
  `state` tinyint(3) NOT NULL COMMENT '状态',
  `cur_order_id` bigint(20) NOT NULL COMMENT '当前交易订单id',
  `version` bigint(20) NOT NULL DEFAULT 0 COMMENT '数据版本号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='货道售卖机表';

-- 正在导出表  smartrm_monolith.trade_slot_vending_machine 的数据：~0 rows (大约)
DELETE FROM `trade_slot_vending_machine`;
/*!40000 ALTER TABLE `trade_slot_vending_machine` DISABLE KEYS */;
INSERT INTO `trade_slot_vending_machine` (`machine_id`, `state`, `cur_order_id`, `version`) VALUES
	(1, 0, 160, 186);
/*!40000 ALTER TABLE `trade_slot_vending_machine` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.unique_id_generator 结构
CREATE TABLE IF NOT EXISTS `unique_id_generator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=161 DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.unique_id_generator 的数据：~136 rows (大约)
DELETE FROM `unique_id_generator`;
/*!40000 ALTER TABLE `unique_id_generator` DISABLE KEYS */;
INSERT INTO `unique_id_generator` (`id`) VALUES
	(1),
	(2),
	(3),
	(4),
	(5),
	(6),
	(7),
	(8),
	(9),
	(10),
	(11),
	(12),
	(13),
	(14),
	(15),
	(16),
	(17),
	(18),
	(19),
	(20),
	(21),
	(22),
	(23),
	(24),
	(25),
	(26),
	(27),
	(28),
	(29),
	(30),
	(31),
	(32),
	(33),
	(34),
	(35),
	(36),
	(37),
	(38),
	(39),
	(40),
	(41),
	(42),
	(43),
	(44),
	(45),
	(46),
	(47),
	(48),
	(49),
	(50),
	(51),
	(52),
	(53),
	(55),
	(56),
	(58),
	(59),
	(60),
	(61),
	(62),
	(63),
	(70),
	(73),
	(74),
	(75),
	(76),
	(77),
	(78),
	(79),
	(80),
	(81),
	(82),
	(83),
	(84),
	(85),
	(86),
	(87),
	(88),
	(89),
	(90),
	(91),
	(92),
	(93),
	(94),
	(95),
	(96),
	(97),
	(98),
	(99),
	(100),
	(101),
	(102),
	(103),
	(104),
	(105),
	(106),
	(107),
	(108),
	(109),
	(110),
	(111),
	(112),
	(113),
	(114),
	(115),
	(116),
	(117),
	(118),
	(119),
	(120),
	(121),
	(122),
	(124),
	(126),
	(127),
	(128),
	(129),
	(130),
	(131),
	(132),
	(133),
	(134),
	(135),
	(136),
	(137),
	(138),
	(139),
	(141),
	(142),
	(143),
	(144),
	(145),
	(146),
	(148),
	(149),
	(150),
	(152),
	(153),
	(154),
	(155),
	(156),
	(157),
	(158),
	(159),
	(160);
/*!40000 ALTER TABLE `unique_id_generator` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.user_account 结构
CREATE TABLE IF NOT EXISTS `user_account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账号id',
  `contract_id` varchar(50) DEFAULT NULL COMMENT '支付协议id',
  `wx_open_id` varchar(50) NOT NULL COMMENT '微信open id',
  `wx_union_id` varchar(50) DEFAULT NULL COMMENT '微信union id',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `uk_openid` (`wx_open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='账号';

-- 正在导出表  smartrm_monolith.user_account 的数据：~4 rows (大约)
DELETE FROM `user_account`;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` (`account_id`, `contract_id`, `wx_open_id`, `wx_union_id`) VALUES
	(1, 'Wx15463511252015071056489715', 'onqOjjmM1tad-3ROpncN-yUfa6ua', 'onqOjjmM1tad-3ROpncN-yUfa6ub'),
	(7, 'contract_id_123456', 'open_id_123456', NULL),
	(8, NULL, 'open_id_234567', NULL),
	(9, 'contrace_id_123', 'open_id_123', NULL),
	(13, NULL, 'open_id_321account', NULL);
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.vending_machine 结构
CREATE TABLE IF NOT EXISTS `vending_machine` (
  `machine_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `machine_type` tinyint(3) NOT NULL COMMENT '类型，0: 货道售卖机，1:柜门机（货柜机）',
  `cabinet_door_state` tinyint(3) DEFAULT NULL COMMENT '状态，0：关闭；1：打开',
  `iot_product_key` varchar(32) DEFAULT NULL COMMENT '设备在iot平台的产品号',
  `iot_device_name` varchar(64) DEFAULT NULL COMMENT '设备在iot平台的名字',
  `model` int(10) NOT NULL COMMENT '设备型号',
  PRIMARY KEY (`machine_id`),
  UNIQUE KEY `uk_iotKey` (`iot_product_key`,`iot_device_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='售卖机信息';

-- 正在导出表  smartrm_monolith.vending_machine 的数据：~2 rows (大约)
DELETE FROM `vending_machine`;
/*!40000 ALTER TABLE `vending_machine` DISABLE KEYS */;
INSERT INTO `vending_machine` (`machine_id`, `machine_type`, `cabinet_door_state`, `iot_product_key`, `iot_device_name`, `model`) VALUES
	(1, 0, 0, 'a1xIngOM007', 'slotMachineTest', 1),
	(2, 1, 0, NULL, NULL, 2);
/*!40000 ALTER TABLE `vending_machine` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.vending_machine_inventory 结构
CREATE TABLE IF NOT EXISTS `vending_machine_inventory` (
  `machine_id` bigint(20) NOT NULL COMMENT '售卖机id',
  `commodity_id` varchar(50) NOT NULL COMMENT '商品id',
  `count` int(10) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`machine_id`,`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='售卖机库存';

-- 正在导出表  smartrm_monolith.vending_machine_inventory 的数据：~12 rows (大约)
DELETE FROM `vending_machine_inventory`;
/*!40000 ALTER TABLE `vending_machine_inventory` DISABLE KEYS */;
INSERT INTO `vending_machine_inventory` (`machine_id`, `commodity_id`, `count`) VALUES
	(1, '1000001', 7),
	(1, '1000002', 9),
	(1, '1000003', 2),
	(1, '1000004', 2),
	(1, '1000005', 6),
	(1, '1000006', 9),
	(1, '1000007', 10),
	(1, '1000008', 10),
	(1, '1000009', 10),
	(1, '1000010', 7),
	(2, '1000001', 5),
	(2, '1000002', 6);
/*!40000 ALTER TABLE `vending_machine_inventory` ENABLE KEYS */;

-- 导出  表 smartrm_monolith.vending_machine_model 结构
CREATE TABLE IF NOT EXISTS `vending_machine_model` (
  `model_code` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `supplier_id` varchar(50) NOT NULL,
  `purchase_org_id` varchar(50) NOT NULL,
  `purchase_dept_id` varchar(50) NOT NULL,
  `purchase_group_id` varchar(50) NOT NULL,
  `purchaser_id` varchar(50) NOT NULL,
  `product_type` varchar(50) NOT NULL,
  `material_id` varchar(50) NOT NULL,
  `bom_id` varchar(50) NOT NULL,
  `material_desc` varchar(50) NOT NULL,
  `processor` varchar(50) NOT NULL,
  `slot_num` int(11) NOT NULL,
  `per_slot_capacity` int(11) NOT NULL,
  `layer_num` int(11) NOT NULL,
  `per_layer_capacity` int(11) NOT NULL,
  PRIMARY KEY (`model_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  smartrm_monolith.vending_machine_model 的数据：~0 rows (大约)
DELETE FROM `vending_machine_model`;
/*!40000 ALTER TABLE `vending_machine_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `vending_machine_model` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
