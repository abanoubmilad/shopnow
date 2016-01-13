-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 14, 2015 at 02:44 PM
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
-- Table structure for table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  `notify_rate` varchar(4) NOT NULL,
  `pref_map` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
  `key_learned_tb_name` varchar(20) NOT NULL,
  `items_count` mediumint(9) unsigned NOT NULL,
  `promotions_count` mediumint(9) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `key_tb_name` (`key_tb_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

-- --------------------------------------------------------

--
-- Table structure for table `feature_computer`
--

CREATE TABLE IF NOT EXISTS `feature_computer` (
  `id` tinyint(4) NOT NULL,
  `value` varchar(20) NOT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `items_keys`
--

CREATE TABLE IF NOT EXISTS `items_keys` (
  `fk_info_id` int(11) NOT NULL,
  `keyword` varchar(25) NOT NULL,
  KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10268 ;

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
  `keyword` varchar(25) NOT NULL,
  KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `key_learned_computer`
--

CREATE TABLE IF NOT EXISTS `key_learned_computer` (
  `keyword` varchar(25) NOT NULL,
  `count` int(11) NOT NULL,
  `fk_item_id` mediumint(9) NOT NULL,
  KEY `keyword` (`keyword`),
  KEY `fk_item_id` (`fk_item_id`)
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

-- --------------------------------------------------------

--
-- Table structure for table `promotion_store`
--

CREATE TABLE IF NOT EXISTS `promotion_store` (
  `fk_promotion_id` int(9) NOT NULL,
  `fk_store_id` int(9) NOT NULL,
  PRIMARY KEY (`fk_promotion_id`,`fk_store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=100007 ;

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
