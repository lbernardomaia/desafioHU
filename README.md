#Configura��o ambiente DesafioHU

A configura��o do meu ambiente de desenvolvimento foi a seguinte:

 - JDK7 (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk7-downloads-1880260.html)
 - Apache Maven 3.3.3 (https://maven.apache.org/download.cgi)
 - MongoDB (https://www.mongodb.org/downloads)

***Sobre o MongoDB:*** 

	A base do desafioHU est� em anexo na aplica��o em /desafioHU/src/main/resources/databaseMongoDB
	
	Os arquivos que est�o no db.rar (desafiohu.0 e desafiohu.ns) deve ser colocados em /data/db(caminho default dos databases) que � o local aonde MongoDB ir� ler esses arquivos.
	
	Ap�s isso, inicie o servi�o do mongodb /MongoDB/Server/3.0/bin/mongod.exe (Ou equivalente dependendo do SO)

***Comandos a serem executados para rodar a aplica��o:***  
	
	.
	.
	.
	
	Para acessar a aplica��o deve acessar a url: http://localhost:8034/desafioHU/
	
	Os servi�os est�o dispon�veis da seguinte forma:
	
	Busca Disponibilidade
	http://localhost:8034/desafioHU/buscaDisponibilidade
	
		Ex:
			http://localhost:8034/desafioHU/buscaDisponibilidade
			Method: Post
			{"busca":"Am Romerweg" , "dataInicio": "03/05/2015", "dataFim": "04/05/2015", "cidade" : "false", "hotel" : "true"}
		
	Busca AutoComplete
	http://localhost:8034/desafioHU/buscaAutoComplete	
		Ex:
			Method: Post
			{"busca":"Araruama"}

***Considera��es finais:*** 

	 O arquivo /desafioH/src/main/resources/application.properties e o /desafioHU/src/test/resources/application.properties, cont�m informa��es
	 sobre o local aonde est�o configuradas as portas do MongoDB ou em qual portar o servidor ir� levantar a aplica��o.
	 
	 Qualquer configura��o diferentes dos itens a seguir devem ser alteradas nos arquivos informados acima:
	 
	 server.port = 8034
 	 server.contextPath=/desafioHU

	 mongodb.host = localhost
	 mongodb.port = 27017
	 mongodb.banco = desafiohu
	
	
	
	
	