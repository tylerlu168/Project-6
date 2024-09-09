public class Complaint
{
    private String causeOfAction;
    private String plaintiffCitizenship;

    private String defendantCitizenship;

    private String originalStateOfFilling;

    private double amountInControversy;
    private int id;

    private static int nextID = 1;

    public Complaint()
    {
        this.id = nextID;
        nextID++;
    }
    public Complaint(String causeOfAction, String plaintiffCitizenship, String defendantCitizenship, String originalStateOfFilling, double amountInControversy)
    {
        this.causeOfAction = causeOfAction;
        this.plaintiffCitizenship = plaintiffCitizenship;
        this.defendantCitizenship = defendantCitizenship;
        this.originalStateOfFilling = originalStateOfFilling;
        this.amountInControversy = amountInControversy;
        this.id = nextID;
        nextID++;
    }

    public String getCauseOfAction()
    {
        return causeOfAction;
    }
    public String getPlaintiffCitizenship()
    {
        return plaintiffCitizenship;
    }
    public String getDefendantCitizenship()
    {
        return defendantCitizenship;
    }
    public String getOriginalStateOfFilling()
    {
        return originalStateOfFilling;
    }
    public double getAmountInControversy()
    {
        return amountInControversy;
    }
    public int getId()
    {
        return id;
    }
}
