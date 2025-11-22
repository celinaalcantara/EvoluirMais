#!/bin/bash

# =================================================================
# Script 1/3: Cria o Resource Group
# Prefixo: script-infra-rg
# =================================================================

# Variáveis
RG_NAME="rg-evoluirmais-devops"
LOCATION="eastus"

echo "Criando o Resource Group: $RG_NAME na localização $LOCATION..."

az group create --name $RG_NAME --location $LOCATION

echo "Resource Group criado com sucesso."