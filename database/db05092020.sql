/*
SQLyog Community Edition- MySQL GUI v7.01 
MySQL - 5.0.27-community-nt : Database - multipleopd
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`multipleopd` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `multipleopd`;

/*Table structure for table `appointment` */

DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `username` varchar(100) default NULL,
  `emailid` varchar(100) default NULL,
  `phonenumber` varchar(100) default NULL,
  `doctor` varchar(100) default NULL,
  `specialization` varchar(100) default NULL,
  `date` varchar(100) default NULL,
  `time` varchar(100) default NULL,
  `status` varchar(100) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `statusbyuser` varchar(100) default 'Pending',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `appointment` */

insert  into `appointment`(`username`,`emailid`,`phonenumber`,`doctor`,`specialization`,`date`,`time`,`status`,`id`,`statusbyuser`) values ('c','c@gmail.com','9029220209','n','Heart','17/2/2017','1:00 to 2:00','checking',7,'Done'),('c','c@gmail.com','9029220209','n','Heart','17/2/2017','2:00 to 3:00','Pending',8,'Pending'),('b','b@gm.c','8655221446','n','Heart','20/2/2017','2:00 to 3:00','Pending',9,'Pending');

/*Table structure for table `dataappointment` */

DROP TABLE IF EXISTS `dataappointment`;

CREATE TABLE `dataappointment` (
  `username` varchar(100) default NULL,
  `emailid` varchar(100) default NULL,
  `phonenumber` varchar(100) default NULL,
  `doctor` varchar(100) default NULL,
  `specialization` varchar(100) default NULL,
  `date` varchar(100) default NULL,
  `time` varchar(100) default NULL,
  `status` varchar(100) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `statusbyuser` varchar(100) default 'Pending',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dataappointment` */

/*Table structure for table `details` */

DROP TABLE IF EXISTS `details`;

CREATE TABLE `details` (
  `username` varchar(150) default NULL,
  `filename` varchar(150) default NULL,
  `path` varchar(1500) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `details` */

insert  into `details`(`username`,`filename`,`path`) values ('a','IMG_20151113_103136.jpg','http://192.168.0.112:8080/AArogyamJava/images/IMG_20151113_103136.jpg'),('xyz','P_20150807_074757.jpg','G:\\JAVA Final project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\AArogyamJava\\images/P_20150807_074757.jpg'),('a','targa1.png','G:\\AQA\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\AArogyamJava\\images/targa1.png'),('l','IMG-20160819-WA0000.jpg','E:\\2016 2017 project backup\\013 location based augmented reality\\java\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\DoctorPatientInteraction\\images/IMG-20160819-WA0000.jpg');

/*Table structure for table `doctoravail` */

DROP TABLE IF EXISTS `doctoravail`;

CREATE TABLE `doctoravail` (
  `doctorname` varchar(100) default NULL,
  `timing` varchar(100) default NULL,
  `day` varchar(100) default NULL,
  `clinic` varchar(100) default NULL,
  `id` int(11) NOT NULL auto_increment,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctoravail` */

insert  into `doctoravail`(`doctorname`,`timing`,`day`,`clinic`,`id`) values ('n','12:00 to 1:00','Friday','maya hospital',3),('n','1:00 to 2:00','Friday','maya hospital',4),('n','2:00 to 3:00','Friday','maya hospital',5),('n','2:00 to 3:00','Saturday','maya hospital',6),('n','2:00 to 3:00','Monday','maya hospital',7);

/*Table structure for table `doctoravailability` */

DROP TABLE IF EXISTS `doctoravailability`;

CREATE TABLE `doctoravailability` (
  `doctorname` varchar(1000) default NULL,
  `time` varchar(1000) default NULL,
  `clinic` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctoravailability` */

insert  into `doctoravailability`(`doctorname`,`time`,`clinic`) values ('n','12:00 to 1:00','dgfdg');

/*Table structure for table `doctorinformation` */

DROP TABLE IF EXISTS `doctorinformation`;

CREATE TABLE `doctorinformation` (
  `username` varchar(100) default NULL,
  `specilization` varchar(1000) default NULL,
  `workinghour` varchar(100) default NULL,
  `apt` varchar(100) default NULL,
  `servicecharges` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctorinformation` */

insert  into `doctorinformation`(`username`,`specilization`,`workinghour`,`apt`,`servicecharges`) values ('n','Heart gbdfdf','fghfdffd','gffghhf','hgfhfg');

/*Table structure for table `doctorsdetails` */

DROP TABLE IF EXISTS `doctorsdetails`;

CREATE TABLE `doctorsdetails` (
  `doctorname` varchar(100) default NULL,
  `specialization` varchar(100) default NULL,
  `availability` varchar(100) default NULL,
  `education` varchar(100) default NULL,
  `operation` varchar(100) default NULL,
  `about` longtext,
  `hospitalname` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `doctorsdetails` */

insert  into `doctorsdetails`(`doctorname`,`specialization`,`availability`,`education`,`operation`,`about`,`hospitalname`) values ('d','s',NULL,NULL,NULL,NULL,NULL),('d','s',NULL,NULL,NULL,NULL,NULL),('d','s',NULL,NULL,NULL,NULL,NULL),('a',NULL,'12082020','2022','58','about','fortis'),('a',NULL,'12082020','2022','58','about','fortis'),('a',NULL,'12082020','2022','58','about','fortis'),('a',NULL,'12082020','2022','58','about','fortis');

/*Table structure for table `hospitallist` */

DROP TABLE IF EXISTS `hospitallist`;

CREATE TABLE `hospitallist` (
  `hospitalname` varchar(100) default NULL,
  `location` varchar(100) default NULL,
  `machines` varchar(100) default NULL,
  `facility` varchar(100) default NULL,
  `doctors` varchar(100) default NULL,
  `staffs` varchar(1000) default NULL,
  `beds` varchar(20) default NULL,
  `operations` varchar(1000) default NULL,
  `lattitude` varchar(1000) default NULL,
  `longitude` varchar(1000) default NULL,
  `phonenumber` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospitallist` */

insert  into `hospitallist`(`hospitalname`,`location`,`machines`,`facility`,`doctors`,`staffs`,`beds`,`operations`,`lattitude`,`longitude`,`phonenumber`) values ('fortis mulund','Mulund Goregaon Link Rd','ventilator,robotic arm','eye specialist,dialysis','doctora,doctorb,doctorc','staffa,staffb,staffc,staffd','55','Eye specialist 2 ,kidney 2 ','19.1621','72.9420',NULL),('avdhut hospital','airoli','ventilator,robotic arm','eye specialist,dialysis','doctora,doctorb,doctorc','staffa,staffb,staffc,staffd','55','Eye specialist','19.157934','70.124992',NULL),('fortis','airoli',NULL,'eye specialist,dialysis',NULL,NULL,'55','ot1,ot2,ot3','19.157934','72.993477','9004670813');

/*Table structure for table `hospitalname` */

DROP TABLE IF EXISTS `hospitalname`;

CREATE TABLE `hospitalname` (
  `id` int(11) NOT NULL auto_increment,
  `hospitalname` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hospitalname` */

insert  into `hospitalname`(`id`,`hospitalname`) values (1,'fortis'),(2,'fortis2'),(3,'fortis3');

/*Table structure for table `machinedetails` */

DROP TABLE IF EXISTS `machinedetails`;

CREATE TABLE `machinedetails` (
  `id` int(11) NOT NULL auto_increment,
  `machinename` varchar(100) default NULL,
  `machinemfg` varchar(100) default NULL,
  `machineexp` varchar(100) default NULL,
  `numberofoperation` varchar(100) default NULL,
  `doctorhandling` varchar(100) default NULL,
  `about` longtext,
  `hospitalname` varchar(1000) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `machinedetails` */

insert  into `machinedetails`(`id`,`machinename`,`machinemfg`,`machineexp`,`numberofoperation`,`doctorhandling`,`about`,`hospitalname`) values (1,'a','12082020','2022','a','d1','about','fortis');

/*Table structure for table `mediclediseaseinfo` */

DROP TABLE IF EXISTS `mediclediseaseinfo`;

CREATE TABLE `mediclediseaseinfo` (
  `disease` varchar(1000) default NULL,
  `doctor` varchar(1000) default NULL,
  `information` varchar(1000) default NULL,
  `medicinedetails` varchar(1000) default NULL,
  `id` int(11) NOT NULL auto_increment,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mediclediseaseinfo` */

insert  into `mediclediseaseinfo`(`disease`,`doctor`,`information`,`medicinedetails`,`id`) values ('dsdsfdf','n','fsfsddsfsd','dfdsfsdfdsgvdsgv',3);

/*Table structure for table `medicleuser` */

DROP TABLE IF EXISTS `medicleuser`;

CREATE TABLE `medicleuser` (
  `username` varchar(255) default NULL,
  `emailid` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `mobilenumber` varchar(255) default NULL,
  `OTP` varchar(255) default NULL,
  `path` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `medicleuser` */

insert  into `medicleuser`(`username`,`emailid`,`password`,`type`,`mobilenumber`,`OTP`,`path`) values ('m','m@gmail.com','m','Doctor','m','604215','G:Deletedirectory.metadata.pluginsorg.eclipse.wst.server.core	mp0wtpwebappsDoctorPatientInteraction\\medcertim.jpg'),('y','y@gmail.com','y','Doctor','8655221446','442678','G:Deletedirectory.metadata.pluginsorg.eclipse.wst.server.core	mp0wtpwebappsDoctorPatientInteraction\\medcertiy.jpg'),('y','y@gmail.com','y','Doctor','8655221446','','G:Deletedirectory.metadata.pluginsorg.eclipse.wst.server.core	mp0wtpwebappsDoctorPatientInteraction\\medcertiy.jpg');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `doctorname` varchar(100) default NULL,
  `username` varchar(100) default NULL,
  `message` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`doctorname`,`username`,`message`) values ('d','a','Appointment received for a on 18/2/2016 at 12:00 to 1:00 for s'),('d','a','Appointment received for a on 19/2/2016 at 11:00 to 12:00 for s'),('n','a','Appointment received for a on 19/2/2016 at 11:00 to 12:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 19/3/2016 at 10:00 to 11:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 1/4/2016 at 1:00 to 2:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 1/4/2016 at 2:00 to 3:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 8/4/2016 at 12:00 to 1:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 8_4_2016 at 1:00 to 2:00 for \nffgh\nService Charges=hgfhfg'),('n','a','Appointment received for a on 8/4/2016 at 1:00 to 2:00 for  ffgh Service Charges=hgfhfg'),('n','a','Appointment received for a on 9/12/2016 at 12:00 to 1:00 for  ffgh Service Charges=hgfhfg');

/*Table structure for table `multipleappointment` */

DROP TABLE IF EXISTS `multipleappointment`;

CREATE TABLE `multipleappointment` (
  `username` varchar(100) default NULL,
  `emailid` varchar(100) default NULL,
  `phonenumber` varchar(100) default NULL,
  `doctor` varchar(100) default NULL,
  `specialization` varchar(100) default NULL,
  `date` varchar(100) default NULL,
  `time` varchar(100) default NULL,
  `status` varchar(100) default NULL,
  `id` int(11) NOT NULL auto_increment,
  `statusbyuser` varchar(100) default 'Pending',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `multipleappointment` */

insert  into `multipleappointment`(`username`,`emailid`,`phonenumber`,`doctor`,`specialization`,`date`,`time`,`status`,`id`,`statusbyuser`) values ('k',NULL,NULL,'','','31/8/2020','14:32','pending',1,'Pending'),('k',NULL,NULL,'','','31/8/2020','21:25','pending',2,'Pending'),('k',NULL,NULL,'','','1/9/2020','12:4','pending',3,'Pending'),('k',NULL,NULL,'','','3/9/2020','17:20','pending',4,'Pending'),('k',NULL,NULL,'','','2/9/2020','15:5','pending',5,'Pending'),('k',NULL,NULL,'','','4/9/2020','15:17','pending',6,'Pending'),('k',NULL,NULL,'','','4/9/2020','19:52','pending',7,'Pending'),('k',NULL,NULL,'','','4/9/2020','19:52','pending',8,'Pending');

/*Table structure for table `patientviewdot` */

DROP TABLE IF EXISTS `patientviewdot`;

CREATE TABLE `patientviewdot` (
  `id` int(11) NOT NULL auto_increment,
  `patientname` varchar(1000) default NULL,
  `date` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `patientviewdot` */

insert  into `patientviewdot`(`id`,`patientname`,`date`) values (1,'f','2020-09-04 17:19:41'),(2,'n','2020-09-04 17:19:51'),(3,'k','2020-09-04 17:19:52'),(4,'l','2020-09-04 17:19:54'),(5,'e','2020-09-04 17:19:31');

/*Table structure for table `staffdetails` */

DROP TABLE IF EXISTS `staffdetails`;

CREATE TABLE `staffdetails` (
  `doctorname` varchar(100) default NULL,
  `specialization` varchar(100) default NULL,
  `availability` varchar(100) default NULL,
  `education` varchar(100) default NULL,
  `operation` varchar(100) default NULL,
  `about` longtext,
  `hospitalname` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `staffdetails` */

insert  into `staffdetails`(`doctorname`,`specialization`,`availability`,`education`,`operation`,`about`,`hospitalname`) values ('a',NULL,'12082020','2022','58','about','fortis');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `username` varchar(255) default NULL,
  `emailid` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `mobilenumber` varchar(255) default NULL,
  `OTP` varchar(255) default NULL,
  `path` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`username`,`emailid`,`password`,`type`,`mobilenumber`,`OTP`,`path`) values ('n','ningesh1406@gmail.com','n','Doctor','8655221446','770659',NULL),('d','a@gmail.com','a','user',NULL,NULL,NULL),('a','a@gmail.com','a','user',NULL,NULL,NULL),('nk','ningesh1406@gmail.com','nk','Doctor','8655221446','252965','anky.png'),('a','a@gmail.com','a','user',NULL,NULL,NULL),('r','r@gmail.com',NULL,'user','8655221446',NULL,NULL),('b','b@gm.c',NULL,'user','8655221446',NULL,NULL),('c','c@gmail.com',NULL,'user','9029220209',NULL,NULL),('l','l@gmail.com','l','user','8655221446','',''),('k','k@gmail.com','k','user','8655221446','','');

/*Table structure for table `usermessagetable` */

DROP TABLE IF EXISTS `usermessagetable`;

CREATE TABLE `usermessagetable` (
  `doctorname` varchar(100) default NULL,
  `username` varchar(100) default NULL,
  `message` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usermessagetable` */

/*Table structure for table `userprescription` */

DROP TABLE IF EXISTS `userprescription`;

CREATE TABLE `userprescription` (
  `username` varchar(1000) default NULL,
  `doctor` varchar(1000) default NULL,
  `date` varchar(1000) default NULL,
  `medicinedetails` varchar(1000) default NULL,
  `disease` varchar(1000) default NULL,
  `id` int(11) NOT NULL auto_increment,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userprescription` */

insert  into `userprescription`(`username`,`doctor`,`date`,`medicinedetails`,`disease`,`id`) values ('a','n','Sun Mar 27 16:11:26 IST 2016','Bacitracin (Bacitracin)@2@Before Meal@midday2','stomach pain',1),('a','n','Mon Mar 28 11:08:48 IST 2016','Abarelix (Plenaxis)@2@After Meal@midday','stomach pain',2);

/*Table structure for table `usertable1` */

DROP TABLE IF EXISTS `usertable1`;

CREATE TABLE `usertable1` (
  `name` varchar(100) default NULL,
  `email` varchar(100) default NULL,
  `regId` varchar(1000) default NULL,
  `year` varchar(1000) default NULL,
  `department` varchar(1000) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `usertable1` */

insert  into `usertable1`(`name`,`email`,`regId`,`year`,`department`) values ('a','a@gmail.com','APA91bGjjNAE1IOvpMBV-P5WXVNjfIyygaVaZ6dmCNeEL1qO33OcSJpwBBZWQKMUK2lZWVN6Ycr3QEqHBKUDp6SETTpgqdE4Jbp5ZBSUKXBACauNFNqZP7kYZq-oZWsm7YPvakwioNSZ','','null'),('r','r@gmail.com','APA91bGWK_n1fjuZnrxSLdlJLMtTEnQLrDSUy0BOfoNRvlMdvsTFNPz-Ad6B16UJ09GHt6dnUl6ZEh6eNGuSZ3nze1hZCMPwev7AGwHrAeV5n3SB6NGd2LyfM9JLh-Ot70C1eWaOCCmG','','null'),('b','b@gm.c','APA91bGso1ZrcwAMqYkjurudS2mbuyqghcmhbFj1EtVpb4VXW5jCPcEDw1Iab8ZjJ4ZpUl6eyNkBRokX-qdANKNQo1ZSjKxehuJMgNOlAjdYzA9LP-cqidjZ4dvmosgaTaQCtg_F6Jov','','null'),('c','c@gmail.com','APA91bGw8B_Vuomxlg23TNtrtapm9TubzLur-k4Bg2vnWOkiMyICA1e-wbKjoSDOu0i4ueYyWm3oSHFlTXVdmzCiTu66PHjLNlJPvNrBZaezOztzsfsz1LXERRytno1Te6XfEma2C2mf','','null');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
