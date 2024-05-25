CREATE DATABASE  IF NOT EXISTS `antoanbaomat_nhom6`;
USE `antoanbaomat_nhom6`;

--
-- Table structure for table `chusohuu`
--

DROP TABLE IF EXISTS `chusohuu`;
CREATE TABLE `chusohuu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name`varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sdt` varchar(20) default NULL,
  `publickey` varchar(500) unique,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `chusohuu` VALUES 
	(1,'Leslie','Andrews','leslie@luv2code.com', '0336361384','MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2AJV5aykGdK8GX7fza939XxcGAABrhaIflYagbZ1nnPsqlmB8BBlHOsjGMqV+07ktFzNc3p9RZF3KOqdt+Mf1BMda+xUkptTEqH6p7Xj9D0aVepRk7klg/a7MwbDC/7sfSi5cNn4QxqNFRr5oRGt/RJuUSR6RkvG01Cyo4zLV4e46emtsQSekbmRTpvW9dHFi62ZcO7Bmeye8tYLzWJ4RYHJhwQZ/zLEI7dCXLUi1m9c+ZzhcjBDo1eDBKbE4uZ3pVkrJbv6avK/HZeT9qGIq5FQQc7C7ANvBdvdx/nFS+7hdK6Ota1+Xbs7yyql0XPke8Hw8CL3qMRnBSZccfIw4QIDAQAB'),
	(2,'Emma','Baumgarten','emma@luv2code.com', '0879891818', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt5aC6v+22h0CfKBqF4JRZEU95bFs5Z9ME4OfebIWPTDqvofc1GRnlCJZq5M8NDbSJs9YEubNCOnGJZLrEjtEs0cLC90/K8olrASnqwvf5qTEaAyplP17E1DYOdoosjHwDYaI2aeLbwM32HZ/oo7iJaEPW/7UwmC7bcrFfPIVclkFNyT8ncW3EvKinBr77a4ZBsnsznutfutTxJqrE4OqCd3/EnsIHfz1fOFpv2K6cIM7918BECveGyokUybwE/w9mTbZToGh2N1Mn2X0kc8XchZxYbbyd6GFcov/vzCJeNAassCTdyBTb/YJsQYHx7qqO3Y6P1fcJXMMOhrlbq3h1QIDAQAB');
	



