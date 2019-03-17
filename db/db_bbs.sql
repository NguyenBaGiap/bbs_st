-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: db_bbs
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `posts_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comments_posts1_idx` (`posts_id`),
  KEY `fk_comments_users1_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Tào Tháo thất bại vì chủ quan trong trận Xích Bích.',1,1),(2,'Gia Cát Lượng quá xuất sắc trong trận này.',1,1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` longtext NOT NULL,
  `body` longtext,
  `user_id` bigint(20) NOT NULL,
  `url_img` varchar(255) NOT NULL DEFAULT 'https://grafreez.com/wp-content/temp_demos/river/img/war.jpg',
  PRIMARY KEY (`id`),
  KEY `fk_posts_users_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'Tam Quốc: Trận Di Lăng, nước cờ mạo hiểm của Lưu Bị quyết định số phận của cả 3 nước Ngụy – Thục – Ngô.','Trận Di Lăng mang ý nghĩa mấu chốt định đoạt số phận của 3 nước Ngụy – Thục – Ngô và Lưu Bị chính là người khởi xướng lên trận đánh này.','Trong Tam Quốc Chí, có 2 trận đánh làm ảnh hưởng lớn đến bước ngoặt của thế cục, một là trận Xích Bích, và một là trận Di Lăng. Đặc biệt, trận Di Lăng mang ý nghĩa mấu chốt định đoạt số phận của 3 nước Ngụy – Thục – Ngô. Lưu Bị chính là người khởi xướng nên trận đánh này qua nhiều lý do. Mạo hiểm đi một nước cờ một mất một còn với Đông Ngô, Lưu Bị đã phải gánh chịu hậu quả nặng nề. Vậy tại sao lại có trận Di Lăng? Vì sao Lưu Bị lại bỏ qua mọi sự can gián để địch thân đi chinh phạt Đông Ngô? Và kết quả trận đánh trên đã ảnh hưởng như thế nào đến thế cục của Tam quốc lúc bấy giờ?',1,'https://images.headlines.pw/topnews-2017/imgs/42/a1/42a1639a68366e16d5c861d402c570912d14af3b_640_409.jpg'),(2,'Ít người biết, Spider-Man là một trong những siêu anh hùng có thể \"đánh bại\" Hulk một cách dễ dàng','Anh chàng Spider-Man của chúng ta thường được biết tới là một siêu anh hùng dí dỏm và khôn ngoan. Trái ngược lại, Hulk thường xuất hiện với hình dạng một tên quái vật da xanh khổng lồ mạnh mẽ mà ai cũng khiếp sợ. Tuy nhiên, các bạn có tin rằng, Spidey từng đánh bại Hulk vô cùng dễ dàng mà gần như không tốn tý công sức gì?','Câu chuyện này xảy ra trong arc truyện The Other: Evolve or Die, một trong những arc truyện ảnh hưởng mạnh mẽ nhất tới sức mạnh của Peter Parker cho tới tận bây giờ. Arc truyện mở đầu với Spectacular Spider-Man #1, khi Spidey của chúng ta cố ngăn cản một villain mang tên Tracer với những viên đạn có khả năng theo đuổi mục tiêu cho dù Peter cố gắng né tránh. ',1,'https://genknews.genkcdn.vn/2019/3/13/photo-1-15524688858251050873449.jpg'),(3,'One Piece: Komurasaki vẫn còn sống, tất cả chỉ là một cú lừa mà thôi?','Cái chết của Komurasaki- đệ nhất kỹ nữ Wano quốc khiến các fan hâm mộ vô cùng hoang mang, chả lẽ cô lại chết một cách \"lãng xẹt\" như vậy sao?','Komurasaki là đệ nhất kỹ nữ ở đất nước Wano. Cô được biết đến là người phụ nữ đẹp nhất nước khiến cả Shogun Kurozumi Orochi say đắm.',1,'https://genknews.genkcdn.vn/thumb_w/640/2019/3/15/anh-3-15526411336851201425620.jpg'),(4,'Bí kíp phá đảo Devil May Cry 5 dễ như trở bàn tay','Nắm chắc được bí kíp này, chắc chắn bạn sẽ thốt lên rằng “game là dễ”','Với mỗi game thủ khi thực hiện các chuỗi nhiệm vụ trong game, ai cũng muốn giành được thứ hạng cao nhất vì nhiều mục đích như mở khóa vật phẩm cao cấp hay gia tăng chỉ số nào đó. Do vậy, hôm nay chúng tôi xin đúc kết ra những kinh nghiệm giúp bạn luôn đạt hạng SSS trong Devil May Cry 5 ở bất cứ nhân vật nào trong game. Nắm chắc được bí kíp này, chắc chắn bạn sẽ thốt lên rằng “game là dễ”',1,'https://genknews.genkcdn.vn/thumb_w/640/2019/3/15/photo-2-1552642686802633031901.jpg'),(5,'8 câu \'thả thính\' theo Toán học, Vật lý khiến crush khó lòng từ chối','Những câu tán tỉnh hàm chứa đầy kiến thức sẽ khiến đối phương vừa bất ngờ, vừa khâm phục vì sự sáng tạo và thông minh của bạn.https://znews-photo.zadn.vn/w1440/Uploaded/ofh_btgazspf/2019_03_16/Tha_thinh_qua_mon_hoc01.jpg','Bạn có biết rằng cả nam và nữ đều bị chinh phục bởi sự thông minh? Muốn \"hạ gục\" trái tim của đối phương, hãy thử vài câu \"thả thính\" đầy sáng tạo theo từng môn học mà chàng hay nàng yêu thích nhé! Ví dụ nếu bạn là dân chuyên Toán, có thể dùng các công thức để làm thơ, tránh bị chê là học khối tự nhiên nên tính cách khô khan, nhạt nhẽo.',1,'https://znews-photo.zadn.vn/w1440/Uploaded/ofh_btgazspf/2019_03_16/Tha_thinh_qua_mon_hoc01.jpg'),(6,'Tuyển Uruguay triệu tập Suarez, Godin để đấu Thái Lan và Trung Quốc','HLV Oscar Tabarez đã triệu tập những cầu thủ tốt nhất lên tuyển Uruguay để chuẩn bị cho giải giao hữu cúp Trung Quốc sắp tới.','Tuần tới, các giải vô địch châu Âu sẽ tạm dừng để cầu thủ trở về đội tuyển quốc gia. Ở đợt tập trung lần này, Uruguay quyết định tham dự giải đấu mang tên \"cúp Trung Quốc\".',1,'https://znews-photo.zadn.vn/w660/Uploaded/mdf_usoddd/2019_03_16/1535909870_269340_1535909922_noticia_normal_1.jpg');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` tinyint(1) DEFAULT '1',
  `url_img` varchar(255) NOT NULL DEFAULT 'http://www.2arcadegames.com/img/a24.jpg',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','1','admin',1,'http://mfamily.vn/wp-content/uploads/2018/07/avatar-11-360x270.jpg'),(2,'nguyenbagiap_t57@hus.edu.vn','2','GiapNB',0,'http://www.2arcadegames.com/img/a24.jpg'),(3,'tranvanan@gmail.com','1','AnTV',0,'https://vinagamemobile.com/wp-content/uploads/2018/04/avatar-facebook-dep.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-17  3:08:39
