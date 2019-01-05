package edu.qc.seclass.glm;


import java.util.Comparator;

public class ReminderPOJO{

    String date, id, type, subtype, note;
    //must implement noArg constructor or will app will crash
    public ReminderPOJO(){
    }

    public ReminderPOJO(String date, String id, String type, String subtype, String note){
        this.date = date;
        this.id = id;
        this.type = type;
        this.subtype = subtype;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static final Comparator<ReminderPOJO> OrderByType = new Comparator<ReminderPOJO>() {
        @Override
        public int compare(ReminderPOJO o1, ReminderPOJO o2) {
            //ascending order
            return o1.getType().compareTo(o2.getType());
        }
    };


}
