--
-- 
--

-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS library1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE library1;
--
-- Cấu trúc bảng cho bảng `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL,
  `Email` varchar(120) DEFAULT NULL,
  `UserName` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Image` blob default null
);

--
-- Đang đổ dữ liệu cho bảng `admin`
--

INSERT INTO `admin` (`id`, `Name`, `Email`, `UserName`, `Password`) VALUES
(2, 'admin', 'admin@test.com', 'admin', '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL,
  `Email` varchar(120) DEFAULT NULL,
  `Address` varchar(120) DEFAULT NULL,
  `Shift_count` int(10) DEFAULT 0,
  `Start_work_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `UserName` varchar(100) NOT NULL UNIQUE,
  `Password` varchar(100) NOT NULL,
  `Image` blob default null
);

--
-- Đổ dữ liệu cho bảng staff
--


INSERT INTO `staff` (`id`, `Name`,`Phone`, `Email`,`Address`,`Shift_count`,`Start_work_date`, `UserName`, `Password`) VALUES
(2, 'Nguyễn Thị Thanh Ngân', '0359172536','staff@test.com', '3 Tạ Quang Bửu, Hai Bà Trưng', 123,null,'ngan','1234');

--
-- Cấu trúc bảng cho bảng `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Category_id` varchar(150) DEFAULT NULL,
  `Author` varchar(150) DEFAULT NULL,
  `PublishYear` int(11) null default null,
  `Amount` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Brief`  varchar(255) default null,
  `Image` blob default null
);
INSERT INTO `book` (`id`, `Name`, `Category_id`, `Author`, `PublishYear`,`Amount`,`Price`) VALUES
(1, 'Harry Potter and the Philosopher Stone', 'cartoon', 'J. K. Rowling', 2001,'14', '15'),
(2, 'Harry Potter and the Chamber of Secrets', 'cartoon', 'J. K. Rowling', 2002,'14', '15'),
(3, 'Harry Potter and the Prisoner of Azkaban', 'cartoon', 'J. K. Rowling', 2004,'14', '15');

--
-- Cấu trúc bảng cho bảng `tblissuedbookdetails`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `BookId` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `StaffID` int(11) NOT NULL,
  `IssuesDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ExpireDate` timestamp null default null ,
  `Price` int(11) NOT NULL,
  `Amount` int(10) NOT NULL
);

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL ,
  `Name` varchar(100) DEFAULT NULL,
  `Phone` char(11) DEFAULT NULL UNIQUE,
  `Email` varchar(120) DEFAULT NULL,
  `Address` varchar(120) DEFAULT NULL,
  `Registerdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `Expireddate` timestamp NULL DEFAULT NULL,
  `Membership`  boolean not null default 0,
  `image` blob default null
);

--
-- Đang đổ dữ liệu cho bảng `tblstudents`
--

INSERT INTO `customer` (`id`, `Name`, `Phone`, `Email`, `Address`, `Expireddate`, `Membership`) VALUES
(1, 'test', '085461587', 'test@gmail.com', '133 hồng mai','2020-04-18 00:00:00', true);

--
-- Cấu trúc cho bảng `BillDetail`
--

create table `BillDetail` (
	`id` int(11) not null,
    `BookId` int(11) not null,
    `Amount` int(11) not null  
);
--
-- Đổ dữ liệu cho bảng BillDetail:
--
insert Into `BillDetail`  (`id`, `BookId`, `Amount`) values ( 1,1,3);
insert Into `BillDetail`  (`id`, `BookId`, `Amount`) values ( 1,2,3);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tblauthors`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_book` (`BookId`),
  ADD KEY `FK_customer` (`CustomerId`),
  ADD KEY `FK_staff` (`StaffId`);

--
-- Chỉ mục cho bảng BillDetail
--
ALTER TABLE `BillDetail`
	ADD PRIMARY KEY (`id`,`BookId`);
    
--
-- Chỉ mục cho bảng `customer`
--

ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT cho các bảng đã đổ
--
--
-- AUTO_INCREMENT cho bảng `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT cho bảng `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FK_id` FOREIGN KEY (`id`) REFERENCES `BillDetail` (`id`),
  ADD CONSTRAINT `FK_customerid` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FK_staffid` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`id`);

-- Các ràng buộc cho bảng `BillDetail`
alter table `BillDetail`
	add constraint `FK_bookid` foreign key (`BookId`) references `book` (`id`)