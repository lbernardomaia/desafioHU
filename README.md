Conclui o desafio utilizando a linguagem Java. 

A configuração do meu ambiente de desenvolvimento foi a seguinte:

 - JDK7 (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)
 - Apache Maven 3.3.3 (https://maven.apache.org/download.cgi)
 - MongoDB (https://www.mongodb.org/downloads)
 
 Comandos a serem executados para criar e popular o banco de dados no MongoDB: 
 
  - use desafiohu
  - db.createCollection("hotel")
  - mongoimport --db <db-name> --collection <coll-name> --type json --file seed.json
  
  Comandos a serem executados Git:  
  
	git clone https://github.com/YOUR-USERNAME/Spoon-Knife
	
  Comandos a serem executados Maven:  	
	mvn spring-boot:run
	
	
Para acessar a aplicação deve acessar a url: http://localhost:8034/desafioHU/

Considerações finais:
	 O arquivo \desafioHU\src\main\resources\application.properties e o \desafioHU\src\test\resources\application.properties contém informações
	 sobre o local  aonde estão configuradas as portas do MongoDB ou em qual poder o servidor irá levantar a aplicação.
	 
	 Qualquer configuração diferentes dos itens a seguir devem ser alteradas nos arquivos informados acima:
	 
	 server.port = 8034
 	 server.contextPath=/desafioHU

	 mongodb.host = localhost
	 mongodb.port = 27017
	 mongodb.banco = desafiohu
	
	
	
	
	server.contextPath=/desafioHU