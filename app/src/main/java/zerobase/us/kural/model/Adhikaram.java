package zerobase.us.kural.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arvindchellapondy on 9/1/15.
 */
public class Adhikaram implements Parcelable {

    @SerializedName("number")
    private int adhikaramNumber;

    @SerializedName("link_2/_text")
    private String englishTitle;

    @SerializedName("link_1/_text")
    private String tamilTitle;

    public int getAdhikaramNumber() {
        return adhikaramNumber;
    }

    public Adhikaram() {
    }

    public String getEnglishTitle() {
        return englishTitle;
    }

    public String getTamilTitle() {
        return tamilTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.adhikaramNumber);
        dest.writeString(this.englishTitle);
        dest.writeString(this.tamilTitle);
    }

    protected Adhikaram(Parcel in) {
        this.adhikaramNumber = in.readInt();
        this.englishTitle = in.readString();
        this.tamilTitle = in.readString();
    }

    public static final Parcelable.Creator<Adhikaram> CREATOR = new Parcelable.Creator<Adhikaram>() {
        public Adhikaram createFromParcel(Parcel source) {
            return new Adhikaram(source);
        }

        public Adhikaram[] newArray(int size) {
            return new Adhikaram[size];
        }
    };
}
