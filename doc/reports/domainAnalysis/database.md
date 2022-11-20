# Database schema

## Relations

```plantuml
hide empty members

class diet {
  {field} id => serial
  {field} name => varchar(255)
}

class ingredientCategory {
  {field} id => serial
  {field} name => varchar(255)
}

class recipeCategory {
  {field} id => serial
  {field} name => varchar(255)
}

class suggestionCategory {
  {field} id => serial
  {field} name => varchar(255)
}

class mealCategory {
  {field} id => serial
  {field} name => varchar(255)
}

class role {
  {field} id => serial
  {field} name => varchar(255)
}

class ingredient {
  {field} id => serial
  {field} name => varchar(255)
  {field} allergen => boolean
}

class recipe {
  {field} id => serial
  {field} name => varchar(255)
  {field} summary => varchar(500)
  {field} description => text
  {field} image => blob
  {field} rating => float
}


class advert {
  {field} promotion => text
  {field} price => float
}

class suggestion {
  {field} id => serial
  {field} title => varchar(255)
  {field} description => text
}


class comment {
  {field} rating => float
  {field} content => text
  {field} date => date
}


class calendar_entry {
  {field} id => serial
  {field} date => date
  {field} quantity => float
}


class recipeList {
  {field} name => varchar(255)
}


class cart_entry {
  quantity => float
}

class partner {
  {field} id => serial
  {field} name => varchar(255)
  {field} description => text
  {field} website => varchar(255)
}

class partnerOffer {
  {field} promotion => text
  {field} price => float
}

class news {
  {field} id => serial
  {field} title => varchar(255)
  {field} content => text
  {field} date => date
}

class user {
  {field} id => serial
  {field} name => varchar(255)
  {field} email => varchar(255)
  {field} password => varchar(255)
  {field} phone => varchar(32)
  {field} birthdate => date
  {field} question => varchar(255)
  {field} answer => varchar(255)
}

advert "*"        -u-> "1" ingredient: > is about
partnerOffer "*"  -u-> "1" ingredient: is about
cart_entry "*"          --> "*" ingredient: > contains
recipe "*"        --> "*" ingredient: > has
user "*"          --> "*" ingredient: > is allergic to

advert "*"        --> "1" partner: > is from
partnerOffer "*"  --> "1" partner: > is from

calendar_entry "*"      --> "1" recipe: > planned
comment "*"       --> "1" recipe: > is about
cart_entry "*"          --> "*" recipe: > contains
recipeList "*"    --> "*" recipe: > has
suggestion "*"    -u-> "1" recipe: > is about

calendar_entry "1"      -u-> "1" user: > owns
cart_entry "1"          -u-> "1" user: > owns
recipeList "*"    -u-> "1" user: > owns
comment "*"       -u-> "1" user: > is from
news "*"          --> "1" user: > is from

calendar_entry "*"      -u-> "1" mealCategory: > is
ingredient "*"    --> "*" ingredientCategory: > is
recipe "*"        --> "1" recipeCategory: > is
recipe "*"        -l-> "1" diet: > is
suggestion "*"    -d-> "1" suggestionCategory: > is
user "*"          -u-> "1" role: > is
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
