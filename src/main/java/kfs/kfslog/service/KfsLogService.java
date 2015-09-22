package kfs.kfslog.service;

import java.util.List;
import kfs.kfslog.domain.KfsLog;
import kfs.kfslog.domain.LogUser;


/**
 *
 * @author pavedrim
 */
public interface KfsLogService {

    List<KfsLog> load(int limit);
    List<KfsLog> load(String user, int limit);
    List<LogUser> loadUsers();
    void log(String user, String text);

    void deleteOld();
}
