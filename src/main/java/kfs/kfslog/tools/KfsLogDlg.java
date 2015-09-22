package kfs.kfslog.tools;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import kfs.kfslog.service.KfsLogService;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class KfsLogDlg extends Window {

    private final UI ui;
    private final KfsLogList ll;
    
    public KfsLogDlg(UI ui, KfsI18n i18n, KfsLogService logService){
        this(ui, i18n, logService, "KfsLogList", "KfsLogList");
    }
    public KfsLogDlg(UI ui, KfsI18n i18n, KfsLogService logService,
            String tableName, String i18nPrefix) {
        super(i18n.getMsg(i18nPrefix + ".title"));
        this.ui = ui;
        setModal(true);
        setContent((ll=new KfsLogList(i18n, logService, tableName, i18nPrefix)));
        setSizeFull();
    }
    
    public void show() {
        ll.show(null);
        ui.addWindow(this);
    }
    
}
