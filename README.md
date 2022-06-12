This repository contains libraries that can be used with the sonnen.de photovoltaics system.  


# [sonnen-api-client](client/README.md)

The sonnen-api-client is a simple pure Java based client
to access the JSON API of a sonnen battery like the sonnenBatterie 10 performance.

    DefaultSonnenClientProperties properties = new DefaultSonnenClientProperties();
    properties.setApiUrl("http://<your_host_or_ip>/api/v2");
    properties.setApiToken("<your_api_token>");

    SonnenClient sonnenClient = new JavaSonnenClient(properties);

    System.out.println("Erzeugung [W]: " + status.getProductionW());
    System.out.println("Verbrauch [W]: " + status.getConsumptionW());
    System.out.println("Einspeisung [W]: " + status.getGridFeedInW());
    System.out.println("Notstromreserve: " + status.getBackupBuffer());

will output something like this:

    Erzeugung [W]: 5864
    Verbrauch [W]: 415
    Einspeisung [W]: 5392
    Notstromreserve: 15