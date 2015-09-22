package kfs.kfslog.dao;

import java.util.List;
import kfs.kfslog.domain.KfsLog;
import kfs.kfslog.domain.LogUser;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface KfsLogDao extends BaseDao<KfsLog, Long>{

    public List<KfsLog> load(int limit);

    public List<KfsLog> load(String user, int limit);

    public List<LogUser> loadUsers();

    public void deleteOld(int deleteDays);


}
