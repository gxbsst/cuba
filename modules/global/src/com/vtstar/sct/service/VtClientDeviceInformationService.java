package com.vtstar.sct.service;


import com.vtstar.sct.entity.VtClientDeviceInformation;

import java.util.List;

public interface VtClientDeviceInformationService {
    String NAME = "sct_VtClientDeviceInformationService";

    List<VtClientDeviceInformation> query();
}