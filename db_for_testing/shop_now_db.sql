-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 02, 2015 at 04:16 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `shop_now_db`
--
CREATE DATABASE IF NOT EXISTS `shop_now_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `shop_now_db`;

-- --------------------------------------------------------

--
-- Table structure for table `bought_together`
--

CREATE TABLE IF NOT EXISTS `bought_together` (
  `fk_item1_id` mediumint(9) unsigned NOT NULL,
  `fk_info1_id` tinyint(4) unsigned NOT NULL,
  `fk_item2_id` mediumint(9) unsigned NOT NULL,
  `fk_info2_id` tinyint(4) unsigned NOT NULL,
  KEY `fk_item1_id` (`fk_item1_id`),
  KEY `fk_info1_id` (`fk_info1_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `column_names`
--

CREATE TABLE IF NOT EXISTS `column_names` (
  `fk_info_id` tinyint(4) unsigned NOT NULL,
  `fk_column_id` tinyint(4) unsigned NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`fk_info_id`,`fk_column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `column_names`
--

INSERT INTO `column_names` (`fk_info_id`, `fk_column_id`, `name`) VALUES
(1, 3, 'Type'),
(1, 4, 'Brand'),
(1, 5, 'Color'),
(1, 6, 'Processor manufactur'),
(1, 7, 'Processor'),
(1, 8, 'CPU speed'),
(1, 9, 'Memory size'),
(1, 10, 'Video Adapter'),
(1, 11, 'Storage capacity'),
(1, 12, 'Operating system'),
(1, 13, 'Screen size'),
(1, 14, 'Display resolution'),
(1, 15, 'Battery'),
(1, 16, 'Weight'),
(1, 17, 'Wireless'),
(1, 18, 'Bluetooth'),
(1, 19, 'Card_reader'),
(1, 20, 'Mic line-in jack'),
(1, 21, 'Usb ports'),
(1, 22, 'hdmi inputs'),
(1, 23, 'Built_in_mic'),
(1, 24, 'Memory card reader'),
(1, 25, 'Webcam'),
(1, 26, 'Depth'),
(1, 27, 'Width');

-- --------------------------------------------------------

--
-- Table structure for table `db_info`
--

CREATE TABLE IF NOT EXISTS `db_info` (
  `id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `sub_category_name` varchar(20) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `item_tb_name` varchar(20) NOT NULL,
  `stock_tb_name` varchar(20) NOT NULL,
  `promotion_tb_name` varchar(20) NOT NULL,
  `feature_tb_name` varchar(20) NOT NULL,
  `key_tb_name` varchar(20) NOT NULL,
  `items_count` mediumint(9) unsigned NOT NULL,
  `promotions_count` mediumint(9) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `key_tb_name` (`key_tb_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `db_info`
--

INSERT INTO `db_info` (`id`, `sub_category_name`, `category_name`, `item_tb_name`, `stock_tb_name`, `promotion_tb_name`, `feature_tb_name`, `key_tb_name`, `items_count`, `promotions_count`) VALUES
(1, 'Computers', 'Electronics', 'item_computer', 'stock_computer', 'promotion_computer', 'feature_computer', 'key_computer', 4, 3),
(2, 'Mobiles & Tablets', 'Electronics', 'item_mobile', 'stock_mobile', 'promotion_mobile', 'feature_mobile', 'key_mobile', 0, 0),
(3, 'Audio', 'Electronics', 'item_audio', 'stock_audio', 'promotion_audio', 'feature_audio', 'key_audio', 0, 0),
(4, 'Cameras', 'Electronics', 'item_camera', 'stock_camera', 'promotion_camera', 'feature_camera', 'key_camera', 0, 0),
(5, 'TVs', 'Electronics', 'item_tv', 'stock_tv', 'promotion_tv', 'feature_tv', 'key_tv', 0, 0),
(6, 'Beverages', 'Grocery', 'item_beverage', 'stock_beverage', 'promotion_beverage', 'feature_beverage', 'key_beverage', 0, 0),
(7, 'Food', 'Grocery', 'item_food', 'stock_food', 'promotion_food', 'feature_food', 'key_food', 0, 0),
(8, 'Furniture', 'Furniture', 'item_furniture', 'stock_furniture', 'promotion_furniture', 'feature_furniture', 'key_furniture', 0, 0),
(9, 'Health', 'Health & Beauty', 'item_health', 'stock_health', 'promotion_health', 'feature_health', 'key_health', 0, 0),
(10, 'Toys', 'Toys', 'item_toy', 'stock_toy', 'promotion_toy', 'feature_toy', 'key_toy', 0, 0),
(11, 'Kitchen', 'Kitchen', 'item_kitchen', 'stock_kitchen', 'promotion_kitchen', 'feature_kitchen', 'key_kitchen', 0, 0),
(12, 'Tools', 'Tools', 'item_tool', 'stock_tool', 'promotion_tool', 'feature_tool', 'key_tool', 0, 0),
(13, 'Stationary', 'Stationary', 'item_stationary', 'stock_stationary', 'promotion_stationary', 'feature_stationary', 'key_stationary', 0, 0),
(14, 'Sports', 'Sports & Fitness', 'item_sport', 'stock_sport', 'promotion_sport', 'feature_sport', 'key_sport', 0, 0),
(15, 'Clothes', 'Fashion', 'item_clothes', 'stock_clothes', 'promotion_clothes', 'feature_clothes', 'key_clothes', 0, 0),
(16, 'Books', 'Books', 'item_book', 'stock_book', 'promotion_book', 'feature_book', 'key_book', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `feature_computer`
--

CREATE TABLE IF NOT EXISTS `feature_computer` (
  `id` tinyint(4) NOT NULL,
  `value` varchar(20) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `feature_computer`
--

INSERT INTO `feature_computer` (`id`, `value`) VALUES
(3, 'laptop'),
(4, 'Lenovo'),
(4, 'Dell'),
(4, 'Toshiba'),
(5, 'red'),
(5, 'black'),
(5, 'white'),
(5, 'blue'),
(6, 'intel'),
(7, 'i3'),
(7, 'i5'),
(7, 'i7'),
(8, '2.8 gh'),
(8, '2.5 gh'),
(8, '2.2 gh'),
(8, '3 gh'),
(9, '8 GB'),
(9, '4 GB'),
(9, '6 GB'),
(9, 'ATI radeon 1 GB'),
(10, 'ATI radeon 2 GB'),
(10, 'ATI radeon 3 GB'),
(10, 'Nividea 2 GB'),
(11, '500 GB'),
(11, '1 tera'),
(11, '2 tera'),
(12, 'DOS'),
(12, 'Windows 10'),
(12, 'Windows 8'),
(13, '15.4 inche'),
(13, '13 inches'),
(14, '1366×768'),
(15, '6-cell Lithium-ion b'),
(15, '4-cell Lithium-ion b'),
(16, '3.75 Kg'),
(16, '2 Kg'),
(16, '3.5 Kg'),
(16, '2 Kg'),
(17, '802.11g'),
(18, 'yes, v3.0'),
(18, 'yes, v4.0'),
(19, 'yes'),
(20, 'yes'),
(21, '4 usb-3 ports'),
(21, '3 usb-3 ports'),
(22, 'yes'),
(23, 'yes'),
(24, 'yes'),
(25, 'yes 2 MB'),
(25, 'yes 4 MB'),
(26, '10 mm'),
(26, '5 mm'),
(26, '12 mm'),
(26, '14 mm'),
(27, '35 cm');

-- --------------------------------------------------------

--
-- Table structure for table `items_keys`
--

CREATE TABLE IF NOT EXISTS `items_keys` (
  `fk_info_id` int(11) NOT NULL,
  `keyword` varchar(25) NOT NULL,
  KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items_keys`
--

INSERT INTO `items_keys` (`fk_info_id`, `keyword`) VALUES
(1, 'gaming'),
(1, 'processing'),
(1, 'laptop'),
(1, 'laptops'),
(1, 'desktop'),
(1, 'desktops'),
(1, 'performance'),
(1, 'storage'),
(1, 'memory'),
(1, 'hd'),
(1, 'thin'),
(1, 'light'),
(1, 'computer'),
(1, 'computers'),
(1, 'pc'),
(1, 'lcd'),
(1, 'led'),
(1, 'lenovo'),
(1, 'toshiba'),
(1, 'hp'),
(1, 'speed'),
(1, 'hdmi'),
(1, 'ultrathin'),
(1, 'mac'),
(1, 'windows'),
(1, 'dos');

-- --------------------------------------------------------

--
-- Table structure for table `item_computer`
--

CREATE TABLE IF NOT EXISTS `item_computer` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` varchar(255) NOT NULL,
  `brand` varchar(25) NOT NULL,
  `color` varchar(10) NOT NULL,
  `processor_manufacturer` varchar(25) NOT NULL,
  `processor` varchar(20) NOT NULL,
  `cpu_speed` varchar(10) NOT NULL,
  `memory_size` varchar(10) NOT NULL,
  `video_adapter` varchar(20) NOT NULL,
  `storage_capacity` varchar(10) NOT NULL,
  `operating_system` varchar(20) NOT NULL,
  `screen_size` varchar(10) NOT NULL,
  `display_resolution` varchar(11) NOT NULL,
  `battery` varchar(25) NOT NULL,
  `weight` varchar(10) NOT NULL,
  `wireless_lan_type` varchar(20) NOT NULL,
  `bluetooth` varchar(20) NOT NULL,
  `card_reader_integrated` varchar(20) NOT NULL,
  `microphone_line_in_jack` varchar(20) NOT NULL,
  `usb_ports` varchar(20) NOT NULL,
  `hdmi_inputs` varchar(20) NOT NULL,
  `built_in_microphone` varchar(255) NOT NULL,
  `memory_card_slot` varchar(20) NOT NULL,
  `webcam` varchar(20) NOT NULL,
  `depth` varchar(10) NOT NULL,
  `width` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `item_computer`
--

INSERT INTO `item_computer` (`id`, `name`, `type`, `brand`, `color`, `processor_manufacturer`, `processor`, `cpu_speed`, `memory_size`, `video_adapter`, `storage_capacity`, `operating_system`, `screen_size`, `display_resolution`, `battery`, `weight`, `wireless_lan_type`, `bluetooth`, `card_reader_integrated`, `microphone_line_in_jack`, `usb_ports`, `hdmi_inputs`, `built_in_microphone`, `memory_card_slot`, `webcam`, `depth`, `width`) VALUES
(1, 'Lenovo G570', 'laptop', 'Lenovo', 'black', 'intel', 'i5', '2.8 gh', '4 GB', 'ATI radeon 1 GB', '500 GB', 'DOS', '15.4 inche', '1366×768', '6-cell Lithium-ion batter', '3.75 Kg', '802.11g', 'yes, v3.0', 'yes', 'yes', '4 usb-3 ports', 'yes', 'yes', 'yes', 'yes 2 MB', '10 mm', '35 cm'),
(2, 'Lenovo Yoga', 'laptop', 'Lenovo', 'white', 'intel', 'i7', '2.5 gh', '6 GB', 'ATI radeon 2 GB', '1 tera', 'Windows 10', '15.4 inche', '1366×768', '6-cell Lithium-ion batter', '2 Kg', '802.11g', 'yes, v4.0', 'yes', 'yes', '3 usb-3 ports', 'yes', 'yes', 'yes', 'yes 2 MB', '5 mm', '35 cm'),
(3, 'Dell Inspiron', 'laptop', 'Dell', 'red', 'intel', 'i3', '2.2 gh', '8 GB', 'Nividea 2 GB', '2 tera', 'Windows 8', '13 inches', '1200×768', '4-cell Lithium-ion batter', '3.5 Kg', '802.11g', 'yes, v3.0', 'yes', 'yes', '4 usb-3 ports', 'yes', 'yes', 'yes', 'yes 4 MB', '12 mm', '35 cm'),
(4, 'Toshiba Satellite', 'laptop', 'Toshiba', 'blue', 'intel', 'i7', '3 gh', '8 GB', 'ATI radeon 3 GB', '2 tera', 'DOS', '15.4 inche', '1366×768', '6-cell Lithium-ion batter', '2 Kg', '802.11g', 'yes, v4.0', 'yes', 'yes', '3 usb-3 ports', 'yes', 'yes', 'yes', 'yes 2 MB', '14 mm', '35 cm');

-- --------------------------------------------------------

--
-- Table structure for table `item_watch_list`
--

CREATE TABLE IF NOT EXISTS `item_watch_list` (
  `fk_client_id` int(11) unsigned NOT NULL,
  `fk_item_id` mediumint(9) unsigned NOT NULL,
  `fk_info_id` tinyint(4) unsigned NOT NULL,
  `available` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`fk_client_id`,`fk_item_id`,`fk_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `key_computer`
--

CREATE TABLE IF NOT EXISTS `key_computer` (
  `fk_item_id` int(11) NOT NULL,
  `keyword` int(11) NOT NULL,
  KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE IF NOT EXISTS `promotion` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `start_in` datetime NOT NULL,
  `end_in` datetime NOT NULL,
  `price` float NOT NULL,
  `items_tb_map` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `name`, `start_in`, `end_in`, `price`, `items_tb_map`) VALUES
(1, 'Carefour 1st Festival', '2015-09-18 00:00:00', '2015-09-30 00:00:00', 8000, '1'),
(2, 'Fathala Laptop sale', '2015-09-18 00:00:00', '2015-09-30 00:00:00', 5000, '1');

-- --------------------------------------------------------

--
-- Table structure for table `promotion_computer`
--

CREATE TABLE IF NOT EXISTS `promotion_computer` (
  `fk_promotion_id` mediumint(9) unsigned NOT NULL,
  `fk_item_id` mediumint(9) unsigned NOT NULL,
  `quantity` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`fk_promotion_id`,`fk_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `promotion_computer`
--

INSERT INTO `promotion_computer` (`fk_promotion_id`, `fk_item_id`, `quantity`) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `promotion_watch_list`
--

CREATE TABLE IF NOT EXISTS `promotion_watch_list` (
  `fk_client_id` int(11) unsigned NOT NULL,
  `fk_item_id` mediumint(9) unsigned NOT NULL,
  `fk_info_id` tinyint(4) unsigned NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`fk_client_id`,`fk_item_id`,`fk_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `stock_computer`
--

CREATE TABLE IF NOT EXISTS `stock_computer` (
  `fk_store_id` mediumint(9) unsigned NOT NULL,
  `fk_item_id` mediumint(9) unsigned NOT NULL,
  `quantity` mediumint(9) unsigned NOT NULL,
  `unit_price` float NOT NULL,
  PRIMARY KEY (`fk_store_id`,`fk_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stock_computer`
--

INSERT INTO `stock_computer` (`fk_store_id`, `fk_item_id`, `quantity`, `unit_price`) VALUES
(1, 1, 20, 4000),
(1, 2, 10, 3800),
(2, 2, 10, 5000),
(3, 1, 10, 5000),
(3, 3, 10, 5000),
(4, 2, 30, 6000),
(5, 4, 20, 2500),
(6, 4, 30, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE IF NOT EXISTS `store` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `store_name` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `map_lat` float NOT NULL DEFAULT '0',
  `map_lng` float NOT NULL DEFAULT '0',
  `map_zoom` float NOT NULL DEFAULT '0',
  `items_tb_map` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`id`, `store_name`, `branch_name`, `email`, `mobile`, `telephone`, `address`, `map_lat`, `map_lng`, `map_zoom`, `items_tb_map`) VALUES
(1, 'carefour', 'Down town', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria egypt desert road', 0, 0, 0, '1'),
(2, 'carefour', 'smart village', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria egypt desert road', 0, 0, 0, '1'),
(3, 'carefour', 'elsyooof', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria egypt desert road', 0, 0, 0, '1'),
(4, 'Fathala', 'elsyooof', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria', 0, 0, 0, '1'),
(5, 'Fathala', 'elmontaza', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria', 0, 0, 0, '1'),
(6, 'Fathala', 'san stifano', 'carefourdowntown@carefour.com', '01289887219', '035645789', 'alexandria', 0, 0, 0, '1');

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

CREATE TABLE IF NOT EXISTS `vendor` (
  `id` mediumint(9) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_item`
--

CREATE TABLE IF NOT EXISTS `vendor_item` (
  `fk_vendor_id` mediumint(9) unsigned NOT NULL,
  `fk_item_id` mediumint(9) unsigned NOT NULL,
  `fk_info_id` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`fk_vendor_id`,`fk_item_id`,`fk_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
