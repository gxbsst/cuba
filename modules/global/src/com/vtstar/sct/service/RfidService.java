package com.vtstar.sct.service;


import com.vtstar.sct.entity.RfidImpinj;

import java.util.List;

public interface RfidService {
    String NAME = "sct_RfidService";
    public List<RfidImpinj> rfid();
}