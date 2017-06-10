### SlackerNews.  

#### Inleiding.

Dit project is gemaakt als voorbeeld van een JMS applicatie,
waarbij verschillende design patterns en methodieken worden gebruikt.
Het is afgeleid van apps zoals HackerNews, Reddit en Flipboard,
waarbij content verzameld wordt uit verschillende bronnen om op één plek te kunnen worden bekeken.

#### Inhoudsopgave

- Inleiding
- Deployment
- Projectoverzicht
    - Het nieuwsaggregaat
    - Het advertentieplatform, (aka _het avontuur van Scather-Gather en de Chained Gateways_)
    - De REST API
- Extra bronnen
    - Monitoring met JMX
    - Unit-testen van JMS.

#### Deployment.

Om het project te kunnen draaien heb je minstens Payara en ActiveMQ nodig.
Voor installatie van de twee servers, zie [Applicatieservers]().

Voor de simpele manier, zie [deployment met Payara]().
Deployen kan ook op de uitgebreide hardcore _omg-waarom-doe-je-dit_ manier met [Maven](), [Docker]() of je [IDE]().

#### Applicatie.

Deployment van de applicatie kan op drie manieren:

*Payara*

Veruit de simpelste manier is om gewoon in te loggen op je admin panel in Payara (localhost:4848, of 192.168.99.100:4848),
en handmatig een .war bestand te uploaden via _List Deployed Applications_ > _Deploy_:

    plaatje

*Maven*

Via de Maven Cargo Plugin kan de applicatie via Maven automatisch gedeployed worden naar een Payara server.
De plugin is in dit project al geconfigureerd- controleer het ip-adres en de standaard logingegevens.  
Als dit klopt kun je deployen met

    mvn clean package install -X

*Docker*

Met Docker kunnen individuele instances van de applicatie opgestart worden. De configuratie hiervoor is al gedaan: de Dockerfile.
Om met docker de applicatie te starten doe je het volgende vanuit de Docker toolbox:

    docker build -t achan/slackernews .
    docker run -p 8080:8080 -p 4848:4848 achan/slackernews


*Netbeans/IntelliJ*

Als je zelf dingen wil aan passen en kijken of het werkt is het aan te raden om je IDE in te stellen zodat je niet steeds handmatig Maven hoeft te runnen.
Extra voordeel: Eenmaal geconfigureerd kun je makkelijk ook andere projecten en applicaties deployen met een druk op de knop(F5 / Shift+F10).

    netbeans.png / intellij.png


#### Applicatieservers.

##### Lokale installatie
De applicatie servers kunnen geinstalleerd worden in een Docker container of lokaal.  
Bij lokale installaties is het aan te raden om gewoon de installers te volgen voor ActiveMQ en Payara.  

##### Installatie via Docker.
Om je machine makkelijker te kunnen onderhouden en te beheren is Docker aan te raden;  
Docker is een containerizatieplatform - zie het als een VM voor je programmas, maar dan zonder al de extra overhead.
Je kan makkelijk alles verwijderen/opnieuw installeren zonder bestanden achter laten op je systeem. 
Als je niet vaak met Docker hebt gewerkt kan dit voor wat problemen zorgen aan het begin van je project.
  
ActiveMQ kan simpel weg geinstalleerd worden zoals in de documentatie of met het volgende commando:

    # Get the image.
    docker pull webcenter/activemq

    # Run container, make all ports on the container accessible on random ports.
    docker run --name='activemq' -it --rm -P \
	webcenter/activemq:latest

Hetzelfde geldt voor Payara:  

	# Get the image.
    docker pull payara/server-full

    # Run container, exposing the admin and user ports from the container to the host.
    docker run -p 4848:4848 -p 8080:8080 payara/server-full



### Het project.

Het project ziet er als volgt uit. Het lijkt ingewikkeld, maar als je nog niet eerder met JMS hebt gewerkt - dat is het ook! :')

    plaatje

