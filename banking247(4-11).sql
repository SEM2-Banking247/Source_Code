-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 04, 2022 at 03:25 AM
-- Server version: 5.7.34
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking247`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch_list`
--

CREATE TABLE `branch_list` (
  `branch_id` int(11) NOT NULL,
  `branch_name` varchar(50) DEFAULT NULL,
  `branch_address` varchar(100) DEFAULT NULL,
  `branch_phone` varchar(15) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `branch_list`
--

INSERT INTO `branch_list` (`branch_id`, `branch_name`, `branch_address`, `branch_phone`, `create_date`, `update_date`) VALUES
(1, 'Hoang Mike Branch', 'Hoang Mai, HN', '09.873.432.445', '2020-12-24', '2022-10-06'),
(2, 'Hoan Sword Branch', 'Hoan Kiem, HN', '09.112.224.343', '2022-10-06', '2022-10-08'),
(3, 'Ba Dinh Branch', 'Ba Dinh, HN', '09.342.357.786', '2022-10-06', '2022-10-06');

-- --------------------------------------------------------

--
-- Table structure for table `card_infor`
--

CREATE TABLE `card_infor` (
  `Card_infor_id` int(11) NOT NULL,
  `User_id` int(11) DEFAULT NULL,
  `branch_id` int(11) NOT NULL,
  `Card_number` varchar(16) DEFAULT NULL,
  `Card_balance` double DEFAULT NULL,
  `Notice` varchar(50) DEFAULT NULL,
  `Create_date` date DEFAULT NULL,
  `Status` varchar(16) DEFAULT NULL,
  `Update_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `card_infor`
--

INSERT INTO `card_infor` (`Card_infor_id`, `User_id`, `branch_id`, `Card_number`, `Card_balance`, `Notice`, `Create_date`, `Status`, `Update_date`) VALUES
(1, 2, 1, '000021', 92900000, 'VIP', '2022-09-10', 'Active', '2022-10-29'),
(2, 2, 1, '000022', 109000000, 'VIP', '2022-09-10', 'Active', '2022-09-10'),
(3, 4, 1, '000041', 208000000, 'VIP', '2022-09-10', 'Active', '2022-09-10'),
(4, 4, 1, '000042', 120000000, 'vippro', '2022-09-10', 'Active', '2022-10-30');

-- --------------------------------------------------------

--
-- Table structure for table `reports`
--

CREATE TABLE `reports` (
  `report_id` int(11) NOT NULL,
  `report_type_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `report_title` varchar(100) DEFAULT NULL,
  `report_content` varchar(250) DEFAULT NULL,
  `report_status` varchar(15) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reports`
--

INSERT INTO `reports` (`report_id`, `report_type_id`, `user_id`, `report_title`, `report_content`, `report_status`, `create_date`, `update_date`) VALUES
(1, 2, 2, 'Dem qua em tuyet lam', 'Dem qua em tuyet lam', 'Resolved', '2022-09-25', '2022-10-23'),
(2, 2, 2, 'title1', 'content1', 'Pending', '2022-09-25', '2022-10-23'),
(3, 1, 3, 'title2', 'content2', 'Resolved', '2022-09-25', '2022-10-23');

-- --------------------------------------------------------

--
-- Table structure for table `report_types`
--

CREATE TABLE `report_types` (
  `report_type_id` int(11) NOT NULL,
  `report_type_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `report_types`
--

INSERT INTO `report_types` (`report_type_id`, `report_type_name`) VALUES
(1, 'Card Lost'),
(2, 'Customer Service'),
(3, 'Account Lost'),
(4, 'Others');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `Role_id` int(11) NOT NULL,
  `Role_Name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`Role_id`, `Role_Name`) VALUES
(1, 'Admin'),
(2, 'Employee'),
(3, 'Customer');

-- --------------------------------------------------------

--
-- Table structure for table `salarys`
--

CREATE TABLE `salarys` (
  `Salary_id` int(11) NOT NULL,
  `User_id` int(11) DEFAULT NULL,
  `Salary_money` float DEFAULT NULL,
  `Update_date` date DEFAULT NULL,
  `Create_date` date DEFAULT NULL,
  `Hr_date` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salarys`
--

INSERT INTO `salarys` (`Salary_id`, `User_id`, `Salary_money`, `Update_date`, `Create_date`, `Hr_date`) VALUES
(1, 3, 8500000, '2022-10-06', '2022-09-01', '2022-12-22'),
(2, 1, 17500000, '2022-09-01', '2022-09-01', '2022-12-22'),
(11, 2, 12000000, '2022-10-06', '2022-10-06', '2021-12-24'),
(13, 4, 12500000, '2022-10-06', '2022-10-06', '2012-12-12');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `Transaction_id` int(11) NOT NULL,
  `Transaction_type_id` int(11) DEFAULT NULL,
  `card_id_request` int(11) DEFAULT NULL,
  `card_id_receiver` int(11) DEFAULT NULL,
  `acc_number_request` varchar(11) DEFAULT NULL,
  `acc_number_receiver` varchar(11) DEFAULT NULL,
  `transaction_amount` double DEFAULT NULL,
  `New_balance_request` double DEFAULT NULL,
  `New_balance_receiver` double DEFAULT NULL,
  `transaction_date` date DEFAULT NULL,
  `Notice` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`Transaction_id`, `Transaction_type_id`, `card_id_request`, `card_id_receiver`, `acc_number_request`, `acc_number_receiver`, `transaction_amount`, `New_balance_request`, `New_balance_receiver`, `transaction_date`, `Notice`) VALUES
(3, 1, 1, 1, '00002', '00002', 10000000, 80000000, 80000000, '2022-09-24', 'abc'),
(4, 1, 1, 1, '00002', '00002', 10000000, 70000000, 70000000, '2022-09-24', 'acs'),
(5, 1, 2, 2, '00002', '00002', 10000000, 140000000, 140000000, '2022-09-24', 'x'),
(6, 3, 1, 2, '00002', '00002', 10000000, 60000000, 150000000, '2022-09-24', 'abc'),
(7, 1, 1, 1, '00002', '00002', 10000000, 50000000, 50000000, '2022-09-25', 'aht'),
(8, 1, 2, 2, '00002', '00002', 10000000, 140000000, 140000000, '2022-09-25', 'aht'),
(9, 1, 2, 2, '00002', '00002', 10000000, 130000000, 130000000, '2022-09-25', 'aht'),
(10, 3, 2, 1, '00002', '00002', 10000000, 120000000, 60000000, '2022-09-25', 'aht'),
(11, 3, 2, 1, '00002', '00002', 10000000, 110000000, 70000000, '2022-09-25', 'test'),
(12, 3, 1, 3, '00002', '00004', 10000000, 90000000, 210000000, '2022-09-25', 'aht'),
(13, 1, 2, 2, '00002', '00002', 1000000, 109000000, 109000000, '2022-09-25', 'a'),
(14, 1, 1, 1, '00002', '00002', 50000, 89950000, 89950000, '2022-10-12', 'sdf'),
(15, 1, 1, 1, '00002', '00002', 50000, 89900000, 89900000, '2022-10-29', 'abc'),
(16, 3, 3, 1, '00004', '00002', 1000000, 209000000, 90900000, '2022-10-29', 'abc'),
(17, 3, 3, 1, '00004', '00002', 1000000, 208000000, 91900000, '2022-10-29', ''),
(18, 2, 4, 4, '00004', '00004', 10000000, 110000000, 110000000, '2022-10-29', ''),
(19, 2, 4, 4, '00004', '00004', 10000000, 120000000, 120000000, '2022-10-29', 'Added by Hoang Mike Branch');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_types`
--

CREATE TABLE `transaction_types` (
  `Transaction_type_id` int(11) NOT NULL,
  `Transaction_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction_types`
--

INSERT INTO `transaction_types` (`Transaction_type_id`, `Transaction_type_name`) VALUES
(1, 'withdraw money'),
(2, 'insert money'),
(3, 'transfer money');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_id` int(11) NOT NULL,
  `Role_id` int(11) DEFAULT NULL,
  `Account_number` varchar(11) NOT NULL,
  `User_name` varchar(50) NOT NULL,
  `Email` varchar(32) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `Full_name` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Phone_number` varchar(16) DEFAULT NULL,
  `CCCD` varchar(16) DEFAULT NULL,
  `Gender` varchar(6) DEFAULT NULL,
  `Create_date` date DEFAULT NULL,
  `DOB` varchar(25) DEFAULT NULL,
  `Pin` varchar(4) DEFAULT NULL,
  `Update_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_id`, `Role_id`, `Account_number`, `User_name`, `Email`, `Password`, `Full_name`, `Address`, `Phone_number`, `CCCD`, `Gender`, `Create_date`, `DOB`, `Pin`, `Update_date`) VALUES
(1, 1, '00001', 'tuanda', 'admin@gmail.com', 'Aa123456@', 'Dang Tuan', 'HN', '0965367815', '123456789', 'Male', '2022-09-10', '1998-12-26', '2412', '2022-09-21'),
(2, 3, '00002', 'hoang', 'hoang@gmail.com', 'Aa123456@', 'Nguyen Minh Hoang', 'HN', '09123456789', '123456789', 'Male', '2022-09-10', '1999-24-13', '1998', '2022-10-09'),
(3, 2, '00003', 'viet', 'viet@gmail.com', 'Aa123456@', 'Nguyen Quoc Viet', 'HN', '0912345678', '123456789', 'Female', '2022-09-10', '2001-24-12', '1234', '2022-10-10'),
(4, 3, '00004', 'tuancap', 'tuan@gmail.com', 'Aa123456@', 'Cao Thai Tuan', 'HN', '0912345678', '123456789', 'Male', '2022-09-10', '1991-24-12', '6630', '2022-10-06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch_list`
--
ALTER TABLE `branch_list`
  ADD PRIMARY KEY (`branch_id`);

--
-- Indexes for table `card_infor`
--
ALTER TABLE `card_infor`
  ADD PRIMARY KEY (`Card_infor_id`),
  ADD KEY `User_id` (`User_id`),
  ADD KEY `branch_id` (`branch_id`);

--
-- Indexes for table `reports`
--
ALTER TABLE `reports`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `report_type_id` (`report_type_id`);

--
-- Indexes for table `report_types`
--
ALTER TABLE `report_types`
  ADD PRIMARY KEY (`report_type_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`Role_id`);

--
-- Indexes for table `salarys`
--
ALTER TABLE `salarys`
  ADD PRIMARY KEY (`Salary_id`),
  ADD KEY `User_id` (`User_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`Transaction_id`),
  ADD KEY `card_id_request` (`card_id_request`),
  ADD KEY `card_id_receiver` (`card_id_receiver`),
  ADD KEY `Transaction_type_id` (`Transaction_type_id`);

--
-- Indexes for table `transaction_types`
--
ALTER TABLE `transaction_types`
  ADD PRIMARY KEY (`Transaction_type_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_id`),
  ADD KEY `Role_id` (`Role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branch_list`
--
ALTER TABLE `branch_list`
  MODIFY `branch_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `card_infor`
--
ALTER TABLE `card_infor`
  MODIFY `Card_infor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `reports`
--
ALTER TABLE `reports`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `report_types`
--
ALTER TABLE `report_types`
  MODIFY `report_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `Role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `salarys`
--
ALTER TABLE `salarys`
  MODIFY `Salary_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `Transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `transaction_types`
--
ALTER TABLE `transaction_types`
  MODIFY `Transaction_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `User_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `card_infor`
--
ALTER TABLE `card_infor`
  ADD CONSTRAINT `card_infor_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `card_infor_ibfk_2` FOREIGN KEY (`branch_id`) REFERENCES `branch_list` (`branch_id`);

--
-- Constraints for table `reports`
--
ALTER TABLE `reports`
  ADD CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`User_id`),
  ADD CONSTRAINT `reports_ibfk_2` FOREIGN KEY (`report_type_id`) REFERENCES `report_types` (`report_type_id`);

--
-- Constraints for table `salarys`
--
ALTER TABLE `salarys`
  ADD CONSTRAINT `salarys_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `users` (`User_id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`card_id_request`) REFERENCES `card_infor` (`Card_infor_id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`card_id_receiver`) REFERENCES `card_infor` (`Card_infor_id`),
  ADD CONSTRAINT `transactions_ibfk_3` FOREIGN KEY (`Transaction_type_id`) REFERENCES `transaction_types` (`Transaction_type_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`Role_id`) REFERENCES `role` (`Role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
