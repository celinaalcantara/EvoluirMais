#!/bin/bash

# =================================================================
# Script 2/3: Cria o Azure Database for PostgreSQL (Flexible Server)
# Prefixo: script-infra-db
# =================================================================

# Variáveis
RG_NAME="rg-evoluirmais-devops"
DB_SERVER_NAME="evoluirmais-db-rm558090"
DB_NAME="evoluirmaisdb"
DB_ADMIN_USER="${AZURE_DB_ADMIN_USER}"
DB_ADMIN_PASSWORD="${AZURE_DB_ADMIN_PASSWORD}"
SKU="Standard_B1ms"
VERSION="13"

if [ -z "$DB_ADMIN_PASSWORD" ]; then
  echo "Erro: Variáveis de ambiente de segurança não configuradas na Pipeline."
  exit 1
fi

echo "Criando o Servidor PostgreSQL Flexível..."

az postgres flexible-server create \
    --resource-group $RG_NAME \
    --name $DB_SERVER_NAME \
    --location "eastus" \
    --admin-user $DB_ADMIN_USER \
    --admin-password $DB_ADMIN_PASSWORD \
    --sku-name $SKU \
    --version $VERSION \
    --storage-size 32 \
    --tier Burstable \
    --database-name $DB_NAME \
    --output none

echo "Configurando regra de firewall para permitir conexões Azure..."
az postgres flexible-server firewall-rule create \
    --resource-group $RG_NAME \
    --name $DB_SERVER_NAME \
    --rule-name AllowAllAzure \
    --start-ip-address 0.0.0.0 \
    --end-ip-address 0.0.0.0 \
    --output none

echo "PostgreSQL Server e Banco de Dados criados com sucesso."