INSERT INTO "public"."meal_category"("name") VALUES ('Breakfirst');
INSERT INTO "public"."meal_category"("name") VALUES ('Lunch');
INSERT INTO "public"."meal_category"("name") VALUES ('Dinner');

INSERT INTO "public"."advert"("description_promotion", "price", "partner_id", "ingredient_id") VALUES ('Une super promo !', 4, 1, 1);
INSERT INTO "public"."advert"("description_promotion", "price", "partner_id", "ingredient_id") VALUES ('Une super promo !', 8, 2, 1);
INSERT INTO "public"."advert"("description_promotion", "price", "partner_id", "ingredient_id") VALUES ('Une promo nulle', 26170, 1, 2);

INSERT INTO "public"."cart_user"("ingredient_id", "user_id", "quantity", "unit") VALUES (1, 1, 8, 1);
INSERT INTO "public"."cart_user"("ingredient_id", "user_id", "quantity", "unit") VALUES (2, 1, 26, 2);

INSERT INTO "public"."comment"("rating", "content") VALUES (5, 'Pretty nice project!');
INSERT INTO "public"."comment"("rating", "content") VALUES (1, 'DO>IG');
INSERT INTO "public"."comment"("rating", "content") VALUES (4, 'Deserves a good grade');

INSERT INTO "public"."comment_user"("comment_id", "user_id") VALUES (1, 1);
INSERT INTO "public"."comment_user"("comment_id", "user_id") VALUES (2, 2);
INSERT INTO "public"."comment_user"("comment_id", "user_id") VALUES (3, 1);

INSERT INTO "public"."ingredient"("name", "allergen", "src") VALUES ('Tilleul de Buis les Baronnies', true, NULL);
INSERT INTO "public"."ingredient"("name", "allergen", "src") VALUES ('Courge', false, NULL);
INSERT INTO "public"."ingredient"("name", "allergen", "src") VALUES ('Tomate', false, NULL);

INSERT INTO "public"."ingredient_category"("name") VALUES ('Fruits');
INSERT INTO "public"."ingredient_category"("name") VALUES ('Desserts');
INSERT INTO "public"."ingredient_category"("name") VALUES ('Produits laitiers');

INSERT INTO "public"."ingredient_category_ingredient" VALUES (2, 1);
INSERT INTO "public"."ingredient_category_ingredient" VALUES (3, 1);

INSERT INTO "public"."news"("title", "content", "src") VALUES ('How good would you grade us?', 'Nice content', NULL);
INSERT INTO "public"."news"("title", "content", "src") VALUES ('Come visit Buis les Baronnies', '26170, au plein milieu de la Drôme Provençale !', NULL);
INSERT INTO "public"."news"("title", "content", "src") VALUES ('Fill the DB', 'Boring content', NULL);

INSERT INTO "public"."partner"("name", "description", "website") VALUES ('Office de tourisme de Buis les Baronnies', 'Office de tourisme exceptionnelle', 'www.cherchezvousmeme.com');
INSERT INTO "public"."partner"("name", "description", "website") VALUES ('Magnus', 'Association étudiante de la faculté de sciences de Montpellier, fondée par Alexis FONDARD MARTIN', 'https://magnusasso.carrd.co/');

INSERT INTO "public"."recipe"("name", "description", "summary", "servings") VALUES ('Recette de la flemme', 'Toastez votre pain, mettez de la moutarde, du fromage foncu, des oeufs et du paprika', 'Cest bon', 1);
INSERT INTO "public"."recipe"("name", "description", "summary", "servings") VALUES ('Panini fromage', 'Mettez dans le four et cest cuit', 'Pas ouf mais 1€', 2);
INSERT INTO "public"."recipe"("name", "description", "summary", "servings") VALUES ('Recette cachée', 'Secret...', 'Cherchez plus', 3);

INSERT INTO "public"."recipe_category"("name") VALUES ('cachée');
INSERT INTO "public"."recipe_category"("name") VALUES ('flemme');
INSERT INTO "public"."recipe_category"("name") VALUES ('Panini');

INSERT INTO "public"."recipe_category_recipe" VALUES (3, 1);
INSERT INTO "public"."recipe_category_recipe" VALUES (2, 3);
INSERT INTO "public"."recipe_category_recipe" VALUES (1, 2);

INSERT INTO "public"."recipe_comment" VALUES (1, 1);
INSERT INTO "public"."recipe_comment" VALUES (1, 2);

INSERT INTO "public"."recipe_ingredient" VALUES (1, 1, 1, 2);
INSERT INTO "public"."recipe_ingredient" VALUES (2, 3, 26, 1);
INSERT INTO "public"."recipe_ingredient" VALUES (3, 2, 12, 1);

INSERT INTO "public"."recipe_list"("name", "user_id", "is_fav") VALUES ('Favorite', 1, true);
INSERT INTO "public"."recipe_list"("name", "user_id", "is_fav") VALUES ('Rock', 1, false);

INSERT INTO "public"."recipe_list_recipe" VALUES (1, 1);
INSERT INTO "public"."recipe_list_recipe" VALUES (1, 2);
INSERT INTO "public"."recipe_list_recipe" VALUES (2, 1);
INSERT INTO "public"."recipe_list_recipe" VALUES (2, 3);

INSERT INTO "public"."calendar"("user_id", "recipe_id", "meal_category_id") VALUES (1, 1, 1);
INSERT INTO "public"."calendar"("user_id", "recipe_id", "meal_category_id") VALUES (2, 3, 1);

INSERT INTO "public"."suggestion"("title", "description", "category", "user_id") VALUES ('One more point', 'Please grade us with a bonus point ^^', 1, 1);

INSERT INTO "public"."suggestion_category"("name") VALUES ('Good suggestion');

INSERT INTO "public"."unit"("name") VALUES ('L');
INSERT INTO "public"."unit"("name") VALUES ('kg');
