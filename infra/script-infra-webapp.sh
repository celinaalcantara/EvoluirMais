#!/bin/bash

# =================================================================
# Script 3/3: Cria o App Service Plan e o Azure Web App (PaaS)
# Prefixo: script-infra-webapp
# =================================================================

# Variáveis
RG_NAME="rg-evoluirmais-devops"
PLAN_NAME="plan-evoluirmais-java"
APP_SERVICE_NAME="app-evoluirmais-api-rm558090"
LOCATION="eastus"

echo "Criando App Service Plan (Java)..."
az appservice plan create \
    --name $PLAN_NAME \
    --resource-group $RG_NAME \
    --location $LOCATION \
    --is-linux \
    --sku B1

echo "Criando Azure Web App com runtime Java 17..."
az webapp create \
    --resource-group $RG_NAME \
    --plan $PLAN_NAME \
    --name $APP_SERVICE_NAME \
    --runtime "JAVA|17-java17"

echo "Web App criado com sucesso: https://$APP_SERVICE_NAME.azurewebsites.net"