package datenmodelle;
//@author Maurice Hennig
public class Timeslot
{
    private String von;
    private String bis;

    public Timeslot(String von, String bis)
    {
        this.von = von;
        this.bis = bis;
    }

    public String getVon()
    {
        return von;
    }

    public void setVon(String von)
    {
        this.von = von;
    }

    public String getBis()
    {
        return bis;
    }

    public void setBis(String bis)
    {
        this.bis = bis;
    }
}
