package com.example.sdtest.CustomerUI;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.sdtest.Nutrient.DietManagement;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

public class DietFragment extends Fragment {
    private LinearLayout ll_diet_kcal;
    private LinearLayout ll_diet_carb;
    private LinearLayout ll_diet_prot;
    private LinearLayout ll_diet_fat;
    private LinearLayout ll_diet_sodi;
    private LinearLayout ll_diet_sug;
    private TextView tv_diet_contents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_diet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DietManagement dm = Customer.getInstance().getDietManagement();

        ll_diet_kcal = view.findViewById(R.id.ll_diet_kcal);
        ll_diet_carb = view.findViewById(R.id.ll_diet_carb);
        ll_diet_prot = view.findViewById(R.id.ll_diet_prot);
        ll_diet_fat = view.findViewById(R.id.ll_diet_fat);
        ll_diet_sodi = view.findViewById(R.id.ll_diet_sodi);
        ll_diet_sug = view.findViewById(R.id.ll_diet_sug);

        LinearLayout.LayoutParams ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * (dm.getTotalCarbohydrate() * 4 + dm.getTotalProtein() * 4 + dm.getTotalFat() * 9), 2300)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_kcal.setLayoutParams(ll_p);

        ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * dm.getTotalCarbohydrate(), 130)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_carb.setLayoutParams(ll_p);

        ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * dm.getTotalProtein(), 65)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_prot.setLayoutParams(ll_p);

        ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * dm.getTotalFat(), 65)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_fat.setLayoutParams(ll_p);

        ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * dm.getTotalSodium(), 5)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_sodi.setLayoutParams(ll_p);

        ll_p = new LinearLayout.LayoutParams(0, changeDp(calculate(200 * dm.getTotalSugars(), 24)));
        ll_p.weight = 1;
        ll_p.gravity = Gravity.BOTTOM;
        ll_p.setMargins(12, 0, 12, 0);
        ll_diet_sug.setLayoutParams(ll_p);


        tv_diet_contents = view.findViewById(R.id.tv_diet_contents);
        tv_diet_contents.setText(Customer.getInstance().getDietManagement().getContents());

        String contents = tv_diet_contents.getText().toString();
        SpannableString spannableString = new SpannableString(contents);
        String word = "칼로리";
        int start = contents.indexOf(word);
        int end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word = "탄수화물";
        start = contents.indexOf(word);
        end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word = "단백질";
        start = contents.indexOf(word);
        end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word = "지방";
        start = contents.indexOf(word);
        end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word = "나트륨";
        start = contents.indexOf(word);
        end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        word = "당류";
        start = contents.indexOf(word);
        end = start + word.length();
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#e49982")), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_diet_contents.setText(spannableString);
    }

    public int changeDp(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public int calculate(double num1, double num2) {
        return (int) (num1 / num2);
    }
}
