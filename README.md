# jsp_login

JPA, spring-security lett használva (csrf_tokent használ)
Van email-es aktiváció is benne de kikommentezve. (Magán Gmailemről küldött ki, jelszó miatt kivettem)
Xampp-is mysql server-en teszteltem, illetve távoli Heroku adatbázis szerverről. (application.properties-ben benne van)
  - db neve "java_db" (ezt létre kell hozni)
  - táblákat automatikusan legenrálja a JPA a kapcsolatokkal együtt (csak nincs semmilyen role az új táblákban ilyenkor)
    - ez aplication.properties-ben állítható spring.jpa.hibernate.ddl-auto=create-ről update-re
