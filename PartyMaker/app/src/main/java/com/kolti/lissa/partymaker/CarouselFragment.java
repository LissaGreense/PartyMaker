package com.kolti.lissa.partymaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

/**
 * Created by t450 on 18.11.2017.
 */


public class CarouselFragment extends Fragment {
    //TextView tvSelected;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.carousel_with_products, container, false);
        final CarouselPicker imageCarousel = view.findViewById(R.id.carousel);
        //tvSelected = view.findViewById(R.id.selected_text);

        List<CarouselPicker.PickerItem> imageItems = new ArrayList<>();
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.ic_food_and_drinks));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.ic_food_and_drinks));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.ic_food_and_drinks));
        imageItems.add(new CarouselPicker.DrawableItem(R.drawable.ic_food_and_drinks));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(getContext(), imageItems, 0);
        imageCarousel.setAdapter(imageAdapter);

        imageCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //tvSelected.setText("Selected item in image carousel is  : "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;

}}