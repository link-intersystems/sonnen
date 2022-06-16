This repository contains libraries that can be used with the sonnen.de photovoltaics system.  

# Disclaimer

Even though I work hard to deliver high quality, the use of the software provided here is
at your own risk.

| :exclamation:  Disclaimer |
|---------------------------|
we make no warranty that:

- The software will meet your requirements.
- The software will be uninterrupted, timely, secure or error-free.
- The results from the use of the software will be effective, accurate or reliable.
- The quality of the software will meet your expectations.

The links to software and the related documentation made available on this website are subject to the following conditions:

- The software could include technical or other mistakes, inaccuracies or typographical errors.
- At any time without prior notice, we may make changes to the links pointing to software or documentation made available on this website.
- The software may be out of date, and we make no commitment to update such materials.
- We assume no responsibility for errors or omissions in the software or documentation available from its website.
- In no event shall we be liable to you or any third parties for any special, punitive, incidental, indirect or consequential damages of any kind, or any damages whatsoever, including, without limitation, those resulting from loss of use, lost data or profits, or any liability, arising out of or in connection with the use of this software.

When you download and/or use this software, either directly or as a transitive dependency, you agree to the disclaimer above.

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