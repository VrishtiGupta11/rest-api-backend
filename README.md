# rest-api-backend

- Install postgresql (pgAdmin)  
Resource - [https://youtu.be/MTRtusym-2s](https://youtu.be/MTRtusym-2s)

- Connect to postgresql in cmd through `psql -U postgres` command in cmd.
  Create a new user 'admin' through following command.
  ```
  CREATE USER admin WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT -1 
    PASSWORD '123456';
  ```
  Resource - [https://www.guru99.com/postgresql-create-alter-add-user.html](https://www.guru99.com/postgresql-create-alter-add-user.html)

- Create a Spring boot application with web, postgresql and jpa dependencies

- Add configuration in application.properties
  ```
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url=jdbc:postgresql://localhost:5432/aliens
  spring.datasource.username=admin
  spring.datasource.password=123456
  spring.jpa.database-platform = org.hibernate.dialect.PostgreSQLDialect
  ```

- Create Model

- Create an interface which extends JpaRepository  
  We can also write customized query over here like
  ```
  // we can use findBy and getBy and then column name all in camel casing
  List<Alien> findByTech(String tech);
	List<Alien> findByAidGreaterThan(int aid);
  
  // To write our own query
  @Query("select a from Alien a where tech=?1 order by a.aname")
	List<Alien> findByTechSorted(String tech);
  ```

- Create Controller

- To run postgresql commands in cmd
  ```
  psql -U username mydatabase
  ```
  
- Join Table
  Resource - [https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection](https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection)

- Deployment
  Resource - [Video](https://www.youtube.com/watch?v=-Ih_ky5HMEA&ab_channel=TakiEddineRahal)  
  [https://github.com/TakiRahal/spring-boot-render](https://github.com/TakiRahal/spring-boot-render)
  
- Resources to explore more on  
  [Flutter + Spring boot](https://www.youtube.com/watch?v=k1VB9YY2Qk8&ab_channel=PXP)
