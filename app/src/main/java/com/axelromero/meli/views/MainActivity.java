package com.axelromero.meli.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import com.axelromero.meli.R;
import com.axelromero.meli.presenters.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.MainActivityInteractor {

    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    private MainActivityPresenter presenter;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(getApplication());

        mPager = findViewById(R.id.pager);
        nextButton = findViewById(R.id.nextStep);

        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);

    }

    @Override
    public void enableNextStep() {
        nextButton.setEnabled(true);
    }

    @Override
    public void disableNextStep() {
        nextButton.setEnabled(false);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    return new ValueInputFragment();
                case 1:
                    return new SelectPaymentFragment();
                case 2:
                    return new SelectProviderFragment();
                case 3:
                    return new SelectInstallmentFragment();
                default:
                    return new ValueInputFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
