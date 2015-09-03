package zerobase.us.kural.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arvindchellapondy on 9/2/15.
 */
public class Kural implements Parcelable {
    @SerializedName("Number")
    private int kuralNumber;
    @SerializedName("Line1")
    private String kuralLine1;
    @SerializedName("Line2")
    private String kuralLine2;
    @SerializedName("Translation")
    private String Translation;

    public int getKuralNumber() {
        return kuralNumber;
    }

    public String getKuralLine1() {
        return kuralLine1;
    }

    public String getKuralLine2() {
        return kuralLine2;
    }

    public String getTranslation() {
        return Translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.kuralNumber);
        dest.writeString(this.kuralLine1);
        dest.writeString(this.kuralLine2);
        dest.writeString(this.Translation);
    }

    public Kural() {
    }

    protected Kural(Parcel in) {
        this.kuralNumber = in.readInt();
        this.kuralLine1 = in.readString();
        this.kuralLine2 = in.readString();
        this.Translation = in.readString();
    }

    public static final Parcelable.Creator<Kural> CREATOR = new Parcelable.Creator<Kural>() {
        public Kural createFromParcel(Parcel source) {
            return new Kural(source);
        }

        public Kural[] newArray(int size) {
            return new Kural[size];
        }
    };
}
