package com.sandeep;

import lombok.extern.slf4j.Slf4j;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class HostProviderVer2 {
    private InetSocketAddress[] connectionUris; //comma separated uris.
    private final String connectString;
    private int index = 0;

    public HostProviderVer2(final String addrs) {
        this.connectString = addrs;
    }

    private InetSocketAddress[] getSAs(final String addrs) {
        List<InetSocketAddress> list = new ArrayList<>(5);
        for (String addr : addrs.split(",")) {
            try {
                list.add(getSocketAddress(addr));
            } catch (URISyntaxException e) {
                System.out.println("Invalid address part of connect String");
            }
        }
        return list.toArray(new InetSocketAddress[list.size()]);

    }

    private InetSocketAddress getSocketAddress(String hostUrl) throws URISyntaxException {
        URI uri = new URI("tcp://" + hostUrl);
        //if ip address is passed then no lookup is performed.
        return new InetSocketAddress(uri.getHost(), uri.getPort());
    }

    public InetSocketAddress[] getConnectionUris() {
        return getSAs(connectString);
    }

    public InetSocketAddress[] refreshGet() {
        List<InetSocketAddress> iass = new ArrayList<>();
        try {
            DNSNameService nameService = new DNSNameService();
            for(String addr: connectString.split(",")){
                URI uri = new URI("tcp://" + addr);
                if (!addr.contains("localhost") &&  Character.digit(addr.charAt(0), 16) == -1) {
                    InetAddress[] addrs = nameService.lookupAllHostAddr(uri.getHost());
                    for(InetAddress a : addrs) {
                        iass.add(new InetSocketAddress(a, uri.getPort()));
                    }
                } else {
                    iass.add(new InetSocketAddress(uri.getHost(), uri.getPort()));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while refreshing the dns");
            e.printStackTrace();
        }
        return iass.toArray(new InetSocketAddress[iass.size()]);
    }


    public static void main(String[] args) throws UnknownHostException, URISyntaxException {
        String uri = "google.com:8080,doesnotExist:8080,10.253.18.171:8081,asd-nautilus-jenkins.isus.emc.com:8080";
        HostProviderVer2 c = new HostProviderVer2(uri);
        for(InetSocketAddress sa: c.refreshGet()) {
            System.out.println(sa);
        }
    }


}
