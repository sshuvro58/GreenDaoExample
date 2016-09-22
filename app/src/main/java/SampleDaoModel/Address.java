package SampleDaoModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity mapped to table "ADDRESS".
 */
public class Address implements Parcelable {

    private Long personId;
    private String city;

    public Address() {
    }

    public Address(Long personId) {
        this.personId = personId;
    }

    public Address(Long personId, String city) {
        this.personId = personId;
        this.city = city;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.personId);
        dest.writeString(this.city);
    }

    protected Address(Parcel in) {
        this.personId = (Long) in.readValue(Long.class.getClassLoader());
        this.city = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
