package src.objects;

import java.util.Date;

public class PeriodOfStudy {

    private String loginID;
    private String label;
    private Date startDate;
    private Date endDate;
    private String levelOfStudy;
    private float weightedMean;

    /**
     * Builds a PeriodOfStudy object, holding POS information
     * @param loginID String containing user's code
     * @param label String, period of study label
     * @param startDate Date starting date of POS
     * @param endDate Date ending date of POS
     * @param levelOfStudy String level of study of degree
     * @param weightedMean float representing the weighted mean in the period of study.
     */
    public PeriodOfStudy(String loginID, String label, Date startDate,
                         Date endDate, String levelOfStudy, float weightedMean) {
        this.loginID = loginID;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
        this.levelOfStudy = levelOfStudy;
        this.weightedMean = weightedMean;
    }

    public String getLoginID() {
        return loginID;
    }

    public String getLabel() {
        return label;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getWeightedMean() { return weightedMean; }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }
}
