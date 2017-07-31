-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: akuntansi
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `accountchart`
--

DROP TABLE IF EXISTS `accountchart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountchart` (
  `chartno` int(4) NOT NULL,
  `chartname` varchar(100) NOT NULL,
  PRIMARY KEY (`chartno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountchart`
--

LOCK TABLES `accountchart` WRITE;
/*!40000 ALTER TABLE `accountchart` DISABLE KEYS */;
INSERT INTO `accountchart` VALUES (1000,'Asset'),(1010,'Cash'),(1020,'Bank'),(1030,'Account Recieveable'),(1040,'Inventory'),(1050,'Tax Out'),(1060,'Cost in Advances'),(1061,'Insurance in Advances'),(1070,'Giro Recieveable'),(1080,'Tax In'),(1100,'Fixed Asset'),(1110,'Vehicle'),(1120,'Building and Land'),(1130,'Machine'),(1140,'Computer'),(2000,'Liability'),(2010,'Bank Credit'),(2020,'Account Payable'),(2030,'Giro Payable'),(2100,'Long Term Liability'),(2110,'Long Term Loan'),(3000,'Capital'),(3010,'Owner Investment'),(3020,'Devidend'),(3030,'Privet'),(3040,'Retained Earning'),(4000,'Sales'),(4010,'Sales Income'),(4020,'Sales Discount'),(4030,'Sales Return'),(5000,'Cost of Good Sold'),(5110,'Purchasing'),(5120,'Purchasing Disc'),(5130,'Purchasing Return'),(6000,'Operational Expenditure'),(6010,'General Expenditure'),(6011,'Insurance Expenses'),(6012,'Computer Expenses'),(6013,'Vehicle Expenses'),(6020,'Marketing Expenditure'),(6030,'Human Resource Expenditure'),(6031,'Salary Expenses'),(7000,'Others'),(7010,'Others Income');
/*!40000 ALTER TABLE `accountchart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datacustomer`
--

DROP TABLE IF EXISTS `datacustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datacustomer` (
  `idcustomer` int(11) NOT NULL AUTO_INCREMENT,
  `namacustomer` varchar(1000) DEFAULT NULL,
  `alamatcustomer` varchar(100) DEFAULT NULL,
  `notelpcustomer` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idcustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datacustomer`
--

LOCK TABLES `datacustomer` WRITE;
/*!40000 ALTER TABLE `datacustomer` DISABLE KEYS */;
INSERT INTO `datacustomer` VALUES (1,'Audian','Jln. Binjai Km 9.1 No.6E','08116552155'),(2,'Lavinia','Jln. Binjai Km 12','082167752018'),(3,'Jovita','Jln. Sei Kera','08510612948'),(4,'Philip','Jln. Timur Baru 2','08116065062');
/*!40000 ALTER TABLE `datacustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datasupplier`
--

DROP TABLE IF EXISTS `datasupplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datasupplier` (
  `idsupplier` int(11) NOT NULL AUTO_INCREMENT,
  `namasupplier` varchar(1000) DEFAULT NULL,
  `alamatsupplier` varchar(100) DEFAULT NULL,
  `notelpsupplier` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idsupplier`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datasupplier`
--

LOCK TABLES `datasupplier` WRITE;
/*!40000 ALTER TABLE `datasupplier` DISABLE KEYS */;
INSERT INTO `datasupplier` VALUES (1,'John\"s Co','Jln. Biduk No 34','0614527851'),(2,'CV. Multi Asa Success','Kompleks Taman Alamanada Indah Blok C No.17','06180020933'),(3,'Jaya Mandiri','Jalan Palangkaraya No 70/15 A','081376286936'),(4,'Toko ABC','Jalan Kepribadian No 9','0614519906'),(5,'Palano Jaya','Jalan Melati Raya Helvetia','0618455606');
/*!40000 ALTER TABLE `datasupplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diskon`
--

DROP TABLE IF EXISTS `diskon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diskon` (
  `kodediskon` varchar(100) DEFAULT NULL,
  `diskon` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diskon`
--

LOCK TABLES `diskon` WRITE;
/*!40000 ALTER TABLE `diskon` DISABLE KEYS */;
INSERT INTO `diskon` VALUES ('disc5',0.05),('disc10',0.1),('disc20',0.2),('disc0',0);
/*!40000 ALTER TABLE `diskon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_ledger`
--

DROP TABLE IF EXISTS `general_ledger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_ledger` (
  `kode_gl` int(11) NOT NULL AUTO_INCREMENT,
  `chartno` int(11) DEFAULT NULL,
  `kode_jurnal` int(11) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `debit` double DEFAULT NULL,
  `kredit` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`kode_gl`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_ledger`
--

LOCK TABLES `general_ledger` WRITE;
/*!40000 ALTER TABLE `general_ledger` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_ledger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventory` (
  `masuk` int(11) DEFAULT NULL,
  `keluar` int(11) DEFAULT NULL,
  `kode_produk` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (0,0,1001),(0,0,1002),(0,0,1003),(0,0,1004),(0,0,1005),(0,0,1006),(0,0,1007),(0,0,1008),(0,0,1009),(0,0,1010),(0,0,1011),(0,0,1012),(0,0,1013),(0,0,1014),(0,0,1015),(0,0,1016),(0,0,1017),(0,0,1018),(0,0,1019),(0,0,1020),(0,0,1021),(0,0,1022),(0,0,1023),(0,0,1024),(0,0,1025),(0,0,1026),(0,0,1027),(0,0,1028),(0,0,1029),(0,0,1030);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jurnal`
--

DROP TABLE IF EXISTS `jurnal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jurnal` (
  `kode_jurnal` int(11) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `chartno` int(11) DEFAULT NULL,
  `debit` double DEFAULT NULL,
  `kredit` double DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jurnal`
--

LOCK TABLES `jurnal` WRITE;
/*!40000 ALTER TABLE `jurnal` DISABLE KEYS */;
/*!40000 ALTER TABLE `jurnal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lastvalue`
--

DROP TABLE IF EXISTS `lastvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lastvalue` (
  `id` int(11) NOT NULL,
  `idpembelian` int(11) DEFAULT NULL,
  `idpenjualan` int(11) DEFAULT NULL,
  `kodejurnal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lastvalue`
--

LOCK TABLES `lastvalue` WRITE;
/*!40000 ALTER TABLE `lastvalue` DISABLE KEYS */;
INSERT INTO `lastvalue` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `lastvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lastvaluepembelian`
--

DROP TABLE IF EXISTS `lastvaluepembelian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lastvaluepembelian` (
  `id` int(11) NOT NULL,
  `idpembelian` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lastvaluepembelian`
--

LOCK TABLES `lastvaluepembelian` WRITE;
/*!40000 ALTER TABLE `lastvaluepembelian` DISABLE KEYS */;
INSERT INTO `lastvaluepembelian` VALUES (1,1);
/*!40000 ALTER TABLE `lastvaluepembelian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modal`
--

DROP TABLE IF EXISTS `modal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggal` date DEFAULT NULL,
  `kode_jurnal` int(11) DEFAULT NULL,
  `chartno` int(11) DEFAULT NULL,
  `debit` int(11) DEFAULT NULL,
  `kredit` int(11) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modal`
--

LOCK TABLES `modal` WRITE;
/*!40000 ALTER TABLE `modal` DISABLE KEYS */;
/*!40000 ALTER TABLE `modal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pajak`
--

DROP TABLE IF EXISTS `pajak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pajak` (
  `kodepajak` varchar(100) DEFAULT NULL,
  `persen` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pajak`
--

LOCK TABLES `pajak` WRITE;
/*!40000 ALTER TABLE `pajak` DISABLE KEYS */;
INSERT INTO `pajak` VALUES ('ppnmasukan',0.1),('ppnkeluaran',0.1);
/*!40000 ALTER TABLE `pajak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pembelian`
--

DROP TABLE IF EXISTS `pembelian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pembelian` (
  `tanggalpembelian` date DEFAULT NULL,
  `idpembelian` int(11) DEFAULT NULL,
  `idsupplier` int(11) DEFAULT NULL,
  `kode_produk` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `kodediskon` varchar(100) DEFAULT NULL,
  `kodepajak` varchar(100) DEFAULT NULL,
  `grandtotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pembelian`
--

LOCK TABLES `pembelian` WRITE;
/*!40000 ALTER TABLE `pembelian` DISABLE KEYS */;
/*!40000 ALTER TABLE `pembelian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profitloss`
--

DROP TABLE IF EXISTS `profitloss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profitloss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tanggalfrom` date DEFAULT NULL,
  `tanggaltill` date DEFAULT NULL,
  `profitloss` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profitloss`
--

LOCK TABLES `profitloss` WRITE;
/*!40000 ALTER TABLE `profitloss` DISABLE KEYS */;
/*!40000 ALTER TABLE `profitloss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `kode_produk` int(4) NOT NULL AUTO_INCREMENT,
  `nama_produk` varchar(100) DEFAULT NULL,
  `kategori` varchar(100) DEFAULT NULL,
  `jumlahpembelian` int(11) DEFAULT NULL,
  `harga_beli` double DEFAULT NULL,
  `harga_jual` double DEFAULT NULL,
  `jumlahpenjualan` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stock_awal` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_produk`)
) ENGINE=InnoDB AUTO_INCREMENT=1031 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1001,'Buku Tulis Kiky 38','Alat Tulis',0,100000,150000,0,0,0),(1002,'Buku Tulis Kiky 58','Alat Tulis',0,65000,67200,0,0,0),(1003,'Buku Tulis Kiky 40','Alat Tulis',0,90000,91600,0,0,0),(1004,'Buku Tulis Kiky 50','Alat Tulis',0,102000,105000,0,0,0),(1005,'Buku Tulis VOS Moopi Kuarto 38','Alat Tulis',0,40000,43800,0,0,0),(1006,'Pentel Pel 105','Alat Tulis',0,20000,21100,0,0,0),(1007,'Pentel Pen 105s','Alat Tulis',0,20000,21000,0,0,0),(1008,'S Sharpie Pen UPC Twin Black','Alat Tulis',0,20000,23000,0,0,0),(1009,'Pilot Pen Hi-Tec 0.3','Alat Tulis',0,25000,27300,0,0,0),(1010,'Snowman Pen 0.2MM','Alat Tulis',0,9500,10600,0,0,0),(1011,'00:00 (Ardelia Karisa)','Buku',0,32500,42500,0,0,0),(1012,'Love in Sydney(Arumi E)','Buku',0,49300,58000,0,0,0),(1013,'Breakfast at Tiffanys (Truman Capote)','Buku',0,29700,35000,0,0,0),(1014,'The Lost Boy (Pelzer)','Buku',0,46700,55000,0,0,0),(1015,'Artemis Fowl (Colfer)','Buku',0,34000,40000,0,0,0);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksidetail`
--

DROP TABLE IF EXISTS `transaksidetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksidetail` (
  `idtransaksi` int(11) NOT NULL AUTO_INCREMENT,
  `nofaktur` int(11) DEFAULT NULL,
  `kode_produk` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `subtotal` int(11) DEFAULT NULL,
  `kodepajak` varchar(100) DEFAULT NULL,
  `kodediskon` varchar(100) DEFAULT NULL,
  `grandtotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtransaksi`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksidetail`
--

LOCK TABLES `transaksidetail` WRITE;
/*!40000 ALTER TABLE `transaksidetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaksidetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksimaster`
--

DROP TABLE IF EXISTS `transaksimaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaksimaster` (
  `nofaktur` int(11) DEFAULT NULL,
  `tanggaltransaksi` date DEFAULT NULL,
  `idcustomer` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksimaster`
--

LOCK TABLES `transaksimaster` WRITE;
/*!40000 ALTER TABLE `transaksimaster` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaksimaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trialbalance`
--

DROP TABLE IF EXISTS `trialbalance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trialbalance` (
  `chartno` int(11) DEFAULT NULL,
  `opening` int(11) DEFAULT NULL,
  `ending` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trialbalance`
--

LOCK TABLES `trialbalance` WRITE;
/*!40000 ALTER TABLE `trialbalance` DISABLE KEYS */;
INSERT INTO `trialbalance` VALUES (1000,0,0),(1010,0,0),(1020,0,0),(1030,0,0),(1040,0,0),(1050,0,0),(1060,0,0),(1061,0,0),(1070,0,0),(1080,0,0),(1100,0,0),(1110,0,0),(1120,0,0),(1130,0,0),(1140,0,0),(2000,0,0),(2010,0,0),(2020,0,0),(2030,0,0),(2100,0,0),(2110,0,0),(3000,0,0),(3010,0,0),(3020,0,0),(3030,0,0),(3040,0,0),(4000,0,0),(4010,0,0),(4020,0,0),(4030,0,0),(5000,0,0),(5110,0,0),(5120,0,0),(6000,0,0),(6010,0,0),(6011,0,0),(6012,0,0),(6013,0,0),(6020,0,0),(6030,0,0),(6031,0,0),(7000,0,0);
/*!40000 ALTER TABLE `trialbalance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `username`
--

DROP TABLE IF EXISTS `username`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `username` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `confirmpassword` varchar(100) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `username`
--

LOCK TABLES `username` WRITE;
/*!40000 ALTER TABLE `username` DISABLE KEYS */;
INSERT INTO `username` VALUES ('admin','admin','admin',0),('pierry','ryry','ryry',1),('val','val','val',1),('valerie','va','va',0);
/*!40000 ALTER TABLE `username` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-31 12:59:56
