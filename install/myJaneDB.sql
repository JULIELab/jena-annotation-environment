-- MySQL dump 10.11
--
-- Host: localhost    Database: myJaneDB
-- ------------------------------------------------------
-- Server version	5.0.32-Debian_7etch1-log

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

DROP TABLE IF EXISTS `AL_statistics`;
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
) ENGINE=MyISAM AUTO_INCREMENT=1626 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AL_statistics`
--

LOCK TABLES `AL_statistics` WRITE;
/*!40000 ALTER TABLE `AL_statistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `AL_statistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `basedata`
--

DROP TABLE IF EXISTS `basedata`;
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
  PRIMARY KEY  (`basedata_id`),
  KEY `FK_basedata_1` (`project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11852 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `basedata`
--

LOCK TABLES `basedata` WRITE;
/*!40000 ALTER TABLE `basedata` DISABLE KEYS */;
INSERT INTO `basedata` VALUES (11849,130,'<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n<!DOCTYPE words SYSTEM \"words.dtd\">\n<words>\n<word id=\"word_1\">Indonesia</word>\n<word id=\"word_2\">\'s</word>\n<word id=\"word_3\">consumer</word>\n<word id=\"word_4\">price</word>\n<word id=\"word_5\">index</word>\n<word id=\"word_6\">for</word>\n<word id=\"word_7\">January</word>\n<word id=\"word_8\">was</word>\n<word id=\"word_9\">likely</word>\n<word id=\"word_10\">to</word>\n<word id=\"word_11\">show</word>\n<word id=\"word_12\">a</word>\n<word id=\"word_13\">month-on-month</word>\n<word id=\"word_14\">rise</word>\n<word id=\"word_15\">of</word>\n<word id=\"word_16\">1.02-1.04</word>\n<word id=\"word_17\">percent</word>\n<word id=\"word_18\">,</word>\n<word id=\"word_19\">banking</word>\n<word id=\"word_20\">sources</word>\n<word id=\"word_21\">said</word>\n<word id=\"word_22\">on</word>\n<word id=\"word_23\">Tuesday</word>\n<word id=\"word_24\">.</word>\n<word id=\"word_25\">Inflation</word>\n<word id=\"word_26\">in</word>\n<word id=\"word_27\">December</word>\n<word id=\"word_28\">was</word>\n<word id=\"word_29\">0.55</word>\n<word id=\"word_30\">percent</word>\n<word id=\"word_31\">.</word>\n<word id=\"word_32\">They</word>\n<word id=\"word_33\">said</word>\n<word id=\"word_34\">the</word>\n<word id=\"word_35\">anticipated</word>\n<word id=\"word_36\">latest</word>\n<word id=\"word_37\">figure</word>\n<word id=\"word_38\">was</word>\n<word id=\"word_39\">lower</word>\n<word id=\"word_40\">than</word>\n<word id=\"word_41\">the</word>\n<word id=\"word_42\">2.16</word>\n<word id=\"word_43\">percent</word>\n<word id=\"word_44\">posted</word>\n<word id=\"word_45\">in</word>\n<word id=\"word_46\">January</word>\n<word id=\"word_47\">1996</word>\n<word id=\"word_48\">following</word>\n<word id=\"word_49\">relatively</word>\n<word id=\"word_50\">stable</word>\n<word id=\"word_51\">food</word>\n<word id=\"word_52\">prices</word>\n<word id=\"word_53\">ahead</word>\n<word id=\"word_54\">of</word>\n<word id=\"word_55\">the</word>\n<word id=\"word_56\">Eid</word>\n<word id=\"word_57\">al-Fitr</word>\n<word id=\"word_58\">celebration</word>\n<word id=\"word_59\">.</word>\n<word id=\"word_60\">Indonesia</word>\n<word id=\"word_61\">posted</word>\n<word id=\"word_62\">an</word>\n<word id=\"word_63\">inflation</word>\n<word id=\"word_64\">of</word>\n<word id=\"word_65\">6.47</word>\n<word id=\"word_66\">percent</word>\n<word id=\"word_67\">for</word>\n<word id=\"word_68\">the</word>\n<word id=\"word_69\">1996</word>\n<word id=\"word_70\">calendar</word>\n<word id=\"word_71\">year</word>\n<word id=\"word_72\">,</word>\n<word id=\"word_73\">compared</word>\n<word id=\"word_74\">with</word>\n<word id=\"word_75\">8.64</word>\n<word id=\"word_76\">percent</word>\n<word id=\"word_77\">in</word>\n<word id=\"word_78\">1995.</word>\n<word id=\"word_79\">The</word>\n<word id=\"word_80\">government</word>\n<word id=\"word_81\">has</word>\n<word id=\"word_82\">forecast</word>\n<word id=\"word_83\">an</word>\n<word id=\"word_84\">inflation</word>\n<word id=\"word_85\">rate</word>\n<word id=\"word_86\">of</word>\n<word id=\"word_87\">6.0</word>\n<word id=\"word_88\">percent</word>\n<word id=\"word_89\">for</word>\n<word id=\"word_90\">the</word>\n<word id=\"word_91\">1997/98</word>\n<word id=\"word_92\">fiscal</word>\n<word id=\"word_93\">year</word>\n<word id=\"word_94\">starting</word>\n<word id=\"word_95\">on</word>\n<word id=\"word_96\">April</word>\n<word id=\"word_97\">1</word>\n<word id=\"word_98\">.</word>\n<word id=\"word_99\">--</word>\n<word id=\"word_100\">Jakarta</word>\n<word id=\"word_101\">newsroom</word>\n<word id=\"word_102\">+6221</word>\n<word id=\"word_103\">384-6364</word>\n</words>\n','355815','raw',0,'<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"\nxmlns:mmax=\"org.eml.MMAX2.discourse.MMAX2DiscourseLoader\"\nxmlns:bioentities=\"www.eml.org/NameSpaces/bioentities\"\nxmlns:sentence=\"www.eml.org/NameSpaces/sentence\">\n<xsl:output method=\"text\" indent=\"no\" omit-xml-declaration=\"yes\"/>\n<xsl:strip-space elements=\"*\"/>\n\n\n<xsl:template match=\"words\">\n\n  <xsl:apply-templates/>\n\n</xsl:template>\n\n<xsl:template match=\"word\">\n\n  <xsl:value-of select=\"mmax:registerDiscourseElement(@id)\"/>\n\n  <xsl:apply-templates select=\"mmax:getStartedMarkables(@id)\" mode=\"opening\"/>\n\n<xsl:value-of select=\"mmax:setDiscourseElementStart()\"/>\n<xsl:value-of select=\"mmax:startBold()\"/>\n   <xsl:apply-templates/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n  <xsl:value-of select=\"mmax:setDiscourseElementEnd()\"/>\n\n  <xsl:apply-templates select=\"mmax:getEndedMarkables(@id)\" mode=\"closing\"/>\n\n<xsl:text> </xsl:text>\n\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"opening\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addLeftMarkableHandle(@mmax_level, @id, \'[\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"closing\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addRightMarkableHandle(@mmax_level, @id, \']\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n\n<xsl:template match=\"sentence:markable\" mode=\"closing\">\n\n<xsl:value-of select=\"mmax:startSubscript()\"/>\n <xsl:text>\n</xsl:text>\n<xsl:value-of select=\"mmax:endSubscript()\"/>\n\n</xsl:template>\n\n\n</xsl:stylesheet>','',0),(11850,130,'<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n<!DOCTYPE words SYSTEM \"words.dtd\">\n<words>\n<word id=\"word_1\">Data</word>\n<word id=\"word_2\">storage</word>\n<word id=\"word_3\">products</word>\n<word id=\"word_4\">maker</word>\n<word id=\"word_5\">Benelux</word>\n<word id=\"word_6\">International</word>\n<word id=\"word_7\">Ltd</word>\n<word id=\"word_8\">said</word>\n<word id=\"word_9\">on</word>\n<word id=\"word_10\">Tuesday</word>\n<word id=\"word_11\">a</word>\n<word id=\"word_12\">capital</word>\n<word id=\"word_13\">injection</word>\n<word id=\"word_14\">and</word>\n<word id=\"word_15\">debt</word>\n<word id=\"word_16\">restructuring</word>\n<word id=\"word_17\">agreement</word>\n<word id=\"word_18\">to</word>\n<word id=\"word_19\">solve</word>\n<word id=\"word_20\">its</word>\n<word id=\"word_21\">liquidity</word>\n<word id=\"word_22\">problems</word>\n<word id=\"word_23\">was</word>\n<word id=\"word_24\">now</word>\n<word id=\"word_25\">in</word>\n<word id=\"word_26\">effect</word>\n<word id=\"word_27\">.</word>\n<word id=\"word_28\">Under</word>\n<word id=\"word_29\">the</word>\n<word id=\"word_30\">agreement</word>\n<word id=\"word_31\">,</word>\n<word id=\"word_32\">executed</word>\n<word id=\"word_33\">by</word>\n<word id=\"word_34\">all</word>\n<word id=\"word_35\">parties</word>\n<word id=\"word_36\">on</word>\n<word id=\"word_37\">January</word>\n<word id=\"word_38\">10</word>\n<word id=\"word_39\">,</word>\n<word id=\"word_40\">the</word>\n<word id=\"word_41\">company</word>\n<word id=\"word_42\">proposes</word>\n<word id=\"word_43\">to</word>\n<word id=\"word_44\">raise</word>\n<word id=\"word_45\">about</word>\n<word id=\"word_46\">HK$175</word>\n<word id=\"word_47\">million</word>\n<word id=\"word_48\">through</word>\n<word id=\"word_49\">a</word>\n<word id=\"word_50\">rights</word>\n<word id=\"word_51\">issue</word>\n<word id=\"word_52\">of</word>\n<word id=\"word_53\">at</word>\n<word id=\"word_54\">least</word>\n<word id=\"word_55\">1.75</word>\n<word id=\"word_56\">billion</word>\n<word id=\"word_57\">new</word>\n<word id=\"word_58\">shares</word>\n<word id=\"word_59\">at</word>\n<word id=\"word_60\">HK$0.10</word>\n<word id=\"word_61\">each</word>\n<word id=\"word_62\">on</word>\n<word id=\"word_63\">the</word>\n<word id=\"word_64\">basis</word>\n<word id=\"word_65\">of</word>\n<word id=\"word_66\">six</word>\n<word id=\"word_67\">rights</word>\n<word id=\"word_68\">shares</word>\n<word id=\"word_69\">for</word>\n<word id=\"word_70\">every</word>\n<word id=\"word_71\">existing</word>\n<word id=\"word_72\">issued</word>\n<word id=\"word_73\">share</word>\n<word id=\"word_74\">held</word>\n<word id=\"word_75\">,</word>\n<word id=\"word_76\">Benelux</word>\n<word id=\"word_77\">said</word>\n<word id=\"word_78\">in</word>\n<word id=\"word_79\">a</word>\n<word id=\"word_80\">statement</word>\n<word id=\"word_81\">.</word>\n<word id=\"word_82\">Prestbury</word>\n<word id=\"word_83\">Incorporated</word>\n<word id=\"word_84\">Ltd</word>\n<word id=\"word_85\">,</word>\n<word id=\"word_86\">owned</word>\n<word id=\"word_87\">by</word>\n<word id=\"word_88\">Indonesian</word>\n<word id=\"word_89\">businessman</word>\n<word id=\"word_90\">Cahyadi</word>\n<word id=\"word_91\">Kumala</word>\n<word id=\"word_92\">,</word>\n<word id=\"word_93\">also</word>\n<word id=\"word_94\">agreed</word>\n<word id=\"word_95\">to</word>\n<word id=\"word_96\">subscribe</word>\n<word id=\"word_97\">for</word>\n<word id=\"word_98\">600</word>\n<word id=\"word_99\">million</word>\n<word id=\"word_100\">new</word>\n<word id=\"word_101\">shares</word>\n<word id=\"word_102\">at</word>\n<word id=\"word_103\">HK$0.10</word>\n<word id=\"word_104\">each</word>\n<word id=\"word_105\">,</word>\n<word id=\"word_106\">it</word>\n<word id=\"word_107\">said</word>\n<word id=\"word_108\">.</word>\n<word id=\"word_109\">Net</word>\n<word id=\"word_110\">proceeds</word>\n<word id=\"word_111\">of</word>\n<word id=\"word_112\">the</word>\n<word id=\"word_113\">rights</word>\n<word id=\"word_114\">issue</word>\n<word id=\"word_115\">and</word>\n<word id=\"word_116\">the</word>\n<word id=\"word_117\">subscription</word>\n<word id=\"word_118\">were</word>\n<word id=\"word_119\">estimated</word>\n<word id=\"word_120\">at</word>\n<word id=\"word_121\">HK$232</word>\n<word id=\"word_122\">million</word>\n<word id=\"word_123\">,</word>\n<word id=\"word_124\">it</word>\n<word id=\"word_125\">said</word>\n<word id=\"word_126\">.</word>\n<word id=\"word_127\">The</word>\n<word id=\"word_128\">rights</word>\n<word id=\"word_129\">issue</word>\n<word id=\"word_130\">and</word>\n<word id=\"word_131\">subscription</word>\n<word id=\"word_132\">price</word>\n<word id=\"word_133\">represents</word>\n<word id=\"word_134\">a</word>\n<word id=\"word_135\">discount</word>\n<word id=\"word_136\">of</word>\n<word id=\"word_137\">about</word>\n<word id=\"word_138\">66.7</word>\n<word id=\"word_139\">percent</word>\n<word id=\"word_140\">to</word>\n<word id=\"word_141\">the</word>\n<word id=\"word_142\">stock</word>\n<word id=\"word_143\">\'s</word>\n<word id=\"word_144\">last</word>\n<word id=\"word_145\">close</word>\n<word id=\"word_146\">of</word>\n<word id=\"word_147\">HK$0.30</word>\n<word id=\"word_148\">on</word>\n<word id=\"word_149\">February</word>\n<word id=\"word_150\">2</word>\n<word id=\"word_151\">,</word>\n<word id=\"word_152\">1996</word>\n<word id=\"word_153\">,</word>\n<word id=\"word_154\">before</word>\n<word id=\"word_155\">trading</word>\n<word id=\"word_156\">was</word>\n<word id=\"word_157\">suspended</word>\n<word id=\"word_158\">,</word>\n<word id=\"word_159\">the</word>\n<word id=\"word_160\">company</word>\n<word id=\"word_161\">said</word>\n<word id=\"word_162\">.</word>\n<word id=\"word_163\">Benelux</word>\n<word id=\"word_164\">said</word>\n<word id=\"word_165\">it</word>\n<word id=\"word_166\">planned</word>\n<word id=\"word_167\">to</word>\n<word id=\"word_168\">use</word>\n<word id=\"word_169\">about</word>\n<word id=\"word_170\">HK$100</word>\n<word id=\"word_171\">million</word>\n<word id=\"word_172\">of</word>\n<word id=\"word_173\">the</word>\n<word id=\"word_174\">proceeds</word>\n<word id=\"word_175\">to</word>\n<word id=\"word_176\">settle</word>\n<word id=\"word_177\">unsecured</word>\n<word id=\"word_178\">debt</word>\n<word id=\"word_179\">and</word>\n<word id=\"word_180\">HK$60</word>\n<word id=\"word_181\">million</word>\n<word id=\"word_182\">to</word>\n<word id=\"word_183\">repay</word>\n<word id=\"word_184\">working</word>\n<word id=\"word_185\">capital</word>\n<word id=\"word_186\">facilities</word>\n<word id=\"word_187\">secured</word>\n<word id=\"word_188\">by</word>\n<word id=\"word_189\">Prestbury</word>\n<word id=\"word_190\">,</word>\n<word id=\"word_191\">with</word>\n<word id=\"word_192\">the</word>\n<word id=\"word_193\">balance</word>\n<word id=\"word_194\">as</word>\n<word id=\"word_195\">additional</word>\n<word id=\"word_196\">working</word>\n<word id=\"word_197\">capital</word>\n<word id=\"word_198\">.</word>\n<word id=\"word_199\">Benelux</word>\n<word id=\"word_200\">said</word>\n<word id=\"word_201\">its</word>\n<word id=\"word_202\">banks</word>\n<word id=\"word_203\">had</word>\n<word id=\"word_204\">agreed</word>\n<word id=\"word_205\">to</word>\n<word id=\"word_206\">a</word>\n<word id=\"word_207\">proposal</word>\n<word id=\"word_208\">involving</word>\n<word id=\"word_209\">the</word>\n<word id=\"word_210\">full</word>\n<word id=\"word_211\">and</word>\n<word id=\"word_212\">final</word>\n<word id=\"word_213\">settlement</word>\n<word id=\"word_214\">of</word>\n<word id=\"word_215\">all</word>\n<word id=\"word_216\">unsecured</word>\n<word id=\"word_217\">outstanding</word>\n<word id=\"word_218\">debt</word>\n<word id=\"word_219\">of</word>\n<word id=\"word_220\">about</word>\n<word id=\"word_221\">HK$500</word>\n<word id=\"word_222\">million</word>\n<word id=\"word_223\">on</word>\n<word id=\"word_224\">completion</word>\n<word id=\"word_225\">of</word>\n<word id=\"word_226\">the</word>\n<word id=\"word_227\">restructuring</word>\n<word id=\"word_228\">.</word>\n<word id=\"word_229\">Benelux</word>\n<word id=\"word_230\">,</word>\n<word id=\"word_231\">which</word>\n<word id=\"word_232\">has</word>\n<word id=\"word_233\">been</word>\n<word id=\"word_234\">suffering</word>\n<word id=\"word_235\">from</word>\n<word id=\"word_236\">severe</word>\n<word id=\"word_237\">liquidity</word>\n<word id=\"word_238\">problems</word>\n<word id=\"word_239\">since</word>\n<word id=\"word_240\">the</word>\n<word id=\"word_241\">begining</word>\n<word id=\"word_242\">of</word>\n<word id=\"word_243\">1996</word>\n<word id=\"word_244\">,</word>\n<word id=\"word_245\">said</word>\n<word id=\"word_246\">the</word>\n<word id=\"word_247\">proposals</word>\n<word id=\"word_248\">were</word>\n<word id=\"word_249\">conditional</word>\n<word id=\"word_250\">and</word>\n<word id=\"word_251\">subject</word>\n<word id=\"word_252\">to</word>\n<word id=\"word_253\">shareholder</word>\n<word id=\"word_254\">approval</word>\n<word id=\"word_255\">.</word>\n<word id=\"word_256\">--</word>\n<word id=\"word_257\">Hong</word>\n<word id=\"word_258\">Kong</word>\n<word id=\"word_259\">Newsroom</word>\n<word id=\"word_260\">(</word>\n<word id=\"word_261\">852</word>\n<word id=\"word_262\">)</word>\n<word id=\"word_263\">2843</word>\n<word id=\"word_264\">6369</word>\n</words>\n','355816','raw',18,'<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"\nxmlns:mmax=\"org.eml.MMAX2.discourse.MMAX2DiscourseLoader\"\nxmlns:bioentities=\"www.eml.org/NameSpaces/bioentities\"\nxmlns:sentence=\"www.eml.org/NameSpaces/sentence\">\n<xsl:output method=\"text\" indent=\"no\" omit-xml-declaration=\"yes\"/>\n<xsl:strip-space elements=\"*\"/>\n\n\n<xsl:template match=\"words\">\n\n  <xsl:apply-templates/>\n\n</xsl:template>\n\n<xsl:template match=\"word\">\n\n  <xsl:value-of select=\"mmax:registerDiscourseElement(@id)\"/>\n\n  <xsl:apply-templates select=\"mmax:getStartedMarkables(@id)\" mode=\"opening\"/>\n\n<xsl:value-of select=\"mmax:setDiscourseElementStart()\"/>\n<xsl:value-of select=\"mmax:startBold()\"/>\n   <xsl:apply-templates/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n  <xsl:value-of select=\"mmax:setDiscourseElementEnd()\"/>\n\n  <xsl:apply-templates select=\"mmax:getEndedMarkables(@id)\" mode=\"closing\"/>\n\n<xsl:text> </xsl:text>\n\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"opening\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addLeftMarkableHandle(@mmax_level, @id, \'[\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"closing\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addRightMarkableHandle(@mmax_level, @id, \']\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n\n<xsl:template match=\"sentence:markable\" mode=\"closing\">\n\n<xsl:value-of select=\"mmax:startSubscript()\"/>\n <xsl:text>\n</xsl:text>\n<xsl:value-of select=\"mmax:endSubscript()\"/>\n\n</xsl:template>\n\n\n</xsl:stylesheet>','',39),(11851,130,'<?xml version=\'1.0\' encoding=\'UTF-8\'?>\n<!DOCTYPE words SYSTEM \"words.dtd\">\n<words>\n<word id=\"word_1\">A</word>\n<word id=\"word_2\">five-year</word>\n<word id=\"word_3\">bank</word>\n<word id=\"word_4\">debenture</word>\n<word id=\"word_5\">with</word>\n<word id=\"word_6\">a</word>\n<word id=\"word_7\">2.15</word>\n<word id=\"word_8\">percent</word>\n<word id=\"word_9\">coupon</word>\n<word id=\"word_10\">issued</word>\n<word id=\"word_11\">in</word>\n<word id=\"word_12\">January</word>\n<word id=\"word_13\">by</word>\n<word id=\"word_14\">Nippon</word>\n<word id=\"word_15\">Credit</word>\n<word id=\"word_16\">Bank</word>\n<word id=\"word_17\">Ltd</word>\n<word id=\"word_18\">(</word>\n<word id=\"word_19\">NCB</word>\n<word id=\"word_20\">)</word>\n<word id=\"word_21\">was</word>\n<word id=\"word_22\">traded</word>\n<word id=\"word_23\">at</word>\n<word id=\"word_24\">a</word>\n<word id=\"word_25\">yield</word>\n<word id=\"word_26\">of</word>\n<word id=\"word_27\">4.10</word>\n<word id=\"word_28\">percent</word>\n<word id=\"word_29\">in</word>\n<word id=\"word_30\">the</word>\n<word id=\"word_31\">secondary</word>\n<word id=\"word_32\">market</word>\n<word id=\"word_33\">on</word>\n<word id=\"word_34\">Tuesday</word>\n<word id=\"word_35\">,</word>\n<word id=\"word_36\">up</word>\n<word id=\"word_37\">from</word>\n<word id=\"word_38\">4.0</word>\n<word id=\"word_39\">percent</word>\n<word id=\"word_40\">on</word>\n<word id=\"word_41\">Monday</word>\n<word id=\"word_42\">,</word>\n<word id=\"word_43\">market</word>\n<word id=\"word_44\">sources</word>\n<word id=\"word_45\">said</word>\n<word id=\"word_46\">.</word>\n<word id=\"word_47\">But</word>\n<word id=\"word_48\">the</word>\n<word id=\"word_49\">traded</word>\n<word id=\"word_50\">amount</word>\n<word id=\"word_51\">was</word>\n<word id=\"word_52\">small</word>\n<word id=\"word_53\">,</word>\n<word id=\"word_54\">they</word>\n<word id=\"word_55\">added</word>\n<word id=\"word_56\">.</word>\n<word id=\"word_57\">Meanwhile</word>\n<word id=\"word_58\">,</word>\n<word id=\"word_59\">a</word>\n<word id=\"word_60\">1.6</word>\n<word id=\"word_61\">percent</word>\n<word id=\"word_62\">five-year</word>\n<word id=\"word_63\">debenture</word>\n<word id=\"word_64\">issued</word>\n<word id=\"word_65\">in</word>\n<word id=\"word_66\">January</word>\n<word id=\"word_67\">by</word>\n<word id=\"word_68\">the</word>\n<word id=\"word_69\">Industrial</word>\n<word id=\"word_70\">Bank</word>\n<word id=\"word_71\">of</word>\n<word id=\"word_72\">Japan</word>\n<word id=\"word_73\">(</word>\n<word id=\"word_74\">IBJ</word>\n<word id=\"word_75\">)</word>\n<word id=\"word_76\">was</word>\n<word id=\"word_77\">traded</word>\n<word id=\"word_78\">at</word>\n<word id=\"word_79\">1.62</word>\n<word id=\"word_80\">percent</word>\n<word id=\"word_81\">.</word>\n<word id=\"word_82\">Asked</word>\n<word id=\"word_83\">about</word>\n<word id=\"word_84\">the</word>\n<word id=\"word_85\">rise</word>\n<word id=\"word_86\">in</word>\n<word id=\"word_87\">debenture</word>\n<word id=\"word_88\">yields</word>\n<word id=\"word_89\">,</word>\n<word id=\"word_90\">an</word>\n<word id=\"word_91\">official</word>\n<word id=\"word_92\">at</word>\n<word id=\"word_93\">the</word>\n<word id=\"word_94\">NCB</word>\n<word id=\"word_95\">said</word>\n<word id=\"word_96\">some</word>\n<word id=\"word_97\">institutional</word>\n<word id=\"word_98\">investors</word>\n<word id=\"word_99\">were</word>\n<word id=\"word_100\">selling</word>\n<word id=\"word_101\">ahead</word>\n<word id=\"word_102\">of</word>\n<word id=\"word_103\">the</word>\n<word id=\"word_104\">end</word>\n<word id=\"word_105\">of</word>\n<word id=\"word_106\">the</word>\n<word id=\"word_107\">fiscal</word>\n<word id=\"word_108\">year</word>\n<word id=\"word_109\">on</word>\n<word id=\"word_110\">March</word>\n<word id=\"word_111\">31</word>\n<word id=\"word_112\">.</word>\n<word id=\"word_113\">&quot;</word>\n<word id=\"word_114\">As</word>\n<word id=\"word_115\">we</word>\n<word id=\"word_116\">have</word>\n<word id=\"word_117\">slightly</word>\n<word id=\"word_118\">reduced</word>\n<word id=\"word_119\">the</word>\n<word id=\"word_120\">amount</word>\n<word id=\"word_121\">of</word>\n<word id=\"word_122\">five-year</word>\n<word id=\"word_123\">debenture</word>\n<word id=\"word_124\">issues</word>\n<word id=\"word_125\">in</word>\n<word id=\"word_126\">the</word>\n<word id=\"word_127\">past</word>\n<word id=\"word_128\">two</word>\n<word id=\"word_129\">months</word>\n<word id=\"word_130\">,</word>\n<word id=\"word_131\">selling</word>\n<word id=\"word_132\">pressure</word>\n<word id=\"word_133\">is</word>\n<word id=\"word_134\">likely</word>\n<word id=\"word_135\">to</word>\n<word id=\"word_136\">ease</word>\n<word id=\"word_137\">,</word>\n<word id=\"word_138\">&quot;</word>\n<word id=\"word_139\">the</word>\n<word id=\"word_140\">official</word>\n<word id=\"word_141\">said</word>\n<word id=\"word_142\">.</word>\n<word id=\"word_143\">&quot;</word>\n<word id=\"word_144\">The</word>\n<word id=\"word_145\">recent</word>\n<word id=\"word_146\">rise</word>\n<word id=\"word_147\">in</word>\n<word id=\"word_148\">the</word>\n<word id=\"word_149\">NCB</word>\n<word id=\"word_150\">debenture</word>\n<word id=\"word_151\">yield</word>\n<word id=\"word_152\">seems</word>\n<word id=\"word_153\">to</word>\n<word id=\"word_154\">have</word>\n<word id=\"word_155\">gone</word>\n<word id=\"word_156\">too</word>\n<word id=\"word_157\">far</word>\n<word id=\"word_158\">,</word>\n<word id=\"word_159\">&quot;</word>\n<word id=\"word_160\">a</word>\n<word id=\"word_161\">brokerage</word>\n<word id=\"word_162\">house</word>\n<word id=\"word_163\">trader</word>\n<word id=\"word_164\">said</word>\n<word id=\"word_165\">.</word>\n<word id=\"word_166\">--</word>\n<word id=\"word_167\">Tokyo</word>\n<word id=\"word_168\">Treasury</word>\n<word id=\"word_169\">Desk</word>\n<word id=\"word_170\">+81-3-3432-9780</word>\n</words>\n','355817','raw',0,'<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"\nxmlns:mmax=\"org.eml.MMAX2.discourse.MMAX2DiscourseLoader\"\nxmlns:bioentities=\"www.eml.org/NameSpaces/bioentities\"\nxmlns:sentence=\"www.eml.org/NameSpaces/sentence\">\n<xsl:output method=\"text\" indent=\"no\" omit-xml-declaration=\"yes\"/>\n<xsl:strip-space elements=\"*\"/>\n\n\n<xsl:template match=\"words\">\n\n  <xsl:apply-templates/>\n\n</xsl:template>\n\n<xsl:template match=\"word\">\n\n  <xsl:value-of select=\"mmax:registerDiscourseElement(@id)\"/>\n\n  <xsl:apply-templates select=\"mmax:getStartedMarkables(@id)\" mode=\"opening\"/>\n\n<xsl:value-of select=\"mmax:setDiscourseElementStart()\"/>\n<xsl:value-of select=\"mmax:startBold()\"/>\n   <xsl:apply-templates/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n  <xsl:value-of select=\"mmax:setDiscourseElementEnd()\"/>\n\n  <xsl:apply-templates select=\"mmax:getEndedMarkables(@id)\" mode=\"closing\"/>\n\n<xsl:text> </xsl:text>\n\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"opening\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addLeftMarkableHandle(@mmax_level, @id, \'[\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n<xsl:template match=\"bioentities:markable\" mode=\"closing\">\n<xsl:value-of select=\"mmax:startBold()\"/>\n<xsl:value-of select=\"mmax:addRightMarkableHandle(@mmax_level, @id, \']\')\"/>\n<xsl:value-of select=\"mmax:endBold()\"/>\n</xsl:template>\n\n\n<xsl:template match=\"sentence:markable\" mode=\"closing\">\n\n<xsl:value-of select=\"mmax:startSubscript()\"/>\n <xsl:text>\n</xsl:text>\n<xsl:value-of select=\"mmax:endSubscript()\"/>\n\n</xsl:template>\n\n\n</xsl:stylesheet>','',0);
/*!40000 ALTER TABLE `basedata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
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

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `markable`
--

DROP TABLE IF EXISTS `markable`;
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
) ENGINE=MyISAM AUTO_INCREMENT=96377 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `markable`
--

LOCK TABLES `markable` WRITE;
/*!40000 ALTER TABLE `markable` DISABLE KEYS */;
INSERT INTO `markable` VALUES (96369,11849,'2007-07-25 07:54:13','<?xml version=\"1.0\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/sentence\">\n<markable id=\"markable_1\" span=\"word_1..word_24\"/>\n<markable id=\"markable_2\" span=\"word_25..word_31\"/>\n<markable id=\"markable_3\" span=\"word_32..word_59\"/>\n<markable id=\"markable_4\" span=\"word_60..word_98\"/>\n<markable id=\"markable_5\" span=\"word_99..word_103\"/>\n</markables>\n',269,0),(96370,11849,'2007-07-25 07:54:13','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/muc\">\n</markables>\n',268,0),(96371,11850,'2007-07-25 07:54:13','<?xml version=\"1.0\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/sentence\">\n<markable id=\"markable_1\" span=\"word_1..word_27\"/>\n<markable id=\"markable_2\" span=\"word_28..word_81\"/>\n<markable id=\"markable_3\" span=\"word_82..word_108\"/>\n<markable id=\"markable_4\" span=\"word_109..word_126\"/>\n<markable id=\"markable_5\" span=\"word_127..word_162\"/>\n<markable id=\"markable_6\" span=\"word_163..word_198\"/>\n<markable id=\"markable_7\" span=\"word_199..word_228\"/>\n<markable id=\"markable_8\" span=\"word_229..word_255\"/>\n<markable id=\"markable_9\" span=\"word_256..word_264\"/>\n</markables>\n',269,0),(96372,11850,'2007-07-25 07:54:13','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/muc\">\n</markables>\n',268,0),(96373,11851,'2007-07-25 07:54:13','<?xml version=\"1.0\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/sentence\">\n<markable id=\"markable_1\" span=\"word_1..word_46\"/>\n<markable id=\"markable_2\" span=\"word_47..word_56\"/>\n<markable id=\"markable_3\" span=\"word_57..word_81\"/>\n<markable id=\"markable_4\" span=\"word_82..word_112\"/>\n<markable id=\"markable_5\" span=\"word_113..word_142\"/>\n<markable id=\"markable_6\" span=\"word_143..word_165\"/>\n<markable id=\"markable_7\" span=\"word_166..word_170\"/>\n</markables>\n',269,0),(96374,11851,'2007-07-25 07:54:13','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/muc\">\n</markables>\n',268,0),(96375,11850,'2007-07-25 08:01:36','<?xml version=\"1.0\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/sentence\">\n<markable id=\"markable_1\" span=\"word_1..word_27\"/>\n<markable id=\"markable_2\" span=\"word_28..word_81\"/>\n<markable id=\"markable_3\" span=\"word_82..word_108\"/>\n<markable id=\"markable_4\" span=\"word_109..word_126\"/>\n<markable id=\"markable_5\" span=\"word_127..word_162\"/>\n<markable id=\"markable_6\" span=\"word_163..word_198\"/>\n<markable id=\"markable_7\" span=\"word_199..word_228\"/>\n<markable id=\"markable_8\" span=\"word_229..word_255\"/>\n<markable id=\"markable_9\" span=\"word_256..word_264\"/>\n</markables>\n',269,0),(96376,11850,'2007-07-25 08:01:36','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE markables SYSTEM \"markables.dtd\">\n<markables xmlns=\"www.eml.org/NameSpaces/muc\">\n</markables>\n',268,0);
/*!40000 ALTER TABLE `markable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mmaxdata`
--

DROP TABLE IF EXISTS `mmaxdata`;
CREATE TABLE `mmaxdata` (
  `mmaxdata_id` bigint(20) NOT NULL auto_increment,
  `project_id` bigint(20) NOT NULL default '0',
  `schema_file` mediumtext NOT NULL,
  `custom_file` mediumtext NOT NULL,
  `level_name` varchar(20) NOT NULL default '0',
  `main_level` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`mmaxdata_id`),
  KEY `FK_mmaxdata_1` (`project_id`)
) ENGINE=MyISAM AUTO_INCREMENT=270 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mmaxdata`
--

LOCK TABLES `mmaxdata` WRITE;
/*!40000 ALTER TABLE `mmaxdata` DISABLE KEYS */;
INSERT INTO `mmaxdata` VALUES (268,130,'<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<annotationscheme>\n\n  <attribute id=\"muc\" name=\"muc\" text=\"\" type=\"nominal_button\">\n    <value id=\"organization\" name=\"organization\"/>\n	<value id=\"person\" name=\"person\"/>\n	<value id=\"location\" name=\"location\"/>\n   </attribute>\n\n</annotationscheme>\n\n\n\n','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<customization>\r\n<rule pattern=\"muc={person}\" style=\"background=green\"/>\r\n<rule pattern=\"muc={organization}\" style=\"background=blue\"/>\r\n<rule pattern=\"muc={location}\" style=\"background=red\"/>\r\n</customization>\r\n','muc',1),(269,130,'<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<annotationscheme>\n</annotationscheme>\n','<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<customization>\n</customization>\n','sentence',0);
/*!40000 ALTER TABLE `mmaxdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
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
) ENGINE=MyISAM AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (130,18,'2007-07-25','news paper text, can annotate person, location, and organisation (MUC)','unlocked','default',0,0,'idle',0,NULL,NULL,'test project',NULL,NULL,NULL,'person\nlocation\norganization',NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `login_name` varchar(10) NOT NULL default '',
  `passwd` varchar(200) NOT NULL default '',
  `complete_name` varchar(100) NOT NULL default '',
  `last_login` date default NULL,
  `supervisor` tinyint(1) NOT NULL default '0',
  `logged_in` tinyint(1) NOT NULL default '0',
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin','admin','Annotation Administrator','2007-07-25',1,0),(18,'test','test','test user','2007-07-25',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2007-07-25  7:57:22
