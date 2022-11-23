# Database schema

## Relations

```plantuml
hide empty members
' skinparam Linetype polyline

class ingredientCategory {
  {field} name => varchar(255)
}

class recipeCategory {
  {field} name => varchar(255)
}

class suggestionCategory {
  {field} name => varchar(255)
}

class mealCategory {
  {field} name => varchar(255)
}

class ingredient {
  {field} name => varchar(255)
  {field} allergen => boolean
}

class recipe {
  {field} name => varchar(255)
  {field} summary => varchar(500)
  {field} description => text
  {field} image => blob
  {field} nbPerson => float
  {field} /rating => float
}


class advert {
  {field} promotion => text
  {field} price => float
}

class suggestion {
  {field} title => varchar(255)
  {field} description => text
}


class comment {
  {field} rating => float
  {field} content => text
  {field} date => date
}

class calendar

class calendar_day {
  {field} date => date
}


class recipeList {
  {field} name => varchar(255)
}

class cart {
}

class cart_entry {
  quantity => float
}

class partner {
  {field} name => varchar(255)
  {field} description => text
  {field} website => varchar(255)
}

class news {
  {field} title => varchar(255)
  {field} content => text
  {field} date => date
  {field} photo => blob
}

class user {
  {field} name => varchar(255)
  {field} email => varchar(255)
  {field} password => varchar(255)
  {field} phone => varchar(32)
  {field} birthdate => date
  {field} question => varchar(255)
  {field} answer => varchar(255)
  {field} isAdmin => boolean
}

class ingredientQuantityForRecipe {
  {field} quantity => int
}

advert "*"                      -d-> "1" ingredient
advert "*"                      -l- "1" partner

calendar "1"                    --> "*" calendar_day
cart "1"                        --> "*" cart_entry
cart_entry "*"                  --> "*" ingredient
comment "1"                     --> "1" user

ingredientQuantityForRecipe "*" -- "*" ingredient

mealCategory "*"                --> "*" recipe

recipe  "1"                     -> "*" comment
recipe "*"                      -- "*" ingredientQuantityForRecipe
recipeList "*"                  -- "*" recipe

user "1"                        --> "1" calendar
user "1"                        --> "1" cart
user "*"                        --l--> "*" ingredient
user "1"                        --> "*" recipeList
user -[hidden]u- news

calendar_day "*"                --> "*" mealCategory
ingredient "*"                  --l--> "*" ingredientCategory
recipe "*"                      --> "*" recipeCategory
suggestion "*"                  --> "1" suggestionCategory
```

## Constraints

- <b>PK</b> = Primary Key
- <b>FK</b> = Foreign Key
- <b>NN</b> = Not Null
- <b>UQ</b> = Unique
- <b>CK</b> = Check
- <b>DF</b> = Default

### Users

|       Table       |   Field    |     Type     | PK  |          FK           | NN  | UQ  | CK  |       DF       |
| :---------------: | :--------: | :----------: | :-: | :-------------------: | :-: | :-: | :-: | :------------: |
|       role        |     id     |    serial    | ✅  |          ❌           | ❌  | ❌  | ❌  | auto-generated |
|       role        |    name    | varchar(255) | ❌  |          ❌           | ✅  | ✅  | ❌  |     `N/A`      |
| calendarEntryType |     id     |    serial    | ✅  |          ❌           | ❌  | ❌  | ❌  | auto-generated |
| calendarEntryType |    name    | varchar(255) | ❌  |          ❌           | ✅  | ✅  | ❌  |     `N/A`      |
|       user        |     id     |    serial    | ✅  |          ❌           | ❌  | ❌  | ❌  | auto-generated |
|       user        |    name    | varchar(255) | ❌  |          ❌           | ✅  | ✅  | ❌  |     `N/A`      |
|       user        |   email    | varchar(255) | ❌  |          ❌           | ✅  | ✅  | ❌  |     `N/A`      |
|       user        |  password  | varchar(255) | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |
|       user        |   phone    | varchar(32)  | ❌  |          ❌           | ❌  | ✅  | ❌  |     `N/A`      |
|       user        |   image    | varchar(255) | ❌  |          ❌           | ❌  | ❌  | ❌  |     `N/A`      |
|       user        |  birthday  |     date     | ❌  |          ❌           | ❌  | ❌  | ❌  |     `N/A`      |
|       user        |  question  | varchar(255) | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |
|       user        |   answer   | varchar(255) | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |
|       user        |    role    | varchar(255) | ❌  |       role(id)        | ✅  | ❌  | ❌  |     `N/A`      |
|   calendarEntry   |     id     |    serial    | ✅  |          ❌           | ❌  | ❌  | ❌  | auto-generated |
|   calendarEntry   |    date    |     date     | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |
|   calendarEntry   |  quantity  |    float     | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |
|   calendarEntry   |    type    |    serial    | ❌  | calendarEntryType(id) | ✅  | ❌  | ❌  |     `N/A`      |
|   calendarEntry   |   owner    |    serial    | ❌  |       user(id)        | ✅  | ❌  | ❌  |     `N/A`      |
|       cart        |   owner    |    serial    | ✅  |       user(id)        | ✅  | ❌  | ❌  |     `N/A`      |
|       cart        | ingredient |    serial    | ✅  |    ingredient(id)     | ❌  | ❌  | ⚠️  |     `N/A`      |
|       cart        |   recipe   |    serial    | ✅  |      recipe(id)       | ❌  | ❌  | ⚠️  |     `N/A`      |
|       cart        |  quantity  |    float     | ❌  |          ❌           | ✅  | ❌  | ❌  |     `N/A`      |

### Recipes

|     Table      |    Field    |     Type     | PK  |         FK         | NN  | UQ  |     CK      |       DF       |
| :------------: | :---------: | :----------: | :-: | :----------------: | :-: | :-: | :---------: | :------------: |
| recipeCategory |     id      |    serial    | ✅  |         ❌         | ❌  | ❌  |     ❌      | auto-generated |
| recipeCategory |    name     | varchar(255) | ❌  |         ❌         | ✅  | ✅  |     ❌      |     `N/A`      |
|      diet      |     id      |    serial    | ✅  |         ❌         | ❌  | ❌  |     ❌      | auto-generated |
|      diet      |    name     | varchar(255) | ❌  |         ❌         | ✅  | ✅  |     ❌      |     `N/A`      |
|     recipe     |     id      |    serial    | ✅  |         ❌         | ❌  | ❌  |     ❌      | auto-generated |
|     recipe     |    name     | varchar(255) | ❌  |         ❌         | ✅  | ✅  |     ❌      |     `N/A`      |
|     recipe     |   summary   | varchar(500) | ❌  |         ❌         | ❌  | ❌  |     ❌      |     `N/A`      |
|     recipe     | description |     text     | ❌  |         ❌         | ✅  | ❌  |     ❌      |     `N/A`      |
|     recipe     |    image    |     blob     | ❌  |         ❌         | ❌  | ❌  |     ❌      |     `N/A`      |
|     recipe     |   rating    |    float     | ❌  |         ❌         | ❌  | ❌  |     ❌      |     `N/A`      |
|     recipe     |  category   |    serial    | ❌  | recipeCategory(id) | ✅  | ❌  |     ❌      |     `N/A`      |
|     recipe     |    diet     |    serial    | ❌  |      diet(id)      | ✅  | ❌  |     ❌      |     `N/A`      |
|   recipeList   |    owner    |    serial    | ✅  |      user(id)      | ❌  | ❌  |     ❌      |     `N/A`      |
|   recipeList   |    name     | varchar(255) | ✅  |         ❌         | ❌  | ❌  |     ❌      |     `N/A`      |
|    comment     |   author    |    serial    | ✅  |      user(id)      | ✅  | ❌  |     ❌      |     `N/A`      |
|    comment     |   recipe    |    serial    | ✅  |     recipe(id)     | ✅  | ❌  |     ❌      |     `N/A`      |
|    comment     |   rating    |    float     | ❌  |         ❌         | ✅  | ❌  | $1 < x < 5$ |     `N/A`      |
|    comment     |   content   |     text     | ❌  |         ❌         | ❌  | ❌  |     ❌      |     `N/A`      |
|    comment     |    date     |     date     | ❌  |         ❌         | ✅  | ❌  |     ❌      |      now       |

### Ingredients

|       Table        |   Field    |     Type     | PK  | FK  | NN  | UQ  | CK  |       DF       |
| :----------------: | :--------: | :----------: | :-: | :-: | :-: | :-: | :-: | :------------: |
| ingredientCategory |     id     |    serial    | ✅  | ❌  | ❌  | ❌  | ❌  | auto-generated |
| ingredientCategory |    name    | varchar(255) | ❌  | ❌  | ✅  | ✅  | ❌  |     `N/A`      |
|     ingredient     |     id     |    serial    | ✅  | ❌  | ❌  | ❌  | ❌  | auto-generated |
|     ingredient     |    name    | varchar(255) | ❌  | ❌  | ✅  | ✅  | ❌  |     `N/A`      |
|     ingredient     | allergenic |   boolean    | ❌  | ❌  | ✅  | ❌  | ❌  |     false      |

### Partners

|    Table     |    Field    |     Type     | PK  |       FK       | NN  | UQ  | CK  |       DF       |
| :----------: | :---------: | :----------: | :-: | :------------: | :-: | :-: | :-: | :------------: |
|   partner    |     id      |    serial    | ✅  |       ❌       | ❌  | ❌  | ❌  | auto-generated |
|   partner    |    name     | varchar(255) | ❌  |       ❌       | ✅  | ✅  | ❌  |     `N/A`      |
|   partner    | description |     text     | ❌  |       ❌       | ❌  | ❌  | ❌  |     `N/A`      |
|   partner    |   website   | varchar(255) | ❌  |       ❌       | ✅  | ✅  | ❌  |     `N/A`      |
| parternOffer |    owner    |    serial    | ✅  |    user(id)    | ❌  | ❌  | ❌  |     `N/A`      |
| parternOffer | ingredient  |    serial    | ✅  | ingredient(id) | ❌  | ❌  | ❌  |     `N/A`      |
| partnerOffer |  promotion  |     text     | ❌  |       ❌       | ✅  | ❌  | ❌  |     `N/A`      |
| parternOffer |    price    |    float     | ❌  |       ❌       | ✅  | ❌  | ❌  |     `N/A`      |
|    advert    |    owner    |    serial    | ✅  |    user(id)    | ❌  | ❌  | ❌  |     `N/A`      |
|    advert    | ingredient  |    serial    | ✅  | ingredient(id) | ❌  | ❌  | ❌  |     `N/A`      |
|    advert    |  promotion  |     text     | ❌  |       ❌       | ✅  | ❌  | ❌  |     `N/A`      |
|    advert    |    price    |    float     | ❌  |       ❌       | ✅  | ❌  | ❌  |     `N/A`      |

### Suggestions

|     Table      |    Field    |     Type     | PK  |         FK         | NN  | UQ  | CK  |       DF       |
| :------------: | :---------: | :----------: | :-: | :----------------: | :-: | :-: | :-: | :------------: |
| suggestionType |     id      |    serial    | ✅  |         ❌         | ❌  | ❌  | ❌  | auto-generated |
| suggestionType |    name     | varchar(255) | ❌  |         ❌         | ✅  | ✅  | ❌  |     `N/A`      |
|   suggestion   |     id      |    serial    | ✅  |         ❌         | ❌  | ❌  | ❌  | auto-generated |
|   suggestion   |    name     | varchar(255) | ❌  |         ❌         | ✅  | ❌  | ❌  |     `N/A`      |
|   suggestion   | description |     text     | ❌  |         ❌         | ✅  | ❌  | ❌  |     `N/A`      |
|   suggestion   |    type     |    serial    | ❌  | suggestionType(id) | ✅  | ❌  | ❌  |     `N/A`      |
|   suggestion   |    owner    |    serial    | ❌  |      user(id)      | ✅  | ❌  | ❌  |     `N/A`      |

### News

| Table |  Field  |     Type     | PK  |    FK    | NN  | UQ  | CK  |       DF       |
| :---: | :-----: | :----------: | :-: | :------: | :-: | :-: | :-: | :------------: |
| news  |   id    |    serial    | ✅  |    ❌    | ❌  | ❌  | ❌  | auto-generated |
| news  |  title  | varchar(255) | ❌  |    ❌    | ✅  | ❌  | ❌  |     `N/A`      |
| news  | content |     text     | ❌  |    ❌    | ✅  | ❌  | ❌  |     `N/A`      |
| news  |  date   |     date     | ❌  |    ❌    | ✅  | ❌  | ❌  |      now       |
| news  | author  |    serial    | ❌  | user(id) | ✅  | ❌  | ❌  |     `N/A`      |
