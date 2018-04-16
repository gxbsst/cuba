package com.vtstar.sct.service;


import com.vtstar.sct.entity.VtApps;

import javax.validation.constraints.Null;
import java.util.List;

public interface VtAppsService {
    String NAME = "sct_VtAppsService";

    List<VtApps> query();

    public boolean checkClientState();

}