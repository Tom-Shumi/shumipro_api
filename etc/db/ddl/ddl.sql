-- public.task definition

-- Drop table

-- DROP TABLE public.task;

CREATE TABLE public.task (
	id serial NOT NULL,
	username varchar(50) NOT NULL,
	task varchar(100) NOT NULL,
	priority int2 NOT NULL,
	status bpchar(1) NOT NULL,
	description varchar(255) NULL,
	plan_date date NULL,
	done_date date NULL,
	CONSTRAINT task_pkey PRIMARY KEY (id)
);

-- public.task_comment definition

-- Drop table

-- DROP TABLE public.task_comment;

CREATE TABLE public.task_comment (
	id serial NOT NULL,
	task_id int4 NOT NULL,
	username varchar(50) NOT NULL,
	"comment" varchar(255) NULL,
	create_date timestamp NULL,
	CONSTRAINT task_comment_pkey PRIMARY KEY (id)
);

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	username varchar(50) NOT NULL,
	"password" varchar(100) NOT NULL,
	enabledflg bpchar(1) NOT NULL,
	adminflg bpchar(1) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (username)
);

-- public.daily_task definition

-- Drop table

-- DROP TABLE public.daily_task;

CREATE TABLE public.daily_task (
	id serial NOT NULL,
	username varchar(50) NOT NULL,
	title varchar(100) NOT NULL,
	description varchar(255) NULL,
	priority int2 NOT NULL,
	quota int4 NOT NULL,
	delete_flg bpchar(1) NOT NULL,
	create_date date NOT NULL,
	CONSTRAINT daily_task_pkey PRIMARY KEY (id)
);

-- public.daily_task_history definition

-- Drop table

-- DROP TABLE public.daily_task_history;

CREATE TABLE public.daily_task_history (
	id serial NOT NULL,
	daily_task_id int4 NOT NULL,
	done_date date NOT NULL,
	done_time int4 NOT NULL,
	quota int4 NOT NULL,
	CONSTRAINT daily_task_history_pkey PRIMARY KEY (id)
);