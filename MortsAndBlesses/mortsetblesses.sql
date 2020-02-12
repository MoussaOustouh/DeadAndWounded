-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 12, 2020 at 12:33 AM
-- Server version: 5.7.26
-- PHP Version: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mortsetblesses`
--

-- --------------------------------------------------------

--
-- Table structure for table `historique_con`
--

DROP TABLE IF EXISTS `historique_con`;
CREATE TABLE IF NOT EXISTS `historique_con` (
  `id_c` int(11) NOT NULL AUTO_INCREMENT,
  `id_u` int(11) NOT NULL,
  `connexion` datetime NOT NULL,
  `deconnexion` datetime DEFAULT NULL,
  PRIMARY KEY (`id_c`),
  KEY `FK_CONNECTER` (`id_u`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `historique_jeu`
--

DROP TABLE IF EXISTS `historique_jeu`;
CREATE TABLE IF NOT EXISTS `historique_jeu` (
  `id_j` int(11) NOT NULL AUTO_INCREMENT,
  `id_u` int(11) NOT NULL,
  `id_adversaire` int(11) NOT NULL,
  `date_et_heure` datetime NOT NULL,
  `temps_passe` time NOT NULL,
  `nombre_de_tours` int(11) NOT NULL,
  `gagner` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_j`),
  KEY `FK_HISTORISER` (`id_u`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `jouer`
--

DROP TABLE IF EXISTS `jouer`;
CREATE TABLE IF NOT EXISTS `jouer` (
  `id_u1` int(11) NOT NULL,
  `id_u2` int(11) NOT NULL,
  `room` varchar(6) NOT NULL,
  `nombre_u1` int(11) NOT NULL,
  `nombre_u2` int(11) NOT NULL,
  `date_et_heure` datetime NOT NULL,
  `nombre_de_tours` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_u1`,`id_u2`),
  UNIQUE KEY `room` (`room`),
  KEY `FK_JOUER2` (`id_u2`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_u` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(128) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `date_de_naissance` date NOT NULL,
  `image` varchar(40) NOT NULL DEFAULT '',
  `points` int(11) NOT NULL DEFAULT '0',
  `parties_gagnees` int(11) NOT NULL DEFAULT '0',
  `parties_perdues` int(11) NOT NULL DEFAULT '0',
  `etat` tinyint(1) NOT NULL DEFAULT '0',
  `pourcentage_reussite` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_u`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_u`, `username`, `password`, `nom`, `prenom`, `email`, `date_de_naissance`, `image`, `points`, `parties_gagnees`, `parties_perdues`, `etat`, `pourcentage_reussite`) VALUES
(1, 'assuom', '12345678', 'moussa', 'oustouh', 'mou@gmail.com', '2020-02-07', '', 0, 0, 0, 0, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
