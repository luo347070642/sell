CREATE TABLE `product_info` (
	`product_id` VARCHAR(32) NOT NULL,
	`product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
	`product_price` DECIMAL(8,2) NOT NULL COMMENT '单价',
	`product_stock` INT NOT NULL COMMENT '库存',
	`product_description` VARCHAR(64) COMMENT '描述',
	`product_icon` VARCHAR(512) COMMENT '小图',
	`category_type` INT NOT NULL COMMENT '类目编号',
    `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`product_id`)
) COMMENT '商品表';

CREATE TABLE `product_category` (
	`category_id` INT NOT NULL AUTO_INCREMENT,
	`category_name` VARCHAR(64) NOT NULL COMMENT '类目名称',
	`category_type` INT NOT NULL COMMENT '类目编号',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`category_id`),
	UNIQUE KEY `uqe_category_type` (`category_type`)
) COMMENT '类目表';

CREATE TABLE `order_master` (
	`order_id` VARCHAR(32) NOT NULL,
	`buyer_name` VARCHAR(32) NOT NULL COMMENT '买家名称',
	`buyer_phone` VARCHAR(32) NOT NULL COMMENT '买家电话',
	`buyer_address` VARCHAR(128) NOT NULL COMMENT '买家地址',
	`buyer_openid` VARCHAR(64) NOT NULL COMMENT '买家微信id',
	`buyer_amount` DECIMAL(8,2) NOT NULL COMMENT '订单总金额',
	`buyer_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '订单状态，默认0新订单',
	`pay_status` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`order_id`),
	KEY `idx_buyer_openid` (`buyer_openid`)
) COMMENT '订单表';

CREATE TABLE `order_detail` (
	`detail_id` VARCHAR(32) NOT NULL,
	`order_id` VARCHAR(32) NOT NULL,
	`product_id` VARCHAR(32) NOT NULL,
	`product_name` VARCHAR(64) NOT NULL COMMENT '商品名称',
	`product_price` DECIMAL(8,2) NOT NULL COMMENT '商品单价',
	`product_quantity` INT NOT NULL COMMENT '商品数量',
	`product_icon` VARCHAR(512) COMMENT '商品小图',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY (`detail_id`),
	KEY `idx_order_id` (`order_id`)
) COMMENT '订单详情表';

create table `seller_info` (
    `id` varchar(32) not null,
    `username` varchar(32) not null,
    `password` varchar(32) not null,
    `openid` varchar(64) not null comment '微信openid',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
    primary key (`id`)
) comment '卖家信息表';