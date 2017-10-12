package org.hisp.dhis.scheduling.Parameters;

import org.hisp.dhis.dataelement.DataElementCategoryOptionCombo;
import org.hisp.dhis.organisationunit.OrganisationUnit;
import org.hisp.dhis.scheduling.JobId;
import org.hisp.dhis.scheduling.JobParameters;
import org.hisp.dhis.schema.annotation.Property;
import org.hisp.dhis.validation.ValidationRuleGroup;

import java.util.Date;
import java.util.List;

/**
 * @author Henning Håkonsen
 */
public class MonitoringJobParameters
    implements JobParameters
{
    private static final long serialVersionUID = 5L;

    private JobId jobId;

    @Property
    private Date periodStartDate;

    @Property
    private Date periodEndDate;

    @Property
    private List<OrganisationUnit> organisationUnits;

    @Property
    private List<ValidationRuleGroup> validationRuleGroups;

    @Property
    private DataElementCategoryOptionCombo attributeOptionCombo;

    // Optional parameters
    @Property
    private boolean sendNotifications;

    @Property
    private boolean persistResults;

    public MonitoringJobParameters()
    {
    }

    public MonitoringJobParameters( JobId jobId, Date periodStartDate, Date periodEndDate,
        List<OrganisationUnit> organisationUnits, List<ValidationRuleGroup> validationRuleGroups,
        boolean sendNotifications, boolean persistResults )
    {
        this.jobId = jobId;
        this.periodStartDate = periodStartDate;
        this.periodEndDate = periodEndDate;
        this.organisationUnits = organisationUnits;
        this.validationRuleGroups = validationRuleGroups;
        this.sendNotifications = sendNotifications;
        this.persistResults = persistResults;
    }

    public JobId getJobId()
    {
        return jobId;
    }

    public void setJobId( JobId jobId )
    {
        this.jobId = jobId;
    }

    public List<OrganisationUnit> getOrganisationUnits()
    {
        return organisationUnits;
    }

    public void setOrganisationUnits( List<OrganisationUnit> organisationUnits )
    {
        this.organisationUnits = organisationUnits;
    }

    public List<ValidationRuleGroup> getValidationRuleGroups()
    {
        return validationRuleGroups;
    }

    public void setValidationRuleGroups( List<ValidationRuleGroup> validationRuleGroups )
    {
        this.validationRuleGroups = validationRuleGroups;
    }

    public boolean isSendNotifications()
    {
        return sendNotifications;
    }

    public void setSendNotifications( boolean sendNotifications )
    {
        this.sendNotifications = sendNotifications;
    }

    public boolean isPersistResults()
    {
        return persistResults;
    }

    public void setPersistResults( boolean persistResults )
    {
        this.persistResults = persistResults;
    }

    public Date getPeriodStartDate()
    {
        return periodStartDate;
    }

    public void setPeriodStartDate( Date periodStartDate )
    {
        this.periodStartDate = periodStartDate;
    }

    public Date getPeriodEndDate()
    {
        return periodEndDate;
    }

    public void setPeriodEndDate( Date periodEndDate )
    {
        this.periodEndDate = periodEndDate;
    }

    public DataElementCategoryOptionCombo getAttributeOptionCombo()
    {
        return attributeOptionCombo;
    }

    public void setAttributeOptionCombo( DataElementCategoryOptionCombo attributeOptionCombo )
    {
        this.attributeOptionCombo = attributeOptionCombo;
    }
}
