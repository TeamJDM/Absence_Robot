import java.sql.Time;
import java.util.Date;

public class Class {

    private String className;
    private Date date;
    private Time time;
    private StudentList students;
    private Unit unit;

    public Class(String n, Date d, Time t, StudentList s, Unit u){
        className = n;
        date = d;
        time = t;
        students = s;
        unit = u;
    }

    public String getClassName() {
        return className;
    }

    //public void setClassName(String className) {
      //  this.className = className;
    //}

    public Unit getUnit() {
        return unit;
    }

    //public void setUnit(Unit unit) {
      //  this.unit = unit;
    //}

    public Date getDate() {
        return date;
    }

    //public void setDate(Date date) {
      //  this.date = date;
    //}

    public Time getTime() {
        return time;
    }

    //public void setTime(Time time) {
      //  this.time = time;
    //}

}
