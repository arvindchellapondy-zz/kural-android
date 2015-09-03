package zerobase.us.kural.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zerobase.us.kural.R;
import zerobase.us.kural.model.Adhikaram;
import zerobase.us.kural.model.Adhikarams;

/**
 * Created by arvindchellapondy on 9/1/15.
 */
public class AdhikaramListViewAdapter extends BaseAdapter {

    private Context context;
    private int lastPosition = -1;
    ArrayList<Adhikaram> adhikarams;
    private int[] randomColorArray;

    public AdhikaramListViewAdapter(Context context) {
        this.context = context;
        adhikarams = new ArrayList<>();
        randomColorArray = context.getResources().getIntArray(R.array.dark_colors);
    }

    @Override
    public int getCount() {
        return adhikarams.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdhikaramListViewHolder adhikaramListViewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.adhikaram_list_item, null);
            adhikaramListViewHolder = new AdhikaramListViewHolder(convertView);
            convertView.setTag(adhikaramListViewHolder);
        } else {
            adhikaramListViewHolder = (AdhikaramListViewHolder) convertView.getTag();
        }

        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        convertView.startAnimation(animation);
        lastPosition = position;

        int random = new Random().nextInt((15 - 0) + 1) + 0;
        int color = randomColorArray[random];

        TextDrawable drawable = TextDrawable.builder()
                .buildRect(Integer.toString(adhikarams.get(position).getAdhikaramNumber()), color);

        adhikaramListViewHolder.AdhikaramNumberImageView.setImageDrawable(drawable);
        adhikaramListViewHolder.AdhikaramEnglishTitle.setText(adhikarams.get(position).getEnglishTitle());
        adhikaramListViewHolder.AdhikaramTamilTitle.setText(adhikarams.get(position).getTamilTitle());

        return convertView;
    }

    static class AdhikaramListViewHolder {

        @InjectView(R.id.adhikaram_number_imageview)
        ImageView AdhikaramNumberImageView;

        @InjectView(R.id.adhikaram_english_title)
        TextView AdhikaramEnglishTitle;

        @InjectView(R.id.adhikaram_tamil_title)
        TextView AdhikaramTamilTitle;

        AdhikaramListViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }

    public void addAdhikarams(ArrayList<Adhikaram> adhikaramsArrayList) {
        adhikarams.addAll(adhikaramsArrayList);
        notifyDataSetChanged();
    }
}
