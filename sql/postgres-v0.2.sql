-- -------------------------------------------------------------
-- TablePlus 5.1.1(471)
--
-- https://tableplus.com/
--
-- Database: postgres
-- Generation Time: 2023-01-08 13:43:33.7640
-- -------------------------------------------------------------

DROP TABLE IF EXISTS "public"."comment_user";
DROP TABLE IF EXISTS "public"."ingredient_category_ingredient";
DROP TABLE IF EXISTS "public"."recipe_comment";
DROP TABLE IF EXISTS "public"."recipe_category_recipe";
DROP TABLE IF EXISTS "public"."recipe_ingredient";
DROP TABLE IF EXISTS "public"."recipe_list_recipe";
DROP TABLE IF EXISTS "public"."recipe_list_user";
DROP TABLE IF EXISTS "public"."cart_user";

DROP TABLE IF EXISTS "public"."advert";
DROP TABLE IF EXISTS "public"."comment";
DROP TABLE IF EXISTS "public"."ingredient";
DROP TABLE IF EXISTS "public"."ingredient_category";
DROP TABLE IF EXISTS "public"."news";
DROP TABLE IF EXISTS "public"."partner";
DROP TABLE IF EXISTS "public"."recipe";
DROP TABLE IF EXISTS "public"."recipe_category";
DROP TABLE IF EXISTS "public"."recipe_list";
DROP TABLE IF EXISTS "public"."suggestion";
DROP TABLE IF EXISTS "public"."unit";
DROP TABLE IF EXISTS "public"."suggestion_category";
DROP TABLE IF EXISTS "public"."users";
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
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."cart_user" (
    "ingredient_id" int4 NOT NULL,
    "user_id" int4 NOT NULL,
    "quantity" float8 NOT NULL,
    "unit" int4 NOT NULL,
    PRIMARY KEY ("ingredient_id", "user_id")
);
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

-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."comment_user" (
    "comment_id" int4 NOT NULL,
    "user_id" int4 NOT NULL,
    PRIMARY KEY ("comment_id","user_id")
);


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

INSERT INTO "public"."ingredient" VALUES (0, 'dumb', false, NULL);
INSERT INTO "public"."ingredient" ("name", "allergen", "src") VALUES (1, 'dummy', false, NULL), (2);

-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS ingredient_category_id_seq;

-- Table Definition
CREATE TABLE "public"."ingredient_category" (
    "id" int4 NOT NULL DEFAULT nextval('ingredient_category_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."ingredient_category_ingredient" (
    "ingredient_id" int4 NOT NULL,
    "ingredient_category_id" int4 NOT NULL,
    PRIMARY KEY ("ingredient_id","ingredient_category_id")
);


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


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_category_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_category" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_category_id_seq'::regclass),
    "name" varchar(20) NOT NULL,
    PRIMARY KEY ("id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."recipe_category_recipe" (
    "recipe_id" int4 NOT NULL,
    "recipe_category_id" int4 NOT NULL,
    PRIMARY KEY ("recipe_id","recipe_category_id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."recipe_comment" (
    "recipe_id" int4 NOT NULL,
    "comment_id" int4 NOT NULL,
    PRIMARY KEY ("recipe_id","comment_id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."recipe_ingredient" (
    "recipe_id" int4 NOT NULL,
    "ingredient_id" int4 NOT NULL,
    "quantity" int4,
    "unit" int4 NOT NULL,
    PRIMARY KEY ("recipe_id","ingredient_id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS recipe_lists_id_seq;

-- Table Definition
CREATE TABLE "public"."recipe_list" (
    "id" int4 NOT NULL DEFAULT nextval('recipe_lists_id_seq'::regclass),
    "name" varchar(255) NOT NULL,
    PRIMARY KEY ("id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."recipe_list_recipe" (
    "recipe_list_id" int4 NOT NULL,
    "recipe_id" int4 NOT NULL,
    PRIMARY KEY ("recipe_id","recipe_list_id")
);


-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."recipe_list_user" (
    "user_id" int4 NOT NULL,
    "recipe_list_id" int4 NOT NULL,
    PRIMARY KEY ("user_id","recipe_list_id")
);


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

-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS unit_id_seq;

-- Table Definition
CREATE TABLE "public"."unit" (
    "id" int4 NOT NULL DEFAULT nextval('unit_id_seq'::regclass),
    "name" varchar(20),
    PRIMARY KEY ("id")
);

INSERT INTO "public"."unit" ("id", "name") VALUES (0, 'dumb');


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

INSERT INTO "public"."users"("id", "name", "email", "phone", "birthdate", "question", "answer", "is_admin", "password") VALUES (
    0, 'u', 'u', 'u', '2018-01-01 00:00:00', 'u', 'u', false, '$2y$10$NdKkEpb9xK5nwmgyJtj80.99jpZo4y39iW0swZfXhXLNn0oP.RRI2');

ALTER TABLE "public"."advert" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."advert" ADD FOREIGN KEY ("partner_id") REFERENCES "public"."partner"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."cart_user" ADD FOREIGN KEY ("unit") REFERENCES "public"."unit"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."comment_user" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."comment_user" ADD FOREIGN KEY ("comment_id") REFERENCES "public"."comment"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ingredient_category_ingredient" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."ingredient_category_ingredient" ADD FOREIGN KEY ("ingredient_category_id") REFERENCES "public"."ingredient_category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_category_recipe" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_category_recipe" ADD FOREIGN KEY ("recipe_category_id") REFERENCES "public"."recipe_category"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_comment" ADD FOREIGN KEY ("comment_id") REFERENCES "public"."comment"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_comment" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("recipe_id") REFERENCES "public"."recipe"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("unit") REFERENCES "public"."unit"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_ingredient" ADD FOREIGN KEY ("ingredient_id") REFERENCES "public"."ingredient"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_recipe" ADD FOREIGN KEY ("recipe_list_id") REFERENCES "public"."recipe_list"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_user" ADD FOREIGN KEY ("recipe_list_id") REFERENCES "public"."recipe_list"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."recipe_list_user" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE "public"."suggestion" ADD FOREIGN KEY ("category") REFERENCES "public"."suggestion_category"("id") ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE "public"."suggestion" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id") ON DELETE SET NULL ON UPDATE CASCADE;
