package com.link_intersystems.sonnen.client.api;

/**
 * @author René Link {@literal <rene.link@link-intersystems.com>}
 */
public interface Powermeter {
    Number getaL1();

    Number getaL2();

    Number getaL3();

    Number getChannel();

    Number getDeviceid();

    String getDirection();

    Number getError();

    Number getKwhExported();

    Number getKwhImported();

    Number getvL1L2();

    Number getvL1N();

    Number getvL2L3();

    Number getvL2N();

    Number getvL3L1();

    Number getvL3N();

    Number getVaTotal();

    Number getVarTotal();

    Number getwL1();

    Number getwL2();

    Number getwL3();

    Number getwTotal();
}
