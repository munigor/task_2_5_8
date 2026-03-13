# Installation

> Copy file from `src/main/resources/db.properties.example` to `src/main/resources/db.properties`, and change all setting for you own

```
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/database
db.username=root
db.password=root
```

Solution provided for MySQL. After setting db.properties file, database must by empty (required). Now database migration version control implemented by FlyWayDB plugin. For quick start db migration create: 2 roles:```ADMIN and USER```
and 2 user:
```
admin@admin admin - with ADMIN,USER roles
user@user user - with USER role
```