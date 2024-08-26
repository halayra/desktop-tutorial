USE [project_Solana]
GO
/****** Object:  Table [dbo].[account]    Script Date: 8/26/2024 4:32:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[username] [varchar](255) NOT NULL,
	[email] [varchar](255) NULL,
	[hinh_anh] [varchar](255) NULL,
	[password] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[hovaten] [nvarchar](255) NULL,
	[phone] [varchar](255) NULL,
 CONSTRAINT [PK__account__F3DBC57302E3D876] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[bai_viet]    Script Date: 8/26/2024 4:32:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[bai_viet](
	[mabv] [int] NOT NULL,
	[hinh_anh] [varchar](255) NULL,
	[ngay_dang] [date] NULL,
	[noi_dung] [nvarchar](255) NULL,
	[tieu_de] [nvarchar](255) NULL,
	[username] [varchar](255) NOT NULL,
 CONSTRAINT [PK__bai_viet__7A2255EC9041F09F] PRIMARY KEY CLUSTERED 
(
	[mabv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[luot_tuong_tac_bai_viet]    Script Date: 8/26/2024 4:32:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[luot_tuong_tac_bai_viet](
	[id] [int] NOT NULL,
	[username] [varchar](255) NULL,
	[mabv] [int] NULL,
 CONSTRAINT [PK__luot_tuo__3213E83F4B6072E6] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tuyen_dung]    Script Date: 8/26/2024 4:32:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tuyen_dung](
	[matd] [int] NOT NULL,
	[hinh_anh] [varchar](255) NULL,
	[ngay_dang] [date] NULL,
	[noi_dung] [nvarchar](255) NULL,
	[tieu_de] [nvarchar](255) NULL,
	[username] [varchar](255) NULL,
	[ma_vi_tuyen_dung] [varchar](255) NULL,
 CONSTRAINT [PK__tuyen_du__7A217E6F0E3BEAA0] PRIMARY KEY CLUSTERED 
(
	[matd] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[wallet]    Script Date: 8/26/2024 4:32:16 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[wallet](
	[ma_vi] [varchar](255) NOT NULL,
	[so_tien] [float] NULL,
	[username] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[ma_vi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[account] ([username], [email], [hinh_anh], [password], [role], [hovaten], [phone]) VALUES (N'thanhne1', N'thanhne@gmail.com', N'img1.jpg', N'123', N'admin', N'Phạm Thị Mỹ Trân', N'0984563130')
INSERT [dbo].[account] ([username], [email], [hinh_anh], [password], [role], [hovaten], [phone]) VALUES (N'thanhne2', N'thanhne2@gmail.com', N'img2.jpg', N'123', N'user', N'Ngô Thị Mỹ Duyên', N'0985632410')
INSERT [dbo].[account] ([username], [email], [hinh_anh], [password], [role], [hovaten], [phone]) VALUES (N'thanhne3', N'thanhne3@gmail.com', N'img3.jpg', N'123', N'user', N'Mỹ Ngọc', N'0987654120')
INSERT [dbo].[account] ([username], [email], [hinh_anh], [password], [role], [hovaten], [phone]) VALUES (N'thanhne4', N'thanhne4@gmail.com', N'img4.jpg', N'123', N'business', N'FPT Software', N'122033599')
GO
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1, N'hinh1.jpg', CAST(N'2024-03-08' AS Date), N'Nội dung tiêu đề 1', N'Tiêu đề 1', N'thanhne1')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1452, N'0e7e7ffd-f5b5-491b-b76b-eed9abda6f21.jpg', CAST(N'2024-08-16' AS Date), N'hi em', N'fdsfsd', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1502, N'3ea414e5-b490-46ba-bf61-c516d5f8c6b7.png', CAST(N'2024-08-16' AS Date), N'bug', N'fdsfs', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1552, N'fbf7865e-38a9-460b-a5fe-3c5fcaee7676.jpg', CAST(N'2024-08-16' AS Date), N'fsdfs', N'fsdfsd', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1553, N'c83482ce-a621-4ee7-bfca-c3c22f2e0e13.png', CAST(N'2024-08-16' AS Date), N'thanhf nef', N'gdfgdf', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1554, N'ebcaf503-406a-4a5f-9c6e-9b23feb7bed2.jpg', CAST(N'2024-08-16' AS Date), N'hihih', N'fdsfsdf', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1555, N'93e80e51-3cef-4920-b331-c61d84e7a6e8.jpg', CAST(N'2024-08-16' AS Date), N'xin chaof', N'fdsfsf', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1602, N'88b98d9c-c4f7-4871-9d1b-57a413bf69f6.png', CAST(N'2024-08-16' AS Date), N'như nè', N'dfssdfsd', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1603, N'244cc983-2e68-4c05-872c-194eeebae3e6.jpg', CAST(N'2024-08-16' AS Date), N'nhi nè', N'fasjkf', N'thanhne3')
INSERT [dbo].[bai_viet] ([mabv], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username]) VALUES (1652, N'f218f7b9-82b4-4e37-b792-bf72d093b9ab.png', CAST(N'2024-08-23' AS Date), N'xin chào 23/8', N'Xin chào 23/8', N'thanhne4')
GO
INSERT [dbo].[luot_tuong_tac_bai_viet] ([id], [username], [mabv]) VALUES (356, N'thanhne3', 1)
INSERT [dbo].[luot_tuong_tac_bai_viet] ([id], [username], [mabv]) VALUES (452, N'thanhne4', 1)
INSERT [dbo].[luot_tuong_tac_bai_viet] ([id], [username], [mabv]) VALUES (502, N'thanhne3', 1452)
GO
INSERT [dbo].[tuyen_dung] ([matd], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username], [ma_vi_tuyen_dung]) VALUES (1, N'a12f2ebb-08eb-45eb-be44-fc16a1448a18.jpg', CAST(N'2024-08-20' AS Date), N'Uu tiên sinh viên tru?ng cao d?ng FPT nhá', N'tuy?n d?ng th?c t?p sinh IT', N'thanhne4', NULL)
INSERT [dbo].[tuyen_dung] ([matd], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username], [ma_vi_tuyen_dung]) VALUES (2, N'd8f96a4b-e247-4e3b-946b-d43bb49cf39b.jpg', CAST(N'2024-08-21' AS Date), N'sdgsdg', N'dsgsdg', N'thanhne4', NULL)
INSERT [dbo].[tuyen_dung] ([matd], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username], [ma_vi_tuyen_dung]) VALUES (52, N'02203c9b-ed75-4283-a319-e84c58b55512.jpg', CAST(N'2024-08-21' AS Date), N'sdfsd', N'fsdfsdf', N'thanhne4', NULL)
INSERT [dbo].[tuyen_dung] ([matd], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username], [ma_vi_tuyen_dung]) VALUES (53, N'f96283e5-7e03-4457-b1de-e7850c189896.jpg', CAST(N'2024-08-21' AS Date), N'fsdfsdf', N'fsdfsdf', N'thanhne4', N'67UPhFyHMbDEFH8t3CfYoXhTS7AunXcJUA2hRvLqn8z3')
INSERT [dbo].[tuyen_dung] ([matd], [hinh_anh], [ngay_dang], [noi_dung], [tieu_de], [username], [ma_vi_tuyen_dung]) VALUES (102, N'1cd35382-353e-4889-b5f1-925ed261573b.png', CAST(N'2024-08-23' AS Date), N'Yêu cầu công việc:
- Hiểu biết Java spring boot
- Có khả năng đọc hiểu tiếng anh
- Là sinh viên FPT Polytechnic là một lợi thế', N'tuyển dụng thực tập sinh IT', N'thanhne4', N'67UPhFyHMbDEFH8t3CfYoXhTS7AunXcJUA2hRvLqn8z3')
GO
INSERT [dbo].[wallet] ([ma_vi], [so_tien], [username]) VALUES (N'67UPhFyHMbDEFH8t3CfYoXhTS7AunXcJUA2hRvLqn8z3', 671.868160957, N'thanhne4')
INSERT [dbo].[wallet] ([ma_vi], [so_tien], [username]) VALUES (N'7wfp6apUR8WfHmB4e4LGDqc8JfnuZiDR7NKFXpLzBsgR', 8.999851996, N'thanhne4')
GO
ALTER TABLE [dbo].[bai_viet]  WITH CHECK ADD  CONSTRAINT [FK9apq87xmvyjd1xr78faywx7tg] FOREIGN KEY([username])
REFERENCES [dbo].[account] ([username])
GO
ALTER TABLE [dbo].[bai_viet] CHECK CONSTRAINT [FK9apq87xmvyjd1xr78faywx7tg]
GO
ALTER TABLE [dbo].[luot_tuong_tac_bai_viet]  WITH CHECK ADD  CONSTRAINT [FKkps429o5wrevhvmvm6vgpepd5] FOREIGN KEY([username])
REFERENCES [dbo].[account] ([username])
GO
ALTER TABLE [dbo].[luot_tuong_tac_bai_viet] CHECK CONSTRAINT [FKkps429o5wrevhvmvm6vgpepd5]
GO
ALTER TABLE [dbo].[luot_tuong_tac_bai_viet]  WITH CHECK ADD  CONSTRAINT [FKkxggrv4k91dli05pucgso2jqt] FOREIGN KEY([mabv])
REFERENCES [dbo].[bai_viet] ([mabv])
GO
ALTER TABLE [dbo].[luot_tuong_tac_bai_viet] CHECK CONSTRAINT [FKkxggrv4k91dli05pucgso2jqt]
GO
ALTER TABLE [dbo].[tuyen_dung]  WITH CHECK ADD  CONSTRAINT [FK958e5cb3b8243lk44dpvg1o6n] FOREIGN KEY([username])
REFERENCES [dbo].[account] ([username])
GO
ALTER TABLE [dbo].[tuyen_dung] CHECK CONSTRAINT [FK958e5cb3b8243lk44dpvg1o6n]
GO
ALTER TABLE [dbo].[wallet]  WITH CHECK ADD  CONSTRAINT [FKjnyoftnu0w3pmj9b5ulqasfi8] FOREIGN KEY([username])
REFERENCES [dbo].[account] ([username])
GO
ALTER TABLE [dbo].[wallet] CHECK CONSTRAINT [FKjnyoftnu0w3pmj9b5ulqasfi8]
GO
