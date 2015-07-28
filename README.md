#Configuração ambiente DesafioHU

A configuração do meu ambiente de desenvolvimento foi a seguinte:

 - JDK7 (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)
 - Apache Maven 3.3.3 (https://maven.apache.org/download.cgi)
 - MongoDB (https://www.mongodb.org/downloads)

***Sobre o MongoDB:*** 

	A base do desafioHU está em anexo na aplicação 
	em /desafioHU/src/main/resources/databaseMongoDB
	
	Os arquivos que estão no db.rar (desafiohu.0 e desafiohu.ns) 
	devem ser colocados em /data/db(caminho default dos databases) 
	que é o local aonde MongoDB irá ler esses arquivos.
	
	Após isso, inicie o serviço do mongodb 
	/MongoDB/Server/3.0/bin/mongod.exe (Ou equivalente dependendo do SO)

***Comandos a serem executados:***  
	
	git clone https://github.com/lbernardomaia/desafioHU
	cd .\desafioHU
	mvn clean install
	mvn spring-boot:run
	
	Para acessar a aplicação deve acessar a url: http://localhost:8034/desafioHU/
	
***Serviços:***  
	
	Os serviços estão disponíveis da seguinte forma:
	
	Busca Disponibilidade
	http://localhost:8034/desafioHU/buscaDisponibilidade
	
		Ex:
			http://localhost:8034/desafioHU/buscaDisponibilidade
			Method: Post
			{"busca":"Am Romerweg" , 
			 "dataInicio": "03/05/2015", 
			 "dataFim": "04/05/2015", 
			 "cidade" : "false", 
			 "hotel" : "true"
			}
		
	Busca AutoComplete
	http://localhost:8034/desafioHU/buscaAutoComplete	
	
		Ex:
			Method: Post
			{"busca":"Araruama"}

***Considerações finais:*** 

	 O arquivo /desafioH/src/main/resources/application.properties 
	 e o /desafioHU/src/test/resources/application.properties, contém informações
	 sobre o local aonde estão configuradas as portas do MongoDB 
	 ou em qual portar o servidor irá levantar a aplicação.
	 
	 Qualquer configuração diferentes dos itens a seguir devem ser alteradas 
	 nos arquivos informados acima:
	 
	 server.port = 8034
 	 server.contextPath=/desafioHU
	 mongodb.host = localhost
	 mongodb.port = 27017
	 mongodb.banco = desafiohu
	
	
	
	
	