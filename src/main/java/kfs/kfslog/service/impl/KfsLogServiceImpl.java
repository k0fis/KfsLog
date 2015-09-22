package kfs.kfslog.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import kfs.kfslog.dao.KfsLogDao;
import kfs.kfslog.domain.KfsLog;
import kfs.kfslog.domain.LogUser;
import kfs.kfslog.service.KfsLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class KfsLogServiceImpl implements KfsLogService {

    private final int deleteDays = 360;
    
    @Autowired
    KfsLogDao logDao;

    private final Logger log = Logger.getLogger(getClass());

    @Override
    public void log(String user, String text) {
        log.info(user +" - " + text);
        KfsLog klog = new KfsLog();
        klog.setLogUser(user);
        klog.setText(text);
        klog.setLogDate(new Timestamp(new Date().getTime()));
        logDao.insert(klog);
    }

    @Override
    public List<KfsLog> load(int limit) {
        return logDao.load(limit);
    }

    @Override
    public List<KfsLog> load(String user, int limit) {
        return logDao.load(user, limit);
    }

    @Override
    public List<LogUser> loadUsers() {
        return logDao.loadUsers();
    }

    @Override
    public void deleteOld() {
        logDao.deleteOld(deleteDays);
    }

    public int getDeleteDays() {
        return deleteDays;
    }


}
