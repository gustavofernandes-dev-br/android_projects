package br.dev.gustavofernandes.smarttablayout.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import br.dev.gustavofernandes.smarttablayout.R;
import br.dev.gustavofernandes.smarttablayout.fragmentos.AnimaisFragment;
import br.dev.gustavofernandes.smarttablayout.fragmentos.NumerosFragment;

public class MainActivity extends AppCompatActivity {

    FragmentPagerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setElevation(0);


        adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Animais", AnimaisFragment.class)
                .add("Numeros", NumerosFragment.class)
                .add("Vogais", NumerosFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

        //int position = FragmentPagerItem.getPosition(getArguments());
    }
    public void onPageSelected(int position) {
        //.instantiateItem() from until .destoryItem() is called it will be able to get the Fragment of page.
        Fragment page = adapter.getPage(position);
    }


}