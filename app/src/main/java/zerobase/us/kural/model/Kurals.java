package zerobase.us.kural.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvindchellapondy on 9/2/15.
 */
public class Kurals implements Parcelable {
    @SerializedName("Kural")
    ArrayList<Kural> kuralArrayList;

    public ArrayList<Kural> getKuralArrayList() {
        return kuralArrayList;
    }

    public void setKuralArrayList(ArrayList<Kural> kuralArrayList) {
        this.kuralArrayList = kuralArrayList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.kuralArrayList);
    }

    public Kurals() {
    }

    protected Kurals(Parcel in) {
        this.kuralArrayList = new ArrayList<Kural>();
        in.readList(this.kuralArrayList, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<Kurals> CREATOR = new Parcelable.Creator<Kurals>() {
        public Kurals createFromParcel(Parcel source) {
            return new Kurals(source);
        }

        public Kurals[] newArray(int size) {
            return new Kurals[size];
        }
    };
}
