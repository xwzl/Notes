Linux 登陆数据库

	mysql -u root -p 

  
创建数据库		

	CREATE DATABASE WE;
	
删除数据库

  drop database WE;

选择数据库

	use wtf;
	
数据库基础

	http://www.runoob.com/mysql/mysql-data-types.html
	
创建数据表

  create table `TableSample` (
			`id` int UNSIGNED AUTO_INCREMENT,
			`title` VARCHAR(100) NOT NULL,
			`author` VARCHAR(40) NOT NULL,
		  `creat_time` DATE,
			 PRIMARY KEY(`id`)
	)ENGINE = INNODB DEFAULT CHARSET = utf8;
	
	如果你不想字段为 NULL 可以设置字段的属性为 NOT NULL， 在操作数据库时如果输入该字段的数据为NULL ，就会报错。
  
	AUTO_INCREMENT定义列为自增的属性，一般用于主键，数值会自动加1。
  
	PRIMARY KEY关键字用于定义列为主键。 您可以使用多列来定义主键，列间以逗号分隔。
  
	ENGINE 设置存储引擎，CHARSET 设置编码。
	
删除数据表

  drop table TableSample

查询		
		select * from user where u_id > 3 

		insert into role (role) values(12)

更新
		update role set address='we' where id=2 

		SELECT * FROM role as a,user as u WHERE a.role = u.role

模糊查询

		Select * from user where username like '张%'

降序

		Select * from user order by phone_number DESC  默认ASC升序

MySQL UNION 操作符用于连接两个以上的 SELECT 语句的结果组合到一个结果集合中。多个 SELECT 语句会删除重复的数据。
UNION All 包含重复数据，UNION不包含重复数据

		select address from role UNION select address from user order by address

		select * from user order by role,phone_number DESC

GROUP BY 语句根据一个或多个列对结果集进行分组。在分组的列上我们可以使用 COUNT, SUM, AVG,等函数
address 与 BY 后面的列表值address 必须一致

		select address,count(*) from user GROUP BY address 

INNER JOIN（内连接,或等值连接）：获取两个表中字段匹配关系的记录。

		SELECT * FROM user INNER JOIN role on user.role = role.role order by user.role

LEFT JOIN（左连接）：获取左表所有记录，即使右表没有对应匹配的记录。

		SELECT * FROM user LEFT JOIN role on user.role = role.role order by user.role

RIGHT JOIN（右连接）： 与 LEFT JOIN 相反，用于获取右表所有记录，即使左表没有对应匹配的记录。

		SELECT * FROM user right JOIN role on user.role = role.role order by user.role

我们已经知道 MySQL 使用 SQL SELECT 命令及 WHERE 子句来读取数据表中的数据,但是当提供的查询条件字段为 NULL 时，该命令可能就无法正常工作。

为了处理这种情况，MySQL提供了三大运算符:

	IS NULL: 当列的值是 NULL,此运算符返回 true。
	IS NOT NULL: 当列的值不为 NULL, 运算符返回 true。
	<=>: 比较操作符（不同于=运算符），当比较的的两个值为 NULL 时返回 true。

关于 NULL 的条件比较运算是比较特殊的。你不能使用 = NULL 或 != NULL 在列中查找 NULL 值 。
在 MySQL 中，NULL 值与任何其它值的比较（即使是 NULL）永远返回 false，即 NULL = NULL 返回false 。
MySQL 中处理 NULL 使用 IS NULL 和 IS NOT NULL 运算符。

	SELECT * FROM user where user.apartment  IS NOT NULL
	
	SELECT * FROM user where user.apartment  IS NULL

模式								描述

^					匹配输入字符串的开始位置。如果设置了 RegExp 对象的 Multiline 属性，^ 也匹配 '\n' 或 '\r' 之后的位置。

$					匹配输入字符串的结束位置。如果设置了RegExp 对象的 Multiline 属性，$ 也匹配 '\n' 或 '\r' 之前的位置。

.					匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用象 '[.\n]' 的模式。

[...]			字符集合。匹配所包含的任意一个字符。例如， '[abc]' 可以匹配 "plain" 中的 'a'。

[^...]		负值字符集合。匹配未包含的任意字符。例如， '[^abc]' 可以匹配 "plain" 中的'p'。

p1|p2|p3	匹配 p1 或 p2 或 p3。例如，'z|food' 能匹配 "z" 或 "food"。'(z|f)ood' 则匹配 "zood" 或 "food"。

*					匹配前面的子表达式零次或多次。例如，zo* 能匹配 "z" 以及 "zoo"。* 等价于{0,}。

+					匹配前面的子表达式一次或多次。例如，'zo+' 能匹配 "zo" 以及 "zoo"，但不能匹配 "z"。+ 等价于 {1,}。

{n}	n 		是一个非负整数。匹配确定的 n 次。例如，'o{2}' 不能匹配 "Bob" 中的 'o'，但是能匹配 "food" 中的两个 o。

{n,m}			m 和 n 均为非负整数，其中n <= m。最少匹配 n 次且最多匹配 m 次。

		SELECT * FROM user where apartment REGEXP '^ma';匹配以ma开头的apprentment忽略大小写
		SELECT * FROM user where apartment REGEXP 'ay$';查找user字段中以'ay'为结尾的所有数据
    SELECT * FROM user where apartment REGEXP 'ay'; 查找包含'ay'的所有数据
		SELECT * FROM user where apartment REGEXP '^[aeiou]|ay$';查找apartment字段中以元音字符开头或以'ay'字符串结尾的所有数据：
		SELECT * FROM user where username REGEXP '^[张王]|四$';
		
MySQL 事务

MySQL 事务主要用于处理操作量大，复杂度高的数据。比如说，在人员管理系统中，你删除一个人员，你即需要删除人员的基本资料，也要删除和该人员相关的信息，如信箱，文章等等，这样，这些数据库操作语句就构成一个事务！

			在 MySQL 中只有使用了 Innodb 数据库引擎的数据库或表才支持事务。
			
			事务处理可以用来维护数据库的完整性，保证成批的 SQL 语句要么全部执行，要么全部不执行。
			
			事务用来管理 insert,update,delete 语句
			
一般来说，事务是必须满足4个条件（ACID）：：原子性（Atomicity，或称不可分割性）、一致性（Consistency）、隔离性（Isolation，又称独立性）、持久性（Durability）。


			原子性：一个事务（transaction）中的所有操作，要么全部完成，要么全部不完成，不会结束在中间某个环节。事务在执行过程中发生错误，会被回滚
			（Rollback）到事务开始前的状态，就像这	个事务从来没有执行过一样。
			
			一致性：在事务开始之前和事务结束以后，数据库的完整性没有被破坏。这表示写入的资料必须完全符合所有的预设规则，这包含			
			资料的精确度、串联性以及后续数据库可以自发性地完成预定的工作。
			
			隔离性：数据库允许多个并发事务同时对其数据进行读写和修改的能力，隔离性可以防止多个事务并发执行时由于交叉执行而导致数据的不一致。事务隔离分为不同级别，
			包括读未提交（Read uncommitted）、读提交（read committed）、可重复读（repeatable read）和串行化（Serializable）。
			
			持久性：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。
			
		在 MySQL 命令行的默认设置下，事务都是自动提交的，即执行 SQL 语句后就会马上执行 COMMIT 操作。因此要显式地开启一个事务务须使用命令 BEGIN 或 START TRANSACTION，
    或者执行命令 SET AUTOCOMMIT=0，用来禁止使用当前会话的自动提交。

事务控制语句：

		BEGIN 或 START TRANSACTION 显式地开启一个事务；

		COMMIT 也可以使用 COMMIT WORK，不过二者是等价的。COMMIT 会提交事务，并使已对数据库进行的所有修改成为永久性的；

		ROLLBACK 也可以使用 ROLLBACK WORK，不过二者是等价的。回滚会结束用户的事务，并撤销正在进行的所有未提交的修改；

		SAVEPOINT identifier，SAVEPOINT 允许在事务中创建一个保存点，一个事务中可以有多个 SAVEPOINT；

		RELEASE SAVEPOINT identifier 删除一个事务的保存点，当没有指定的保存点时，执行该语句会抛出一个异常；

		ROLLBACK TO identifier 把事务回滚到标记点；

		SET TRANSACTION 用来设置事务的隔离级别。InnoDB 存储引擎提供事务的隔离级别有READ U NCOMMITTED、READ COMMITTED、REPEATABLE READ 和 SERIALIZABLE。
		
MYSQL 事务处理主要有两种方法：
		
		1、用 BEGIN, ROLLBACK, COMMIT来实现

				BEGIN 开始一个事务

				ROLLBACK 事务回滚

				COMMIT 事务确认
				
		2、直接用 SET 来改变 MySQL 的自动提交模式:

				SET AUTOCOMMIT=0 禁止自动提交

				SET AUTOCOMMIT=1 开启自动提交


事务测试	
    
		SET AUTOCOMMIT=0 禁止自动提交
		
		begin;                                #开启事务
		
		insert into role values(8,1,"22");
		
		SELECT * FROM role ;
		
		ROllback;
		
		commit;                     					   # 提交事务
		
	1.	SET AUTOCOMMIT=0                   		 #禁止自动提交
		
	2. 	begin;                              	 #开启事务
	
	3. 	SAVEPOINT id1;                         #设置保存点
		
	4.	insert into role values(9,2,"112");    #插入记录,查询列表
		
	5. 	SAVEPOINT id2;                         #设置保存点
		
	6. 	insert into role values(10,2,"112");    #插入记录

	7. 	SELECT * FROM role;                     #查询
		
	8. 	ROLLBACK to id2;                        #回滚 到保存点id1,再次查询
		
	9.  ROLLBACK TO id1;											  #回滚 到保存点id2,再次查询
		
	10.	RELEASE SAVEPOINT id2;                  #删除保存点
		
	11.	RELEASE SAVEPOINT id1;                  #删除保存点

  12. commit;                                 #提交事务
	 
	13. SET AUTOCOMMIT=1;                       #开启自动提交

MySQL ALTER命令

   当我们需要修改数据表名或者修改数据表字段时，就需要使用到MySQL ALTER命令。
	 
   删除，添加或修改表字段
	 
	 show columns from TableSample           			            #查看表的字段信息
	 
	 1. ALTER table TableSample drop title;     			            #删除title字段
	 
	 2. alter table TableSample add title VARCHAR(10)            #添加title字段
	 
	 如果你需要指定新增字段的位置，可以使用MySQL提供的关键字 FIRST (设定位第一列)， AFTER 字段名（设定位于某个字段之后）。
	 
	 1. alter table TableSample add title VARCHAR(10) AFTER id;  #添加并指定字段并且排在ID后面
	 
	 2. alter table TableSample add title VARCHAR(10) FIRST;     #添加并指定字段da到收尾
	 
	 FIRST 和 AFTER 关键字可用于 ADD 与 MODIFY 子句，所以如果你想重置数据表字段的位置就
	 需要先使用 DROP 删除字段然后使用 ADD 来添加字段并设置位置。
	 
   修改字段类型及名称
	 
	 1. ALTER TABLE TableSample MODIFY title int;   #修改字段属性
	 
	 使用 CHANGE 子句, 语法有很大的不同。 在 CHANGE 关键字之后，紧跟着的是你要修改的字段名，然后指定新字段名及类型。
	 
	 1. ALTER TABLE TableSample change title i BIGINT
 
	 2. ALTER TABLE TableSample change i title int 
	 
	 3. ALTER TABLE TableSample change title title VARCHAR(100)
	 
	 ALTER TABLE 对 Null 值和默认值的影响
	 
   当你修改字段时，你可以指定是否包含值或者是否设置默认值。
	 
	  show columns from TableSample
		
	 以下实例，指定字段 j 为 NOT NULL 且默认值为100 。
	 
	 1. ALTER TABLE TableSample MODIFY title BIGINT NOT NULL DEFAULT 100; #修改默认值
	 
	 修改字段默认值
	 
	 你可以使用 ALTER 来修改字段的默认值
	 
	 ALTER TABLE TableSample ALTER title SET DEFAULT 1000;
	 
	 你也可以使用 ALTER 命令及 DROP子句来删除字段的默认值，
	 
	 ALTER TABLE TableSample ALTER title drop default
	 
	 修改数据表类型，可以使用 ALTER 命令及 TYPE 子句来完成。
	 
	 ALTER TABLE TableSample ENGINE = MYISAM

   ALTER TABLE TableSample RENAME TO Women; #修改表名
	 
	 show columns from Women
	 
	 删除外键约束：keyName是外键别名
	 
	 SHOW TABLE STATUS LIKE 'Women';\G
	 
	 alter table Women drop foreign key keyName;
 
MySQL 索引

  MySQL索引的建立对于MySQL的高效运行是很重要的，索引可以大大提高MySQL的检索速度。
	
	打个比方，如果合理的设计且使用索引的MySQL是一辆兰博基尼的话，那么没有设计和使用索引的MySQL就是一个人力三轮车。

  索引分单列索引和组合索引。
	
	  单列索引，即一个索引只包含单个列，一个表可以有多个单列索引，但这不是组合索引。
		
		组合索引，即一个索引包含多个列。

  创建索引时，你需要确保该索引是应用在	SQL 查询语句的条件(一般作为 WHERE 子句的条件)。

  实际上，索引也是一张表，该表保存了主键与索引字段，并指向实体表的记录。

  上面都在说使用索引的好处，但过多的使用索引将会造成滥用。因此索引也会有它的缺点：虽然索引大大提高了查询速度，同时
	却会降低更新表的速度，如对表进行INSERT、UPDATE和DELETE。因为更新表时，MySQL不仅要保存数据，还要保存一下索引文件。

  建立索引会占用磁盘空间的索引文件。

普通索引
	
	创建索引
	
	这是最基本的索引，它没有任何限制。它有以下几种创建方式：
	
	  CREATE INDEX indexName ON mytable(username(length)); 
		
  如果是CHAR，VARCHAR类型，length可以小于字段实际长度；如果是BLOB和TEXT类型，必须指定 length。
	
	修改表结构(添加索引)
	
	  ALTER table tableName ADD INDEX indexName(columnName)
		
	创建表的时候直接指定
	
		CREATE TABLE mytable(  
 
			ID INT NOT NULL,   
			 
			username VARCHAR(16) NOT NULL,  
			 
			INDEX [indexName] (username(length))  
			 
		);  

   删除索引的语法
	 
	  DROP INDEX [indexName] ON mytable; 
		
	 唯一索引
	 
	 它与前面的普通索引类似，不同的就是：索引列的值必须唯一，但允许有空值。如果是组合索引，则列值的组合必须唯一。
	 它有以下几种创建方式：
	 
	 创建索引
	 
			CREATE UNIQUE INDEX indexName ON mytable(username(length)) 
	 
	 修改表结构
	 
			ALTER table mytable ADD UNIQUE [indexName] (username(length))
	 
	 创建表的时候直接指定
	 
		  CREATE TABLE mytable(  
 
				ID INT NOT NULL,   
				 
				username VARCHAR(16) NOT NULL,  
				 
				UNIQUE [indexName] (username(length))  
				 
			);  
			
		使用ALTER 命令添加和删除索引
		
		  有四种方式来添加数据表的索引：
			
			    ALTER TABLE tbl_name ADD PRIMARY KEY (column_list): 该语句添加一个主键，这意味着索引值必须是唯一的，且不能为NULL。

					ALTER TABLE tbl_name ADD UNIQUE index_name (column_list): 这条语句创建索引的值必须是唯一的（除了NULL外，NULL可能会出现多次）。
					
					ALTER TABLE tbl_name ADD INDEX index_name (column_list): 添加普通索引，索引值可出现多次。
  
	        ALTER TABLE tbl_name ADD FULLTEXT index_name (column_list):该语句指定了索引为 FULLTEXT ，用于全文索引。
					
		show columns from Women 
		
		ALTER TABLE Women ADD INDEX titles(title);  #添加索引
		
	  CREATE INDEX people1 ON Women(author(10));
		
		create table `TableSample` (
				`id` int UNSIGNED AUTO_INCREMENT,
				`title` VARCHAR(100) NOT NULL,
				`author` VARCHAR(40) NOT NULL,
				`creat_time` DATE,
				 INDEX we (title),
				 PRIMARY KEY(`id`)
		)ENGINE = INNODB DEFAULT CHARSET = utf8;
		
		CREATE INDEX we ON Women(title);            #有病
		
		ALTER TABLE Women ADD UNIQUE INDEX (title); #唯一索引
	  
		ALTER TABLE Women DROP INDEX title;         #删除索引
		
		使用 ALTER 命令添加和删除主键
		
		 ALTER TABLE Women MODIFY id INT NOT NULL;
		 
		 ALTER TABLE Women ADD PRIMARY KEY (id);
		 
	  显示索引信息
		 
		 SHOW INDEX FROM Women; \G
		 
MySQL 临时表

  MySQL 临时表在我们需要保存一些临时数据时是非常有用的。临时表只在当前连接可见，当关闭连接时，Mysql会自动删除表并释放所有空间。

  临时表在MySQL 3.23版本中添加，如果你的MySQL版本低于 3.23版本就无法使用MySQL的临时表。不过现在一般很少有再使用这么低版本的MySQL数据库服务了。
	
	实例
	
	create TEMPORARY table `TableSample1` (
				`id` int UNSIGNED AUTO_INCREMENT,
				`title` VARCHAR(100) NOT NULL,
				`author` VARCHAR(40) NOT NULL,
				`creat_time` DATE,
				 INDEX we (title),
				 PRIMARY KEY(`id`)
		)ENGINE = INNODB DEFAULT CHARSET = utf8;
		
		insert into TableSample1 values(1,"2212","sd",null)
		
		select * from TableSample1;
		
		当你使用 SHOW TABLES命令显示数据表列表时，你将无法看到 SalesSummary表。

    如果你退出当前MySQL会话，再使用 SELECT命令来读取原先创建的临时表数据，那你会发现数据库中没有该表的存在，因为在你退出时该临时表已经被销毁了。
		
	删除MySQL 临时表
	
	  默认情况下，当你断开与数据库的连接后，临时表就会自动被销毁。当然你也可以在当前MySQL会话使用 DROP TABLE 命令来手动删除临时表。
		
		Drop Table TableSample1
		
MySQL 复制表

    如果我们需要完全的复制MySQL的数据表，包括表的结构，索引，默认值等。 如果仅仅使用CREATE TABLE ... SELECT 命令，是无法实现的。

    本章节将为大家介绍如何完整的复制MySQL数据表，步骤如下：
		
		使用 SHOW CREATE TABLE 命令获取创建数据表(CREATE TABLE) 语句，该语句包含了原数据表的结构，索引等。
		
		复制以下命令显示的SQL语句，修改数据表名，并执行SQL语句，通过以上命令 将完全的复制数据表结构。
		
		如果你想复制表的内容，你就可以使用 INSERT INTO ... SELECT 语句来实现。
		
		实例
		
		尝试一下复制表user
		
		另一种完整复制表的方法,只复制表结构到新表
		
		create table user4 select * from user where 1=2

    create table user3 like user 
		
		可以拷贝一个表中其中的一些字段和数据
		
		CREATE TABLE user5 AS (SELECT username, password FROM user)
		
		可以将新建的表的字段改名:
		
		CREATE TABLE user6 AS (SELECT u_id, username AS uname, password AS pass FROM user)
		
		复制表结构和数据
		
		create table user2 select * from user
		
		CREATE TABLE user7 ( id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY ) AS ( SELECT * FROM user )
		
		步骤一：
		
		  获取数据表的完整结构。
			
			SHOW CREATE TABLE user;\G
			  
			//获取结果
			CREATE TABLE `user` (
				`u_id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
				`address` VARCHAR ( 255 ) DEFAULT NULL,
				`apartment` VARCHAR ( 255 ) DEFAULT NULL,
				`create_time` datetime DEFAULT NULL,
				`password` VARCHAR ( 255 ) DEFAULT NULL,
				`phone_number` VARCHAR ( 255 ) DEFAULT NULL,
				`role` INT ( 11 ) DEFAULT NULL,
				`username` VARCHAR ( 255 ) DEFAULT NULL,
				PRIMARY KEY ( `u_id` ) 
			) ENGINE = MyISAM AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8
		
		步骤二：

   修改SQL语句的数据表名，并执行SQL语句。
	 
	 CREATE TABLE `user1` (
			`u_id` INT ( 11 ) NOT NULL AUTO_INCREMENT,
			`address` VARCHAR ( 255 ) DEFAULT NULL,
			`apartment` VARCHAR ( 255 ) DEFAULT NULL,
			`create_time` datetime DEFAULT NULL,
			`password` VARCHAR ( 255 ) DEFAULT NULL,
			`phone_number` VARCHAR ( 255 ) DEFAULT NULL,
			`role` INT ( 11 ) DEFAULT NULL,
			`username` VARCHAR ( 255 ) DEFAULT NULL,
			PRIMARY KEY ( `u_id` ) 
		) ENGINE = MyISAM AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8
		
		步骤三：
		
	  执行完第二步骤后，你将在数据库中创建新的克隆表 user1。 如果你想拷贝数据表的数据你可以使用 INSERT INTO... SELECT 语句来实现。
    
		select * from user1
	  
		insert into user1 (select * from user)  拷贝所有数据
		

MySQL 处理重复数据

   有些 MySQL 数据表中可能存在重复的记录，有些情况我们允许重复数据的存在，但有时候我们也需要删除这些重复的数据。

   本章节我们将为大家介绍如何防止数据表出现重复数据及如何删除数据表中的重复数据。

  防止表中出现重复数据
   
	 你可以在 MySQL 数据表中设置指定的字段为 PRIMARY KEY（主键） 或者 UNIQUE（唯一） 索引来保证数据的唯一性。
   
	 让我们尝试一个实例：下表中无索引及主键，所以该表允许出现多条重复记录。

			CREATE TABLE person_tbl ( first_name CHAR ( 20 ), last_name CHAR ( 20 ), sex CHAR ( 10 ) );
								
   如果你想设置表中字段 first_name，last_name 数据不能重复，你可以设置双主键模式来设置数据的唯一性， 如果你设
	 
	 置了双主键，那么那个键的默认值不能为 NULL，可设置为 NOT NULL。如下所示：

		CREATE TABLE person_tbl (
			first_name CHAR ( 20 ) NOT NULL,
			last_name CHAR ( 20 ) NOT NULL,
			sex CHAR ( 10 ),
			PRIMARY KEY ( last_name, first_name ) 
		);
  
	如果我们设置了唯一索引，那么在插入重复数据时，SQL 语句将无法执行成功,并抛出错。

  INSERT IGNORE INTO 与 INSERT INTO 的区别就是 INSERT IGNORE 会忽略数据库中已经存在   
	
	的数据，如果数据库没有数据，就插入新的数据，如果有数据的话就跳过这条数据。这样就可
	
	以保留数据库中已经存在数据，达到在间隙中插入数据的目的。

  以下实例使用了 INSERT IGNORE INTO，执行后不会出错，也不会向数据表中插入重复数据：

  INSERT IGNORE INTO person_tbl (last_name, first_name) VALUES( 'Jay', 'Thomas');

  INSERT IGNORE INTO person_tbl (last_name, first_name) VALUES ( 'Jay', 'Thomas');
	
	INSERT IGNORE INTO 当插入数据时，在设置了记录的唯一性后，如果插入重复数据，将不返回错误，只以警告形式返回。 
	
	而 REPLACE INTO 如果存在 primary 或 unique  相同的记录，则先删除掉。再插入新记录。


	另一种设置数据的唯一性方法是添加一个 UNIQUE 索引，如下所示：

		CREATE TABLE person_tbl (
			first_name CHAR ( 20 ) NOT NULL,
			last_name CHAR ( 20 ) NOT NULL,
			sex CHAR ( 10 ),
			UNIQUE ( last_name, first_name ) 
		);
	
	统计重复数据

  SELECT phone_number, role ,count(*) as count from user as u GROUP BY u.role, u.phone_number HAVING count > 1 
	
	SELECT DISTINCT address,role FROM user

  删除重复数据
	
	如果你想删除数据表中的重复数据，你可以使用以下的SQL语句：
	
	CREATE TABLE tmp SELECT phone_number, role FROM user  GROUP BY phone_number, role;
	
	select * from tmp
	
	drop table user
	
	alter table tmp rename to user1;
	
	alter table user1 ADD INDEX role1(role)

	
	
		
