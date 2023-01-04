-- -------------------------------------------------------------
-- TablePlus 5.1.1(471)
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2023-01-04 09:16:20.0520
-- -------------------------------------------------------------


DROP TABLE IF EXISTS "public"."advert";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS advert_id_seq;

-- Table Definition
CREATE TABLE "public"."advert" (
    "id" int4 NOT NULL DEFAULT nextval('advert_id_seq'::regclass),
    "description_promotion" text NOT NULL,
    "price" float4 NOT NULL,
    "partner_id" int4 NOT NULL,
    "ingredient_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."cart_user";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS cart_entry_id_seq;

-- Table Definition
CREATE TABLE "public"."cart_user" (
    "id" int4 NOT NULL DEFAULT nextval('cart_entry_id_seq'::regclass),
    "ingredient_id" int4 NOT NULL,
    "user_id" int4 NOT NULL,
    "quantity" int4 NOT NULL,
    "unit" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."comment";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS comment_id_seq;

-- Table Definition
CREATE TABLE "public"."comment" (
    "id" int4 NOT NULL DEFAULT nextval('comment_id_seq'::regclass),
    "rating" float4 NOT NULL,
    "content" text NOT NULL DEFAULT '""'::text,
    "date" timestamp NOT NULL DEFAULT now(),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."comment_user";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS comment_user_id_seq;

-- Table Definition
CREATE TABLE "public"."comment_user" (
    "id" int4 NOT NULL DEFAULT nextval('comment_user_id_seq'::regclass),
    "comment_id" int4 NOT NULL,
    "user_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."ingredient";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS ingredient_id_seq;

-- Table Definition
CREATE TABLE "public"."ingredient" (
    "id" int4 NOT NULL DEFAULT nextval('ingredient_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "allergen" bool NOT NULL DEFAULT false,
    "src" bytea,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."ingredient_category";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS ingredient_category_id_seq;

-- Table Definition
CREATE TABLE "public"."ingredient_category" (
    "id" int4 NOT NULL DEFAULT nextval('ingredient_category_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."ingredient_category_ingredient";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS ingredient_category_ingredient_id_seq;

-- Table Definition
CREATE TABLE "public"."ingredient_category_ingredient" (
    "id" int4 NOT NULL DEFAULT nextval('ingredient_category_ingredient_id_seq'::regclass),
    "ingredient_id" int4 NOT NULL,
    "ingredient_category_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."news";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS news_id_seq;

-- Table Definition
CREATE TABLE "public"."news" (
    "id" int4 NOT NULL DEFAULT nextval('news_id_seq'::regclass),
    "title" varchar(255) NOT NULL,
    "content" text NOT NULL DEFAULT '""'::text,
    "date" timestamp NOT NULL DEFAULT now(),
    "src" bytea,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."partner";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS partner_id_seq;

-- Table Definition
CREATE TABLE "public"."partner" (
    "id" int4 NOT NULL DEFAULT nextval('partner_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "description" text NOT NULL DEFAULT '""'::text,
    "website" varchar(255) NOT NULL DEFAULT '""'::character varying,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "description" text NOT NULL,
    "summary" varchar(500) NOT NULL,
    "src" bytea NOT NULL,
    "servings" int4 NOT NULL,
    "rating" float4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_category";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_category_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_category" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_category_id_seq'::regclass),
    "name" varchar(20) NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_category_recipe";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_category_recipe_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_category_recipe" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_category_recipe_id_seq'::regclass),
    "recipe_id" int4 NOT NULL,
    "recipe_category_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_comment";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS comment_recipe_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_comment" (
    "id" int4 NOT NULL DEFAULT nextval('comment_recipe_id_seq'::regclass),
    "recipe_id" int4 NOT NULL,
    "comment_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_ingredient";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_ingredient_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_ingredient" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_ingredient_id_seq'::regclass),
    "recipe_id" int4 NOT NULL,
    "ingredient_id" int4 NOT NULL,
    "quantity" int4,
    "unit" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_list";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_lists_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_list" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_lists_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_list_recipe";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_list_recipe_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_list_recipe" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_list_recipe_id_seq'::regclass),
    "recipe_list_id" int4 NOT NULL,
    "recipe_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."recipe_list_user";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS untitled_table_222_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_list_user" (
    "id" int4 NOT NULL DEFAULT nextval('untitled_table_222_id_seq'::regclass),
    "user_id" int4 NOT NULL,
    "recipe_list_id" int4 NOT NULL,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."suggestion";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS suggestion_id_seq;

-- Table Definition
CREATE TABLE "public"."suggestion" (
    "id" int4 NOT NULL DEFAULT nextval('suggestion_id_seq'::regclass),
    "title" varchar(255) NOT NULL,
    "description" text NOT NULL,
    "category" int4 NOT NULL,
    "user_id" int4,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."suggestion_category";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS suggestion_category_id_seq;

-- Table Definition
CREATE TABLE "public"."suggestion_category" (
    "id" int4 NOT NULL DEFAULT nextval('suggestion_category_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO suggestion_category VALUES (0, 'test0');
INSERT INTO suggestion_category(name) VALUES ('test1'), ('test2');

DROP TABLE IF EXISTS "public"."unit";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS unit_id_seq;

-- Table Definition
CREATE TABLE "public"."unit" (
    "id" int4 NOT NULL DEFAULT nextval('unit_id_seq'::regclass),
    "name" varchar(20),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."users";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

-- Table Definition
CREATE TABLE "public"."users" (
    "id" int4 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    "email" varchar(255) NOT NULL,
    "phone" varchar(32) NOT NULL,
    "birthdate" timestamp NOT NULL,
    "question" varchar(255) NOT NULL,
    "answer" varchar(255) NOT NULL,
    "is_admin" bool NOT NULL DEFAULT false,
    "password" varchar(60) NOT NULL,
    PRIMARY KEY ("id")
);

INSERT INTO "public"."unit" ("id", "name") VALUES
(1, 'mg'),
(2, 'g'),
(3, 'kg'),
(4, 'mL'),
(5, 'cl'),
(6, 'L');

INSERT INTO "public"."users" ("id", "name", "email", "phone", "birthdate", "question", "answer", "is_admin", "password") VALUES
(0, 'u', 'u', '0613604212', '2022-12-20 22:45:39.647885', 'u?', 'addx', 't', '$2y$10$j1ax6LL0EZwxfP5S9xDfreDCGKo16JG4zLVQzOjsvxNFJMQ1wCvDe');

ALTER TABLE "public"."advert" ADD FOREIGN KEY ("partner_id") REFERENCES "public"."partner"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."advert" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."comment_user" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."comment_user" ADD FOREIGN KEY ("comment_id") REFERENCES "public"."comment"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ingredient_category_ingredient" ADD FOREIGN KEY ("ingredient_category_id") REFERENCES "public"."ingredient_category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ingredient_category_ingredient" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_category_recipe" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_category_recipe" ADD FOREIGN KEY ("recipe_category_id") REFERENCES "public"."recipe_category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_comment" ADD FOREIGN KEY ("comment_id") REFERENCES "public"."comment"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_comment" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("unit") REFERENCES "public"."unit"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_recipe" ADD FOREIGN KEY ("recipe_list_id") REFERENCES "public"."recipe_list"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_user" ADD FOREIGN KEY ("recipe_list_id") REFERENCES "public"."recipe_list"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_user" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."suggestion" ADD FOREIGN KEY ("category") REFERENCES "public"."suggestion_category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."suggestion" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE SET NULL ON UPDATE CASCADE;
