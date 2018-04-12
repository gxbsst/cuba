package com.vtstar.sct.opc;

import org.openscada.opc.lib.common.ConnectionInformation;

/**
 * @author wgd
 */
public class ConnectionUtil {
//    private static String clsid = "f8582cf2-88fb-11d0-b850-00c0f0104305";
//    //    private static String host = "192.168.3.22";
//    private static String host = "192.168.7.175";
//    private static String passoword = "123456";
//    private static String user = "OPCUser";
//    private static String progId = "Matrikon.OPC.Simulation.1";
    private static String clsid = "75d00bbb-dda5-11d1-b944-9e614d000000";
    //    private static String host = "192.168.3.22";
    private static String host = "192.168.24.9";
    private static String passoword = "a";
    private static String user = "buhler";
    private static String progId = "OPCServer.WinCC.1";

    private static ConnectionInformation connectionInformation = new ConnectionInformation();

    public static ConnectionInformation getConnectionInformation() {
        connectionInformation.setClsid(clsid);
        connectionInformation.setHost(host);
        connectionInformation.setPassword(passoword);
        connectionInformation.setUser(user);
        connectionInformation.setProgId(progId);
        return connectionInformation;
    }
}
