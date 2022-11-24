# rest-api-backend

- Install postgresql (pgAdmin)  
Resource - [link](https://youtu.be/MTRtusym-2s)

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
  Resource - [link](https://www.guru99.com/postgresql-create-alter-add-user.html)

- Create a Spring boot application with web, postgresql and jpa dependencies
