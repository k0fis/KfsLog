package kfs.kfslog.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
@Entity
public class KfsLog implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, insertable = false, updatable = false)    
    private Long id;
    
    @Column(nullable = false)
    @KfsTablePos({@Pos(name = "KfsLogList", value = 10)})
    private Timestamp logDate = new Timestamp(new Date().getTime());
    
    @Column(nullable = false, length = 50)
    @KfsTablePos({@Pos(name = "KfsLogList", value = 20)})
    private String logUser;
    
    @Column(length = 1024, nullable = false)
    @KfsTablePos({@Pos(name = "KfsLogList", value = 30)})
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLogDate() {
        return logDate;
    }

    public void setLogDate(Timestamp logDate) {
        this.logDate = logDate;
    }

    public String getLogUser() {
        return logUser;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
