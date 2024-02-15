package datenmodelle;
/**
 * @author Maurice Hennig
 */
public class TimeslotAlt
{
    private String von;
    private String bis;

    public TimeslotAlt(String von, String bis)
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
