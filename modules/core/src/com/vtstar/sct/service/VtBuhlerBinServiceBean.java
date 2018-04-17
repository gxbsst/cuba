package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.VtBuhlerBin;
import com.vtstar.sct.entity.VtBuhlerBinStateEnum;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(VtBuhlerBinService.NAME)
public class VtBuhlerBinServiceBean implements VtBuhlerBinService {
    @Inject
    private DataManager dataManager;

    @Inject
    private Persistence persistence;

    @Inject
    private UtilsService utilsService;

    @Inject
    private OpcService opcService;

    @Override
    public boolean updateBinCoverStatus(String canNo, String status) {

        LoadContext<VtBuhlerBin> loadContext = LoadContext.create(VtBuhlerBin.class)
                .setQuery(LoadContext.createQuery("select e from sct$VtBuhlerBin e where e.canNo=:canNo").setParameter("canNo", canNo))
                .setView("vtBuhlerBin-with-material-view");
        VtBuhlerBin item = dataManager.load(loadContext);
        try (Transaction tx = persistence.createTransaction()) {

            EntityManager entityManager = persistence.getEntityManager();
            if (status.equals("close")) {
                item.setStatus(VtBuhlerBinStateEnum.close);
            } else {
                item.setStatus(VtBuhlerBinStateEnum.open);
            }

            entityManager.merge(item);
            tx.commit();
        }
        return true;
    }

}