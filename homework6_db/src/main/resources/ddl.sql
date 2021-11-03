-- -----------------------------------------------------
-- Table `mydb`.`t_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`t_user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`t_user` (
  `ID` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `cellphone` VARCHAR(15) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`t_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`t_order` ;

CREATE TABLE IF NOT EXISTS `mydb`.`t_order` (
  `id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  `total_money` DECIMAL(20,4) NOT NULL COMMENT '总金额',
  `status` TINYINT NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`),
  INDEX `IDX_USER_ID` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`t_order_goods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`t_order_goods` ;

CREATE TABLE IF NOT EXISTS `mydb`.`t_order_goods` (
  `id` BIGINT NOT NULL COMMENT '订单商品表',
  `order_id` BIGINT NOT NULL,
  `goods_id` BIGINT NOT NULL,
  `num` INT NOT NULL COMMENT '数量',
  `create_time` DATETIME NOT NULL,
  `update_Time` DATETIME NOT NULL,
  `t_order_goodscol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`t_goods`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`t_goods` ;

CREATE TABLE IF NOT EXISTS `mydb`.`t_goods` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL COMMENT '商品名称',
  `description` VARCHAR(45) NULL COMMENT '商品描述',
  `stock_num` INT NOT NULL COMMENT '库存数量',
  `price` DECIMAL NOT NULL COMMENT '单价',
  `create_time` DATETIME NULL,
  `update_time` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`t_shopping_car`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`t_shopping_car` ;

CREATE TABLE IF NOT EXISTS `mydb`.`t_shopping_car` (
  `id` BIGINT NOT NULL,
  `user_id` VARCHAR(45) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `update_time` DATETIME NOT NULL,
  `num` BIGINT NOT NULL,
  `goods_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `IDX_USER_ID` (`user_id` ASC) VISIBLE)
ENGINE = InnoDB;


