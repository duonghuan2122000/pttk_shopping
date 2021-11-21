-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.41-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table pttk.account
CREATE TABLE IF NOT EXISTS `account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên đăng nhập',
  `Password` varchar(50) NOT NULL DEFAULT '' COMMENT 'Mật khẩu, mã hóa md5',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin đăng nhập';

-- Dumping data for table pttk.account: ~1 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`Id`, `UserName`, `Password`) VALUES
	(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table pttk.address
CREATE TABLE IF NOT EXISTS `address` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `HouseNumber` varchar(10) NOT NULL DEFAULT '' COMMENT 'Số nhà',
  `Street` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên phố',
  `District` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên quận/huyện',
  `City` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên thành phố/Tỉnh',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin địa chỉ';

-- Dumping data for table pttk.address: ~1 rows (approximately)
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`Id`, `HouseNumber`, `Street`, `District`, `City`) VALUES
	(1, '160', 'Lương Thế Vinh', 'Thanh Xuân', 'Hà Nội');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Dumping structure for table pttk.author
CREATE TABLE IF NOT EXISTS `author` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL DEFAULT '',
  `Biography` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin tác giả sách';

-- Dumping data for table pttk.author: ~1 rows (approximately)
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` (`Id`, `Name`, `Biography`) VALUES
	(1, 'Dương Huân', NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;

-- Dumping structure for table pttk.book
CREATE TABLE IF NOT EXISTS `book` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL DEFAULT '',
  `Summary` varchar(255) DEFAULT NULL,
  `Pages` int(11) NOT NULL,
  `Language` varchar(50) DEFAULT NULL,
  `PublisherId` int(11) NOT NULL,
  `AuthorId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_book_PublisherId` (`PublisherId`),
  KEY `FK_book_AuthorId` (`AuthorId`),
  CONSTRAINT `FK_book_AuthorId` FOREIGN KEY (`AuthorId`) REFERENCES `author` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_book_PublisherId` FOREIGN KEY (`PublisherId`) REFERENCES `publisher` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin sách';

-- Dumping data for table pttk.book: ~1 rows (approximately)
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`Id`, `Title`, `Summary`, `Pages`, `Language`, `PublisherId`, `AuthorId`) VALUES
	(1, 'Dạy con làm giàu', NULL, 100, 'Tiếng Việt', 1, 1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table pttk.bookitem
CREATE TABLE IF NOT EXISTS `bookitem` (
  `BarCode` varchar(20) NOT NULL COMMENT 'Mã sách (duy nhất)',
  `Price` decimal(13,2) NOT NULL COMMENT 'Giá bán',
  `Discount` decimal(13,2) DEFAULT 0.00 COMMENT 'Giảm giá',
  `BookId` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  PRIMARY KEY (`BarCode`),
  KEY `FK_bookItem_BookId` (`BookId`),
  CONSTRAINT `FK_bookItem_BookId` FOREIGN KEY (`BookId`) REFERENCES `book` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin mã sách';

-- Dumping data for table pttk.bookitem: ~1 rows (approximately)
/*!40000 ALTER TABLE `bookitem` DISABLE KEYS */;
INSERT INTO `bookitem` (`BarCode`, `Price`, `Discount`, `BookId`, `CreatedDate`) VALUES
	('S001', 100000.00, 0.00, 1, '2021-11-21 00:00:00');
/*!40000 ALTER TABLE `bookitem` ENABLE KEYS */;

-- Dumping structure for table pttk.boughtbookitem
CREATE TABLE IF NOT EXISTS `boughtbookitem` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Quantity` int(11) NOT NULL,
  `BookBarCode` varchar(20) NOT NULL DEFAULT '' COMMENT 'Mã sách',
  `CartId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_BoughtBookItem_BookBarCode` (`BookBarCode`),
  KEY `FK_BoughtBookItem_CartId` (`CartId`),
  CONSTRAINT `FK_BoughtBookItem_BookBarCode` FOREIGN KEY (`BookBarCode`) REFERENCES `bookitem` (`BarCode`) ON DELETE NO ACTION,
  CONSTRAINT `FK_BoughtBookItem_CartId` FOREIGN KEY (`CartId`) REFERENCES `cart` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Bảng khóa ngoại của sách và giỏ hàng';

-- Dumping data for table pttk.boughtbookitem: ~0 rows (approximately)
/*!40000 ALTER TABLE `boughtbookitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `boughtbookitem` ENABLE KEYS */;

-- Dumping structure for table pttk.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TotalAmount` decimal(13,2) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin giỏ hàng';

-- Dumping data for table pttk.cart: ~1 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`Id`, `TotalAmount`, `CreatedDate`) VALUES
	(1, 0.00, '2021-11-21 00:00:00');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table pttk.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Note` varchar(255) DEFAULT NULL,
  `CardMember` varchar(255) DEFAULT NULL,
  `AccountId` int(11) NOT NULL,
  `AddressId` int(11) NOT NULL,
  `FullNameId` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL COMMENT 'Thời gian tạo',
  PRIMARY KEY (`Id`),
  KEY `FK_customer_AccountId` (`AccountId`),
  KEY `FK_customer_AddressId` (`AddressId`),
  KEY `FK_customer_FullNameId` (`FullNameId`),
  CONSTRAINT `FK_customer_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_customer_AddressId` FOREIGN KEY (`AddressId`) REFERENCES `address` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_customer_FullNameId` FOREIGN KEY (`FullNameId`) REFERENCES `fullname` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin khách hàng';

-- Dumping data for table pttk.customer: ~0 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table pttk.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Role` varchar(50) NOT NULL DEFAULT '' COMMENT 'Quyền hạn của nhân viên',
  `CreatedDate` date NOT NULL COMMENT 'Thời gian bắt đầu làm việc',
  `FullNameId` int(11) NOT NULL COMMENT 'Id bảng fullname',
  `AccountId` int(11) NOT NULL COMMENT 'id bảng account',
  `AddressId` int(11) NOT NULL COMMENT 'Id bảng address',
  PRIMARY KEY (`Id`),
  KEY `FK_employee_FullNameId` (`FullNameId`),
  KEY `FK_employee_AccountId` (`AccountId`),
  KEY `FK_employee_AddressId` (`AddressId`),
  CONSTRAINT `FK_employee_AccountId` FOREIGN KEY (`AccountId`) REFERENCES `account` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_employee_AddressId` FOREIGN KEY (`AddressId`) REFERENCES `address` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_employee_FullNameId` FOREIGN KEY (`FullNameId`) REFERENCES `fullname` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin nhân viên';

-- Dumping data for table pttk.employee: ~1 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`Id`, `Role`, `CreatedDate`, `FullNameId`, `AccountId`, `AddressId`) VALUES
	(1, 'ADMIN', '2021-11-21', 1, 1, 1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table pttk.fullname
CREATE TABLE IF NOT EXISTS `fullname` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'khóa chính của bảng',
  `FirstName` varchar(50) NOT NULL DEFAULT '' COMMENT 'Tên',
  `LastName` varchar(50) NOT NULL DEFAULT '' COMMENT 'Họ và tên đệm',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin họ và tên';

-- Dumping data for table pttk.fullname: ~1 rows (approximately)
/*!40000 ALTER TABLE `fullname` DISABLE KEYS */;
INSERT INTO `fullname` (`Id`, `FirstName`, `LastName`) VALUES
	(1, 'Huân', 'Dương Bằng');
/*!40000 ALTER TABLE `fullname` ENABLE KEYS */;

-- Dumping structure for table pttk.order
CREATE TABLE IF NOT EXISTS `order` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TotalAmount` decimal(13,2) DEFAULT NULL COMMENT 'bằng cart + shipment',
  `CartId` int(11) NOT NULL,
  `ShipmentId` int(11) NOT NULL,
  `PaymentId` int(11) NOT NULL,
  `Status` int(4) NOT NULL COMMENT 'Trạng thái của đơn hàng',
  `CreatedDate` datetime NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_order_CartId` (`CartId`),
  KEY `FK_order_ShipmentId` (`ShipmentId`),
  KEY `FK_order_PaymentId` (`PaymentId`),
  CONSTRAINT `FK_order_CartId` FOREIGN KEY (`CartId`) REFERENCES `cart` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_PaymentId` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`) ON DELETE NO ACTION,
  CONSTRAINT `FK_order_ShipmentId` FOREIGN KEY (`ShipmentId`) REFERENCES `shipment` (`Id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin đơn hàng';

-- Dumping data for table pttk.order: ~0 rows (approximately)
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;

-- Dumping structure for table pttk.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin hình thức thanh toán';

-- Dumping data for table pttk.payment: ~0 rows (approximately)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumping structure for table pttk.publisher
CREATE TABLE IF NOT EXISTS `publisher` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT '' COMMENT 'Tên',
  `Address` varchar(255) DEFAULT NULL COMMENT 'Địa chỉ',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Nhà xuất bản';

-- Dumping data for table pttk.publisher: ~1 rows (approximately)
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` (`Id`, `Name`, `Address`) VALUES
	(1, 'Nhà xuất bản trẻ', NULL);
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;

-- Dumping structure for table pttk.shipment
CREATE TABLE IF NOT EXISTS `shipment` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL DEFAULT '',
  `Price` decimal(13,2) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bảng lưu thông tin giao hàng';

-- Dumping data for table pttk.shipment: ~0 rows (approximately)
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
