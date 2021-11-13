/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
create table Customer (
   Id                   int                  not null,
   FirstName            varchar(40)         not null,
   LastName             varchar(40)         not null,
   City                 varchar(40)         null,
   Country              varchar(40)         null,
   Phone                varchar(20)         null,
   constraint PK_CUSTOMER primary key (Id)
)
;

/*==============================================================*/
/* Index: IndexCustomerName                                     */
/*==============================================================*/
create index IndexCustomerName on Customer (
LastName ASC,
FirstName ASC
)
;

/*==============================================================*/
/* Table: ShopOrder                                               */
/*==============================================================*/
create table ShopOrder (
   Id                   int                  not null,
   OrderDate            datetime             not null,
   OrderNumber          varchar(10)         null,
   CustomerId           int                  not null,
   TotalAmount          decimal(12,2)        null default 0,
   constraint PK_ORDER primary key (Id)
)
;

/*==============================================================*/
/* Index: IndexOrderCustomerId                                  */
/*==============================================================*/
create index IndexOrderCustomerId on ShopOrder (
CustomerId ASC
)
;

/*==============================================================*/
/* Index: IndexOrderOrderDate                                   */
/*==============================================================*/
create index IndexOrderOrderDate on ShopOrder (
OrderDate ASC
)
;

/*==============================================================*/
/* Table: OrderItem                                             */
/*==============================================================*/
create table OrderItem (
   Id                   int                  not null,
   OrderId              int                  not null,
   ProductId            int                  not null,
   UnitPrice            decimal(12,2)        not null default 0,
   Quantity             int                  not null default 1,
   constraint PK_ORDERITEM primary key (Id)
)
;

/*==============================================================*/
/* Index: IndexOrderItemOrderId                                 */
/*==============================================================*/
create index IndexOrderItemOrderId on OrderItem (
OrderId ASC
)
;

/*==============================================================*/
/* Index: IndexOrderItemProductId                               */
/*==============================================================*/
create index IndexOrderItemProductId on OrderItem (
ProductId ASC
)
;

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
create table Product (
   Id                   int                  not null,
   ProductName          varchar(50)         not null,
   SupplierId           int                  not null,
   UnitPrice            decimal(12,2)        null default 0,
   Package              varchar(30)         null,
   IsDiscontinued       bit                  not null default 0,
   constraint PK_PRODUCT primary key (Id)
)
;

/*==============================================================*/
/* Index: IndexProductSupplierId                                */
/*==============================================================*/
create index IndexProductSupplierId on Product (
SupplierId ASC
)
;

/*==============================================================*/
/* Index: IndexProductName                                      */
/*==============================================================*/
create index IndexProductName on Product (
ProductName ASC
)
;

/*==============================================================*/
/* Table: Supplier                                              */
/*==============================================================*/
create table Supplier (
   Id                   int                  not null,
   CompanyName          varchar(40)         not null,
   ContactName          varchar(50)         null,
   ContactTitle         varchar(40)         null,
   City                 varchar(40)         null,
   Country              varchar(40)         null,
   Phone                varchar(30)         null,
   Fax                  varchar(30)         null,
   constraint PK_SUPPLIER primary key (Id)
)
;

/*==============================================================*/
/* Index: IndexSupplierName                                     */
/*==============================================================*/
create index IndexSupplierName on Supplier (
CompanyName ASC
)
;

/*==============================================================*/
/* Index: IndexSupplierCountry                                  */
/*==============================================================*/
create index IndexSupplierCountry on Supplier (
Country ASC
)
;

alter table ShopOrder
   add constraint FK_ORDER_REFERENCE_CUSTOMER foreign key (CustomerId)
      references Customer (Id)
;

alter table OrderItem
   add constraint FK_ORDERITE_REFERENCE_ORDER foreign key (OrderId)
      references ShopOrder (Id)
;

alter table OrderItem
   add constraint FK_ORDERITE_REFERENCE_PRODUCT foreign key (ProductId)
      references Product (Id)
;

alter table Product
   add constraint FK_PRODUCT_REFERENCE_SUPPLIER foreign key (SupplierId)
      references Supplier (Id)
;
