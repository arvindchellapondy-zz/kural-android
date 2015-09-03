package zerobase.us.kural.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvindchellapondy on 9/1/15.
 */
public class Adhikarams implements Parcelable {
    @SerializedName("adhikaram")
    ArrayList<Adhikaram> adhikarams;

    public Adhikarams() {
    }

    public ArrayList<Adhikaram> getAdhikarams() {
        return adhikarams;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.adhikarams);
    }

    protected Adhikarams(Parcel in) {
        this.adhikarams = new ArrayList<Adhikaram>();
        in.readList(this.adhikarams, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Adhikarams> CREATOR = new Parcelable.Creator<Adhikarams>() {
        public Adhikarams createFromParcel(Parcel source) {
            return new Adhikarams(source);
        }

        public Adhikarams[] newArray(int size) {
            return new Adhikarams[size];
        }
    };
}
