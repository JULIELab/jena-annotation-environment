-- MySQL dump 10.11
--
-- Host: localhost    Database: julielab_JaneDB
-- ------------------------------------------------------
-- Server version	5.0.51a-24+lenny2+spu1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AL_statistics`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `AL_statistics` (
  `stat_id` bigint(20) NOT NULL auto_increment,
  `project_id` bigint(20) NOT NULL default '0',
  `seldis` double NOT NULL default '0',
  `sentences` bigint(20) NOT NULL default '0',
  `tokens` bigint(20) NOT NULL default '0',
  `timeEntered` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `corpus_fraction` float NOT NULL default '0',
  `alldis` double NOT NULL default '0',
  `nodis` double NOT NULL default '0',
  `seltime` bigint(20) NOT NULL default '0',
  `traintime` bigint(20) NOT NULL default '0',
  `traindatatime` bigint(20) NOT NULL default '0',
  `testtime` bigint(20) NOT NULL default '0',
  `testdatatime` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`stat_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1781 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `basedata`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `basedata` (
  `basedata_id` bigint(20) NOT NULL auto_increment,
  `project_id` bigint(20) NOT NULL default '0',
  `basedata_file` longtext NOT NULL,
  `uri` varchar(255) NOT NULL default '',
  `status` enum('raw','progress','done') NOT NULL default 'raw',
  `annotator` int(11) NOT NULL default '0',
  `style_file` longtext NOT NULL,
  `AL_TIDSID_file` mediumtext NOT NULL,
  `annotation_time` int(11) default NULL,
  `description` mediumtext,
  PRIMARY KEY  (`basedata_id`),
  KEY `FK_basedata_1` (`project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12952 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `log`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `log` (
  `level` int(11) NOT NULL default '0',
  `logger` varchar(64) NOT NULL default '',
  `message` varchar(255) NOT NULL default '',
  `sequence` int(11) NOT NULL default '0',
  `sourceClass` varchar(64) NOT NULL default '',
  `sourceMethod` varchar(32) NOT NULL default '',
  `threadID` int(11) NOT NULL default '0',
  `timeEntered` datetime NOT NULL default '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `markable`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `markable` (
  `markable_id` bigint(20) NOT NULL auto_increment,
  `basedata_id` bigint(20) NOT NULL default '0',
  `creation_date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `markable_file` mediumtext NOT NULL,
  `mmaxdata_id` bigint(20) NOT NULL default '0',
  `autosave` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`markable_id`),
  KEY `FK_markable_1` (`basedata_id`),
  KEY `FK_markable_2` (`mmaxdata_id`)
) ENGINE=MyISAM AUTO_INCREMENT=100728 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `mmaxdata`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `mmaxdata` (
  `mmaxdata_id` bigint(20) NOT NULL auto_increment,
  `project_id` bigint(20) NOT NULL default '0',
  `schema_file` mediumtext NOT NULL,
  `custom_file` mediumtext NOT NULL,
  `level_name` varchar(20) NOT NULL default '0',
  `main_level` tinyint(1) NOT NULL default '0',
  `at_startup` varchar(20) NOT NULL default 'active',
  PRIMARY KEY  (`mmaxdata_id`),
  KEY `FK_mmaxdata_1` (`project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=376 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `project`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL default '0',
  `creation_date` date NOT NULL default '0000-00-00',
  `description` varchar(255) NOT NULL default '',
  `status` enum('locked','unlocked') NOT NULL default 'locked',
  `mode` enum('AL','default') NOT NULL default 'AL',
  `AL_batchsize` int(11) default '0',
  `AL_corpusfraction` float default '0',
  `AL_status` varchar(10) default '0',
  `AL_iterations` bigint(20) default '0',
  `AL_committee` varchar(20) default NULL,
  `AL_trainprop` float default NULL,
  `name` varchar(20) NOT NULL default '',
  `AL_corpusPOS` varchar(255) default NULL,
  `AL_corpusTOK` varchar(255) default NULL,
  `AL_corpusBIN` varchar(255) default NULL,
  `priolist_file` mediumtext,
  `AL_t2_file` longtext,
  `AL_tags_file` mediumtext,
  PRIMARY KEY  (`project_id`),
  KEY `FK_project_1` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=185 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `user`
--

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `login_name` varchar(10) NOT NULL default '',
  `passwd` varchar(200) NOT NULL default '',
  `complete_name` varchar(100) NOT NULL default '',
  `last_login` date default NULL,
  `supervisor` tinyint(1) NOT NULL default '0',
  `logged_in` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-05-26  8:53:12
