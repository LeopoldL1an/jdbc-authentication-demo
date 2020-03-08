create table if not exists SYS_DEPT
(
	UUID varchar(32) not null comment '主键'
		primary key,
	ROLE_UUID varchar(32) not null comment '角色主键',
	DEPT_PARENT varchar(32) null comment '父类部门',
	DEPT_NAME varchar(255) not null comment '部门名称',
	DEPT_EDITABLE tinyint(1) not null comment '可编辑状态',
	DEPT_ORDER tinyint null comment '部门顺序',
	REMARK varchar(255) null comment '备注'
)
comment '系统部门表';

create table if not exists SYS_MENU
(
	UUID varchar(32) not null comment '主键'
		primary key,
	MENU_NAME varchar(255) not null comment '菜单名称',
	MENU_ICON varchar(255) not null comment '菜单图标',
	MENU_PATH varchar(255) not null comment '菜单路径',
	MENU_ORDER tinyint not null comment '菜单顺序',
	REMARk varchar(255) null comment '备注'
)
comment '系统菜单表';

create table if not exists SYS_ROLE
(
	UUID varchar(32) not null comment '主键'
		primary key,
	ROLE_NAME varchar(255) not null comment '角色名称',
	ROLE_EDITABLE tinyint(1) not null comment '可编辑状态',
	PERM_LEVEL tinyint(1) not null comment '权限等级',
	REMARK varchar(255) null comment '备注'
)
comment '系统角色表';

create table if not exists SYS_DEPT_ROLE_RELA
(
	UUID varchar(32) not null
		primary key,
	DEPT_UUID varchar(32) not null comment '部门主键',
	ROLE_UUID varchar(32) not null comment '角色主键',
	constraint SYS_DEPT_ROLE_FK_01
		foreign key (DEPT_UUID) references SYS_DEPT (UUID),
	constraint SYS_DEPT_ROLE_FK_02
		foreign key (ROLE_UUID) references SYS_ROLE (UUID)
)
comment '系统部门角色关系表';

create table if not exists SYS_ROLE_MENU_RELA
(
	UUID varchar(32) not null comment '主键'
		primary key,
	ROLE_UUID varchar(32) not null comment '角色主键',
	MENU_UUID varchar(32) not null comment '菜单主键',
	constraint SYS_ROLE_MENU_FK_1
		foreign key (ROLE_UUID) references SYS_ROLE (UUID),
	constraint SYS_ROLE_MENU_RELA_SYS_MENU_UUID_fk
		foreign key (MENU_UUID) references SYS_MENU (UUID),
	constraint SYS_ROLE_MENU_FK_2
		foreign key (MENU_UUID) references SYS_MENU (UUID)
)
comment '系统角色菜单关系表';

create table if not exists SYS_USER
(
	UUID varchar(32) not null comment '主键'
		primary key,
	USERNAME varchar(50) not null comment '用户名称',
	PASSWORD varchar(100) not null comment '用户密码',
	ENABLED tinyint(1) default 1 not null comment '可用状态',
	EMAIL varchar(255) null comment '电子邮箱',
	PHONE varchar(255) null comment '手机号码',
	AVATAR varchar(255) null comment '用户头像',
	REMARK varchar(255) null comment '备注'
)
comment '系统用户表';

create table if not exists SYS_USER_DEPT_RELA
(
	UUID varchar(32) not null
		primary key,
	USER_UUID varchar(32) not null,
	DEPT_UUID varchar(32) not null,
	constraint SYS_USER_DEPT_FK_01
		foreign key (USER_UUID) references SYS_USER (UUID),
	constraint SYS_USER_DEPT_FK_02
		foreign key (DEPT_UUID) references SYS_DEPT (UUID)
)
comment '系统用户部门关系表';

