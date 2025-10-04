<h3> Подключение двух и более источников данных </h3>

Description: подключение двух и более источников данных <br>
в современных проектах на основе Spring Boot 3.5.6 <br>
Вы можете использовать встроенную базу данных с данными, <br>
или с помощью настроечных бинов Liquebase залить свои данные 

Tech stack: Windows 11, Spring Boot 3.5.6, Maven, Java  21, <br>
Spring Data JPA, Liquebase Migration, H2 Database

Result:<br>
```
Данные первой базы данных
Hibernate: select pe1_0.id,pe1_0.age,pe1_0.name from public.primary_table pe1_0
[PrimaryEntity(id=1, name=Primary User 1, age=25), 
PrimaryEntity(id=2, name=Primary User 2, age=30), 
PrimaryEntity(id=3, name=Primary User 3, age=35)]

Данные второй базы данных
Hibernate: select se1_0.id,se1_0.age,se1_0.name from public.secondary_table se1_0
[SecondaryEntity(id=1, name=Secondary User 1, age=22), 
SecondaryEntity(id=2, name=Secondary User 2, age=28), 
SecondaryEntity(id=3, name=Secondary User 3, age=33)]
```