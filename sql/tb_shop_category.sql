
CREATE TABLE `tb_shop_category`(
`shop_category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '店铺类别id',
`shop_category_name` VARCHAR(100) NOT NULL DEFAULT ''  COMMENT '店铺类别名称',
`shop_category_desc` VARCHAR(1000) DEFAULT  '' COMMENT '店铺类别的描述',
`shop_category_img` VARCHAR(2000) DEFAULT NULL COMMENT '店铺类别图片地址',
`priority` INT(2) NOT NULL DEFAULT '0' COMMENT '店铺类别的优先级',
`create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
`last_edit_time` DATETIME DEFAULT NULL COMMENT '最近一次的修改时间',
`parent_id` INT(11) DEFAULT NULL COMMENT '店铺类别的父类别',
PRIMARY KEY(`shop_category_id`),
KEY `fk_shop_category_self`(`parent_id`),
CONSTRAINT `fk_shop_category_self` FOREIGN KEY (`parent_id`)
REFERENCES `tb_shop_category` (`shop_category_id`)
)

ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8

