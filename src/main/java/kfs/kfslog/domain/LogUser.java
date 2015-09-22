package kfs.kfslog.domain;

import java.sql.Timestamp;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
public class LogUser {
    
    @KfsTablePos({@Pos(name = "KfsLogList", value = 10)})
    private String userName;
    @KfsTablePos({@Pos(name = "KfsLogList", value = 20)})
    private Integer cnt;
    @KfsTablePos({@Pos(name = "KfsLogList", value = 30)})
    private Timestamp minDate;
    @KfsTablePos({@Pos(name = "KfsLogList", value = 40)})
    private Timestamp maxDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Timestamp getMinDate() {
        return minDate;
    }

    public void setMinDate(Timestamp minDate) {
        this.minDate = minDate;
    }

    public Timestamp getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Timestamp maxDate) {
        this.maxDate = maxDate;
    }

}
