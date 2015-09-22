package kfs.kfslog.tools;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import kfs.kfslog.domain.KfsLog;
import kfs.kfslog.domain.LogUser;
import kfs.kfslog.service.KfsLogService;
import kfs.kfsvaalib.kfsTable.KfsTable;
import kfs.kfsvaalib.kfsTable.KfsTable.KfsSelect;
import kfs.kfsvaalib.listener.KfsButtonClickListener;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class KfsLogList extends VerticalLayout implements KfsSelect<LogUser>{

    private final KfsLogService logService;
    private final BeanItemContainer<LogUser> contUser;
    private final BeanItemContainer<KfsLog> cont;
    private int limit = 1500;
    private String selectedUser = null;

    public KfsLogList(KfsI18n i18n, KfsLogService logService) {
        this(i18n, logService, "KfsLogList", "KfsLogList");
    }

    public KfsLogList(KfsI18n i18n, KfsLogService logService,
            String tableName, String i18nPrefix) {
        this.logService = logService;
        this.contUser = new BeanItemContainer<>(LogUser.class);
        this.cont = new BeanItemContainer<>(KfsLog.class);
        KfsTable<LogUser> userTable = new KfsTable<>(tableName, i18n, LogUser.class, i18n.getMsg(i18nPrefix + ".users"), contUser, this, null);
        KfsTable<KfsLog> table = new KfsTable<>(tableName, i18n, KfsLog.class, i18n.getMsg(i18nPrefix + ".title"), cont, null, null);
        
        HorizontalLayout hl = new HorizontalLayout(userTable, table);
        hl.setSpacing(true);
        addComponent(hl);
        
        hl = new HorizontalLayout(new Button(
                        i18n.getMsg(i18nPrefix + ".refresh"),
                        new KfsButtonClickListener(this, "refreshClick"))
        );
        hl.setSpacing(true);
        addComponent(hl);
        
        userTable.setWidth("250px");
        userTable.setHeight("500px");
        table.setWidth("500px");
        table.setHeight("500px");
        
        setSpacing(true);
        setMargin(true);
    }

    public void show(KfsRefresh kfsRefresh) {
        refreshClick(null);
    }

    public void refreshClick(Button.ClickEvent event) {
        cont.removeAllItems();
        if (selectedUser == null) {
            cont.addAll(logService.load(limit));
        } else {
            cont.addAll(logService.load(selectedUser, limit));
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void KfsSelect(LogUser data) {
        if (data == null) {
            selectedUser = null;
        } else {
            selectedUser = data.getUserName();
        }
        refreshClick(null);
    }
}
