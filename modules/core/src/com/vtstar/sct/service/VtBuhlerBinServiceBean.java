package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.VtBuhlerBin;
import com.vtstar.sct.entity.VtBuhlerBinStateEnum;
import com.vtstar.sct.entity.VtOPCSignal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    //Buhler系统下料以后通过OPC更新料仓数量
    @Override
    public String opcUpdateBuhlerBinQty(String topic, String value) {
        String itemId = topic.split("/")[1];
        Transaction ex = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            // 更新布勒仓
            VtBuhlerBin buhlerBin = utilsService.queryBuhlerBin(itemId);
            if (buhlerBin != null) {
                if (itemId.contains("Binlm")) {
                    buhlerBin.setCanName(value);
                } else if (itemId.contains("BinWeight")) {
                    System.out.println("whq --- " + value);
                    DecimalFormat df = new DecimalFormat("######0.000");
                    double valueD = Double.parseDouble(value);
                    buhlerBin.setActualRemainingQuantity(new BigDecimal(df.format(valueD)));
                }
                em.merge(buhlerBin);
            }
            ex.commit();
        } finally {
            ex.end();
        }
        updateOpcSignal(itemId, value);
        return "Success";
    }

    private String updateOpcSignal(String itemId, String value) {
        Transaction ex = persistence.createTransaction();
        try {
            EntityManager em = persistence.getEntityManager();
            // 更新布勒仓
            LoadContext<VtOPCSignal> loadContext = LoadContext.create(VtOPCSignal.class)
                    .setQuery(LoadContext
                            .createQuery("select e from sct$VtOPCSignal e where e.itemId=:itemId").setParameter("itemId", itemId));
            VtOPCSignal item = dataManager.load(loadContext);

            if (item != null) {
                item.setValue(value);
                em.merge(item);
            }
            ex.commit();
        } finally {
            ex.end();
        }
        return "Success";
    }

}