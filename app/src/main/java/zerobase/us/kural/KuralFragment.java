package zerobase.us.kural;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zerobase.us.kural.model.Kural;
import zerobase.us.kural.util.CirclePageIndicator;
import zerobase.us.kural.util.KuralPagerAdapter;
import zerobase.us.kural.util.ZoomOutPageTransformer;

/**
 * Created by arvindchellapondy on 9/2/15.
 */
public class KuralFragment extends ThirukkuralFragment {

    private View view;

    @InjectView(R.id.kural_view_pager)
    ViewPager kuralViewPager;

    @InjectView(R.id.kural_circle_pager_indicator)
    CirclePageIndicator circlePageIndicator;

    public static final String SELECTED_ADHIKARAM_KURAL_LIST = "SELECTED_ADHIKARAM_KURAL_LIST";
    public static final String SELECTED_ADHIKARAM_ENGLISH_TITLE = "SELECTED_ADHIKARAM_ENGLISH_TITLE";
    public static final String SELECTED_ADHIKARAM_TAMIL_TITLE = "SELECTED_ADHIKARAM_TAMIL_TITLE";

    private KuralPagerAdapter kuralPagerAdapter;
    private ArrayList<Kural> kuralArrayList;
    private String adhikaramEnglishTitle = "";
    private String adhikaramTamilTitle = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kural, container, false);
        ButterKnife.inject(this, view);
        kuralArrayList = new ArrayList<>();
        Bundle bundle = getArguments();

        if (bundle != null) {
            kuralArrayList = bundle.getParcelableArrayList(SELECTED_ADHIKARAM_KURAL_LIST);
            adhikaramEnglishTitle = bundle.getString(SELECTED_ADHIKARAM_ENGLISH_TITLE);
            adhikaramTamilTitle = bundle.getString(SELECTED_ADHIKARAM_TAMIL_TITLE);

        }
        kuralPagerAdapter = new KuralPagerAdapter(getThirukuralActivity(), adhikaramEnglishTitle, adhikaramTamilTitle);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupView();
    }

    public void setupView() {

        kuralViewPager.setAdapter(kuralPagerAdapter);
        kuralViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        circlePageIndicator.setViewPager(kuralViewPager);
        kuralPagerAdapter.addAll(kuralArrayList);
    }
}
