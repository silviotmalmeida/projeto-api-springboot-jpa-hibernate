#!/bin/bash

echo "Definindo permissoes da pasta de código-fonte..."
docker container exec api-springboot-jpa-hibernate chmod 777 -R /app
sleep 1

echo "Criando a build do projeto..."
docker container exec -it api-springboot-jpa-hibernate bash -c "cd /app; ./mvnw package;"
sleep 1

echo "Definindo permissoes da pasta de código-fonte..."
docker container exec api-springboot-jpa-hibernate chmod 777 -R /app
sleep 1

echo "Iniciando o app..."
docker container exec -it api-springboot-jpa-hibernate bash -c "cd /app; java -jar target/app-0.0.1-SNAPSHOT.jar;"
