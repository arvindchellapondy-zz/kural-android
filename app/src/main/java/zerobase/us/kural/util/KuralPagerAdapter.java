package zerobase.us.kural.util;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zerobase.us.kural.R;
import zerobase.us.kural.model.Kural;

/**
 * Created by arvindchellapondy on 9/2/15.
 */
public class KuralPagerAdapter extends PagerAdapter {

    final HashMap<Integer, View> viewList = new HashMap<>();
    private Context context;
    private ArrayList<Kural> kuralArrayList;
    private String adhikaramEnglishTitle;
    private String adhikaramTamilTitle;
    private int[] randomColorArray;


    public KuralPagerAdapter(Context context, String adhikaramEnglishTitle, String adhikaramTamilTitle) {
        this.context = context;
        kuralArrayList = new ArrayList<>();
        this.adhikaramEnglishTitle = adhikaramEnglishTitle;
        this.adhikaramTamilTitle = adhikaramTamilTitle;
        randomColorArray = context.getResources().getIntArray(R.array.dark_colors);
    }

    @Override
    public int getCount() {
        return kuralArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = getView(position);
        KuralViewPagerHolder kuralViewPagerHolder = (KuralViewPagerHolder) convertView.getTag();
        container.addView(convertView);

        int random = new Random().nextInt((15 - 0) + 1) + 0;
        int color = randomColorArray[random];

        String kuralNumber = Integer.toString(kuralArrayList.get(position).getKuralNumber());
        String kuralLine1 = kuralArrayList.get(position).getKuralLine1();
        String kuralLine2 = kuralArrayList.get(position).getKuralLine2();
        String kuralTranslation = kuralArrayList.get(position).getTranslation();


        kuralViewPagerHolder.kuralNumber.setText(kuralNumber);
        kuralViewPagerHolder.kuralAdhikaram.setText(adhikaramTamilTitle + context.getString(R.string.slash_seperator) + adhikaramEnglishTitle);
        kuralViewPagerHolder.kuralLine1.setText(kuralLine1);
        kuralViewPagerHolder.kuralLine2.setText(kuralLine2);
        kuralViewPagerHolder.kuralTranslation.setText(kuralTranslation);

        convertView.setBackgroundColor(color);
        return convertView;
    }

    private View getView(final int position) {
        View convertView = null;
        try {
            convertView = viewList.get(position);
        } catch (java.util.NoSuchElementException e) {

        } finally {
            if (convertView == null) {
                convertView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.kural_view_pager, null);
                KuralViewPagerHolder kuralViewPagerHolder = new KuralViewPagerHolder(convertView);
                kuralViewPagerHolder.kuralViewPagerRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.kural_view_pager_relative_layout);
                kuralViewPagerHolder.kuralNumberLabel = (TextView) convertView.findViewById(R.id.kural_number_label);
                kuralViewPagerHolder.kuralNumber = (TextView) convertView.findViewById(R.id.kural_number);
                kuralViewPagerHolder.kuralAdhikaramLabel = (TextView) convertView.findViewById(R.id.kural_adhikaram_label);
                kuralViewPagerHolder.kuralAdhikaram = (TextView) convertView.findViewById(R.id.kural_adhikaram);
                kuralViewPagerHolder.kuralLabel = (TextView) convertView.findViewById(R.id.kural_label);
                kuralViewPagerHolder.kuralLine1 = (TextView) convertView.findViewById(R.id.kural_line_1);
                kuralViewPagerHolder.kuralLine2 = (TextView) convertView.findViewById(R.id.kural_line_2);
                kuralViewPagerHolder.kuralTranslationLabel = (TextView) convertView.findViewById(R.id.kural_translation_label);
                kuralViewPagerHolder.kuralTranslation = (TextView) convertView.findViewById(R.id.kural_translation);

                convertView.setTag(kuralViewPagerHolder);
                viewList.put(position, convertView);
            }
        }
        return convertView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewList.remove(position);
        container.removeView((View) object);

    }

    static class KuralViewPagerHolder {

        @InjectView(R.id.kural_view_pager_relative_layout)
        RelativeLayout kuralViewPagerRelativeLayout;

        @InjectView(R.id.kural_number_label)
        TextView kuralNumberLabel;

        @InjectView(R.id.kural_number)
        TextView kuralNumber;

        @InjectView(R.id.kural_adhikaram_label)
        TextView kuralAdhikaramLabel;

        @InjectView(R.id.kural_adhikaram)
        TextView kuralAdhikaram;

        @InjectView(R.id.kural_label)
        TextView kuralLabel;

        @InjectView(R.id.kural_line_1)
        TextView kuralLine1;

        @InjectView(R.id.kural_line_2)
        TextView kuralLine2;

        @InjectView(R.id.kural_translation_label)
        TextView kuralTranslationLabel;

        @InjectView(R.id.kural_translation)
        TextView kuralTranslation;

        KuralViewPagerHolder(View view) {
            ButterKnife.inject(this, view);
        }

    }

    public void addAll(Collection<Kural> kuralList) {
        this.kuralArrayList.addAll(kuralList);
        notifyDataSetChanged();
    }


}
