package zerobase.us.kural;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import zerobase.us.kural.model.Adhikarams;
import zerobase.us.kural.model.Kural;
import zerobase.us.kural.model.Kurals;
import zerobase.us.kural.util.AdhikaramListViewAdapter;

/**
 * Created by arvindchellapondy on 9/2/15.
 */
public class AdhikaramFragment extends ThirukkuralFragment {
    private View view;

    @InjectView(R.id.adhikaram_list_view)
    ListView AdhikaramListView;

    public static final String ADHIKARAMS = "ADHIKARAMS";
    public static final String KURALS = "KURALS";

    private AdhikaramListViewAdapter adhikaramListViewAdapter;
    private Adhikarams adhikarams;
    private Kurals kurals;

    Parcelable state;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adhikaram, container, false);
        ButterKnife.inject(this, view);
        adhikarams = new Adhikarams();
        kurals = new Kurals();
        Bundle bundle = getArguments();
        if(bundle!=null){
            adhikarams = bundle.getParcelable(ADHIKARAMS);
            kurals = bundle.getParcelable(KURALS);
        }

        adhikaramListViewAdapter = new AdhikaramListViewAdapter(getThirukuralActivity());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setupView();
        adhikaramListViewAdapter.addAdhikarams(adhikarams.getAdhikarams());


    }

    @Override
    public void onResume() {
        super.onResume();
        if(state!=null){
            AdhikaramListView.onRestoreInstanceState(state);
        }
    }

    public void setupView(){

        AdhikaramListView.setAdapter(adhikaramListViewAdapter);

        AdhikaramListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Kural> selectedKuralList = new ArrayList<Kural>();
                String selectedAdhikaramEnglishTitle = adhikarams.getAdhikarams().get(position).getEnglishTitle();
                String selectedAdhikaramTamilTitle = adhikarams.getAdhikarams().get(position).getTamilTitle();;
                int startingKuralNumber = position * 10;

                for (int i = 0; i < 10; i++) {
                    selectedKuralList.add(kurals.getKuralArrayList().get(startingKuralNumber));
                    startingKuralNumber++;
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(KuralFragment.SELECTED_ADHIKARAM_KURAL_LIST, selectedKuralList);
                bundle.putString(KuralFragment.SELECTED_ADHIKARAM_ENGLISH_TITLE, selectedAdhikaramEnglishTitle);
                bundle.putString(KuralFragment.SELECTED_ADHIKARAM_TAMIL_TITLE, selectedAdhikaramTamilTitle);
                getThirukuralActivity().switchToFragment(new KuralFragment(), true, bundle);

            }
        });

    }

    @Override
    public void onPause() {
        state = AdhikaramListView.onSaveInstanceState();
        super.onPause();
    }
}
