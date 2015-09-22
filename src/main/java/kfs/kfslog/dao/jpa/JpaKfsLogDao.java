package kfs.kfslog.dao.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kfs.kfslog.dao.KfsLogDao;
import kfs.kfslog.domain.KfsLog;
import kfs.kfslog.domain.LogUser;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaKfsLogDao extends BaseDaoJpa<KfsLog, Long> implements KfsLogDao {

    @Override
    protected Class<KfsLog> getDataClass() {
        return KfsLog.class;
    }

    @Override
    protected Long getId(KfsLog data) {
        return data.getId();
    }

    @Override
    public List<KfsLog> load(int limit) {
        return em.createQuery("SELECT l FROM KfsLog l ORDER BY l.logDate DESC")
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<KfsLog> load(String user, int limit) {
        return em.createQuery("SELECT l FROM KfsLog l WHERE l.logUser = :user ORDER BY l.logDate DESC")
                .setParameter("user", user)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<LogUser> loadUsers() {
        return em.createQuery("SELECT l.logUser, count(l.id) cnt, min(l.logDate) minDate, max(l.logDate) maxDate FROM KfsLog l GROUP BY l.logUser ORDER BY l.logUser")
                .getResultList();
    }

    @Override
    public void deleteOld(int deleteDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_YEAR, -1 * deleteDays);
        Date older = c.getTime();
        em.createQuery("DELETE FROM KfsLog l WHERE l.logDate < :older")
                .setParameter("older", older)
                .executeUpdate();
    }

}
