package com.example.sdtest.HomeUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sdtest.CustomerUI.CustomerActivity;
import com.example.sdtest.DB.Menu_Control;
import com.example.sdtest.DB.Menu_DaM;
import com.example.sdtest.DB.User_Control;
import com.example.sdtest.DB.User_DAM;
import com.example.sdtest.Menu.Ingredient;
import com.example.sdtest.Menu.MainMenuList;
import com.example.sdtest.Menu.Menu;
import com.example.sdtest.Menu.SideMenuList;
import com.example.sdtest.Menu.SoupMenuList;
import com.example.sdtest.MenuUI.MenuActivity;
import com.example.sdtest.R;
import com.example.sdtest.User.Customer.Customer;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private TextView tv_home_title;
    private TextView tv_home_tip;
    private Button btn_home_menu;
    private Button btn_home_shop;
    private Button btn_home_info;
    private MainMenuList mainList = MainMenuList.getInstance();
    private SideMenuList sideList = SideMenuList.getInstance();
    private SoupMenuList soupList = SoupMenuList.getInstance();
    private ArrayList<Ingredient> ingredients = IngredientList.getInstance().getIngredientList();
    private Customer customer = Customer.getInstance();

    private Menu_DaM menu_daM;
    private Menu_Control menu_control;
    private User_DAM user_dam;
    private User_Control user_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // DB에서 유저 정보 가지고 오기
        {
            user_dam = new User_DAM(getApplicationContext(), "dbuser0.db");
            user_control = new User_Control(user_dam);
            customer.setEatenMenu(user_control.getdb_EatenMenu());
            customer.setOwned(user_control.getdb_own_ingredient());
            user_control.db_close();
        }

        // 글자 스타일 변경
        {
            tv_home_title = findViewById(R.id.tv_home_title);
            String title = tv_home_title.getText().toString();
            SpannableString spannableString = new SpannableString(title);
            String word = "요리";
            int start = title.indexOf(word);
            int end = start + word.length();
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_home_title.setText(spannableString);

            tv_home_tip = findViewById(R.id.tv_home_tip);
            String tip = tv_home_tip.getText().toString();
            spannableString = new SpannableString(tip);
            word = "메뉴 보기";
            start = tip.indexOf(word);
            end = start + word.length();
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            word = "쇼핑하기";
            start = tip.indexOf(word);
            end = start + word.length();
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            word = "내 정보";
            start = tip.indexOf(word);
            end = start + word.length();
            spannableString.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_home_tip.setText(spannableString);
        }

        // 버튼 등록
        {
            btn_home_menu = findViewById(R.id.btn_home_menu);
            btn_home_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            });

            btn_home_shop = findViewById(R.id.btn_home_shop);
            btn_home_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.coupang.com"));
                    startActivity(intent);
                }
            });

            btn_home_info = findViewById(R.id.btn_home_info);
            btn_home_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, CustomerActivity.class);
                    startActivity(intent);
                }
            });
        }

        // DB에서 메뉴 가지고 오기
        {
            menu_daM = new Menu_DaM(getApplicationContext(), "dbmenu0.db");
            menu_control = new Menu_Control(menu_daM);
            mainList.setMenuList(menu_control.getdb_MAIN());
            sideList.setMenuList(menu_control.getdb_SIDE());
            soupList.setMenuList(menu_control.getdb_SOUP());
        }

        // 메뉴 sample
        if (mainList.getMenuList().size() == 0 && sideList.getMenuList().size() == 0 && soupList.getMenuList().size() == 0) {
            Menu main1 = new Menu();
            main1.setName("계란찜");
            Drawable drawable1 = getResources().getDrawable(R.drawable.main1);
            Bitmap bitmap1 = ((BitmapDrawable) drawable1).getBitmap();
            main1.getRecipe().addPhoto(bitmap1);
            main1.getRecipe().addContents("전 재료부터 준비해줍니다");
            main1.getRecipe().addContents("작은 뚝배기에 계란 3개를 깨뜨려 넣은 뒤 거품기로 계란을 풀어줍니다");
            main1.getRecipe().addContents("설탕 1/3 큰 술, 소금 1/3 큰 술을 넣고 한 번 더 거품기로 잘 섞어주기");
            main1.getRecipe().addContents("송송 썰어둔 쪽파를 투척한 뒤 뚜껑을 닫고 4-5분가량 익혀주세요");
            main1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(4), 3));
            main1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(42), 5));
            main1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(44), 5));
            main1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(18), 1));
            main1.getRecipe().setIngredientRatio(customer.getOwned());
            Menu main2 = new Menu();
            main2.setName("수육");
            Drawable drawable2 = getResources().getDrawable(R.drawable.main2);
            Bitmap bitmap2 = ((BitmapDrawable) drawable2).getBitmap();
            main2.getRecipe().addPhoto(bitmap2);
            main2.getRecipe().addContents("삼겹살 앞뒤로 후추를 고루고루 잘 뿌려주세요");
            main2.getRecipe().addContents("전체에 굴소스를 발라줍니다");
            main2.getRecipe().addContents("30-40분 센불 20-30분 중불로 익힌 후 불을 끄고 5분 뜸을 들여 주세요");
            main2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(19), 200));
            main2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(72), 10));
            main2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(22), 10));
            main2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(18), 1));
            main2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(10), 15));
            main2.getRecipe().setIngredientRatio(customer.getOwned());
            Menu side1 = new Menu();
            side1.setName("계란 장조림");
            Drawable drawable3 = getResources().getDrawable(R.drawable.side1);
            Bitmap bitmap3 = ((BitmapDrawable) drawable3).getBitmap();
            side1.getRecipe().addPhoto(bitmap3);
            side1.getRecipe().addContents("껍질을 까서 준비해줍니다");
            side1.getRecipe().addContents("양념과 통마늘을 넣고 바글바글 끓여줍니다");
            side1.getRecipe().addContents("국물이 거의 줄어들고 달걀에 양념이 잘 베어 거뭇해지면 계란외에 건더기는 건져주세요");
            side1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(4), 5));
            side1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(49), 2));
            side1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(1), 5));
            side1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(42), 5));
            side1.getRecipe().setIngredientRatio(customer.getOwned());
            Menu side2 = new Menu();
            side2.setName("계란말이");
            Drawable drawable4 = getResources().getDrawable(R.drawable.side2);
            Bitmap bitmap4 = ((BitmapDrawable) drawable4).getBitmap();
            side2.getRecipe().addPhoto(bitmap4);
            side2.getRecipe().addContents("계란 4개와 물 50cc를 잘섞어주세요");
            side2.getRecipe().addContents("계란물을 1/3 혹은 1/4정도 부어주세요");
            side2.getRecipe().addContents("한쪽으로 밀어두고 또 계란물 붓고 말아주시면 됩니다");
            side2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(4), 4));
            side2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(18), 1));
            side2.getRecipe().addNeededIngredients(getIngredient(ingredients.get(44), 3));
            side2.getRecipe().setIngredientRatio(customer.getOwned());
            Menu side3 = new Menu();
            side3.setName("에그 스크램블");
            Drawable drawable5 = getResources().getDrawable(R.drawable.side3);
            Bitmap bitmap5 = ((BitmapDrawable) drawable5).getBitmap();
            side3.getRecipe().addPhoto(bitmap5);
            side3.getRecipe().addContents("먼저 달걀 3개를 깨서 풀어 줍니다");
            side3.getRecipe().addContents("계란물을 모두다 부어주시고 나무 젓가락으로 천천히 휘저어 몽글몽글 만들어 주세요");
            side3.getRecipe().addNeededIngredients(getIngredient(ingredients.get(4), 3));
            side3.getRecipe().addNeededIngredients(getIngredient(ingredients.get(42), 5));
            side3.getRecipe().addNeededIngredients(getIngredient(ingredients.get(44), 3));
            side3.getRecipe().setIngredientRatio(customer.getOwned());
            Menu soup1 = new Menu();
            soup1.setName("된장찌개");
            Drawable drawable6 = getResources().getDrawable(R.drawable.soup1);
            Bitmap bitmap6 = ((BitmapDrawable) drawable6).getBitmap();
            soup1.getRecipe().addPhoto(bitmap6);
            soup1.getRecipe().addContents("물1.5종이컵에 된장,고추가루,다진마늘,설탕,간장을 한데넣고 한소끔 끓여줍니다");
            soup1.getRecipe().addContents("한소끔 끓은 된장물에 양파,버섯,애호박,청양고추,두부를 넣어 줍니다");
            soup1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(20), 30));
            soup1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(52), 0.25));
            soup1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(21), 30));
            soup1.getRecipe().addNeededIngredients(getIngredient(ingredients.get(15), 7));
            soup1.getRecipe().setIngredientRatio(customer.getOwned());

            ArrayList<Menu> mainSample = new ArrayList<>();
            ArrayList<Menu> sideSample = new ArrayList<>();
            ArrayList<Menu> soupSample = new ArrayList<>();
            mainSample.add(main1);
            menu_control.add_db_mainMenu(main1);
            mainSample.add(main2);
            menu_control.add_db_mainMenu(main2);
            sideSample.add(side1);
            menu_control.add_db_sideMenu(side1);
            sideSample.add(side2);
            menu_control.add_db_sideMenu(side2);
            sideSample.add(side3);
            menu_control.add_db_sideMenu(side3);
            soupSample.add(soup1);
            menu_control.add_db_soupMenu(soup1);
            mainList.setMenuList(mainSample);
            sideList.setMenuList(sideSample);
            soupList.setMenuList(soupSample);
        }
        else {
            for (Menu e : mainList.getMenuList()) {
                if (e.getName().equals("계란찜")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.main1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("수육")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.main2);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                }
            }
            for (Menu e : sideList.getMenuList()) {
                if (e.getName().equals("계란 장조림")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("계란말이")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side2);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("에그 스크램블")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side3);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                }
            }
            for (Menu e : soupList.getMenuList()) {
                if (e.getName().equals("된장찌개")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.soup1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                }
            }

            for (Menu e : customer.getEatenMenu()) {
                if (e.getName().equals("계란 장조림")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("계란말이")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side2);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("에그 스크램블")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side3);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("된장찌개")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.soup1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("계란찜")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.main1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("수육")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.main2);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("계란 장조림")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("계란말이")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side2);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("에그 스크램블")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.side3);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                } else if (e.getName().equals("된장찌개")) {
                    Drawable drawable = getResources().getDrawable(R.drawable.soup1);
                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    e.getRecipe().addPhoto(bitmap);
                }
            }
        }
        menu_control.update_db();
        menu_control.db_close();
    }

    public Ingredient getIngredient(Ingredient ingredient, double amount) {
        return new Ingredient(ingredient.getName(), ingredient.getCarbohydrate(), ingredient.getProtein(), ingredient.getFat(), ingredient.getSodium(), ingredient.getSugars(), amount, ingredient.getUnit());
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}