package com.vt.cuba.entity;

/**
 * <p>Description：</p>
 * <p>Company：vsdata</p>
 *
 * @author wgd
 * @date 2017/12/1 10:47
 */
public class RFID {
    private String epc;
    private String type;
    private String antPort;
    private String impinjReaderSpecId;

    public RFID() {
        super();
    }

    public RFID(String epc, String type, String antPort, String impinjReaderSpecId) {
        this.epc = epc;
        this.type = type;
        this.antPort = antPort;
        this.impinjReaderSpecId = impinjReaderSpecId;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAntPort() {
        return antPort;
    }

    public void setAntPort(String antPort) {
        this.antPort = antPort;
    }

    public String getImpinjReaderSpecId() {
        return impinjReaderSpecId;
    }

    public void setImpinjReaderSpecId(String impinjReaderSpecId) {
        this.impinjReaderSpecId = impinjReaderSpecId;
    }
}
