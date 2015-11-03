
public class PhoneNumberEntity {
    private Long id;
    private String indexedValue;
    private Byte[] uuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
