# TP 2 - Architecture microservices

Auteur : OUAZZANI TAYBI Ilyas
Enseignant : Mohamed YOUSSFI

Construire une application de facturation : clients, produits, factures et interface Angular.

## Découpage et communication
customer-service gère les clients (8082), inventory-service les produits (8083), billing-service les factures (8084). Chaque service possède sa base H2. Billing appelle les autres services par des interfaces OpenFeign. Le détail facture enrichit les lignes et retourne le total calculé.

## Discovery et routage
Eureka (8761) centralise les instances enregistrées. La Gateway (8080) expose /customers, /products et /bills. Le profil par défaut utilise les URI lb://customer-service, lb://inventory-service et lb://billing-service. Le profil static utilise directement localhost:8082, 8083 et 8084.

## Configuration centralisée
Config Server (8888) utilise le profil native et des fichiers versionnables dans son classpath. Les services importent optional:configserver:http://localhost:8888. La configuration commune fournit notamment l’adresse du serveur Eureka. Le profil native remplace ici le stockage Git distant, sans modifier le principe de centralisation.

## Facturation
À la création, le service vérifie le client, refuse les lignes vides et les quantités non positives, puis récupère le prix actuel de chaque produit. Les prix sont mémorisés dans la facture pour conserver le tarif appliqué. Les montants du TP utilisent double ; BigDecimal serait préférable en production.

## Client Angular
Le client autonome propose des onglets de consultation, la création de clients et produits, la création d’une facture et sa consultation détaillée. Le proxy Angular transmet les requêtes vers la Gateway. Les erreurs HTTP sont affichées à l’utilisateur.

## Exécution
```powershell
# Dans chaque service : mvn clean package
# Ordre de démarrage : discovery, config, customer-service,
# inventory-service, billing-service, gateway.
./start.ps1
# Interface (autre terminal)
cd angular-client
npm ci
npm start
# http://localhost:4200 et http://localhost:8761
# Profil routage statique, dans gateway :
mvn spring-boot:run -Dspring-boot.run.profiles=static
```

## Validation
Compilation Java et construction du client Angular réussies. Aucun test automatisé de bout en bout de cette activité n’a été exécuté.

Exécution réelle vérifiée : quatre services UP dans Eureka, configuration centralisée, facture enrichie via Gateway/OpenFeign à 300 et création/consultation depuis Angular.
