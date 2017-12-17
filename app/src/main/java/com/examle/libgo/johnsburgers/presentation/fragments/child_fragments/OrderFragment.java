package com.examle.libgo.johnsburgers.presentation.fragments.child_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.examle.libgo.johnsburgers.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author libgo (03.12.2017)
 */

public class OrderFragment extends MvpAppCompatFragment {

    @BindView(R.id.editText3)
    EditText editText;
    @BindView(R.id.textView2)
    TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        textView.setVisibility(View.INVISIBLE);

        editText.setOnFocusChangeListener((view1, b) -> textView.setVisibility(View.VISIBLE));

        return view;

    }
}
