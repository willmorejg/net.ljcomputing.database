
public class EmailAddressEntity {
    private Long id;
    private String emailAddress;
    private String indexedValue;
    private Byte[] uuid;
    private String domain;
    private String fullEmailAddress;
    private String localPart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getIndexedValue() {
        return indexedValue;
    }

    public void setIndexedValue(String indexedValue) {
        this.indexedValue = indexedValue;
    }

    public Byte[] getUuid() {
        return uuid;
    }

    public void setUuid(Byte[] uuid) {
        this.uuid = uuid;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFullEmailAddress() {
        return fullEmailAddress;
    }

    public void setFullEmailAddress(String fullEmailAddress) {
        this.fullEmailAddress = fullEmailAddress;
    }

    public String getLocalPart() {
        return localPart;
    }

    public void setLocalPart(String localPart) {
        this.localPart = localPart;
    }


}
