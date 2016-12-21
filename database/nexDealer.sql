-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Tempo de geração: 21/12/2016 às 01:18
-- Versão do servidor: 5.6.31-0ubuntu0.15.10.1
-- Versão do PHP: 7.0.3-5+deb.sury.org~wily+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `nexDealer`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `description` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `brand`
--

INSERT INTO `brand` (`id`, `description`) VALUES
(1, 'Chevrolet'),
(2, 'GM'),
(3, 'Peugeot'),
(4, 'Fiat'),
(5, 'Renault'),
(6, 'Hyundai'),
(7, 'Nissan'),
(8, 'Ford'),
(9, 'Honda'),
(10, 'Jeep');

-- --------------------------------------------------------

--
-- Estrutura para tabela `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `id_state` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `city`
--

INSERT INTO `city` (`id`, `id_state`, `name`) VALUES
(1, 1, 'Guarapuava'),
(2, 1, 'Turvo'),
(3, 1, 'Pinhão'),
(4, 1, 'Palmeirinha'),
(5, 1, 'Pitanga'),
(6, 1, 'Curitiba'),
(7, 1, 'Cascavel'),
(8, 1, 'Foz do Iguaçu'),
(9, 1, 'Santa Maria do Oeste'),
(10, 1, 'Laranjal');

-- --------------------------------------------------------

--
-- Estrutura para tabela `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `id_contact` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthDate` varchar(10) NOT NULL,
  `civilStatus` varchar(40) NOT NULL,
  `status` varchar(1) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `client`
--

INSERT INTO `client` (`id`, `id_contact`, `name`, `cpf`, `rg`, `gender`, `birthDate`, `civilStatus`, `status`, `createdAt`) VALUES
(1, 1, 'Guilherme Ribas Carneiro', '064.534.649-77', '10.662.288-4', 'M', '30/05/1996', 'Namorando', 'A', '2016-06-24 03:15:17'),
(2, 2, 'Célia Ribas de Lima', '699.321.010-90', '12.365.497-7', 'F', '17/03/1971', 'Solteira', 'A', '2016-06-24 03:15:17'),
(3, 3, 'Emerson', '123.654.987-85', '25.638.974-5', 'M', '25/06/1968', 'Casado', 'A', '2016-06-24 17:39:37');

-- --------------------------------------------------------

--
-- Estrutura para tabela `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `id_city` int(11) DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `complement` varchar(200) NOT NULL,
  `neighborhood` varchar(200) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberOfHouse` int(8) DEFAULT NULL,
  `ddd` int(3) NOT NULL,
  `telephone` int(10) NOT NULL,
  `postalCode` int(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `contact`
--

INSERT INTO `contact` (`id`, `id_city`, `address`, `complement`, `neighborhood`, `email`, `numberOfHouse`, `ddd`, `telephone`, `postalCode`) VALUES
(1, 1, 'Caçador', 'Casa', 'Centro', 'guilherme@familiaribas.com', 459, 42, 98533012, 89500000),
(2, 1, 'Rua Rivadávia Roseira Ribas', 'Casa', 'Bonsucesso', 'celiaribas@outlook.com', 253, 42, 91315504, 85055370),
(3, 1, 'Rua Francisco de Camargo Ribas', 'Casa', 'Alto da XV', 'emerson@emerson.com', 259, 42, 99685749, 85065147);

-- --------------------------------------------------------

--
-- Estrutura para tabela `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `employee`
--

INSERT INTO `employee` (`id`, `name`) VALUES
(1, 'Guilherme');

-- --------------------------------------------------------

--
-- Estrutura para tabela `model`
--

CREATE TABLE `model` (
  `id` int(11) NOT NULL,
  `id_brand` int(11) NOT NULL,
  `description` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `model`
--

INSERT INTO `model` (`id`, `id_brand`, `description`) VALUES
(1, 2, 'Blazer'),
(2, 2, 'Corsa'),
(3, 2, 'Cruze'),
(4, 2, 'Pontiac'),
(5, 2, 'Procopio Plus'),
(6, 2, 'Space'),
(9, 1, 'Astra'),
(10, 1, 'Monza');

-- --------------------------------------------------------

--
-- Estrutura para tabela `sale`
--

CREATE TABLE `sale` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_vehicle` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `total` varchar(9) NOT NULL,
  `annotation` text,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pdf_way` varchar(255) NOT NULL,
  `status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `sale`
--

INSERT INTO `sale` (`id`, `id_client`, `id_vehicle`, `id_employee`, `id_type`, `total`, `annotation`, `date`, `pdf_way`, `status`) VALUES
(3, 1, 3, 1, 1, '38.000,00', NULL, '2016-06-24 15:52:49', '/home/cabrito/workspace/NexDealer/lib/generatedPdf/Guilherme-06453464977-3.pdf', 'E'),
(4, 2, 1, 1, 1, '38.000,00', NULL, '2016-06-24 16:08:36', '/home/cabrito/workspace/NexDealer/lib/generatedPdf/Célia-69932101090-1.pdf', 'R'),
(5, 1, 2, 1, 1, '38.000,00', NULL, '2016-06-24 17:44:49', '/home/cabrito/workspace/NexDealer/lib/generatedPdf/Guilherme-06453464977-2.pdf', 'E'),
(6, 1, 4, 1, 3, '38.000,00', NULL, '2016-06-24 23:04:18', '/home/cabrito/workspace/NexDealer/lib/generatedPdf/Guilherme-06453464977-4.pdf', 'R');

-- --------------------------------------------------------

--
-- Estrutura para tabela `state`
--

CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `initials` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `state`
--

INSERT INTO `state` (`id`, `name`, `initials`) VALUES
(1, 'Paraná', 'PR'),
(2, 'Santa Catarina', 'SC'),
(3, 'Rio Grande do Sul', 'RS'),
(4, 'São Paulo', 'SP');

-- --------------------------------------------------------

--
-- Estrutura para tabela `type`
--

CREATE TABLE `type` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `type`
--

INSERT INTO `type` (`id`, `description`) VALUES
(1, 'Carro'),
(2, 'Utilitário Pequeno'),
(3, 'Micro-Ônibus'),
(4, 'Caminhão'),
(5, 'Moto');

-- --------------------------------------------------------

--
-- Estrutura para tabela `vehicle`
--

CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL,
  `id_version` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `year` int(4) NOT NULL,
  `plate` varchar(8) NOT NULL,
  `renavam` bigint(11) NOT NULL,
  `fuel` varchar(25) NOT NULL,
  `color` varchar(25) NOT NULL,
  `frame` varchar(90) NOT NULL,
  `status` char(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `vehicle`
--

INSERT INTO `vehicle` (`id`, `id_version`, `id_type`, `year`, `plate`, `renavam`, `fuel`, `color`, `frame`, `status`, `created_at`) VALUES
(1, 17, 1, 1995, 'AKO-4656', 35469878532, 'Gasolina', 'Azul', 'ADA32DSFS1SD4', 'V', '2016-06-24 03:15:20'),
(2, 1, 1, 2009, 'EMP-1461', 15996335759, 'Flex', 'Branco', 'asdfasd2131asdf', 'E', '2016-06-24 03:15:20'),
(3, 1, 1, 2004, 'AAA-1234', 23658954128, 'Gasolina', 'Azul', 'SADFASD1WSDF', 'E', '2016-06-24 03:15:20'),
(4, 19, 3, 1996, 'AEW-4902', 15632198715, 'Flex', 'Azul', 'ADWQER123E12', 'V', '2016-06-24 15:27:39');

-- --------------------------------------------------------

--
-- Estrutura para tabela `version`
--

CREATE TABLE `version` (
  `id` int(11) NOT NULL,
  `id_model` int(11) NOT NULL,
  `description` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `version`
--

INSERT INTO `version` (`id`, `id_model`, `description`) VALUES
(1, 9, '2.0 8V/ CD Hatchback 5p Aut'),
(2, 9, '2.0 8V/ CD Hatchback 5p Mec'),
(3, 9, '2.0 8v/ CD 3p Aut'),
(4, 6, '2.0 16v/ CD GLS MPFI 3p'),
(5, 9, '2.0 8v/ CD Sunny GLS 3p '),
(11, 2, 'Hat. Joy 1.0/ 1.0 FlexPower 8v 5p'),
(12, 2, 'Furgão 1.6 MPFI Powertech 92cv'),
(13, 2, 'GL 1.6 MPFI/ 1.4 EFI 2p e 4p'),
(14, 2, 'GLS 1.6 MPFI 5p'),
(15, 2, 'GSi 16v'),
(16, 10, '1.6i/ 1.8i (restante)'),
(17, 10, 'Class 1.8/ 2.0'),
(18, 10, 'Classic SE 2.0/ MPFI e EFI 2p e 4p'),
(19, 10, 'Classic SL/e/SR 1.8'),
(20, 10, 'GL 1.8 EFI/ SL/ L/ 650/ Barc 2p e 4p');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_state` (`id_state`);

--
-- Índices de tabela `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_client_contact` (`id_contact`);

--
-- Índices de tabela `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_city` (`id_city`);

--
-- Índices de tabela `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `model`
--
ALTER TABLE `model`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_brand` (`id_brand`);

--
-- Índices de tabela `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_sale_vehicle` (`id_vehicle`),
  ADD KEY `fk_sale_client` (`id_client`),
  ADD KEY `fk_sale_employee` (`id_employee`),
  ADD KEY `fk_sale_type` (`id_type`);

--
-- Índices de tabela `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_vehicle_version` (`id_version`),
  ADD KEY `fk_vehicle_type` (`id_type`);

--
-- Índices de tabela `version`
--
ALTER TABLE `version`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_model` (`id_model`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de tabela `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de tabela `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de tabela `model`
--
ALTER TABLE `model`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de tabela `sale`
--
ALTER TABLE `sale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de tabela `state`
--
ALTER TABLE `state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `type`
--
ALTER TABLE `type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de tabela `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `version`
--
ALTER TABLE `version`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `fk_id_state` FOREIGN KEY (`id_state`) REFERENCES `state` (`id`);

--
-- Restrições para tabelas `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `fk_client_contact` FOREIGN KEY (`id_contact`) REFERENCES `contact` (`id`);

--
-- Restrições para tabelas `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `fk_contact_city` FOREIGN KEY (`id_city`) REFERENCES `city` (`id`);

--
-- Restrições para tabelas `model`
--
ALTER TABLE `model`
  ADD CONSTRAINT `fk_model_brand` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id`);

--
-- Restrições para tabelas `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `fk_sale_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `fk_sale_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `fk_sale_type` FOREIGN KEY (`id_type`) REFERENCES `type` (`id`),
  ADD CONSTRAINT `fk_sale_vehicle` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicle` (`id`);

--
-- Restrições para tabelas `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `fk_vehicle_type` FOREIGN KEY (`id_type`) REFERENCES `type` (`id`),
  ADD CONSTRAINT `fk_vehicle_version` FOREIGN KEY (`id_version`) REFERENCES `version` (`id`);

--
-- Restrições para tabelas `version`
--
ALTER TABLE `version`
  ADD CONSTRAINT `fk_version_model` FOREIGN KEY (`id_model`) REFERENCES `model` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
