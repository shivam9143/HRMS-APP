package info.androidhive.slidingmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
public class FirstTab extends Fragment {
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
Bundle savedInstanceState) {
TextView tv = new TextView(getActivity());
tv.setText("Latest Tasks");
tv.setGravity(Gravity.CENTER);
tv.setTextColor(Color.WHITE);
tv.setWidth(LayoutParams.MATCH_PARENT);
tv.setHeight(LayoutParams.MATCH_PARENT);
tv.setBackgroundColor(Color.BLUE);
tv.setTextAppearance(getActivity(),
android.R.style.TextAppearance_Large);
return tv;
}
}
