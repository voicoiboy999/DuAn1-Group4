Use SonGlasses

create table SanPham(
	MaSP nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenSP nvarchar(20),
);

--Bang kieu dang
create table KieuDang(
	MaKD nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenKD nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Loai mat kinh
create table LoaiMatKinh(
	MaLMK nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenLMK nvarchar(20) not null,
	Mota nvarchar(20) 
);

--Bang Mau mat kinh
create table MauMatKinh(
	MaMMK nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenMMK nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Chat lieu mat kinh
create table ChatLieuMatKinh(
	MaCLMK nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenCLMK nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Chat lieu gong
create table ChatLieuGong(
	MaCLG nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenCLG nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Màu gong
create table MauGong(
	MaMG nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenMG nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Kich co
create table KichCo(
	MaKC nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenKC nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Thuong hieu
create table ThuongHieu(
	MaTH nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenTH nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Chi tiet san pham
create table SanPhamChiTiet(
	MaSPCT int identity(1,1) primary key,
	MaSP nvarchar(10) references SanPham(MaSP),
	MaKD nvarchar(10) references KieuDang(MaKD),
	MaLMK nvarchar(10) references LoaiMatKinh(MaLMK),
	MaMMK nvarchar(10) references MauMatKinh(MaMMK),
	MaCLMK nvarchar(10) references ChatLieuMatKinh(MaCLMK),
	MaCLG nvarchar(10) references ChatLieuGong(MaCLG),
	MaMG nvarchar(10) references MauGong(MaMG),
	MaKC nvarchar(10) references KichCo(MaKC),
	MaTH nvarchar(10) references ThuongHieu(MaTH),
	SoLuong int,
	GiaBan float,
	NgayNhap date,
	TrangThai bit Default 1
);


create table KhachHang(
	MaKH nvarchar(10) primary key,
	TenKH nvarchar(20) not null,
	DiaChi nvarchar(100),
	SDT varchar(15) not null,
	Email varchar(50),
	CCCD nchar(12),
	NgayTao date,
);


-- Bang Vai tro
create table VaiTro(
	MaVT int identity(1,1) primary key,
	TenVT nvarchar(20),
	MoTa nvarchar(20)
);

-- Bang Nhan vien
create table NhanVien(
	MaNV nvarchar(10) primary key,
	HoTen nvarchar(30),
	GioiTinh bit,
	DiaChi nvarchar(100),
	SDT varchar(12),
	Email varchar(50),
	CCCD nchar(12),
	VaiTro bit
);

select  from NhanVien
-- Bang Hinh thuc giam gia
create table HinhThucGiamGia(
	MaHTGG bit primary key,
	TenHTGG nvarchar(20) not null,
	MoTa nvarchar(20),
);

-- Bang Hinh thuc thanh toan
create table HinhThucThanhToan(
	MaHTTT bit primary key,
	TenHTTT nvarchar(20),
	MoTa nvarchar(20)
);


-- Bang Trang thai
create table TrangThai(
	MaTT int primary key,
	TenTT nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Voucher
create table PhieuGiamGia(
	MaPGG nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	TenPGG nvarchar(20),
	DieuKienGiam float not null,
	GiaGiamToiDa float not null,
	SoLuong int,
	NgayTao date,
	NgayBatDau date,
	NgayKetThuc date,
	MaHTGG bit references HinhThucGiamGia(MaHTGG)
);

-- Bang Hoa don
create table HoaDon(
	MaHD nvarchar(10) primary key DEFAULT LEFT(REPLACE(NEWID(), '-', ''), 10),
	MaKH nvarchar(10) references KhachHang(MaKH),
	MaPGG nvarchar(10) references PhieuGiamGia(MaPGG),
	MaHTTT bit references HinhThucThanhToan(MaHTTT),
	MaTT int references TrangThai(MaTT),
	NgayTao date,
	GiaBanDau float,
	GiaGiam float,
	GiaCuoiCung float,
	TenKhachHang nvarchar(30),
	DiaChi nvarchar(100),
	SDT nvarchar(12)
);


-- Bang Hoa don chi tiet
create table HoaDonChiTiet(
	MaHDCT int identity(1,1) primary key,
	MaHD nvarchar(10) references HoaDon(MaHD),
	MaSPCT int references SanPhamChiTiet(MaSPCT),
	SoLuong int,
	DonGia float,
	ThanhTien float,
);

-- Bang Account
create table Account(
	TaiKhoan nvarchar(20) primary key,
	MatKhau nvarchar(20) not null,
	MaNV nvarchar(10) references NhanVien(MaNV),
	SDT varchar(12),
);

-- Bang Cong viec
create table CongViec(
	MaCV int primary key,
	TenCV nvarchar(20),
	MoTa nvarchar(20),
);

-- Bang Lich su hoa don
create table LichSuHoaDon(
	MaLSHD int identity(1,1) primary key,
	MaHD nvarchar(10) references HoaDon(MaHD),
	MaNV nvarchar(10) references NhanVien(MaNV),
	MaCV int references CongViec(MaCV),
	NgayThucHien date,
);

insert into HinhThucGiamGia(MaHTGG,TenHTGG)
values(1,N'Phần trăm')

select * from PhieuGiamGia
insert into PhieuGiamGia(TenPGG,DieuKienGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG)
values('abc',100,10,10,'2024-09-26','2024-09-26','2024-09-27',1)

select * from SanPham

select * from SanPhamChiTiet

insert into TrangThai(MaTT,TenTT)
values(4,N'Hoàn trả')

select * from HoaDon

insert into HoaDon(MaPGG,MaHTTT,MaTT,NgayTao,TenKhachHang,SDT,DiaChi)
values('3E4A638C5A',1,1,CONVERT(VARCHAR(10), GETDATE(), 120),'Vuong','','Lang Son')
